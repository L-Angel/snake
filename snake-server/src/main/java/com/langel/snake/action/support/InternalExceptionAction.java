package com.langel.snake.action.support;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;


public class InternalExceptionAction {
    private final Throwable throwable;

    public InternalExceptionAction(Throwable throwable) {
        this.throwable = throwable;
    }

    public void service(SnakeRequest request, SnakeResponse response) {
        response.content().clear();
        response.content().writeBytes("500 internal error.<\\br>".getBytes());
        request.content().writeBytes(this.throwable.toString().getBytes());
    }
}
