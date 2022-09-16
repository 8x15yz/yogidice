package com.specialization.yogidice.dto.response.category;

import com.specialization.yogidice.domain.entity.category.MechanismList;
import com.specialization.yogidice.domain.entity.category.TypeList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanismListResponse {
    private long id;

    private Long gameId;

    private String gameTitle;

    private Long mechanismId;

    private String mechanismName;

    public static MechanismListResponse response(MechanismList mechanismList) {
        return new MechanismListResponse(
                mechanismList.getId(),
                mechanismList.getBoardGame().getId(),
                mechanismList.getBoardGame().getTitleKr(),
                mechanismList.getMechanism().getId(),
                mechanismList.getMechanism().getName()
        );
    }
}
