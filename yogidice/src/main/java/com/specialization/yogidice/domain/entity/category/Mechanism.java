package com.specialization.yogidice.domain.entity.category;

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
    @Column(name = "mechanism_id")
    private byte id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private int parentsMec;

    @OneToMany(mappedBy = "mechanism", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MechanismGroup> mechanismGroups = new ArrayList<>();

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
