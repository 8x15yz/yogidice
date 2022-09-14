package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.domain.entity.category.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanismResponse {
    private long id;

    private String name;

    private int parentsMec;

    public static MechanismResponse response(Mechanism mechanism) {
        return new MechanismResponse(mechanism.getId(), mechanism.getName(), mechanism.getParentsMec());
    }
}
