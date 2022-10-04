package com.specialization.yogidice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.common.util.DeduplicationUtils;
import com.specialization.yogidice.common.util.MechanismClassifier;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.Bookmark;
import com.specialization.yogidice.domain.entity.Recommend;
import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.domain.entity.category.MechanismGroup;
import com.specialization.yogidice.domain.repository.*;
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
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private final BookmarkRepository bookmarkRepository;

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
            return new ArrayList<>();
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
        for (int i = responses.size() - 1; i >= 0; i--) {
            System.out.println(responses.size());
            if (!MechanismClassifier.checkMechanism(request.getQuestion3(), responses.get(i))) {
                responses.remove(i);
            }
        }
        if (responses.isEmpty()) {
            throw new NotFoundException(BOARDGAME_LIST_NOT_FOUND);
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
        for (Long id : boardGameIds) {
            for (BoardGame boardGame : boardGames) {
                if (boardGame.getId() == id) {
                    boardGamesSorted.add(boardGame);
                    break;
                }
            }
        }
        for (BoardGame board : boardGames) {
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
        if (recommends.isEmpty()) {
            return new ArrayList<>();
        }

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

    public List<BoardGameSimpleResponse> recommendByBookmark(List<BookmarkResponse> bookmarkResponses) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://172.18.0.1:8000/analyze/recommend/detail/" + bookmarkResponses.get(0).getGameId();
//        String url = "http://localhost:8000/analyze/recommend/detail/"+bookmarkResponses.get(0).getGameId();  //로컬에서

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("gameId", bookmarkResponses.get(0).getGameId());

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        String boardGameList = restTemplate.getForObject(url, String.class);

        HashMap<String, Object> mapping = new ObjectMapper().readValue(boardGameList, HashMap.class);
        HashMap<Integer, Long> boardMap = new HashMap<>();
        for (String key : mapping.keySet()) {
            boardMap.put(Integer.parseInt(key), Long.parseLong((String) mapping.get(key)));
        }
        List<Long> boardGameIds = new ArrayList<>(boardMap.values());
        for (Integer key : boardMap.keySet()) {
            boardGameIds.add(boardMap.get(key));
        }
        List<BoardGameSimpleResponse> boardGames = detailRecommend(boardGameIds);


        return boardGames;
    }

    public int analyzeChemi(User user, Long gameId) {
        //유저를 유저가 북마크한 보드게임 불러오기
        List<Bookmark> bookmarkList = bookmarkRepository.findByUser(user);
        BoardGame boardGame = boardGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        double[] userArr = new double[6];

        double[] gameArr = new double[6];

        for (Bookmark bookmark : bookmarkList) {
            mechanismGroupRepository.findByBoardGame(bookmark.getBoardGame()).stream()
                    .forEach(m ->countMechanism(userArr, m.getMechanism()));
        }
        mechanismGroupRepository.findByBoardGame(boardGame).stream().
                forEach(m->countMechanism(gameArr, m.getMechanism()));
        double userSum = Arrays.stream(userArr).sum();
        double gameSum = Arrays.stream(gameArr).sum();
        for(int i=0; i<6; i++){
            userArr[i] = (userArr[i]*100)/userSum;
        }
        for(int i=0; i<6; i++){
            gameArr[i] = (gameArr[i]*100)/gameSum;
        }

        //gameid를 통해 게임 불러오기
        double result = 200.0;
        for(int i=0; i<6 ; i++){
            result -= Math.abs(gameArr[i]-userArr[i]);
        }
        result/=2;
        return (int)(0.5+result);
    }

    public void countMechanism(double[] mechamismArr, Mechanism mechanism) {
        String parentMechanism = mechanism.getParentsMec();
        if(parentMechanism.equals("조건")){
            mechamismArr[0]++;
        }else if(parentMechanism.equals("말")){
            mechamismArr[1]++;
        }else if(parentMechanism.equals("파티")){
            mechamismArr[2]++;
        }else if(parentMechanism.equals("경제")){
            mechamismArr[3]++;
        }else if(parentMechanism.equals("전략")){
            mechamismArr[4]++;
        }else {
            mechamismArr[5]++;
        }
    }
}
