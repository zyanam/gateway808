package com.cccts.gateway808.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;


public class TcpServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    final ByteBuf tag1 = Unpooled.copiedBuffer(new byte[]{0x7E});
    final ByteBuf tag2 = Unpooled.copiedBuffer(new byte[]{0x7E, 0x7E});

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        /**
         * 超时检测
         */
        pipeline.addLast("idleStateHandler", new IdleStateHandler(Config.TERMINAL_OVERTIME, 0, 0));

        /**
         * 粘包，拆包处理
         */
        pipeline.addLast("delimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(1024, tag1, tag2));

        /**
         * 记录收到消息
         */
        pipeline.addLast("logInboundHandler", new LogInboundHandler());

        /**
         * 反转义加验证校验码
         */
        pipeline.addLast("unescapeCrcHandler", new UnescapeCrcHandler());

        /**
         * 解析808消息
         */
        pipeline.addLast("message808Decoder", new Message808Decoder());


        /**
         *  终端态处理
         */
        pipeline.addLast("SessionHandler", new SessionHandler());

        /**
         * 回复消息
         */
        pipeline.addLast("responseHandler", new ResponseHandler());

        /**
         * 808消息
         */
        pipeline.addLast("message808Encoder", new Message808Encoder());

        /**
         * 转义并加校验码
         */
        pipeline.addLast("escapeCrcHandler", new EscapeCrcHandler());

        /**
         * 记录发送消息
         */
        pipeline.addLast("logOutboundHandler", new LogOutboundHandler());

    }
}
