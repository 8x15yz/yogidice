package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.NumOfReview;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("NumOfReviewResponse")
public class NumOfReviewResponse extends BaseResponseBody {
    private Long id;

    private Long gameId;

    private String gameTitle;

    private int number;

    public static NumOfReviewResponse response(NumOfReview numOfReview) {
        return new NumOfReviewResponse(
                numOfReview.getId(),
                numOfReview.getBoardGame().getId(),
                numOfReview.getBoardGame().getTitleKr(),
                numOfReview.getNumber()
        );
    }

    public static NumOfReviewResponse of(Integer statusCode, String message, Long numReviewId) {
        NumOfReviewResponse response = new NumOfReviewResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(numReviewId);
        return response;
    }
}
