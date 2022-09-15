package com.specialization.yogidice.service.category;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.category.Category;
import com.specialization.yogidice.domain.entity.category.CategoryList;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.category.CategoryListRepository;
import com.specialization.yogidice.domain.repository.category.CategoryRepository;
import com.specialization.yogidice.dto.request.category.CategoryListCreateRequest;
import com.specialization.yogidice.dto.request.category.CategoryListUpdateRequest;
import com.specialization.yogidice.dto.response.category.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryListService {
    private final BoardGameRepository boardGameRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryListRepository categoryListRepository;

    @Transactional
    public Long createCategoryList(CategoryListCreateRequest request) {
        BoardGame boardGame = boardGameRepository.findById(request.getGameId())
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        if (categoryListRepository.findByBoardGameAndCategory(boardGame, category).isPresent())
            throw new DuplicateException(String.format("%s 은/는 이미 %s에 등록된 카테고리(테마)입니다.", category.getName(), boardGame.getTitleKr()));
        CategoryList saveCategoryList = CategoryList.create(boardGame, category);
        return categoryListRepository.save(saveCategoryList).getId();
    }

    @Transactional
    public List<CategoryListResponse> readCategoryListOfBoardGame(Long gameId) {
        BoardGame boardGame = boardGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<CategoryList> categoryLists = categoryListRepository.findByBoardGame(boardGame);
        if (categoryLists.isEmpty()) {
            throw new NotFoundException(CATEGORY_LIST_NOT_FOUND);
        }
        List<CategoryListResponse> responses = new ArrayList<>();
        for (CategoryList categoryList : categoryLists) {
            responses.add(CategoryListResponse.response(categoryList));
        }
        return responses;
    }

    @Transactional
    public List<CategoryListResponse> readCategoryListOfCategory(Long cateId) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        List<CategoryList> categoryLists = categoryListRepository.findByCategory(category);
        if (categoryLists.isEmpty()) {
            throw new NotFoundException(CATEGORY_LIST_NOT_FOUND);
        }
        List<CategoryListResponse> responses = new ArrayList<>();
        for (CategoryList categoryList : categoryLists) {
            responses.add(CategoryListResponse.response(categoryList));
        }
        return responses;
    }

    @Transactional
    public void updateCategoryList(Long cateListId, CategoryListUpdateRequest request) {
        CategoryList categoryList = categoryListRepository.findById(cateListId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_LIST_NOT_FOUND));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        categoryList.update(category);
        categoryListRepository.save(categoryList);
    }

    @Transactional
    public void deleteCategoryList(Long cateListId) {
        CategoryList categoryList = categoryListRepository.findById(cateListId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_LIST_NOT_FOUND));
        categoryListRepository.delete(categoryList);
    }
}
