package com.langel.snake.delegate;

import com.langel.snake.SnakeResponse;
import com.langel.snake.delegate.converter.HttpResponseConverter;
import com.langel.snake.delegate.converter.IResponseConveter;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.ArrayList;
import java.util.List;

public class HttpResponseDelegate {
    private static List<IResponseConveter> converters = new ArrayList<IResponseConveter>();

    static {
        converters.add(new HttpResponseConverter());
    }

    public static void transfer(SnakeResponse snakeResponse, FullHttpResponse fullResponse) {
        for (IResponseConveter conveter : converters) {
            conveter.convert(snakeResponse, fullResponse);
        }
    }
}
