package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.BoardgameCafe;
import com.specialization.yogidice.domain.entity.CafeGamelist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeGamelistRepository extends JpaRepository<CafeGamelist, Integer> {
    List<CafeGamelist> findCafeGamelistsByBoardgameCafe(BoardgameCafe boardgameCafe);

    List<CafeGamelist> findCafeGamelistsByBoardGame(BoardGame boardGame);
}
