package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.domain.entity.type.Reviewed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userResponse {
    private String kakaoId;
    private String nickName;
    private Reviewed reviewed;
    private List<HistoryResponse> historyResponses;
    private List<BookmarkResponse> bookmarkResponses;


    public static userResponse response(
            User user,
            List<HistoryResponse> historys,
            List<BookmarkResponse> bookmarks
    ) {
        return new userResponse(
                user.getKakaoId(),
                user.getNickName(),
                user.getReviewed(),
                historys,
                bookmarks
        );
    }
}
