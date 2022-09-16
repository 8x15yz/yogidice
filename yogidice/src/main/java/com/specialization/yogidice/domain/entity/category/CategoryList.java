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
public class CategoryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_list_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private BoardGame boardGame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id")
    private Category category;

    public static CategoryList create(BoardGame boardGame, Category category) {
        CategoryList categoryList = new CategoryList();
        categoryList.boardGame = boardGame;
        categoryList.category = category;
        return categoryList;
    }

    public void update(Category category) {
        this.category = category;
    }
}
