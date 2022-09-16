package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {
    private Long Id;

    private Integer rating;

    private String review;

    private LocalDateTime createAt;

    private String nickName;

    private String gameTitle;

    public static HistoryResponse response(History history) {
        return new HistoryResponse(
                history.getId(),
                history.getRating(),
                history.getReview(),
                history.getCreateAt(),
                history.getUser().getNickName(),
                history.getBoardGame().getTitleKr()
        );
    }
}
