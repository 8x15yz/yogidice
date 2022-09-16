package com.specialization.yogidice.domain.entity;

import com.specialization.yogidice.common.util.ReviewedType;
import com.specialization.yogidice.common.util.RoleType;
import lombok.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="history_id")
    private Integer id;

    @NotNull
    private Integer rating;

    @Column(length = 1000)
    private String review;

    @Generated(GenerationTime.INSERT)
    private LocalDateTime create_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "game_id")
    private BoardGame boardGame;

    @Builder
    public History( Integer rating, String review, LocalDateTime create_at, User user, BoardGame boardGame) {
        this.rating = rating;
        this.review = review;
        this.create_at = create_at;
        this.user = user;
        this.boardGame = boardGame;
    }

    public static History create(Integer rating, String review, LocalDateTime create_at, User user, BoardGame boardGame) {
        History history = new History();
        history.rating = rating;
        history.review = review;
        history.user = user;
        history.boardGame = boardGame;
        return history;
    }

    public void update(Integer rating, String review, LocalDateTime create_at, User user, BoardGame boardGame) {
        this.rating = rating;
        this.review = review;
        this.user = user;
        this.boardGame = boardGame;
    }
}
