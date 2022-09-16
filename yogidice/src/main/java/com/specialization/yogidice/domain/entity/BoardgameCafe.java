package com.specialization.yogidice.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Boardgame_cafe")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class BoardgameCafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cafe_id")
    private Long id;

    @Column(length = 100)
    @NotNull
    private String name;

    @Column(length = 200)
    @NotNull
    private String address;

    @Builder
    public BoardgameCafe( String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static BoardgameCafe create(String name, String address) {
        BoardgameCafe boardgameCafe = new BoardgameCafe();
        boardgameCafe.name = name;
        boardgameCafe.address = address;
        return boardgameCafe;
    }

    public void update(String name, String address){
        this.name = name;
        this.address = address;
    }
}
