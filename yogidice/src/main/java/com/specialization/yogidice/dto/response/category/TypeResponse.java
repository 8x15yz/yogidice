package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Type;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("TypeResponse")
public class TypeResponse extends BaseResponseBody {
    private long id;

    private String name;

    public static TypeResponse response(Type type) {
        return new TypeResponse(
                type.getId(),
                type.getName()
        );
    }

    public static TypeResponse of(Integer statusCode, String message, Long typeId) {
        TypeResponse response = new TypeResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(typeId);
        return response;
    }
}
