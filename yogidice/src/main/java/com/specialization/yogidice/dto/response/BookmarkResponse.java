package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.Bookmark;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BookmarkResponse")
public class BookmarkResponse extends BaseResponseBody {
    private Long id;

    private Long userId;

    private String nickName;

    private Long gameId;

    private String gameTitle;

    public static BookmarkResponse response(Bookmark bookmark) {
        return new BookmarkResponse(
                bookmark.getId(),
                bookmark.getUser().getId(),
                bookmark.getUser().getNickName(),
                bookmark.getBoardGame().getId(),
                bookmark.getBoardGame().getTitleKr()
        );
    }

    public static BookmarkResponse of(Integer statusCode, String message, Long bookmarkId) {
        BookmarkResponse response = new BookmarkResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(bookmarkId);
        return response;
    }
}
