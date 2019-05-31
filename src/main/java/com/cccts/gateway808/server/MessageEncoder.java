package com.cccts.gateway808.server;

import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message808> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message808 msg, ByteBuf out) throws Exception {
        System.out.println("Encoder");
    }
}
