package com.specialization.yogidice.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Integer id;

    @OneToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(referencedColumnName = "game_id")
    private BoardGame boardGame;

    @Builder
    public Bookmark(User user, BoardGame boardGame) {
        this.user = user;
        this.boardGame = boardGame;
    }

    public static Bookmark create(User user, BoardGame boardGame){
        Bookmark bookmark = new Bookmark();
        bookmark.user = user;
        bookmark.boardGame = boardGame;
        return bookmark;
    }
}
