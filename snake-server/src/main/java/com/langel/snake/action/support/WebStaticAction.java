package com.langel.snake.action.support;

import com.langel.snake.SnakeRequest;
import com.langel.snake.SnakeResponse;
import com.langel.snake.action.AbstractAction;
import com.langel.snake.action.Action;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

public class WebStaticAction extends AbstractAction {

    private static Map<String, String> staticContentTypeMap = new ConcurrentHashMap<String, String>();

    static {
        staticContentTypeMap.put(".js", "application/javascript");
        staticContentTypeMap.put(".css", "text/css");

        staticContentTypeMap.put(".ico", "image/x-icon");

        staticContentTypeMap.put(".gif", "image/gif");

        staticContentTypeMap.put(".jpg", "image/jpeg");
        staticContentTypeMap.put(".jfif", "image/jpeg");
        staticContentTypeMap.put(".jpe", "image/jpeg");
        staticContentTypeMap.put(".jpeg", "image/jpeg");

        staticContentTypeMap.put(".png", "image/png");

        staticContentTypeMap.put(".tiff", "image/tiff");
        staticContentTypeMap.put(".tif", "image/tiff");

    }

    public void service(SnakeRequest request, SnakeResponse response) throws Exception {
        response.headers().set(CONTENT_TYPE, contentType(request.uri()));

        InputStream in = this.getClass().getResourceAsStream("/" + STATIC_PREFIX + request.uri());
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = in.read(buff, 0, 100)) > 0) {
            response.content().writeBytes(buff, 0, rc);
        }
    }

    public static boolean isStatic(SnakeRequest request) {
        String uri = request.uri();
        if (uri == null) {
            return false;
        }
        for (String pre : staticContentTypeMap.keySet()) {
            if (uri.endsWith(pre)) {
                return true;
            }
        }
        return false;
    }

    public static String contentType(String uri) {
        uri = uri.toLowerCase();
        String suffix = uri.substring(uri.lastIndexOf("."), uri.length());
        if (staticContentTypeMap.containsKey(suffix)) {
            return staticContentTypeMap.get(suffix);
        }
        /*if(uri.endsWith(".html")||uri.endsWith(".htm")){
            return "text/html;charset=UTF-8";
        }*/
        return "text/plain";
    }
}
