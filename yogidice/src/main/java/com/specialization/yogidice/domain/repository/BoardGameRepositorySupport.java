package com.specialization.yogidice.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.specialization.yogidice.common.util.QuestionConfig;
import com.specialization.yogidice.domain.entity.BoardGame;
import com.specialization.yogidice.domain.entity.QBoardGame;
import com.specialization.yogidice.dto.request.BoardGamePickRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardGameRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QBoardGame qBoardGame = QBoardGame.boardGame;

    public List<BoardGame> findBoardGameByPick(BoardGamePickRequest request) {
        QuestionConfig questionConfig = new QuestionConfig(request);
        List<BoardGame> boardGameList = jpaQueryFactory.selectFrom(qBoardGame)
                .where(qBoardGame.maxPlayers.between(questionConfig.getQOneLower(), questionConfig.getQOneUpper()),
                        qBoardGame.difficulty.between(questionConfig.getQTwoLower(), questionConfig.getQTwoUpper()),
                        qBoardGame.playingTime.between(questionConfig.getQFourLower(), questionConfig.getQFourUpper()),
                        qBoardGame.publishYear.between(questionConfig.getQFiveLower(), questionConfig.getQFiveUpper()))
                .orderBy(qBoardGame.ratingUser.add(qBoardGame.ratingBl).desc())
                .limit(5)
                .fetch();
        return boardGameList;
    }
}
