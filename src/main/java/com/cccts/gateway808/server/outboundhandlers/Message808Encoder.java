package com.cccts.gateway808.server.outboundhandlers;

import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

public class Message808Encoder extends MessageToByteEncoder<Message808> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message808 msg, ByteBuf out) throws Exception {
       //out = msg.originalBuf;
    }
}
