package com.langel.snake;

import com.langel.snake.action.Action;
import com.langel.snake.action.ActionRegister;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class SnakeNettyServer {

    public void start(int port) {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try {
            final ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, wokerGroup)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // HTTP请求消息解码器
                                    .addLast("http-decoder", new HttpRequestDecoder())
                                    /*
                                     * HttpObjectAggregator解码器
                                     * 将多个消息转换为单一的FullHttpRequest或FullHttpResponse对象
                                     */
                                    .addLast("http-aggregator", new HttpObjectAggregator(65536))

                                    .addLast("http-encoder", new HttpResponseEncoder())
                                    .addLast("http-chunked", new ChunkedWriteHandler())
                                    .addLast("action-handler", new ActionHandler());
                        }
                    });
            ChannelFuture future = b.bind(port).sync();
            System.out.println("Snake server started.");
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }

    public static void setAction(String path, Class<? extends Action> action) {
        ActionRegister.register(path, action);
    }
}
