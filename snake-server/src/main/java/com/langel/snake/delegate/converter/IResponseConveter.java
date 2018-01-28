package com.langel.snake.delegate.converter;

import com.langel.snake.SnakeResponse;
import io.netty.handler.codec.http.FullHttpResponse;

public interface IResponseConveter {
    void convert(SnakeResponse snakeResponse, FullHttpResponse fullResponse);
}
