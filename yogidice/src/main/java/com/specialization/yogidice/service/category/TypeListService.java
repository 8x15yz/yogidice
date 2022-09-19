package com.specialization.yogidice.service.category;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.category.Type;
import com.specialization.yogidice.domain.entity.category.TypeList;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.category.TypeListRepository;
import com.specialization.yogidice.domain.repository.category.TypeRepository;
import com.specialization.yogidice.dto.request.category.TypeListCreateRequest;
import com.specialization.yogidice.dto.request.category.TypeListUpdateRequest;
import com.specialization.yogidice.dto.response.category.TypeListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TypeListService {
    private final BoardGameRepository boardGameRepository;
    private final TypeRepository typeRepository;
    private final TypeListRepository typeListRepository;

    @Transactional
    public Long createTypeList(TypeListCreateRequest request) {
        BoardGame boardGame = boardGameRepository.findById(request.getGameId())
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        Type type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new NotFoundException(TYPE_NOT_FOUND));
        if (typeListRepository.findByBoardGameAndType(boardGame, type).isPresent())
            throw new DuplicateException(String.format("%s 은/는 이미 %s에 등록된 유형입니다.", type.getName(), boardGame.getTitleKr()));
        TypeList saveTypeList = TypeList.create(boardGame, type);
        return typeListRepository.save(saveTypeList).getId();
    }

    @Transactional
    public List<TypeListResponse> readTypeListOfBoardGame(Long gameId) {
        BoardGame boardGame = boardGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<TypeList> typeLists = typeListRepository.findByBoardGame(boardGame);
        if (typeLists.isEmpty()) {
            throw new NotFoundException(TYPE_LIST_NOT_FOUND);
        }
        List<TypeListResponse> responses = new ArrayList<>();
        for (TypeList typeList : typeLists) {
            responses.add(TypeListResponse.response(typeList));
        }
        return responses;
    }

    @Transactional
    public List<TypeListResponse> readTypeListOfType(Long typeId) {
        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new NotFoundException(TYPE_NOT_FOUND));
        List<TypeList> typeLists = typeListRepository.findByType(type);
        if (typeLists.isEmpty()) {
            throw new NotFoundException(TYPE_LIST_NOT_FOUND);
        }
        List<TypeListResponse> responses = new ArrayList<>();
        for (TypeList typeList : typeLists) {
            responses.add(TypeListResponse.response(typeList));
        }
        return responses;
    }

    @Transactional
    public void updateTypeList(Long typeListId, TypeListUpdateRequest request) {
        TypeList typeList = typeListRepository.findById(typeListId)
                .orElseThrow(() -> new NotFoundException(TYPE_LIST_NOT_FOUND));
        Type type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new NotFoundException(TYPE_NOT_FOUND));
        typeList.update(type);
        typeListRepository.save(typeList);
    }

    @Transactional
    public void deleteTypeList(Long typeListId) {
        TypeList typeList = typeListRepository.findById(typeListId)
                .orElseThrow(() -> new NotFoundException(TYPE_LIST_NOT_FOUND));
        typeListRepository.delete(typeList);
    }
}
