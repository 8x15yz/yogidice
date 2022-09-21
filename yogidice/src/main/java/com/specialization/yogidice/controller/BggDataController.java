package com.specialization.yogidice.controller;

import com.specialization.yogidice.dto.request.BggDataRequest;
import com.specialization.yogidice.dto.request.BoardGameRequest;
import com.specialization.yogidice.dto.response.*;
import com.specialization.yogidice.service.BggDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bggData")
@RequiredArgsConstructor
@Api(tags = {"BGG 보드게임 API"})
public class BggDataController {
    private final BggDataService bggDataService;

    // BGG 보드게임 추가
    @PostMapping
    @ApiOperation(value = "BGG 보드게임 추가", notes = "BGG 보드게임에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createBggData(
            @Valid @RequestBody BggDataRequest request
    ) {
        Long bggDataId = bggDataService.createBggData(request);
        return ResponseEntity.status(HttpStatus.OK).body(BggDataResponse.of(200, "Success", bggDataId));
    }

    // BGG 보드게임 목록 조회
    @GetMapping
    @ApiOperation(value = "BGG 보드게임 목록 조회", notes = "BGG의 보드게임 목록을 조회합니다.")
    public ResponseEntity<?> readBggDataList() {
        return ResponseEntity.status(HttpStatus.OK).body(BggDataListResponse.of(200, "Success", bggDataService.readBggDataList()));
    }

    // BGG 보드게임 상세 조회
    @GetMapping("/{bggDataId}")
    @ApiOperation(value = "BGG 보드게임 상세 조회", notes = "BGG의 보드게임을 상세 조회합니다.")
    public ResponseEntity<?> readBggData(
            @PathVariable Long bggDataId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(BggDataResponse.of(200, "Success", bggDataService.readBggData(bggDataId)));
    }

    // BGG 보드게임 정보 수정
    @PutMapping("/{bggDataId}")
    @ApiOperation(value = "BGG 보드게임 정보 수정", notes = "BGG의 보드게임의 정보를 수정합니다.")
    public ResponseEntity<?> updateBggData(
            @PathVariable Long bggDataId,
            @Valid @RequestBody BggDataRequest request
    ) {
        bggDataService.updateBggData(bggDataId, request);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "Success"));
    }

    // BGG 보드게임 삭제
    @DeleteMapping("/{bggDataId}")
    @ApiOperation(value = "BGG 보드게임 삭제", notes = "BGG의 보드게임을 삭제합니다.")
    public ResponseEntity<?> deleteBggData(
            @PathVariable Long bggDataId
    ) {
        bggDataService.deleteBggData(bggDataId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "Success"));
    }
}