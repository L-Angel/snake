package com.langel.snake;

import com.langel.snake.action.Action;
import com.langel.snake.action.ActionRegister;
import com.langel.snake.action.support.InternalExceptionAction;
import com.langel.snake.action.support.WebStaticAction;
import com.langel.snake.delegate.HttpRequestDelegate;
import com.langel.snake.http.RequestMethod;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

public class ActionHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        fullHttpResponse.headers().set(CONTENT_TYPE, "charset=UTF-8");

        SnakeResponse snakeResponse = newSnakeResponseInstance(fullHttpResponse);
        SnakeRequest snakeRequest = newSnakeRequestInstance(fullHttpRequest);
        HttpRequestDelegate.transfer(fullHttpRequest, snakeRequest);

        this.doService(snakeRequest, snakeResponse);

        ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }

    protected void doService(SnakeRequest snakeRequest, SnakeResponse snakeResponse) {
        Action action = null;
        if (WebStaticAction.isStatic(snakeRequest)) {
            action = new WebStaticAction();
        } else if (!ActionRegister.contains(snakeRequest.path())) {
            action = ActionRegister.notFoundAction();
        } else {
            action = ActionRegister.getAction(snakeRequest.path(), RequestMethod.valueOf(snakeRequest.method().name()));
        }

        try {
            action.service(snakeRequest, snakeResponse);
        } catch (Exception e) {
            InternalExceptionAction err = new InternalExceptionAction(e);
            err.service(snakeRequest, snakeResponse);
        }
    }

    private SnakeRequest newSnakeRequestInstance(FullHttpRequest fullHttpRequest) {
        return new SnakeRequest(fullHttpRequest.uri(),
                fullHttpRequest.method(),
                fullHttpRequest.headers(),
                fullHttpRequest.content());
    }

    private SnakeResponse newSnakeResponseInstance(FullHttpResponse fullHttpResponse) {
        return new SnakeResponse(fullHttpResponse.headers(),
                fullHttpResponse.content());
    }
}
