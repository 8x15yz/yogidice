package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.History;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("HistoryResponse")
public class HistoryResponse extends BaseResponseBody {
    private Long Id;

    private double rating;

    private String review;

    private LocalDateTime createDate;

    private Long userId;

    private String nickName;

    private Long gameId;

    private String gameTitle;

    public static HistoryResponse response(History history) {
        return new HistoryResponse(
                history.getId(),
                history.getRating(),
                history.getReview(),
                history.getCreatedDate(),
                history.getUser().getId(),
                history.getUser().getNickName(),
                history.getBoardGame().getId(),
                history.getBoardGame().getTitleKr()
        );
    }

    public static HistoryResponse of(Integer statusCode, String message, Long historyId) {
        HistoryResponse response = new HistoryResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(historyId);
        return response;
    }
}
