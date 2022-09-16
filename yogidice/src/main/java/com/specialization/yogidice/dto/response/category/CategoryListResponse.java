package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Category;
import com.specialization.yogidice.domain.entity.category.CategoryList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListResponse {
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
}
