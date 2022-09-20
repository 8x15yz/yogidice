package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Category;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("CategoryResponse")
public class CategoryResponse extends BaseResponseBody {
    private long id;

    private String name;

    public static CategoryResponse response(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    public static CategoryResponse of(Integer statusCode, String message, Long categoryId) {
        CategoryResponse response = new CategoryResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(categoryId);
        return response;
    }

    public static CategoryResponse of(Integer statusCode, String message, CategoryResponse categoryResponse) {
        CategoryResponse response = new CategoryResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(categoryResponse.getId());
        response.setName(categoryResponse.getName());
        return response;
    }
}
