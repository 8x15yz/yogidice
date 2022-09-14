package com.specialization.yogidice.controller;

import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.service.BoardGameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Api(tags = {"보드게임 API"})
public class BoardGameController {
    private final BoardGameService boardGameService;

    // 보드게임 등록
    @PostMapping
    @ApiOperation(value = "보드게임 등록", notes = "보드게임에 필요한 정보를 입력하고 등록합니다.")
    public ResponseEntity<?> createBoardGame(
            @Valid @RequestBody BoardGameRequest request
    ) {
        Long gameId = boardGameService.createBoardGame(request);
        Map<String, Long> response = new HashMap<>();
        response.put("gameId", gameId);
        return ResponseEntity.ok(response);
    }

    // 보드게임 상세 조회
    @GetMapping("/{gameId}")
    @ApiOperation(value = "보드게임 상세 조회", notes = "보드게임을 상세 조회합니다.")
    public ResponseEntity<?> readBoardGame(
            @PathVariable Long gameId
    ){
        return ResponseEntity.ok(boardGameService.readBoardGame(gameId));
    }

    // 보드게임 목록 조회
    @GetMapping
    @ApiOperation(value = "보드게임 목록 조회", notes = "보드게임 목록을 조회합니다.")
    public ResponseEntity<?> readBoardGameList() {
        return ResponseEntity.ok(boardGameService.readBoardGameList());
    }

    // 보드게임 정보 수정
    @PutMapping("/{gameId}")
    @ApiOperation(value = "보드게임 정보 수정", notes = "보드게임의 정보를 수정합니다.")
    public ResponseEntity<?> updateBoardGame(
            @PathVariable Long gameId,
            @Valid @RequestBody BoardGameRequest request
    ) {
        boardGameService.updateBoardGame(gameId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 보드게임 삭제
    @DeleteMapping("/{gameId}")
    @ApiOperation(value = "보드게임 삭제", notes = "보드게임을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteUser(
            @PathVariable Long gameId
    ) {
        boardGameService.deleteBoardGame(gameId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}