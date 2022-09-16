package com.specialization.yogidice.domain.repository.category;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.domain.entity.category.MechanismList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MechanismListRepository extends JpaRepository<MechanismList, Long> {
    List<MechanismList> findByBoardGame(BoardGame boardGame);
    List<MechanismList> findByMechanism(Mechanism mechanism);
    Optional<MechanismList> findByBoardGameAndMechanism(BoardGame boardGame, Mechanism mechanism);
}
