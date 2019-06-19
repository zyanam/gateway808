package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Message808解码器
 */
public class Message808Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.isReadable()) {
//            System.out.println(LocalDateTime.now() + ",解码" + ":" + ByteBufUtil.hexDump(in));
            ServerLog.DEBUG_LOG.trace("解码");

            Message808 msg = new Message808();
            msg.decode(in, false);
            out.add(msg);


//            System.out.println("in.refCnt="+in.refCnt());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//        cause.printStackTrace();
        ServerLog.DEBUG_LOG.error(cause.getMessage());
        ctx.close();
    }
}
