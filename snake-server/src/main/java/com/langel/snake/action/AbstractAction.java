package com.langel.snake.action;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;

public abstract class AbstractAction implements Action {
    public static final String STATIC_PREFIX = "snake-web";
    //Json
    protected final String CONTENT_TYPE_JSON = "application/json";

    //Xml
    protected final String CONTENT_TYPE_XML = "application/xml";

    //Html
    protected final String CONTENT_TYPE_HTML = "text/html";

}
