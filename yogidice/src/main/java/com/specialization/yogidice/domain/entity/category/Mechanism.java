package com.specialization.yogidice.domain.entity.category;

import com.specialization.yogidice.domain.entity.BoardGame;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Mechanism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private int parentsMec;

    @OneToMany(mappedBy = "mechanism")
    private List<BoardGame> boardGames = new ArrayList<>();

    public static Mechanism create(String name, int parentsMec) {
        Mechanism mechanism = new Mechanism();
        mechanism.name = name;
        mechanism.parentsMec = parentsMec;
        return mechanism;
    }

    public void update(String name, int parentsMec) {
        this.name = name;
        this.parentsMec = parentsMec;
    }
}
