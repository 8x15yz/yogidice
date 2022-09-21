package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.dto.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("MechanismResponse")
public class MechanismResponse extends BaseResponseBody {
    private byte id;

    private String name;

    private int parentsMec;

    public static MechanismResponse response(Mechanism mechanism) {
        return new MechanismResponse(
                mechanism.getId(),
                mechanism.getName(),
                mechanism.getParentsMec()
        );
    }

    public static MechanismResponse of(Integer statusCode, String message, byte mechanismId) {
        MechanismResponse response = new MechanismResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(mechanismId);
        return response;
    }

    public static MechanismResponse of(Integer statusCode, String message, MechanismResponse mechanismResponse) {
        MechanismResponse response = new MechanismResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(mechanismResponse.getId());
        response.setName(mechanismResponse.getName());
        response.setParentsMec(mechanismResponse.getParentsMec());
        return response;
    }
}
