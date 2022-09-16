package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private Long userId;

    private String nickName;

    private Long gameId;

    private String gameTitle;

    public static BookmarkResponse response(Bookmark bookmark) {
        return new BookmarkResponse(
                bookmark.getUser().getId(),
                bookmark.getUser().getNickName(),
                bookmark.getBoardGame().getId(),
                bookmark.getBoardGame().getTitleKr()
        );
    }
}
