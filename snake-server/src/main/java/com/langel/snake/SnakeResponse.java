package com.langel.snake;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;

public class SnakeResponse {
    private ByteBuf content;
    private HttpHeaders headers;

    public SnakeResponse(HttpHeaders headers, ByteBuf content) {
        this.headers = headers;
        this.content = content;
    }

    public HttpHeaders headers() {
        return headers;
    }

    public ByteBuf content() {
        return content;
    }
}
