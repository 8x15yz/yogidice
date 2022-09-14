package com.specialization.yogidice.service;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.response.BoardGameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.BOARDGAME_LIST_NOT_FOUND;
import static com.specialization.yogidice.common.exception.NotFoundException.BOARDGAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardGameService {
    private final BoardGameRepository boardGameRepository;

    @Transactional
    public Long createBoardGame(BoardGameRequest request) {
        if (boardGameRepository.findByTitleKr(request.getTitleKr()).isPresent()) {
            throw new DuplicateException(String.format("%s는 이미 등록된 보드게임입니다.", request.getTitleKr()));
        } else {
            BoardGame saveBoardGame = BoardGame.create(
                    request.getTitleKr(),
                    request.getTitleEng(),
                    request.getPublishYear(),
                    request.getThumbURL(),
                    request.getRating(),
                    request.getPlayers(),
                    request.getPlayingTime(),
                    request.getDifficulty(),
                    request.getYoutubeURL(),
                    request.getContents(),
                    request.getContentsImgURL()
            );
            return boardGameRepository.save(saveBoardGame).getId();
        }
    }

    @Transactional
    public BoardGameResponse readBoardGame(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        return BoardGameResponse.response(boardGame);
    }

    @Transactional
    public List<BoardGameResponse> readBoardGameList() {
        List<BoardGame> boardGames = boardGameRepository.findAll();
        if (boardGames.isEmpty() || boardGames == null) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<BoardGameResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : boardGames) {
            responses.add(BoardGameResponse.response(boardGame));
        }
        return responses;
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
                request.getRating(),
                request.getPlayers(),
                request.getPlayingTime(),
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
