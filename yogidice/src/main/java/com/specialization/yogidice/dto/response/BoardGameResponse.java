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
    private Long id;

    private String titleKr;

    private String titleEng;

    private int publishYear;

    private String thumbUrl;

    private double ratingBl;

    private double ratingUser;

    private Long bggCode;

    private Byte minPlayers;

    private Byte maxPlayers;

    private String playingTime;

    private String minTime;

    private String maxTime;

    private int age;

    private double difficulty;

    private String youtubeUrl;

    private String contents;

    private String contentsImgUrl;

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
                boardGame.getThumbUrl(),
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
                boardGame.getYoutubeUrl(),
                boardGame.getContents(),
                boardGame.getContentsImgUrl(),
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
        response.setThumbUrl(boardGameResponse.getThumbUrl());
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
        response.setYoutubeUrl(boardGameResponse.getYoutubeUrl());
        response.setContents(boardGameResponse.getContents());
        response.setContentsImgUrl(boardGameResponse.getContentsImgUrl());
        response.setCategoryGroupResponses(boardGameResponse.getCategoryGroupResponses());
        response.setTypeGroupResponses(boardGameResponse.getTypeGroupResponses());
        response.setMechanismGroupResponses(boardGameResponse.getMechanismGroupResponses());
        return response;
    }
}
