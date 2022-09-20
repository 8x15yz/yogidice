package com.specialization.yogidice.service;

import com.specialization.yogidice.common.exception.DuplicateException;
import com.specialization.yogidice.common.exception.NotFoundException;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.Bookmark;
import com.specialization.yogidice.domain.entity.User;
import com.specialization.yogidice.domain.repository.BoardGameRepository;
import com.specialization.yogidice.domain.repository.BookmarkRepository;
import com.specialization.yogidice.domain.repository.UserRepository;
import com.specialization.yogidice.dto.request.BookmarkRequest;
import com.specialization.yogidice.dto.response.BookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.specialization.yogidice.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {
    private final UserRepository userRepository;
    private final BoardGameRepository boardGameRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public Long createBookmark(Long userId, BookmarkRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        BoardGame boardGame = boardGameRepository.findById(request.getGameId())
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        if (bookmarkRepository.findByUserAndBoardGame(user, boardGame).isPresent()) {
            throw new DuplicateException(String.format("%s 은/는 이미 북마크에 등록된 보드게임입니다.", boardGame.getTitleKr()));
        }
        Bookmark saveBookmark = Bookmark.create(user,boardGame);
        return bookmarkRepository.save(saveBookmark).getId();
    }

    @Transactional
    public List<BookmarkResponse> readBookmarkListOfUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<Bookmark> bookmarks = bookmarkRepository.findByUser(user);
        if (bookmarks.isEmpty()) {
            throw new NotFoundException((BOOKMARK_LIST_NOT_FOUND));
        }
        List<BookmarkResponse> responses = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            responses.add(BookmarkResponse.response(bookmark));
        }
        return responses;
    }

    @Transactional
    public List<BookmarkResponse> readBookmarkListOfBoardGame(Long gameId) {
        BoardGame boardGame = boardGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(BOARDGAME_NOT_FOUND));
        List<Bookmark> bookmarks = bookmarkRepository.findByBoardGame(boardGame);
        if (bookmarks.isEmpty()) {
            throw new NotFoundException((BOOKMARK_LIST_NOT_FOUND));
        }
        List<BookmarkResponse> responses = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            responses.add(BookmarkResponse.response(bookmark));
        }
        return responses;
    }

    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new NotFoundException(BOOKMARK_NOT_FOUND));
        bookmarkRepository.delete(bookmark);
    }
}
