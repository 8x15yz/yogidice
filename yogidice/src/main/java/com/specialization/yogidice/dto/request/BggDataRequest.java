package com.specialization.yogidice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BggDataRequest {
    @NotNull
    private Long bbgCode;

    @NotNull
    private String nickName;

    @NotNull
    private double ratingUser;
}
