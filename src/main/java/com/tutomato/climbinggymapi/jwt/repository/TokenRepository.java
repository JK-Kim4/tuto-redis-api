package com.tutomato.climbinggymapi.jwt.repository;

import com.tutomato.climbinggymapi.jwt.domain.Token;
import org.springframework.stereotype.Repository;

/**
 * Token 정보를 nosql(redis) 저장소에 입력
 * */
@Repository
public class TokenRepository {


    public void save(Token token) {
        //redis repository 에 token 내용을 저장합니다.
    }

    public Token findByIdentifier(String identifier) {
        //identifier 일치하는 refresh 토큰 조회
        return null;
    }

    public void deleteById(String email) {

    }
}
