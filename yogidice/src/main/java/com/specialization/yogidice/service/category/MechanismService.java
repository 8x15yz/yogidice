package com.specialization.yogidice.service.category;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.category.Mechanism;
import com.specialization.yogidice.domain.repository.category.MechanismRepository;
import com.specialization.yogidice.dto.request.category.MechanismRequest;
import com.specialization.yogidice.dto.response.category.MechanismResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.MECHANISM_LIST_NOT_FOUND;
import static com.specialization.yogidice.common.exception.NotFoundException.MECHANISM_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MechanismService {
    private final MechanismRepository mechanismRepository;

    @Transactional
    public Long createMechanism(MechanismRequest request) {
        if (mechanismRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicateException(String.format("%s는 이미 등록된 보드게임 진행방식입니다.", request.getName()));
        }
        Mechanism saveMechanism = Mechanism.create(
                request.getName(),
                request.getParentsMec()
        );
        return mechanismRepository.save(saveMechanism).getId();
    }

    @Transactional
    public List<MechanismResponse> readMechanismList() {
        List<Mechanism> mechanisms = mechanismRepository.findAll();
        if (mechanisms.isEmpty() || mechanisms == null) {
            throw new NotFoundException(MECHANISM_LIST_NOT_FOUND);
        }
        List<MechanismResponse> mechanismResponses = new ArrayList<>();
        for (Mechanism mechanism : mechanisms) {
            mechanismResponses.add(MechanismResponse.response(mechanism));
        }
        return mechanismResponses;
    }

    @Transactional
    public MechanismResponse readMechanism(Long mechanismId) {
        Mechanism mechanism = mechanismRepository.findById(mechanismId)
                .orElseThrow(() -> new NotFoundException(MECHANISM_NOT_FOUND));
        return MechanismResponse.response(mechanism);
    }

    @Transactional
    public void updateMechanism(Long mechanismId, MechanismRequest request) {
        Mechanism mechanism = mechanismRepository.findById(mechanismId)
                .orElseThrow(() -> new NotFoundException(MECHANISM_NOT_FOUND));
        mechanism.update(
                request.getName(),
                request.getParentsMec()
        );
        mechanismRepository.save(mechanism);
    }

    @Transactional
    public void deleteMechanism(Long mechanismId) {
        Mechanism mechanism = mechanismRepository.findById(mechanismId)
                .orElseThrow(() -> new NotFoundException(MECHANISM_NOT_FOUND));
        mechanismRepository.delete(mechanism);
    }
}
