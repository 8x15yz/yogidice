package com.specialization.yogidice.service;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.category.CategoryGroupRepository;
import com.specialization.yogidice.domain.repository.category.MechanismGroupRepository;
import com.specialization.yogidice.domain.repository.category.TypeGroupRepository;
import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.response.BoardGameResponse;
import com.specialization.yogidice.dto.response.category.CategoryGroupResponse;
import com.specialization.yogidice.dto.response.category.MechanismGroupResponse;
import com.specialization.yogidice.dto.response.category.TypeGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.specialization.yogidice.common.exception.NotFoundException.BOARDGAME_LIST_NOT_FOUND;
import static com.specialization.yogidice.common.exception.NotFoundException.BOARDGAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardGameService {
    private final BoardGameRepository boardGameRepository;
    private final CategoryGroupRepository categoryGroupRepository;
    private final TypeGroupRepository typeGroupRepository;
    private final MechanismGroupRepository mechanismGroupRepository;

    @Transactional
    public Long createBoardGame(BoardGameRequest request) {
        if (boardGameRepository.findByTitleKr(request.getTitleKr()).isPresent()) {
            throw new DuplicateException(String.format("%s 은/는 이미 등록된 보드게임입니다.", request.getTitleKr()));
        }
        BoardGame saveBoardGame = BoardGame.create(
                request.getTitleKr(),
                request.getTitleEng(),
                request.getPublishYear(),
                request.getThumbURL(),
                request.getRatingBl(),
                request.getRatingUser(),
                request.getBbgCode(),
                request.getMinPlayers(),
                request.getMaxPlayers(),
                request.getPlayingTime(),
                request.getMinTime(),
                request.getMaxTime(),
                request.getAge(),
                request.getDifficulty(),
                request.getYoutubeURL(),
                request.getContents(),
                request.getContentsImgURL()
        );
        return boardGameRepository.save(saveBoardGame).getId();
    }

    @Transactional
    public List<BoardGameResponse> readBoardGameList() {
        List<BoardGame> boardGames = boardGameRepository.findAll();
        if (boardGames.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<BoardGameResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : boardGames) {
            List<CategoryGroupResponse> categoryGroupResponses = categoryGroupRepository.findByBoardGame(boardGame).stream()
                    .map(CategoryGroupResponse::response)
                    .collect(Collectors.toList());
            List<TypeGroupResponse> typeGroupResponses = typeGroupRepository.findByBoardGame(boardGame).stream()
                    .map(TypeGroupResponse::response)
                    .collect(Collectors.toList());
            List<MechanismGroupResponse> mechanismGroupResponses = mechanismGroupRepository.findByBoardGame(boardGame).stream()
                    .map(MechanismGroupResponse::response)
                    .collect(Collectors.toList());
            responses.add(BoardGameResponse.response(boardGame, categoryGroupResponses, typeGroupResponses, mechanismGroupResponses));
        }
        return responses;
    }

    @Transactional
    public BoardGameResponse readBoardGame(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<CategoryGroupResponse> categoryGroupRespons = categoryGroupRepository.findByBoardGame(boardGame).stream()
                .map(CategoryGroupResponse::response)
                .collect(Collectors.toList());
        List<TypeGroupResponse> typeGroupRespons = typeGroupRepository.findByBoardGame(boardGame).stream()
                .map(TypeGroupResponse::response)
                .collect(Collectors.toList());
        List<MechanismGroupResponse> mechanismGroupRespons = mechanismGroupRepository.findByBoardGame(boardGame).stream()
                .map(MechanismGroupResponse::response)
                .collect(Collectors.toList());
        return BoardGameResponse.response(boardGame, categoryGroupRespons, typeGroupRespons, mechanismGroupRespons);
    }

    @Transactional
    public void updateBoardGame(Long boardGameId, BoardGameRequest request) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        boardGame.update(
                request.getTitleKr(),
                request.getTitleEng(),
                request.getPublishYear(),
                request.getThumbURL(),
                request.getRatingBl(),
                request.getRatingUser(),
                request.getBbgCode(),
                request.getMinPlayers(),
                request.getMaxPlayers(),
                request.getPlayingTime(),
                request.getMinTime(),
                request.getMaxTime(),
                request.getAge(),
                request.getDifficulty(),
                request.getYoutubeURL(),
                request.getContents(),
                request.getContentsImgURL()
        );
        boardGameRepository.save(boardGame);
    }

    @Transactional
    public void deleteBoardGame(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        boardGameRepository.delete(boardGame);
    }
}
