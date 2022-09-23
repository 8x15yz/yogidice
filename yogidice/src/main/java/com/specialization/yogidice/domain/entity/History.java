package com.specialization.yogidice.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    private double rating;

    @Column(length = 1000)
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private BoardGame boardGame;

    public static History create(double rating, String review, User user, BoardGame boardGame) {
        History history = new History();
        history.rating = rating;
        history.review = review;
        history.user = user;
        history.boardGame = boardGame;
        return history;
    }

    public void update(double rating, String review) {
        this.rating = rating;
        this.review = review;
    }
}
