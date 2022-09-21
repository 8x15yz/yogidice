package com.specialization.yogidice.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class BggData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bgg_data_id")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String titleEng;

    @NotNull
    private long bggCode;

    @NotNull
    @Column(length = 50)
    private String nickName;

    @NotNull
    private double ratingUser;

    public static BggData create(String titleEng, Long bggCode, String nickName, double ratingUser) {
        BggData bggData = new BggData();
        bggData.titleEng = titleEng;
        bggData.bggCode = bggCode;
        bggData.nickName = nickName;
        bggData.ratingUser = ratingUser;
        return bggData;
    }

    public void update(String titleEng, Long bggCode, String nickName, double ratingUser) {
        this.titleEng = titleEng;
        this.bggCode = bggCode;
        this.nickName = nickName;
        this.ratingUser = ratingUser;
    }
}