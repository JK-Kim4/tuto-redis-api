package com.tutomato.common.validator;

import com.tutomato.common.exception.EmptyResultListException;
import com.tutomato.common.exception.UnsatisfyingParameterException;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ValidatorService {

    /**
     * 참조 타입 파라미터의 null 여부를 검증합니다.
     * */
    protected <T> void nonNull(T obj, String message) {
        if(Objects.isNull(obj)){
            throw new UnsatisfyingParameterException(message);
        }
    }

    /**
     * 1개 이상의 연속된 참조타입 파라미터의 null 여부를 검사합니다.
     * */
    protected <T> void nestedNonNull(T obj, String message, Consumer<T> consumer) {
        nonNull(obj, message);

        consumer.accept(obj);
    }

    /**
     * 목록 조회 요청의 결과가 비어있는지 확인합니다.
     * */
    protected <T> void empty(List<T> list, String message) {
        if(list.isEmpty()){
            throw new EmptyResultListException(message);
        }
    }

}
