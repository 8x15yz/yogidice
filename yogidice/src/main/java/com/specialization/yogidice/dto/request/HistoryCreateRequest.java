package com.specialization.yogidice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCreateRequest {
    @NotNull
    private Integer rating;

    private String review;

    @NotNull
    private Long userId;

    @NotNull
    private Long gameId;
}
