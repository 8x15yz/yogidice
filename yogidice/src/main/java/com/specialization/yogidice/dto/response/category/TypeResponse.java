package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse {
    private long id;

    private String name;

    public static TypeResponse response(Type type) {
        return new TypeResponse(
                type.getId(),
                type.getName()
        );
    }
}
