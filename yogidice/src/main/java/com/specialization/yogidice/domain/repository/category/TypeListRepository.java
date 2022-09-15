package com.specialization.yogidice.domain.repository.category;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.category.Type;
import com.specialization.yogidice.domain.entity.category.TypeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeListRepository extends JpaRepository<TypeList, Long> {
    List<TypeList> findByBoardGame(BoardGame boardGame);
    List<TypeList> findByType(Type type);
    Optional<TypeList> findByBoardGameAndType(BoardGame boardGame, Type type);
}
