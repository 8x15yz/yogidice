package com.specialization.yogidice.controller;

import com.specialization.yogidice.dto.request.category.CategoryRequest;
import com.specialization.yogidice.dto.request.category.MechanismRequest;
import com.specialization.yogidice.dto.request.category.TypeRequest;
import com.specialization.yogidice.service.category.CategoryService;
import com.specialization.yogidice.service.category.MechanismService;
import com.specialization.yogidice.service.category.TypeService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
@Api(tags = {"카테고리 API"})
public class CategoryController {
    private final CategoryService categoryService;
    private final TypeService typeService;
    private final MechanismService mechanismService;

    // 카테고리(테마) 추가
    @PostMapping("/theme")
    @ApiOperation(value = "카테고리(테마) 추가", notes = "카테고리(테마)에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryRequest request
    ) {
        Long cateId = categoryService.createCategory(request);
        Map<String, Long> response = new HashMap<>();
        response.put("cateId", cateId);
        return ResponseEntity.ok(response);
    }

    // 카테고리(테마) 목록 조회
    @GetMapping("/theme")
    @ApiOperation(value = "카테고리(테마) 목록 조회", notes = "카테고리(테마) 목록을 조회합니다.")
    public ResponseEntity<?> readCategoryList() {
        return ResponseEntity.ok(categoryService.readCategoryList());
    }

    // 카테고리(테마) 상세 조회
    @GetMapping("/theme/{cateId}")
    @ApiOperation(value = "카테고리(테마) 상세 조회", notes = "카테고리(테마)을 상세 조회합니다.")
    public ResponseEntity<?> readCategory(
            @PathVariable Long cateId
    ){
        return ResponseEntity.ok(categoryService.readCategory(cateId));
    }

    // 카테고리(테마) 수정
    @PutMapping("/theme/{cateId}")
    @ApiOperation(value = "카테고리(테마) 정보 수정", notes = "카테고리(테마)의 정보를 수정합니다.")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long cateId,
            @Valid @RequestBody CategoryRequest request
    ) {
        categoryService.updateCategory(cateId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 카테고리(테마) 삭제
    @DeleteMapping("/theme/{cateId}")
    @ApiOperation(value = "카테고리(테마) 삭제", notes = "카테고리(테마)을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteCategory(
            @PathVariable Long cateId
    ) {
        categoryService.deleteCategory(cateId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 타입 추가
    @PostMapping("/type")
    @ApiOperation(value = "타입 추가", notes = "타입에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createType(
            @Valid @RequestBody TypeRequest request
    ) {
        Long typeId = typeService.createType(request);
        Map<String, Long> response = new HashMap<>();
        response.put("typeId", typeId);
        return ResponseEntity.ok(response);
    }

    // 타입 목록 조회
    @GetMapping("/type")
    @ApiOperation(value = "타입 목록 조회", notes = "타입 목록을 조회합니다.")
    public ResponseEntity<?> readTypeList() {
        return ResponseEntity.ok(typeService.readTypeList());
    }

    // 타입 상세 조회
    @GetMapping("/type/{typeId}")
    @ApiOperation(value = "타입 상세 조회", notes = "타입을 상세 조회합니다.")
    public ResponseEntity<?> readType(
            @PathVariable Long typeId
    ){
        return ResponseEntity.ok(typeService.readType(typeId));
    }

    // 타입 수정
    @PutMapping("/type/{typeId}")
    @ApiOperation(value = "타입 정보 수정", notes = "타입의 정보를 수정합니다.")
    public ResponseEntity<?> updateType(
            @PathVariable Long typeId,
            @Valid @RequestBody TypeRequest request
    ) {
        typeService.updateType(typeId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 타입 삭제
    @DeleteMapping("/type/{typeId}")
    @ApiOperation(value = "타입 삭제", notes = "타입을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteType(
            @PathVariable Long typeId
    ) {
        typeService.deleteType(typeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 진행방식 추가
    @PostMapping("/mechanism")
    @ApiOperation(value = "진행방식 추가", notes = "진행방식에 필요한 정보를 입력하고 추가합니다.")
    public ResponseEntity<?> createMechanism(
            @Valid @RequestBody MechanismRequest request
    ) {
        Long mechanismId = mechanismService.createMechanism(request);
        Map<String, Long> response = new HashMap<>();
        response.put("mechanismId", mechanismId);
        return ResponseEntity.ok(response);
    }

    // 진행방식 목록 조회
    @GetMapping("/mechanism")
    @ApiOperation(value = "진행방식 목록 조회", notes = "진행방식 목록을 조회합니다.")
    public ResponseEntity<?> readMechanismList() {
        return ResponseEntity.ok(mechanismService.readMechanismList());
    }

    // 진행방식 상세 조회
    @GetMapping("/mechanism/{mechanismId}")
    @ApiOperation(value = "진행방식 상세 조회", notes = "진행방식을 상세 조회합니다.")
    public ResponseEntity<?> readMechanism(
            @PathVariable Long mechanismId
    ){
        return ResponseEntity.ok(mechanismService.readMechanism(mechanismId));
    }

    // 진행방식 수정
    @PutMapping("/mechanism/{mechanismId}")
    @ApiOperation(value = "진행방식 정보 수정", notes = "진행방식의 정보를 수정합니다.")
    public ResponseEntity<?> updateMechanism(
            @PathVariable Long mechanismId,
            @Valid @RequestBody MechanismRequest request
    ) {
        mechanismService.updateMechanism(mechanismId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 진행방식 삭제
    @DeleteMapping("/mechanism/{mechanismId}")
    @ApiOperation(value = "진행방식 삭제", notes = "진행방식을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> deleteMechanism(
            @PathVariable Long mechanismId
    ) {
        mechanismService.deleteMechanism(mechanismId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
