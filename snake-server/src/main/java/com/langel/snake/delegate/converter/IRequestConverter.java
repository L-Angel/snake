package com.langel.snake.delegate.converter;

import com.langel.snake.SnakeRequest;
import io.netty.handler.codec.http.FullHttpRequest;

public interface IRequestConverter {
    void convert(FullHttpRequest fullRequest, SnakeRequest snakeRequest);
}
