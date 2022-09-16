package com.specialization.yogidice.controller;

import com.specialization.yogidice.dto.request.category.*;
import com.specialization.yogidice.service.category.*;
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
@RequestMapping("/categorylist")
@RequiredArgsConstructor
@Api(tags = {"카테고리 목록 API"})
public class CategoryListController {
    private final CategoryListService categoryListService;
    private final TypeListService typeListService;
    private final MechanismListService mechanismListService;

    // 카테고리(테마) 추가
    @PostMapping("/theme")
    @ApiOperation(value = "카테고리(테마) 목록 추가", notes = "카테고리(테마) 목록에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createCategoryList(
            @Valid @RequestBody CategoryListCreateRequest request
    ) {
        Long cateListId = categoryListService.createCategoryList(request);
        Map<String, Long> response = new HashMap<>();
        response.put("cateListId", cateListId);
        return ResponseEntity.ok(response);
    }

    // 보드게임을 통한 카테고리(테마) 목록 조회
    @GetMapping("/theme/{gameId}")
    @ApiOperation(value = "보드게임을 통한 카테고리(테마) 목록 조회", notes = "보드게임을 통해 카테고리(테마) 목록을 조회합니다.")
    public ResponseEntity<?> readCategoryListOfBoardGame(
            @PathVariable Long gameId
    ) {
        return ResponseEntity.ok(categoryListService.readCategoryListOfBoardGame(gameId));
    }

    // 카테고리를 통한 카테고리(테마) 목록 조회
    @GetMapping("/theme/{cateId}")
    @ApiOperation(value = "카테고리를 통한 카테고리(테마) 목록 조회", notes = "카테고리를 통해 카테고리(테마) 목록을 조회합니다.")
    public ResponseEntity<?> readCategoryListOfCategory(
            @PathVariable Long cateId
    ) {
        return ResponseEntity.ok(categoryListService.readCategoryListOfCategory(cateId));
    }

    // 카테고리(테마) 목록 수정
    @PutMapping("/theme/{cateListId}")
    @ApiOperation(value = "카테고리(테마) 목록 정보 수정", notes = "카테고리(테마) 목록의 정보를 수정합니다.")
    public ResponseEntity<?> updateCategoryList(
            @PathVariable Long cateListId,
            @Valid @RequestBody CategoryListUpdateRequest request
    ) {
        categoryListService.updateCategoryList(cateListId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 카테고리(테마) 목록 삭제
    @DeleteMapping("/theme/{cateListId}")
    @ApiOperation(value = "카테고리(테마) 목록 삭제", notes = "카테고리(테마) 목록을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteCategoryList(
            @PathVariable Long cateListId
    ) {
        categoryListService.deleteCategoryList(cateListId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 타입 목록 추가
    @PostMapping("/type")
    @ApiOperation(value = "타입 목록 추가", notes = "타입 목록에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createTypeList(
            @Valid @RequestBody TypeListCreateRequest request
    ) {
        Long typeListId = typeListService.createTypeList(request);
        Map<String, Long> response = new HashMap<>();
        response.put("typeListId", typeListId);
        return ResponseEntity.ok(response);
    }

    // 보드게임을 통한 타입 목록 조회
    @GetMapping("/type/{gameId}")
    @ApiOperation(value = "보드게임을 통한 타입 목록 조회", notes = "보드게임을 통해 타입 목록을 조회합니다.")
    public ResponseEntity<?> readTypeListOfBoardGame(
            @PathVariable Long gameId
    ) {
        return ResponseEntity.ok(typeListService.readTypeListOfBoardGame(gameId));
    }

    // 타입을 통한 타입 목록 조회
    @GetMapping("/type/{typeId}")
    @ApiOperation(value = "타입을 통한 타입 목록 조회", notes = "타입을 통해 타입 목록을 조회합니다.")
    public ResponseEntity<?> readTypeListOfType(
            @PathVariable Long typeId
    ) {
        return ResponseEntity.ok(typeListService.readTypeListOfType(typeId));
    }

    // 타입 목록 수정
    @PutMapping("/type/{typeListId}")
    @ApiOperation(value = "타입 목록 정보 수정", notes = "타입 목록의 정보를 수정합니다.")
    public ResponseEntity<?> updateTypeList(
            @PathVariable Long typeListId,
            @Valid @RequestBody TypeListUpdateRequest request
    ) {
        typeListService.updateTypeList(typeListId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 타입 목록 삭제
    @DeleteMapping("/type/{typeListId}")
    @ApiOperation(value = "타입 목록 삭제", notes = "타입 목록을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteTypeList(
            @PathVariable Long typeListId
    ) {
        typeListService.deleteTypeList(typeListId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 진행방식 목록 추가
    @PostMapping("/mechanism")
    @ApiOperation(value = "진행방식 목록 추가", notes = "진행방식 목록에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createMechanismList(
            @Valid @RequestBody MechanismListCreateRequest request
    ) {
        Long mechanismListId = mechanismListService.createMechanismList(request);
        Map<String, Long> response = new HashMap<>();
        response.put("mechanismListId", mechanismListId);
        return ResponseEntity.ok(response);
    }

    // 보드게임을 통한 진행방식 목록 조회
    @GetMapping("/mechanism/{gameId}")
    @ApiOperation(value = "보드게임을 통한 진행방식 목록 조회", notes = "보드게임을 통해 진행방식 목록을 조회합니다.")
    public ResponseEntity<?> readMechanismListOfBoardGame(
            @PathVariable Long gameId
    ) {
        return ResponseEntity.ok(mechanismListService.readMechanismListOfBoardGame(gameId));
    }

    // 진행방식을 통한 진행방식 목록 조회
    @GetMapping("/mechanism/{mechanismId}")
    @ApiOperation(value = "진행방식을 통한 진행방식 목록 조회", notes = "진행방식을 통해 진행방식 목록을 조회합니다.")
    public ResponseEntity<?> readMechanismListOfMechanism(
            @PathVariable Long mechanismId
    ) {
        return ResponseEntity.ok(mechanismListService.readMechanismListOfMechanism(mechanismId));
    }

    // 진행방식 목록 수정
    @PutMapping("/mechanism/{mechanismListId}")
    @ApiOperation(value = "진행방식 목록 정보 수정", notes = "진행방식 목록의 정보를 수정합니다.")
    public ResponseEntity<?> updateMechanismList(
            @PathVariable Long mechanismListId,
            @Valid @RequestBody MechanismListUpdateRequest request
    ) {
        mechanismListService.updateMechanismList(mechanismListId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 진행방식 목록 삭제
    @DeleteMapping("/mechanism/{mechanismListId}")
    @ApiOperation(value = "진행방식 목록 삭제", notes = "진행방식 목록을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteMechanismList(
            @PathVariable Long mechanismListId
    ) {
        mechanismListService.deleteMechanismList(mechanismListId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}