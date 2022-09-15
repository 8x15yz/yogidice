package com.specialization.yogidice.domain.entity.category;

import com.specialization.yogidice.domain.entity.BoardGame;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MechanismList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanism_list_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private BoardGame boardGame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanism_id")
    private Mechanism mechanism;

    public static MechanismList create(BoardGame boardGame, Mechanism mechanism) {
        MechanismList categoryList = new MechanismList();
        categoryList.boardGame = boardGame;
        categoryList.mechanism = mechanism;
        return categoryList;
    }

    public void update(Mechanism mechanism) {
        this.mechanism = mechanism;
    }
}
