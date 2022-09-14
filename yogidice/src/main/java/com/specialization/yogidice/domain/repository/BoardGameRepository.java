package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    Optional<BoardGame> findByTitleKr(String titleKr);
}
