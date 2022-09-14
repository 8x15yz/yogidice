package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private long id;

    private String name;

    public static CategoryResponse response(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
