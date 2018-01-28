package com.langel.snake.sample.action;

import com.langel.snake.SnakeRequest;
import com.langel.snake.action.AbstractPageAction;

public class SamplePageAction extends AbstractPageAction {
    protected String service(SnakeRequest request) {
        return "index.html";
    }
}
