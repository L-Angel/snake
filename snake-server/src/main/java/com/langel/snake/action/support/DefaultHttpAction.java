package com.langel.snake.action.support;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;
import com.langel.snake.action.Action;

public class DefaultHttpAction implements Action {
    public void service(SnakeRequest request, SnakeResponse response) {
        response.content().writeBytes(("Welcome to snake Server").getBytes());
    }
}
