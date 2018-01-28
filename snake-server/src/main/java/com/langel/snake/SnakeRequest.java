package com.langel.snake;

import com.langel.snake.util.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class SnakeRequest {
    private HttpHeaders headers;
    private ByteBuf content;
    private String uri;
    private String path;
    private HttpMethod method;
    private Map<String, Object> params;

    public SnakeRequest(String uri, HttpMethod method, HttpHeaders headers, ByteBuf content) {
        this.uri = uri;
        this.path = newPath(uri);
        this.params = newParams(uri);
        this.method = method;
        this.headers = headers;
        this.content = content;
    }

    public HttpMethod method() {
        return method;
    }

    public String uri() {
        return uri;
    }


    public HttpHeaders headers() {
        return headers;
    }

    public ByteBuf content() {
        return content;
    }

    public String path() {
        return this.path;
    }

    private String newPath(String uri) {
        if (uri == null) {
            return null;
        }
        String[] ps = uri.split("\\?");
        if (ps.length > 1) {
            return ps[0];
        }
        return uri;
    }

    private Map<String, Object> newParams(String uri) {
        if (uri == null) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        String[] ps = uri.split("\\?");
        if (ps.length > 1) {
            String[] ps2 = ps[1].split("&");
            for (int i = 0; i < ps2.length; i++) {
                String[] kv = ps2[i].split("=");
                if (kv.length == 2) {
                    params.put(kv[0], StringUtils.of(kv[1]));
                }
            }
        }
        return params;
    }
}
