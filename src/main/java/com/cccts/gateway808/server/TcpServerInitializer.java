package com.cccts.gateway808.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline()
//                .addLast(new LoggingHandler("LogLevel.DEBUG)", LogLevel.DEBUG))
                .addLast("IdleStateHandler", new IdleStateHandler(30, 0, 0, TimeUnit.MINUTES))
                .addLast(
                        new DelimiterBasedFrameDecoder(1024,
                                Unpooled.copiedBuffer(new byte[]{0x7E}),
                                Unpooled.copiedBuffer(new byte[]{0x7E, 0x7E})))
//                .addLast(new LoggingHandler("LogLevel.INFO)", LogLevel.INFO))
//                .addLast(new MessageLogHandler())
                .addLast(new MessageDecoder())
                .addLast(new MessageEncoder())
                .addLast(new ServerHandler());


//                        "DelimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(1024,
//                                Unpooled.copiedBuffer(new byte[]{0x7e}),
//                                Unpooled.copiedBuffer(new byte[]{0x7e, 0x7e})))
//                .addLast("IdleStateHandler", new IdleStateHandler(10, 0, 0, TimeUnit.MINUTES))
//                .addLast("TcpServerHandler", new TcpServerHandler());

    }
}
