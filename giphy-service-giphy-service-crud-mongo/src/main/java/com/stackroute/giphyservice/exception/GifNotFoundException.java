package com.stackroute.giphyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Gif resource with specified gifd is not found")
public class GifNotFoundException extends Exception {
}
