package com.specialization.yogidice.service;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.History;
import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.HistoryRepository;
import com.specialization.yogidice.domain.repository.UserRepository;
import com.specialization.yogidice.dto.request.HistoryCreateRequest;
import com.specialization.yogidice.dto.request.HistoryUpdateRequest;
import com.specialization.yogidice.dto.response.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {
    private final UserRepository userRepository;
    private final BoardGameRepository boardGameRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public Long createHistory(User user, HistoryCreateRequest request) {
        BoardGame boardGame = boardGameRepository.findById(request.getGameId())
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        if (historyRepository.findByUserAndBoardGame(user, boardGame).isPresent()) {
            throw new DuplicateException(String.format("%s 은/는 이미 History에 등록된 보드게임입니다.", boardGame.getTitleKr()));
        }
        History saveHistory = History.create(
                request.getRating(),
                request.getReview(),
                user,
                boardGame
        );
        return historyRepository.save(saveHistory).getId();
    }

    @Transactional
    public List<HistoryResponse> readHistoryListOfUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<History> histories = historyRepository.findByUser(user);
        if (histories.isEmpty()) {
            throw new NotFoundException((HISTORY_LIST_NOT_FOUND));
        }
        List<HistoryResponse> responses = new ArrayList<>();
        for (History history : histories) {
            responses.add(HistoryResponse.response(history));
        }
        return responses;
    }

    @Transactional
    public List<HistoryResponse> readHistoryListOfBoardGame(Long gameId) {
        BoardGame boardGame = boardGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<History> histories = historyRepository.findByBoardGame(boardGame);
        if (histories.isEmpty()) {
            throw new NotFoundException((HISTORY_LIST_NOT_FOUND));
        }
        List<HistoryResponse> responses = new ArrayList<>();
        for (History history : histories) {
            responses.add(HistoryResponse.response(history));
        }
        return responses;
    }

    @Transactional
    public void updateHistory(Long historyId, HistoryUpdateRequest request) {
        History history = historyRepository.findById(historyId)
                .orElseThrow(() -> new NotFoundException(HISTORY_NOT_FOUND));
        history.update(
                request.getRating(),
                request.getReview()
        );
        historyRepository.save(history);
    }

    @Transactional
    public void deleteHistory(Long historyId) {
        History history = historyRepository.findById(historyId)
                .orElseThrow(() -> new NotFoundException(HISTORY_NOT_FOUND));
        historyRepository.delete(history);
    }
}
