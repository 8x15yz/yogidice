package com.specialization.yogidice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialization.yogidice.common.auth.SsafyUserDetails;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = {"유저 API"})
public class UserController {
    private final UserService userService;

    // 카카오 계정 정보 가져오기
    @GetMapping("/kakao/callback")
    @ApiOperation(value = "카카오 계정 정보 가져오기", notes = "카카오 계정 정보를 가져옵니다.")
    public ResponseEntity<?> getKakaoUserInfo(
            @RequestParam String code
    ) throws JsonProcessingException {
        String accessToken = userService.getAccessToken(code);
        KakaoUserInfo kakaoUserInfo = userService.getKakaoUserInfo(accessToken);
        Map<String, KakaoUserInfo> response = new HashMap<>();
        response.put("kakaoUserInfo", kakaoUserInfo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @ApiOperation(value = "카카오 회원 가입 로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "이메일 또는 닉네임 중복"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> login(
            //회원가입 요청
            /*@RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo*/) {

        // 카카오 로그인 잘 모르겠습니다.

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping()
    @ApiOperation(value = "회원 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends  BaseResponseBody> getUserInfo(@ApiIgnore Authentication authentication){
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PutMapping()
    @ApiOperation(value = "회원 정보 수정", notes = "회원 닉네임 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> modifyUser(
           @ApiIgnore Authentication authentication/*,  @RequestBody @ApiParam(value = "수정할 회원 정보", required = true) UserRegisterPostReq userRegisterInfo*/) {
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();

        //유저 서비스로 회원 정보 수정 코드
//        User user = userService.updateUser(Long.parseLong(userId), userRegisterInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping()
    @ApiOperation(value = "회원 탈퇴", notes = "현재 로그인 되어있는 사용자 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> resignUser(@ApiIgnore Authentication authentication) {
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String id = userDetails.getUsername();

        //유저 서비스로 회원 정보 수정 코드
//        userService.resignUserById(Long.parseLong(id));

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}