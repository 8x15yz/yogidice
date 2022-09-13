package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
}
