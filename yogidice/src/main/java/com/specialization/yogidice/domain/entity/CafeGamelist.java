package com.specialization.yogidice.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Cafe_gamelist")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeGamelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gamelist_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "cafe_id")
    private BoardgameCafe boardgameCafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "game_id")
    private BoardGame boardGame;

    public static CafeGamelist create(BoardgameCafe boardgameCafe, BoardGame boardgame) {
        CafeGamelist cafeGamelist = new CafeGamelist();
        cafeGamelist.boardgameCafe = boardgameCafe;
        cafeGamelist.boardGame = boardgame;
        return cafeGamelist;
    }

}
