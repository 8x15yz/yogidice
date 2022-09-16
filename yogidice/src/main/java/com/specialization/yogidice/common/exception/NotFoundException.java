package com.specialization.yogidice.common.exception;

public class NotFoundException extends RuntimeException {
    public static final String USER_NOT_FOUND = "존재하지 않는 사용자입니다.";
    public static final String BOARDGAME_NOT_FOUND = "존재하지 않는 보드게임입니다.";
    public static final String BOARDGAME_LIST_NOT_FOUND = "보드게임이 존재하지 않습니다.";
    public static final String CATEGORY_NOT_FOUND = "존재하지 않는 카테고리(테마)입니다.";
    public static final String CATEGORY_LIST_NOT_FOUND = "카테고리(테마)가 존재하지 않습니다.";
    public static final String TYPE_NOT_FOUND = "존재하지 않는 타입입니다.";
    public static final String TYPE_LIST_NOT_FOUND = "타입이 존재하지 않습니다.";
    public static final String MECHANISM_NOT_FOUND = "존재하지 않는 진행방식입니다.";
    public static final String MECHANISM_LIST_NOT_FOUND = "진행방식이 존재하지 않습니다.";

    public NotFoundException(String message) {
        super(message);
    }
}