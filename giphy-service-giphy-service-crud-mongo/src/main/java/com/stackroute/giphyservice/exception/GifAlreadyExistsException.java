package com.stackroute.giphyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="Gif with specified giphyId already exists ")
public class GifAlreadyExistsException extends Exception{
    }
