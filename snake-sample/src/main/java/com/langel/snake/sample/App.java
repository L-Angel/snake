package com.langel.snake.sample;

import com.langel.snake.SnakeNettyServer;
import com.langel.snake.sample.action.SampleAction;
import com.langel.snake.sample.action.SamplePageAction;
import com.langel.snake.sample.action.SamplePostAction;

public class App {
    public static void main(String[] args){
        SnakeNettyServer server=new SnakeNettyServer();
        server.setAction("/sample", SampleAction.class);
        server.setAction("/samplepost", SamplePostAction.class);
        server.setAction("/index", SamplePageAction.class);
        server.start(10101);
    }
}
