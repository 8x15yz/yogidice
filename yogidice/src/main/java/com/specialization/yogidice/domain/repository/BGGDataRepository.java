package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.BGGData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BGGDataRepository extends JpaRepository<BGGData, Integer>{
    List<BGGData> findBGGDataById(Integer id);
}
