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
public class BookmarkResponse {
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
}
