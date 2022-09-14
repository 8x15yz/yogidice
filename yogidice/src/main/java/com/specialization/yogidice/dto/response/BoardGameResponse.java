package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.BoardGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameResponse {
    private long id;

    private String titleKr;

    private String titleEng;

    private int publishYear;

    private String thumbURL;

    private double rating;

    private int players;

    private String playingTime;

    private double difficulty;

    private String youtubeURL;

    private String contents;

    private String contentsImgURL;

    public static BoardGameResponse response(BoardGame boardGame) {
        return new BoardGameResponse(boardGame.getId(), boardGame.getTitleKr(), boardGame.getTitleEng(), boardGame.getPublishYear(), boardGame.getThumbURL(), boardGame.getRating(), boardGame.getPlayers(), boardGame.getPlayingTime(), boardGame.getDifficulty(), boardGame.getYoutubeURL(), boardGame.getContents(), boardGame.getContentsImgURL());
    }
}
