package com.langel.snake.http;

import io.netty.handler.codec.http.HttpMethod;

public enum RequestMethod {
    NONE,
    GET,
    POST;

    public RequestMethod convert(final HttpMethod method) {
        if ("GET".equals(method.name())) {
            return GET;
        } else if ("POST".equals(method.name())) {
            return POST;
        }
        return NONE;
    }

}
