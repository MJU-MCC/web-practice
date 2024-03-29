package com.example.mcc.response;

public abstract class Message {

    public final static String SIGN_SUCCESS = "회원가입 성공하였습니다.";
    public final static String SIGN_FAIL = "회원가입 실패하였습니다.";

    public final static String LOGIN_SUCCESS = "로그인 성공하였습니다.";
    public final static String LOGIN_FAIL = "회원정보를 찾을 수 없습니다.";
    public final static String LOGOUT_SUCCESS = "로그아웃 성공하였습니다.";
    public final static String LOGOUT_FAIL = "로그아웃 실패하였습니다.";

    public final static String VOTE_SUCCESS = "투표목록 불러오기 성공하였습니다.";
    public final static String VOTE_FAIL = "투표목록 불러오기 실패하였습니다.";
    public final static String VOTE_SUCCESS_SAVE = "투표 저장을 성공하였습니다.";
    public final static String VOTE_FAIL_SAVE = "투표 저장을 실패하였습니다.";
    public final static String VOTE_SUCCESS_SCORE_SAVE = "투표 점수 저장을 성공하였습니다.";
    public final static String VOTE_FAIL_SCORE_SAVE = "투표 점수 저장을 실패하였습니다.";

}
