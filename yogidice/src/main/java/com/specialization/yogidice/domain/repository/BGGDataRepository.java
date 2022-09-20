package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.BGGData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BGGDataRepository extends JpaRepository<BGGData, Long> {
    List<BGGData> findBGGDataById(Integer id);
}
