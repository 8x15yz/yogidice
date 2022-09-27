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
    private Byte id;

    private String name;

    public static TypeResponse response(Type type) {
        return new TypeResponse(
                type.getId(),
                type.getName()
        );
    }

    public static TypeResponse of(Integer statusCode, String message, Byte typeId) {
        TypeResponse response = new TypeResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(typeId);
        return response;
    }

    public static TypeResponse of(Integer statusCode, String message, TypeResponse typeResponse) {
        TypeResponse response = new TypeResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(typeResponse.getId());
        response.setName(typeResponse.getName());
        return response;
    }
}
