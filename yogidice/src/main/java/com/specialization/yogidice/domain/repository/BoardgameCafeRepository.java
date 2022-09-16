package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.BoardgameCafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardgameCafeRepository extends JpaRepository<BoardgameCafe, Integer> {

    Optional<BoardgameCafe> findBoardgameCafeByName(String name);

    Optional<BoardgameCafe> findBoardgameCafeByNameAndAddress(String name, String address);
}
