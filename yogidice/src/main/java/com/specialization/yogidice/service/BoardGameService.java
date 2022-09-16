package com.specialization.yogidice.service;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.category.CategoryListRepository;
import com.specialization.yogidice.domain.repository.category.MechanismListRepository;
import com.specialization.yogidice.domain.repository.category.TypeListRepository;
import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.response.BoardGameResponse;
import com.specialization.yogidice.dto.response.category.CategoryListResponse;
import com.specialization.yogidice.dto.response.category.MechanismListResponse;
import com.specialization.yogidice.dto.response.category.TypeListResponse;
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
    private final CategoryListRepository categoryListRepository;
    private final TypeListRepository typeListRepository;
    private final MechanismListRepository mechanismListRepository;

    @Transactional
    public Long createBoardGame(BoardGameRequest request) {
        if (boardGameRepository.findByTitleKr(request.getTitleKr()).isPresent()) {
            throw new DuplicateException(String.format("%s는 이미 등록된 보드게임입니다.", request.getTitleKr()));
        }
        BoardGame saveBoardGame = BoardGame.create(
                request.getTitleKr(),
                request.getTitleEng(),
                request.getPublishYear(),
                request.getThumbURL(),
                request.getRating(),
                request.getMinPlayers(),
                request.getMaxPlayers(),
                request.getPlayingTime(),
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
            List<CategoryListResponse> categoryListResponses = categoryListRepository.findByBoardGame(boardGame).stream()
                    .map(CategoryListResponse::response)
                    .collect(Collectors.toList());
            List<TypeListResponse> typeListResponses = typeListRepository.findByBoardGame(boardGame).stream()
                    .map(TypeListResponse::response)
                    .collect(Collectors.toList());
            List<MechanismListResponse> mechanismListResponses = mechanismListRepository.findByBoardGame(boardGame).stream()
                    .map(MechanismListResponse::response)
                    .collect(Collectors.toList());
            responses.add(BoardGameResponse.response(boardGame, categoryListResponses, typeListResponses, mechanismListResponses));
        }
        return responses;
    }

    @Transactional
    public BoardGameResponse readBoardGame(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<CategoryListResponse> categoryListResponses = categoryListRepository.findByBoardGame(boardGame).stream()
                .map(CategoryListResponse::response)
                .collect(Collectors.toList());
        List<TypeListResponse> typeListResponses = typeListRepository.findByBoardGame(boardGame).stream()
                .map(TypeListResponse::response)
                .collect(Collectors.toList());
        List<MechanismListResponse> mechanismListResponses = mechanismListRepository.findByBoardGame(boardGame).stream()
                .map(MechanismListResponse::response)
                .collect(Collectors.toList());
        return BoardGameResponse.response(boardGame, categoryListResponses, typeListResponses, mechanismListResponses);
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
                request.getMinPlayers(),
                request.getMaxPlayers(),
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
