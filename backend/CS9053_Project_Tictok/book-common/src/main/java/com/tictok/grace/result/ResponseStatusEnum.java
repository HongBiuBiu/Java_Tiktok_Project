package com.tictok.grace.result;


public enum ResponseStatusEnum {

    SUCCESS(200, true, "Operation successful!"),
    FAILED(500, false, "Operation failed!"),

    // 50x
    UN_LOGIN(501,false,"Please log in before continuing!"),
    TICKET_INVALID(502,false,"Session expired, please log in again!"),
    NO_AUTH(503,false,"You do not have sufficient permissions to continue!"),
    MOBILE_ERROR(504,false,"SMS sending failed, please try again later!"),
    SMS_NEED_WAIT_ERROR(505,false,"SMS sending too fast, please try again later!"),
    SMS_CODE_ERROR(506,false,"Verification code expired or does not match, please try again later!"),
    USER_FROZEN(507,false,"User has been frozen, please contact the administrator!"),
    USER_UPDATE_ERROR(508,false,"User information update failed, please contact the administrator!"),
    USER_INACTIVE_ERROR(509,false,"Please go to [Account Settings] to modify and activate your information before continuing!"),
    USER_INFO_UPDATED_ERROR(5091,false,"User information modification failed!"),
    USER_INFO_UPDATED_NICKNAME_EXIST_ERROR(5092,false,"Nickname already exists!"),
    USER_INFO_UPDATED_TICTOKNUM_EXIST_ERROR(5092,false,"IMooc number already exists!"),
    USER_INFO_CANT_UPDATED_TICTOKNUM_ERROR(5092,false,"IMooc number cannot be modified!"),
    FILE_UPLOAD_NULL_ERROR(510,false,"File cannot be empty, please select a file to upload!"),
    FILE_UPLOAD_FAILD(511,false,"File upload failed!"),
    FILE_FORMATTER_FAILD(512,false,"File image format not supported!"),
    FILE_MAX_SIZE_500KB_ERROR(5131,false,"Only supports image upload size of 500KB or less!"),
    FILE_MAX_SIZE_2MB_ERROR(5132,false,"Only supports image upload size of 2MB or less!"),
    FILE_NOT_EXIST_ERROR(514,false,"The file you are viewing does not exist!"),
    USER_STATUS_ERROR(515,false,"User status parameter error!"),
    USER_NOT_EXIST_ERROR(516,false,"User does not exist!"),

    // Custom system-level exceptions 54x
    SYSTEM_INDEX_OUT_OF_BOUNDS(541, false, "System error, array out of bounds!"),
    SYSTEM_ARITHMETIC_BY_ZERO(542, false, "System error, division by zero!"),
    SYSTEM_NULL_POINTER(543, false, "System error, null pointer!"),
    SYSTEM_NUMBER_FORMAT(544, false, "System error, number format exception!"),
    SYSTEM_PARSE(545, false, "System error, parse exception!"),
    SYSTEM_IO(546, false, "System error, IO input/output exception!"),
    SYSTEM_FILE_NOT_FOUND(547, false, "System error, file not found!"),
    SYSTEM_CLASS_CAST(548, false, "System error, class cast exception!"),
    SYSTEM_PARSER_ERROR(549, false, "System error, parsing error!"),
    SYSTEM_DATE_PARSER_ERROR(550, false, "System error, date parsing error!"),

    // Admin management system 56x
    ADMIN_USERNAME_NULL_ERROR(561, false, "Administrator login name cannot be empty!"),
    ADMIN_USERNAME_EXIST_ERROR(562, false, "Administrator login name already exists!"),
    ADMIN_NAME_NULL_ERROR(563, false, "Administrator responsible person cannot be empty!"),
    ADMIN_PASSWORD_ERROR(564, false, "Password cannot be empty or entered twice inconsistently!"),
    ADMIN_CREATE_ERROR(565, false, "Failed to add administrator!"),
    ADMIN_PASSWORD_NULL_ERROR(566, false, "Password cannot be empty!"),
    ADMIN_NOT_EXIT_ERROR(567, false, "Administrator does not exist or password is incorrect!"),
    ADMIN_FACE_NULL_ERROR(568, false, "Face information cannot be empty!"),
    ADMIN_FACE_LOGIN_ERROR(569, false, "Face recognition failed, please try again!"),
    CATEGORY_EXIST_ERROR(570, false, "Article category already exists, please choose a different name!"),

    // Media center related errors 58x
    ARTICLE_COVER_NOT_EXIST_ERROR(580, false, "Article cover does not exist, please choose one!"),
    ARTICLE_CATEGORY_NOT_EXIST_ERROR(581, false, "Please select the correct article category!"),
    ARTICLE_CREATE_ERROR(582, false, "Failed to create article, please try again or contact administrator!"),
    ARTICLE_QUERY_PARAMS_ERROR(583, false, "Article list query parameter error!"),
    ARTICLE_DELETE_ERROR(584, false, "Failed to delete article!"),
    ARTICLE_WITHDRAW_ERROR(585, false, "Failed to withdraw article!"),
    ARTICLE_REVIEW_ERROR(585, false, "Article review error!"),
    ARTICLE_ALREADY_READ_ERROR(586, false, "Article already read!"),

    // Face recognition error codes
    FACE_VERIFY_TYPE_ERROR(600, false, "Face comparison verification type is incorrect!"),
    FACE_VERIFY_LOGIN_ERROR(601, false, "Face login failed!"),

    // System error, unexpected error 555
    SYSTEM_ERROR(555, false, "System busy, please try again later!"),
    SYSTEM_OPERATION_ERROR(556, false, "Operation failed, please try again or contact administrator"),
    SYSTEM_RESPONSE_NO_INFO(557, false, ""),
    SYSTEM_ERROR_GLOBAL(558, false, "Global degradation: System busy, please try again later!"),
    SYSTEM_ERROR_FEIGN(559, false, "Client Feign degradation: System busy, please try again later!"),
    SYSTEM_ERROR_ZUUL(560, false, "Request system is too busy, please try again later!");


    // Response business status
    private Integer status;
    // Whether the call was successful or not
    private Boolean success;
    // Response message, can be a success or failure message
    private String msg;


    ResponseStatusEnum(Integer status, Boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }

    public Integer status() {
        return status;
    }
    public Boolean success() {
        return success;
    }
    public String msg() {
        return msg;
    }
}
