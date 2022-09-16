package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.dto.response.category.CategoryListResponse;
import com.specialization.yogidice.dto.response.category.MechanismListResponse;
import com.specialization.yogidice.dto.response.category.TypeListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private int minPlayers;

    private int maxPlayers;

    private String playingTime;

    private double difficulty;

    private String youtubeURL;

    private String contents;

    private String contentsImgURL;

    private List<CategoryListResponse> categoryListResponses;

    private List<TypeListResponse> typeListResponses;

    private List<MechanismListResponse> mechanismListResponses;

    public static BoardGameResponse response(
            BoardGame boardGame,
            List<CategoryListResponse> categoryLists,
            List<TypeListResponse> typeLists,
            List<MechanismListResponse> mechanismLists
            ) {
        return new BoardGameResponse(
                boardGame.getId(),
                boardGame.getTitleKr(),
                boardGame.getTitleEng(),
                boardGame.getPublishYear(),
                boardGame.getThumbURL(),
                boardGame.getRating(),
                boardGame.getMinPlayers(),
                boardGame.getMaxPlayers(),
                boardGame.getPlayingTime(),
                boardGame.getDifficulty(),
                boardGame.getYoutubeURL(),
                boardGame.getContents(),
                boardGame.getContentsImgURL(),
                categoryLists,
                typeLists,
                mechanismLists
        );
    }
}
