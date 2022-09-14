package com.specialization.yogidice.common.exception;

public class NotFoundException extends RuntimeException {

    public static final String BOARDGAME_NOT_FOUND = "존재하지 않는 보드게임입니다.";
    public static final String BOARDGAME_LIST_NOT_FOUND = "보드게임이 존재하지 않습니다.";

    public NotFoundException(String message) {
        super(message);
    }
}