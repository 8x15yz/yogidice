package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.TypeList;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("TypeListResponse")
public class TypeListResponse extends BaseResponseBody {
    private long id;

    private Long gameId;

    private String gameTitle;

    private Long typeId;

    private String typeName;

    public static TypeListResponse response(TypeList typeList) {
        return new TypeListResponse(
                typeList.getId(),
                typeList.getBoardGame().getId(),
                typeList.getBoardGame().getTitleKr(),
                typeList.getType().getId(),
                typeList.getType().getName()
        );
    }

    public static TypeListResponse of(Integer statusCode, String message, Long typyListId) {
        TypeListResponse response = new TypeListResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(typyListId);
        return response;
    }
}
