package com.specialization.yogidice.controller;

import com.specialization.yogidice.common.config.web.LoginUser;
import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.request.NumOfReviewRequest;
import com.specialization.yogidice.dto.response.*;
import com.specialization.yogidice.service.BoardGameService;
import com.specialization.yogidice.service.NumOfReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Api(tags = {"보드게임 API"})
public class BoardGameController {
    private final BoardGameService boardGameService;
    private final NumOfReviewService numOfReviewService;

    // 보드게임 추가
    @PostMapping
    @ApiOperation(value = "보드게임 추가", notes = "보드게임에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createBoardGame(
            @Valid @RequestBody BoardGameRequest request
    ) {
        Long gameId = boardGameService.createBoardGame(request);
        NumOfReviewRequest numOfReviewRequest = new NumOfReviewRequest(gameId);
        numOfReviewService.createNumOfReview(numOfReviewRequest);
        return ResponseEntity.status(HttpStatus.OK).body(BoardGameResponse.of(200, "Success", gameId));
    }

    // 보드게임 목록 조회
    @GetMapping
    @ApiOperation(value = "보드게임 목록 조회", notes = "보드게임 목록을 조회합니다.")
    public ResponseEntity<?> readBoardGameList() {
        return ResponseEntity.status(HttpStatus.OK).body(BoardGameListResponse.of(200, "Success", boardGameService.readBoardGameList()));
    }

    // 보드게임 상세 조회
    @GetMapping("/{gameId}")
    @ApiOperation(value = "보드게임 상세 조회", notes = "보드게임을 상세 조회합니다.")
    public ResponseEntity<?> readBoardGame(
            @PathVariable Long gameId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(BoardGameResponse.of(200, "Success", boardGameService.readBoardGame(gameId)));
    }

    // 보드게임 정보 수정
    @PutMapping("/{gameId}")
    @ApiOperation(value = "보드게임 정보 수정", notes = "보드게임의 정보를 수정합니다.")
    public ResponseEntity<?> updateBoardGame(
            @PathVariable Long gameId,
            @Valid @RequestBody BoardGameRequest request
    ) {
        boardGameService.updateBoardGame(gameId, request);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "Success"));
    }

    // 보드게임 삭제
    @DeleteMapping("/{gameId}")
    @ApiOperation(value = "보드게임 삭제", notes = "보드게임을 삭제합니다.")
    public ResponseEntity<?> deleteBoardGame(
            @PathVariable Long gameId
    ) {
        boardGameService.deleteBoardGame(gameId);
        numOfReviewService.deleteNumOfReview((gameId));
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "Success"));
    }

    // 상위 10개 게임 리스트 조회 (최신 게임 순)
    @GetMapping("/recent")
    @ApiOperation(value = "보드게임 상위 10개 게임 리스트 조회 (최신 게임 순)", notes = "상위 10개 게임 리스트를 최신 게임 순으로 조회합니다.")
    public ResponseEntity<?> readBoardGameListByPublishYear() {
        return ResponseEntity.status(HttpStatus.OK).body(RecentGameListResponse.of(200, "Success", boardGameService.readPublishYearTop()));
    }

    // 상위 10개 게임 리스트 조회 (리뷰 많은 순)
    @GetMapping("/review")
    @ApiOperation(value = "보드게임 상위 10개 게임 리스트 조회 (리뷰 많은 순)", notes = "상위 10개 게임 리스트를 리뷰가 많은 순으로 조회합니다.")
    public ResponseEntity<?> readBoardGameListByReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(NumOfReviewListResponse.of(200, "Success", numOfReviewService.readNumOfReviewTop10List()));
    }

    @PostMapping("/recommend")
    @ApiOperation(value = "pick 추천", notes = "질문 답변에 따른 추천 보드게임을 분석합니다.")
    public ResponseEntity<?> pickRecommend(/*@RequestBody*/ ) {
        String boardGameList="";
        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.of(200, "Success", boardGameList));
    }

    @GetMapping("/recommend/play/{gameId}")
    @ApiOperation(value = "게임이 끝난 후 다음 게임 추천", notes = "게임이 끝난 후 연관된 다음 게임을 추천합니다.")
    public ResponseEntity<?> nextRecommend(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long gameId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8000/analyze/recommend/play";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getId());
        jsonObject.put("gameId", gameId);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        String boardGameList = restTemplate.postForObject(url, request, String.class);

        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.of(200, "Success", boardGameList));
    }

    @GetMapping("/recommend/main")
    @ApiOperation(value = "메인 추천", notes = "메인 페이지에서 게임을 추천합니다.")
    public ResponseEntity<?> mainRecommend(
            @ApiIgnore @LoginUser User user
            ) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8000/analyze/recommend/main";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getId());

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        String boardGameList = restTemplate.postForObject(url, request, String.class);

        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.of(200, "Success", boardGameList));
    }


    @GetMapping("/recommend/detail/{gameId}")
    @ApiOperation(value = "보드게임 상세 페이지 하단 추천", notes = "보드게임 상세 페이지 하단에서 보드게임을 추천합니다.")
    public ResponseEntity<?> detailRecommend(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long gameId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8000/analyze/recommend/detail";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getId());
        jsonObject.put("gameId", gameId);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), httpHeaders);
        String boardGameList = restTemplate.postForObject(url, request, String.class);
        return ResponseEntity.status(HttpStatus.OK).body(JsonResponse.of(200, "Success", boardGameList));
    }
}