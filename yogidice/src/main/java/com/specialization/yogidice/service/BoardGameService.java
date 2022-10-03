package com.specialization.yogidice.service;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.common.util.DeduplicationUtils;
import com.specialization.yogidice.common.util.MechanismClassifier;
import com.specialization.yogidice.common.util.SortUtil;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.Recommend;
import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.BoardGameRepositorySupport;
import com.specialization.yogidice.domain.repository.RecommendRepository;
import com.specialization.yogidice.domain.repository.UserRepository;
import com.specialization.yogidice.domain.repository.category.CategoryGroupRepository;
import com.specialization.yogidice.domain.repository.category.MechanismGroupRepository;
import com.specialization.yogidice.domain.repository.category.TypeGroupRepository;
import com.specialization.yogidice.dto.request.BoardGamePickRequest;
import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.response.*;
import com.specialization.yogidice.dto.response.category.CategoryGroupResponse;
import com.specialization.yogidice.dto.response.category.MechanismGroupResponse;
import com.specialization.yogidice.dto.response.category.TypeGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.specialization.yogidice.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardGameService {
    private final BoardGameRepository boardGameRepository;
    private final CategoryGroupRepository categoryGroupRepository;
    private final TypeGroupRepository typeGroupRepository;
    private final MechanismGroupRepository mechanismGroupRepository;
    private final BoardGameRepositorySupport boardGameRepositorySupport;
    private final RecommendRepository recommendRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long createBoardGame(BoardGameRequest request) {
        if (boardGameRepository.findByTitleKr(request.getTitleKr()).isPresent()) {
            throw new DuplicateException(String.format("%s 은/는 이미 등록된 보드게임입니다.", request.getTitleKr()));
        }
        BoardGame saveBoardGame = BoardGame.create(
                request.getTitleKr(),
                request.getTitleEng(),
                request.getPublishYear(),
                request.getThumbUrl(),
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
                request.getYoutubeUrl(),
                request.getContents(),
                request.getContentsImgUrl()
        );
        return boardGameRepository.save(saveBoardGame).getId();
    }

    @Transactional
    public List<BoardGameResponse> readBoardGameList(Pageable pageable) {
        List<BoardGame> boardGames = boardGameRepository.findAll(pageable).getContent();
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
        responses = DeduplicationUtils.deduplication(responses, BoardGameResponse::getBggCode);
        return responses;
    }

    @Transactional
    public BoardGameResponse readBoardGame(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<CategoryGroupResponse> categoryGroupResponses = categoryGroupRepository.findByBoardGame(boardGame).stream()
                .map(CategoryGroupResponse::response)
                .collect(Collectors.toList());
        List<TypeGroupResponse> typeGroupResponses = typeGroupRepository.findByBoardGame(boardGame).stream()
                .map(TypeGroupResponse::response)
                .collect(Collectors.toList());
        List<MechanismGroupResponse> mechanismGroupResponses = mechanismGroupRepository.findByBoardGame(boardGame).stream()
                .map(MechanismGroupResponse::response)
                .collect(Collectors.toList());
        return BoardGameResponse.response(boardGame, categoryGroupResponses, typeGroupResponses, mechanismGroupResponses);
    }

    @Transactional
    public void updateBoardGame(Long boardGameId, BoardGameRequest request) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        boardGame.update(
                request.getTitleKr(),
                request.getTitleEng(),
                request.getPublishYear(),
                request.getThumbUrl(),
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
                request.getYoutubeUrl(),
                request.getContents(),
                request.getContentsImgUrl()
        );
        boardGameRepository.save(boardGame);
    }

    @Transactional
    public void deleteBoardGame(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        boardGameRepository.delete(boardGame);
    }

    @Transactional
    public List<RatingGameResponse> readTop10ListByRatingUser() {
        List<BoardGame> games = boardGameRepository.findTop10ByOrderByRatingUserDesc();
        if (games.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<RatingGameResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : games) {
            responses.add(RatingGameResponse.response(boardGame));
        }
        return responses;
    }

    @Transactional
    public List<RatingGameResponse> readAllListByRatingUser(Pageable pageable) {
        List<BoardGame> games = boardGameRepository.findAllByOrderByRatingUserDesc(pageable).getContent();
        if (games.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<RatingGameResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : games) {
            responses.add(RatingGameResponse.response(boardGame));
        }
        return responses;
    }

    @Transactional
    public List<RecentGameResponse> readTop10ListByPublishYear() {
        List<BoardGame> games = boardGameRepository.findTop10ByOrderByPublishYearDesc();
        if (games.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<RecentGameResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : games) {
            responses.add(RecentGameResponse.response(boardGame));
        }
        return responses;
    }

    @Transactional
    public List<RecentGameResponse> readAllListByPublishYear(Pageable pageable) {
        List<BoardGame> games = boardGameRepository.findAllByOrderByPublishYearDesc(pageable).getContent();
        if (games.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<RecentGameResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : games) {
            responses.add(RecentGameResponse.response(boardGame));
        }
        return responses;
    }

    @Transactional
    public List<BoardGameResponse> readExtendedEditionGameList(Long boardGameId) {
        BoardGame boardGame = boardGameRepository.findById(boardGameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<BoardGame> games = boardGameRepository.findByBggCodeAndPublishYearNotLikeAndTitleKrNotLike(boardGame.getBggCode(), boardGame.getPublishYear(), boardGame.getTitleKr());
        if (games.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        List<BoardGameResponse> responses = new ArrayList<>();
        for (BoardGame game : games) {
            List<CategoryGroupResponse> categoryGroupResponses = categoryGroupRepository.findByBoardGame(game).stream()
                    .map(CategoryGroupResponse::response)
                    .collect(Collectors.toList());
            List<TypeGroupResponse> typeGroupResponses = typeGroupRepository.findByBoardGame(game).stream()
                    .map(TypeGroupResponse::response)
                    .collect(Collectors.toList());
            List<MechanismGroupResponse> mechanismGroupResponses = mechanismGroupRepository.findByBoardGame(game).stream()
                    .map(MechanismGroupResponse::response)
                    .collect(Collectors.toList());
            responses.add(BoardGameResponse.response(game, categoryGroupResponses, typeGroupResponses, mechanismGroupResponses));
        }
        return responses;
    }

    @Transactional
    public List<BoardGameResponse> readPickBoardGame(BoardGamePickRequest request) {
        List<BoardGame> boardGames = boardGameRepositorySupport.findBoardGameByPick(request);
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

        //여기서 메카니즘 대분류 필터링
        for (int i = responses.size()-1; i>=0; i--) {
            System.out.println(responses.size());
            if (!MechanismClassifier.checkMechanism(request.getQuestion3(), responses.get(i))) {
                responses.remove(i);
            }
            System.out.println(responses.size());
        }
        return responses;
    }

    public List<BoardGameResponse> searchBoardGame(String title) {
        List<BoardGame> boardGames = boardGameRepository.findAllByTitleKrContains(title);
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

    public List<BoardGameSimpleResponse> detailRecommend(List<Long> boardGameIds) {
        List<BoardGame> boardGames = boardGameRepository.findAllByIdIn(boardGameIds);
        ArrayList<BoardGame> boardGamesSorted = new ArrayList<>();
        for(Long id : boardGameIds){
            for(BoardGame boardGame: boardGames){
                if(boardGame.getId() == id){
                    boardGamesSorted.add(boardGame);
                    break;
                }
            }
        }
        for(BoardGame board : boardGames){
            System.out.println(board.getTitleKr());
        }
        if (boardGames.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
        }
        ArrayList<BoardGameSimpleResponse> responses = new ArrayList<>();
        for (BoardGame boardGame : boardGamesSorted) {
            responses.add(BoardGameSimpleResponse.response(boardGame));
        }

        return responses;
    }

    public List<BoardGameResponse> mainRecommend(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<Recommend> recommends = recommendRepository.findByUser(user);


        List<BoardGameResponse> responses = new ArrayList<>();
        for (Recommend recommend : recommends) {
            BoardGame boardGame = recommend.getBoardGame();
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
}
