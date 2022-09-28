package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.BggData;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BggDataResponse")
public class BggDataResponse extends BaseResponseBody {
    private Long id;

    private String titleEng;

    private Long bggCode;

    private String nickName;

    private double ratingUser;

    public static BggDataResponse response(
            BggData bggData
    ) {
        return new BggDataResponse(
                bggData.getId(),
                bggData.getTitleEng(),
                bggData.getBggCode(),
                bggData.getNickName(),
                bggData.getRatingUser()
        );
    }

    public static BggDataResponse of(Integer statusCode, String message, Long bggDataId) {
        BggDataResponse response = new BggDataResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(bggDataId);
        return response;
    }

    public static BggDataResponse of(Integer statusCode, String message, BggDataResponse bggDataResponse) {
        BggDataResponse response = new BggDataResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(bggDataResponse.getId());
        response.setTitleEng(bggDataResponse.getTitleEng());
        response.setBggCode(bggDataResponse.getBggCode());
        response.setNickName(bggDataResponse.getNickName());
        response.setRatingUser(bggDataResponse.getRatingUser());
        return response;
    }
}
