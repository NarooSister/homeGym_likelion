package com.example.homeGym.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalExceptionHandler extends RuntimeException{
    private CustomGlobalErrorCode customGlobalErrorCode;
}