package com.specialization.yogidice.dto.response;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.dto.response.category.CategoryGroupResponse;
import com.specialization.yogidice.dto.response.category.MechanismGroupResponse;
import com.specialization.yogidice.dto.response.category.TypeGroupResponse;
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

    private double ratingBl;

    private double ratingUser;

    private Long bggCode;

    private int minPlayers;

    private int maxPlayers;

    private String playingTime;

    private String minTime;

    private String maxTime;

    private int age;

    private double difficulty;

    private String youtubeURL;

    private String contents;

    private String contentsImgURL;

    private List<CategoryGroupResponse> categoryGroupResponses;

    private List<TypeGroupResponse> typeGroupResponses;

    private List<MechanismGroupResponse> mechanismGroupResponses;

    public static BoardGameResponse response(
            BoardGame boardGame,
            List<CategoryGroupResponse> categoryLists,
            List<TypeGroupResponse> typeLists,
            List<MechanismGroupResponse> mechanismLists
    ) {
        return new BoardGameResponse(
                boardGame.getId(),
                boardGame.getTitleKr(),
                boardGame.getTitleEng(),
                boardGame.getPublishYear(),
                boardGame.getThumbURL(),
                boardGame.getRatingBl(),
                boardGame.getRatingUser(),
                boardGame.getBggCode(),
                boardGame.getMinPlayers(),
                boardGame.getMaxPlayers(),
                boardGame.getPlayingTime(),
                boardGame.getMinTime(),
                boardGame.getMaxTime(),
                boardGame.getAge(),
                boardGame.getDifficulty(),
                boardGame.getYoutubeURL(),
                boardGame.getContents(),
                boardGame.getContentsImgURL(),
                categoryLists,
                typeLists,
                mechanismLists
        );
    }

    public static BoardGameResponse of(Integer statusCode, String message, Long gameId) {
        BoardGameResponse response = new BoardGameResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setId(gameId);
        return response;
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
        response.setRatingBl(boardGameResponse.getRatingBl());
        response.setRatingUser(boardGameResponse.getRatingUser());
        response.setBggCode(boardGameResponse.getBggCode());
        response.setMinPlayers(boardGameResponse.getMinPlayers());
        response.setMaxPlayers(boardGameResponse.getMaxPlayers());
        response.setPlayingTime(boardGameResponse.getPlayingTime());
        response.setMinTime(boardGameResponse.getMinTime());
        response.setMaxTime(boardGameResponse.getMaxTime());
        response.setAge(boardGameResponse.getAge());
        response.setDifficulty(boardGameResponse.getDifficulty());
        response.setYoutubeURL(boardGameResponse.getYoutubeURL());
        response.setContents(boardGameResponse.getContents());
        response.setContentsImgURL(boardGameResponse.getContentsImgURL());
        response.setCategoryGroupResponses(boardGameResponse.getCategoryGroupResponses());
        response.setTypeGroupResponses(boardGameResponse.getTypeGroupResponses());
        response.setMechanismGroupResponses(boardGameResponse.getMechanismGroupResponses());
        return response;
    }
}
