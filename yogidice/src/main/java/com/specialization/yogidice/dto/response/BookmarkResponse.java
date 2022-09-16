package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private String nickName;

    private String gameTitle;

    public static BookmarkResponse response(Bookmark bookmark) {
        return new BookmarkResponse(
                bookmark.getUser().getNickName(),
                bookmark.getBoardGame().getTitleKr()
        );
    }
}
