package com.specialization.yogidice.domain.entity;

import lombok.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="history_id")
    private Long id;

    @NotNull
    private byte rating;

    @Column(length = 1000)
    private String review;

    @Generated(GenerationTime.INSERT)
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "game_id")
    private BoardGame boardGame;

    @Builder
    public History(byte rating, String review, User user, BoardGame boardGame) {
        this.rating = rating;
        this.review = review;
        this.user = user;
        this.boardGame = boardGame;
    }

    public static History create(byte rating, String review, User user, BoardGame boardGame) {
        History history = new History();
        history.rating = rating;
        history.review = review;
        history.user = user;
        history.boardGame = boardGame;
        return history;
    }

    public void update(byte rating, String review) {
        this.rating = rating;
        this.review = review;
    }
}
