package com.cccts.gateway808.server;

import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Message808解码器
 */
public class Message808Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.isReadable()) {
            System.out.println(LocalDateTime.now() + ",解码" + ":" + ByteBufUtil.hexDump(in));

            Message808 msg = new Message808();
            msg.decode(in, false);
            out.add(msg);
        }
    }
}
