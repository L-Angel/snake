package com.langel.snake.sample.action;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;
import com.langel.snake.action.Action;

public class SampleAction implements Action {
    public void service(SnakeRequest request, SnakeResponse response) {
        response.content().writeBytes("Smaple page.".getBytes());
    }
}
