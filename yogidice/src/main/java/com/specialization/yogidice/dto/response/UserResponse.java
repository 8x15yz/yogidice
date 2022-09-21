package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.domain.entity.type.Reviewed;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("UserResponse")
public class UserResponse extends BaseResponseBody {
    private Long id;
    private String kakaoId;
    private String nickName;
    private Reviewed reviewed;
    private List<HistoryResponse> historyResponses;
    private List<BookmarkResponse> bookmarkResponses;

    public static UserResponse response(
            User user,
            List<HistoryResponse> historys,
            List<BookmarkResponse> bookmarks
    ) {
        return new UserResponse(
                user.getId(),
                user.getKakaoId(),
                user.getNickName(),
                user.getReviewed(),
                historys,
                bookmarks
        );
    }

    public static UserResponse of(Integer statusCode, String message, UserResponse userResponse) {
        UserResponse response = new UserResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(userResponse.getId());
        response.setKakaoId(userResponse.getKakaoId());
        response.setNickName(userResponse.getNickName());
        response.setReviewed(userResponse.getReviewed());
        response.setHistoryResponses(userResponse.getHistoryResponses());
        response.setBookmarkResponses(userResponse.getBookmarkResponses());
        return response;
    }

    public static UserResponse of(Integer statusCode, String message, Reviewed reviewed) {
        UserResponse response = new UserResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setReviewed(reviewed);
        return response;
    }
}
