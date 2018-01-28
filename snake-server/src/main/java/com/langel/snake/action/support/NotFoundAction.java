package com.langel.snake.action.support;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;
import com.langel.snake.action.Action;

public class NotFoundAction implements Action {
    public void service(SnakeRequest request, SnakeResponse response) {
        response.content().writeBytes(("404 Not Found! path=" + request.path() + ",method=" + request.method().name()).getBytes());
    }
}
