package com.example.springpractice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandling {

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleArrayException(Exception ex) {
		log.error("配列の要素外参照が発生しました。メソッド名：{}, メッセージ：{}",
				ex.getStackTrace()[0].getMethodName(),
				ex.getMessage()
			);
	}
}
