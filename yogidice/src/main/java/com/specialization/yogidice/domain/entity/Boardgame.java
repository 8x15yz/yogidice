package com.specialization.yogidice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Boardgame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private long id;

    @NotNull
    @Column(length = 100)
    private String titleKr;

    @NotNull
    @Column(length = 100)
    private String titleEng;

    @NotNull
    private int publishYear;

    @NotNull
    @Column(length = 200)
    private String thumbURL;

    @NotNull
    private double rating;

    // mysql data type => tinyint
    private int players;

    @Column(length = 50)
    private String playingTime;

    private double difficulty;

    @Column(length = 200)
    private String youtubeURL;

    @Column(length = 500)
    private String contents;

    @Column(length = 200)
    private String contentsImgURL;

    public static Boardgame create(String titleKr, String titleEng, int publishYear, String thumbURL, double rating, int players, String playingTime, double difficulty, String youtubeURL, String contents, String contentsImgURL) {
        Boardgame boardgame = new Boardgame();
        boardgame.titleKr = titleKr;
        boardgame.titleEng = titleEng;
        boardgame.publishYear = publishYear;
        boardgame.thumbURL = thumbURL;
        boardgame.rating = rating;
        boardgame.players = players;
        boardgame.playingTime = playingTime;
        boardgame.difficulty = difficulty;
        boardgame.youtubeURL = youtubeURL;
        boardgame.contents = contents;
        boardgame.contentsImgURL = contentsImgURL;
        return boardgame;
    }

    public void update(String titleKr, String titleEng, int publishYear, String thumbURL, double rating, int players, String playingTime, double difficulty, String youtubeURL, String contents, String contentsImgURL) {
        this.titleKr = titleKr;
        this.titleEng = titleEng;
        this.publishYear = publishYear;
        this.thumbURL = thumbURL;
        this.rating = rating;
        this.players = players;
        this.playingTime = playingTime;
        this.difficulty = difficulty;
        this.youtubeURL = youtubeURL;
        this.contents = contents;
        this.contentsImgURL = contentsImgURL;
    }
}
