package com.langel.snake.delegate;

import com.langel.snake.SnakeRequest;
import com.langel.snake.delegate.converter.HttpRequestConverter;
import com.langel.snake.delegate.converter.IRequestConverter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.List;

public class HttpRequestDelegate {
    private static List<IRequestConverter> converters = new ArrayList<IRequestConverter>();

    static {
        converters.add(new HttpRequestConverter());
    }

    public static void transfer(FullHttpRequest fullRequest, SnakeRequest snakeRequest) {
        for (IRequestConverter converter : converters) {
            converter.convert(fullRequest, snakeRequest);
        }
    }
}
