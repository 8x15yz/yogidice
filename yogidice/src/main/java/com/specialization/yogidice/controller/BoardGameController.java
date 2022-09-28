package com.specialization.yogidice.controller;

import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.request.NumOfReviewRequest;
import com.specialization.yogidice.dto.response.*;
import com.specialization.yogidice.service.BoardGameService;
import com.specialization.yogidice.service.NumOfReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.OK).body(BoardGameCreateResponse.of(200, "Success", gameId));
    }

    // 보드게임 목록 조회
    @GetMapping
    @ApiOperation(value = "보드게임 목록 조회", notes = "보드게임 목록을 조회합니다.")
    public ResponseEntity<?> readBoardGameList(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(BoardGameListResponse.of(200, "Success", boardGameService.readBoardGameList(pageable)));
    }

    // 보드게임 상세 조회
    @GetMapping("/{gameId}")
    @ApiOperation(value = "보드게임 상세 조회", notes = "보드게임을 상세 조회합니다.")
    public ResponseEntity<?> readBoardGame(
            @PathVariable Long gameId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(BoardGameDetailResponse.of(200, "Success", boardGameService.readBoardGame(gameId)));
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
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "Success"));
    }

    // 상위 10개 게임 리스트 조회 (평점 순)
    @GetMapping("/top10/rating")
    @ApiOperation(value = "보드게임 상위 10개 게임 리스트 조회 (평점 순)", notes = "상위 10개 게임 리스트를 평점 순으로 조회합니다.")
    public ResponseEntity<?> readTop10ListByRatingUser() {
        return ResponseEntity.status(HttpStatus.OK).body(RatingGameListResponse.of(200, "Success", boardGameService.readTop10ListByRatingUser()));
    }

    // 평점 순 전체 보드게임 리스트 조회
    @GetMapping("/rating")
    @ApiOperation(value = "평점 순 전체 보드게임 리스트 조회", notes = "평점 순 전체 보드게임 리스트를 조회합니다.")
    public ResponseEntity<?> readAllListByRatingUser(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(RatingGameListResponse.of(200, "Success", boardGameService.readAllListByRatingUser(pageable)));
    }

    // 상위 10개 게임 리스트 조회 (최신 게임 순)
    @GetMapping("/top10/recent")
    @ApiOperation(value = "보드게임 상위 10개 게임 리스트 조회 (최신 게임 순)", notes = "상위 10개 게임 리스트를 최신 게임 순으로 조회합니다.")
    public ResponseEntity<?> readTop10ListByPublishYear() {
        return ResponseEntity.status(HttpStatus.OK).body(RecentGameListResponse.of(200, "Success", boardGameService.readTop10ListByPublishYear()));
    }

    // 최신 게임 순 전체 보드게임 리스트 조회
    @GetMapping("/recent")
    @ApiOperation(value = "최신 게임 순 전체 보드게임 리스트 조회", notes = "최신 게임 순 전체 보드게임 리스트를 조회합니다.")
    public ResponseEntity<?> readAllListByPublishYear(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(RecentGameListResponse.of(200, "Success", boardGameService.readAllListByPublishYear(pageable)));
    }

    // 상위 10개 게임 리스트 조회 (리뷰 많은 순)
    @GetMapping("/top10/review")
    @ApiOperation(value = "보드게임 상위 10개 게임 리스트 조회 (리뷰 많은 순)", notes = "상위 10개 게임 리스트를 리뷰가 많은 순으로 조회합니다.")
    public ResponseEntity<?> readTop10ListByNumOfReview() {
        return ResponseEntity.status(HttpStatus.OK).body(NumOfReviewListResponse.of(200, "Success", numOfReviewService.readTop10ListByNumOfReview()));
    }

    // 리뷰 많은 순 전체 보드게임 리스트 조회
    @GetMapping("/review")
    @ApiOperation(value = "리뷰 많은 순 전체 보드게임 리스트 조회", notes = "리뷰 많은 순 전체 보드게임 리스트를 조회합니다.")
    public ResponseEntity<?> readAllListByNumOfReview(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(NumOfReviewListResponse.of(200, "Success", numOfReviewService.readAllListByNumOfReview(pageable)));
    }
}