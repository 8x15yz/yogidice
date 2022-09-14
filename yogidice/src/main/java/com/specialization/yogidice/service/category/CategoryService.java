package com.specialization.yogidice.service.category;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.category.Category;
import com.specialization.yogidice.domain.repository.category.CategoryRepository;
import com.specialization.yogidice.dto.request.category.CategoryRequest;
import com.specialization.yogidice.dto.response.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.CATEGORY_LIST_NOT_FOUND;
import static com.specialization.yogidice.common.exception.NotFoundException.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createCategory(CategoryRequest request) {
        if (categoryRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateException(String.format("%s는 이미 등록된 보드게임 테마입니다.", request.getName()));
        }
        Category saveCategory = Category.create(
                request.getName()
        );
        return categoryRepository.save(saveCategory).getId();
    }

    @Transactional
    public List<CategoryResponse> readCategoryList() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty() || categories == null) {
            throw new NotFoundException(CATEGORY_LIST_NOT_FOUND);
        }
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(CategoryResponse.response(category));
        }
        return categoryResponses;
    }

    @Transactional
    public CategoryResponse readCategory(Long cateId) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        return CategoryResponse.response(category);
    }

    @Transactional
    public void updateCategory(Long cateId, CategoryRequest request) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        category.update(
                request.getName()
        );
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long cateId) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
    }
}
