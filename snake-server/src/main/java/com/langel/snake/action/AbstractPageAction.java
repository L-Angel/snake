package com.langel.snake.action;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractPageAction extends AbstractAction {

    protected void writePage(String path, SnakeResponse response) throws IOException {
        InputStream in = this.getClass().getResourceAsStream("/" + STATIC_PREFIX + "/" + path);
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = in.read(buff, 0, 100)) > 0) {
            response.content().writeBytes(buff, 0, rc);
        }
    }

    public void service(SnakeRequest request, SnakeResponse response) throws Exception {
        String path = service(request);
        writePage(path, response);
    }

    protected abstract String service(SnakeRequest request);
}
