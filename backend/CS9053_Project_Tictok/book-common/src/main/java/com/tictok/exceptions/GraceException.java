package com.tictok.exceptions;

import com.tictok.grace.result.ResponseStatusEnum;

public class GraceException {

    public static void display(ResponseStatusEnum responseStatusEnum) {
        throw new MyCustomException(responseStatusEnum);
    }

}
