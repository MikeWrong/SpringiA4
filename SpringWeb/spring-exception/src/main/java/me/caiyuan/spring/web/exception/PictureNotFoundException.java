package me.caiyuan.spring.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * YUAN
 * 8/13/16
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,
        reason = "Picture Not Found")
public class PictureNotFoundException extends RuntimeException {
}
