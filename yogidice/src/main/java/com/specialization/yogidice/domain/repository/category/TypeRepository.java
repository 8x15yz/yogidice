package com.specialization.yogidice.domain.repository.category;

import com.specialization.yogidice.domain.entity.category.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String name);
}
