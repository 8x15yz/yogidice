package com.specialization.yogidice.domain.repository;

import com.specialization.yogidice.domain.entity.Bookmark;
import com.specialization.yogidice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findBookmarksByUser(User user);
}
