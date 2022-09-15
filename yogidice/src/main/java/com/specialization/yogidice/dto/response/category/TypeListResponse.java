package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.TypeList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeListResponse {
    private long id;

    private String gameTitle;

    private String typeName;

    public static TypeListResponse response(TypeList typeList) {
        return new TypeListResponse(
                typeList.getId(),
                typeList.getBoardGame().getTitleKr(),
                typeList.getType().getName()
        );
    }
}
