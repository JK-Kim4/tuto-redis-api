package com.tutomato.common.util;

public class CommonValues {

    protected CommonValues() {
        throw new IllegalArgumentException("공통 변수용 class입니다.");
    }


    /*Error Code*/
    public static String ERROR_CODE_INCORRECT_QUERY     =   "SE01";
    public static String ERROR_CODE_INVALID_DISPLAY     =   "SE02";
    public static String ERROR_CODE_INVALID_START       =   "SE03";
    public static String ERROR_CODE_INVALID_SORT        =   "SE04";
    public static String ERROR_CODE_MALFORMED_ENCODING  =   "SE05";
    public static String ERROR_CODE_SYSTEM_ERROR        =   "SE99";

    /*Error Message*/
    public static String ERROR_MESSAGE_INCORRECT_QUERY     =   "Incorrect query request (잘못된 쿼리요청입니다.)";
    public static String ERROR_MESSAGE_INVALID_DISPLAY     =   "Invalid display value (부적절한 display 값입니다.)";
    public static String ERROR_MESSAGE_INVALID_START       =   "Invalid start value (부적절한 start 값입니다.)";
    public static String ERROR_MESSAGE_INVALID_SORT        =   "Invalid sort value (부적절한 sort 값입니다.)";
    public static String ERROR_MESSAGE_MALFORMED_ENCODING  =   "Malformed encoding (잘못된 형식의 인코딩입니다.)";
    public static String ERROR_MESSAGE_SYSTEM_ERROR        =   "System Error (시스템 에러)";


}
