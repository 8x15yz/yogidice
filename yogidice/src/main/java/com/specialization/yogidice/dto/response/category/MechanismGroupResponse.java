package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.MechanismGroup;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("MechanismGroupResponse")
public class MechanismGroupResponse extends BaseResponseBody {
    private long id;

    private Long gameId;

    private String gameTitle;

    private byte mechanismId;

    private String mechanismName;

    public static MechanismGroupResponse response(MechanismGroup mechanismGroup) {
        return new MechanismGroupResponse(
                mechanismGroup.getId(),
                mechanismGroup.getBoardGame().getId(),
                mechanismGroup.getBoardGame().getTitleKr(),
                mechanismGroup.getMechanism().getId(),
                mechanismGroup.getMechanism().getName()
        );
    }

    public static MechanismGroupResponse of(Integer statusCode, String message, Long mechanismGroupId) {
        MechanismGroupResponse response = new MechanismGroupResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(mechanismGroupId);
        return response;
    }
}
