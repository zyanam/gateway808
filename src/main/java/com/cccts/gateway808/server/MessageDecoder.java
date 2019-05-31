package com.cccts.gateway808.server;

import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 808协议解码器
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable()) {
            return;
        }

        System.out.println("Decode");
        System.out.println("Decode,：" + ByteBufUtil.hexDump(in));


        Message808 msg = new Message808();
        msg.decode(in);



        out.add(msg);
    }
}
