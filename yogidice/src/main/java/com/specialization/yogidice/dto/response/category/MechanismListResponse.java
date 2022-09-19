package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.MechanismList;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("MechanismListResponse")
public class MechanismListResponse extends BaseResponseBody {
    private long id;

    private Long gameId;

    private String gameTitle;

    private Long mechanismId;

    private String mechanismName;

    public static MechanismListResponse response(MechanismList mechanismList) {
        return new MechanismListResponse(
                mechanismList.getId(),
                mechanismList.getBoardGame().getId(),
                mechanismList.getBoardGame().getTitleKr(),
                mechanismList.getMechanism().getId(),
                mechanismList.getMechanism().getName()
        );
    }

    public static MechanismListResponse of(Integer statusCode, String message, Long mechanismListId) {
        MechanismListResponse response = new MechanismListResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(mechanismListId);
        return response;
    }
}
