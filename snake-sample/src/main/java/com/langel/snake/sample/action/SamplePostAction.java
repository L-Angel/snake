package com.langel.snake.sample.action;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;
import com.langel.snake.action.Action;
import com.langel.snake.annotation.SnakeAction;
import com.langel.snake.http.RequestMethod;

@SnakeAction(method = {RequestMethod.POST})
public class SamplePostAction implements Action {
    public void service(SnakeRequest request, SnakeResponse response) {
        response.content().writeBytes("Sample post action.".getBytes());
    }
}
