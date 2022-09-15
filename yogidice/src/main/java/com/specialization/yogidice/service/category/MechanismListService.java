package com.specialization.yogidice.service.category;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.domain.entity.category.MechanismList;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.category.MechanismListRepository;
import com.specialization.yogidice.domain.repository.category.MechanismRepository;
import com.specialization.yogidice.dto.request.category.MechanismListCreateRequest;
import com.specialization.yogidice.dto.request.category.MechanismListUpdateRequest;
import com.specialization.yogidice.dto.response.category.MechanismListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MechanismListService {
    private final BoardGameRepository boardGameRepository;
    private final MechanismRepository mechanismRepository;
    private final MechanismListRepository mechanismListRepository;

    @Transactional
    public Long createMechanismList(MechanismListCreateRequest request) {
        BoardGame boardGame = boardGameRepository.findById(request.getGameId())
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        Mechanism mechanism = mechanismRepository.findById(request.getMechanismId())
                .orElseThrow(() -> new NotFoundException(MECHANISM_NOT_FOUND));
        if (mechanismListRepository.findByBoardGameAndMechanism(boardGame, mechanism).isPresent())
            throw new DuplicateException(String.format("%s 은/는 이미 %s에 등록된 진행방식입니다.", mechanism.getName(), boardGame.getTitleKr()));
        MechanismList saveMechanismList = MechanismList.create(boardGame, mechanism);
        return mechanismListRepository.save(saveMechanismList).getId();
    }

    @Transactional
    public List<MechanismListResponse> readMechanismListOfBoardGame(Long gameId) {
        BoardGame boardGame = boardGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<MechanismList> mechanismLists = mechanismListRepository.findByBoardGame(boardGame);
        if (mechanismLists.isEmpty()) {
            throw new NotFoundException(MECHANISM_LIST_NOT_FOUND);
        }
        List<MechanismListResponse> responses = new ArrayList<>();
        for (MechanismList mechanismList : mechanismLists) {
            responses.add(MechanismListResponse.response(mechanismList));
        }
        return responses;
    }

    @Transactional
    public List<MechanismListResponse> readMechanismListOfMechanism(Long mechanismId) {
        Mechanism mechanism = mechanismRepository.findById(mechanismId)
                .orElseThrow(() -> new NotFoundException(MECHANISM_NOT_FOUND));
        List<MechanismList> mechanismLists = mechanismListRepository.findByMechanism(mechanism);
        if (mechanismLists.isEmpty()) {
            throw new NotFoundException(MECHANISM_LIST_NOT_FOUND);
        }
        List<MechanismListResponse> responses = new ArrayList<>();
        for (MechanismList mechanismList : mechanismLists) {
            responses.add(MechanismListResponse.response(mechanismList));
        }
        return responses;
    }

    @Transactional
    public void updateMechanismList(Long mechanismListId, MechanismListUpdateRequest request) {
        MechanismList mechanismList = mechanismListRepository.findById(mechanismListId)
                .orElseThrow(() -> new NotFoundException(MECHANISM_LIST_NOT_FOUND));
        Mechanism mechanism = mechanismRepository.findById(request.getMechanismId())
                .orElseThrow(() -> new NotFoundException(MECHANISM_NOT_FOUND));
        mechanismList.update(mechanism);
        mechanismListRepository.save(mechanismList);
    }

    @Transactional
    public void deleteMechanismList(Long mechanismListId) {
        MechanismList mechanismList = mechanismListRepository.findById(mechanismListId)
                .orElseThrow(() -> new NotFoundException(MECHANISM_LIST_NOT_FOUND));
        mechanismListRepository.delete(mechanismList);
    }
}
