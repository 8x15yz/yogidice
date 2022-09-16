package com.specialization.yogidice.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Boardgame_cafe")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardgameCafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cafe_id")
    private Integer id;

    @Column(length = 100)
    @NotNull
    private String name;

    @Column(length = 200)
    @NotNull
    private String address;

    @OneToMany(mappedBy = "boardgamecafe" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CafeGamelist> cafeGamelists = new ArrayList<CafeGamelist>();
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
