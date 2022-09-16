package com.specialization.yogidice.domain.repository.category;

import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.category.Category;
import com.specialization.yogidice.domain.entity.category.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryListRepository extends JpaRepository<CategoryList, Long> {
    List<CategoryList> findByBoardGame(BoardGame boardGame);
    List<CategoryList> findByCategory(Category category);
    Optional<CategoryList> findByBoardGameAndCategory(BoardGame boardGame, Category category);
}
