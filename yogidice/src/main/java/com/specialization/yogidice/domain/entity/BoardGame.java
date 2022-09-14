package com.specialization.yogidice.domain.entity;

import com.specialization.yogidice.domain.entity.category.Category;
import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.domain.entity.category.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class BoardGame {
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

    @OneToMany(mappedBy = "boardgame")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame")
    private List<Type> types = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame")
    private List<Mechanism> mechanisms = new ArrayList<>();

    public static BoardGame create(String titleKr, String titleEng, int publishYear, String thumbURL, double rating, int players, String playingTime, double difficulty, String youtubeURL, String contents, String contentsImgURL) {
        BoardGame boardGame = new BoardGame();
        boardGame.titleKr = titleKr;
        boardGame.titleEng = titleEng;
        boardGame.publishYear = publishYear;
        boardGame.thumbURL = thumbURL;
        boardGame.rating = rating;
        boardGame.players = players;
        boardGame.playingTime = playingTime;
        boardGame.difficulty = difficulty;
        boardGame.youtubeURL = youtubeURL;
        boardGame.contents = contents;
        boardGame.contentsImgURL = contentsImgURL;
        return boardGame;
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
