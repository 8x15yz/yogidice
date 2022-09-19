package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.dto.response.category.CategoryListResponse;
import com.specialization.yogidice.dto.response.category.MechanismListResponse;
import com.specialization.yogidice.dto.response.category.TypeListResponse;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BoardGameResponse")
public class BoardGameResponse extends BaseResponseBody {
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

    public static BoardGameResponse of(Integer statusCode, String message, BoardGameResponse boardGameResponse) {
        BoardGameResponse response = new BoardGameResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(boardGameResponse.getId());
        response.setTitleKr(boardGameResponse.getTitleKr());
        response.setTitleEng(boardGameResponse.getTitleEng());
        response.setPublishYear(boardGameResponse.getPublishYear());
        response.setThumbURL(boardGameResponse.getThumbURL());
        response.setRating(boardGameResponse.getRating());
        response.setMinPlayers(boardGameResponse.getMinPlayers());
        response.setMaxPlayers(boardGameResponse.getMaxPlayers());
        response.setPlayingTime(boardGameResponse.getPlayingTime());
        response.setDifficulty(boardGameResponse.getDifficulty());
        response.setYoutubeURL(boardGameResponse.getYoutubeURL());
        response.setContents(boardGameResponse.getContents());
        response.setContentsImgURL(boardGameResponse.getContentsImgURL());
        response.setCategoryListResponses(boardGameResponse.getCategoryListResponses());
        response.setTypeListResponses(boardGameResponse.getTypeListResponses());
        response.setMechanismListResponses(boardGameResponse.getMechanismListResponses());
        return response;
    }
}
