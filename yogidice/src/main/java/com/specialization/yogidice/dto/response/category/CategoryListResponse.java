package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.CategoryList;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("CategoryListResponse")
public class CategoryListResponse extends BaseResponseBody {
    private long id;

    private Long gameId;

    private String gameTitle;

    private Long cateId;

    private String categoryName;

    public static CategoryListResponse response(CategoryList categoryList) {
        return new CategoryListResponse(
                categoryList.getId(),
                categoryList.getBoardGame().getId(),
                categoryList.getBoardGame().getTitleKr(),
                categoryList.getCategory().getId(),
                categoryList.getCategory().getName()
        );
    }

    public static CategoryListResponse of(Integer statusCode, String message, Long categoryListId) {
        CategoryListResponse response = new CategoryListResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(categoryListId);
        return response;
    }
}
