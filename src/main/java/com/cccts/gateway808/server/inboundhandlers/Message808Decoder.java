package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.Config;
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
            Message808 msg = new Message808();

            int result = msg.decode(in, false, Config.CHECK_CRC);
            if (result == -1) {
                ServerLog.DATA_LOG.error("校验码验证失败");
                ServerLog.DEBUG_LOG.error("校验码验证失败");
                ctx.close();
            }

            out.add(msg);
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
