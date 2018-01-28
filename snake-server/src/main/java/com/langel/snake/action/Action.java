package com.langel.snake.action;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;

public interface Action {
    void service(SnakeRequest request, SnakeResponse response) throws Exception;
}
