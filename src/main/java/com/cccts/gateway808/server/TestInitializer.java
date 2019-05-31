package com.cccts.gateway808.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
//                .addLast(new ServerHandler2())
//                .addLast(new LoggingHandler("LogLevel.INFO)", LogLevel.INFO))

                .addLast(new ServerHandler3())
                .addLast(new TestLogHandler())
                ;
    }
}
