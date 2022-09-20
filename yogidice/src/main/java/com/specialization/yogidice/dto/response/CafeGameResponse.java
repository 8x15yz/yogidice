package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.CafeGameList;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("CafeGameResponse")
public class CafeGameResponse extends BaseResponseBody {
    private Long id;

    private Long cafeId;

    private String cafeName;

    private Long gameId;

    private String gameTitle;

    public static CafeGameResponse response(CafeGameList cafeGameList) {
        return new CafeGameResponse(
                cafeGameList.getId(),
                cafeGameList.getBoardGameCafe().getId(),
                cafeGameList.getBoardGameCafe().getName(),
                cafeGameList.getBoardGame().getId(),
                cafeGameList.getBoardGame().getTitleKr()
        );
    }

    public static CafeGameResponse of(Integer statusCode, String message, Long gameListId) {
        CafeGameResponse response = new CafeGameResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(gameListId);
        return response;
    }
}
