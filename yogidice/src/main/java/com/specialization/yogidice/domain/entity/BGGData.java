package com.specialization.yogidice.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BGGData")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BGGData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer rating;
}