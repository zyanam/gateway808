package com.cccts.gateway808.test;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class TestServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new DelimiterBasedFrameDecoder(
                        1024,
                        Unpooled.copiedBuffer(new byte[]{0x7E}),
                        Unpooled.copiedBuffer(new byte[]{0x7E, 0x7E})))
                .addLast(new TestServerHandler())
                .addLast(new TestServerHandler1())
                .addLast(new TestServerHandler2())
        ;
    }
}
