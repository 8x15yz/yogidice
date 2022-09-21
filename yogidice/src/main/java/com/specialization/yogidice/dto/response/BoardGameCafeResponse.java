package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.BoardGameCafe;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BoardGameCafeResponse")
public class BoardGameCafeResponse extends BaseResponseBody {
    private Long id;

    private String name;

    private String address;

    public static BoardGameCafeResponse response(BoardGameCafe cafe) {
        return new BoardGameCafeResponse(
                cafe.getId(),
                cafe.getName(),
                cafe.getAddress()
        );
    }

    public static BoardGameCafeResponse of(Integer statusCode, String message, Long cafeId) {
        BoardGameCafeResponse response = new BoardGameCafeResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(cafeId);
        return response;
    }

    public static BoardGameCafeResponse of(Integer statusCode, String message, BoardGameCafeResponse boardGameCafeResponse) {
        BoardGameCafeResponse response = new BoardGameCafeResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(boardGameCafeResponse.getId());
        response.setName(boardGameCafeResponse.getName());
        response.setAddress(boardGameCafeResponse.getAddress());
        return response;
    }
}
