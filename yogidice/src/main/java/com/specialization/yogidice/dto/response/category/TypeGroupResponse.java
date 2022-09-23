package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.TypeGroup;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("TypeGroupResponse")
public class TypeGroupResponse extends BaseResponseBody {
    private long id;

    private Long gameId;

    private String gameTitle;

    private Long typeId;

    private String typeName;

    public static TypeGroupResponse response(TypeGroup typeGroup) {
        return new TypeGroupResponse(
                typeGroup.getId(),
                typeGroup.getBoardGame().getId(),
                typeGroup.getBoardGame().getTitleKr(),
                typeGroup.getType().getId(),
                typeGroup.getType().getName()
        );
    }

    public static TypeGroupResponse of(Integer statusCode, String message, Long typyGroupId) {
        TypeGroupResponse response = new TypeGroupResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(typyGroupId);
        return response;
    }
}
