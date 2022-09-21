package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.CategoryGroup;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("CategoryGroupResponse")
public class CategoryGroupResponse extends BaseResponseBody {
    private long id;

    private Long gameId;

    private String gameTitle;

    private Long cateId;

    private String categoryName;

    public static CategoryGroupResponse response(CategoryGroup categoryGroup) {
        return new CategoryGroupResponse(
                categoryGroup.getId(),
                categoryGroup.getBoardGame().getId(),
                categoryGroup.getBoardGame().getTitleKr(),
                categoryGroup.getCategory().getId(),
                categoryGroup.getCategory().getName()
        );
    }

    public static CategoryGroupResponse of(Integer statusCode, String message, Long categoryGroupId) {
        CategoryGroupResponse response = new CategoryGroupResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(categoryGroupId);
        return response;
    }
}
