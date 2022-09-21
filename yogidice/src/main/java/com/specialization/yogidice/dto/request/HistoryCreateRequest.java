package com.specialization.yogidice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCreateRequest {
    @NotNull
    private byte rating;

    private String review;

    @NotNull
    private Long gameId;
}
