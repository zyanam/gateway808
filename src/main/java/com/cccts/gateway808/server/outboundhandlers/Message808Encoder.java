package com.cccts.gateway808.server.outboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

public class Message808Encoder extends MessageToByteEncoder<Message808> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message808 msg, ByteBuf out) throws Exception {

        ByteBuf bf = msg.encode();

        String hex = ByteBufUtil.hexDump(bf);

        ServerLog.DATA_LOG.info(hex);
        ServerLog.DEBUG_LOG.trace(hex);

        out.writeBytes(bf);
        bf.release();
    }
}
