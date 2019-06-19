package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.Config;
import com.cccts.gateway808.ServerLog;
import com.cccts.pojos.TwoTuple;
import com.cccts.protocol808.Message808Util;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;

/**
 * 反转义，以及验证校验码
 */
public class UnescapeCrcHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ServerLog.DEBUG_LOG.error(cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof ByteBuf) {
            ByteBuf in = (ByteBuf)msg;
            int crcCode = in.getUnsignedByte(in.readableBytes() - 1);

            ServerLog.DEBUG_LOG.trace("反转义");
//        TwoTuple<ByteBuf, Integer> re = Message808Util.unEscape(msg);

            int crc = Message808Util.unEscape(in);

            ServerLog.DEBUG_LOG.trace("验证校验码;计算的校验码=" + Integer.toHexString(crc) +
                    ";消息中的校验码=" + Integer.toHexString(crcCode));

            if (Config.CHECK_CRC) {
                if (crc != crcCode) {
                    //校验码验证失败
                    ServerLog.DEBUG_LOG.trace("校验码验证失败。");
                    ctx.close();
                    return;
                }
            }

//            ReferenceCountUtil.retain(in);
//            in.retain();
            ctx.fireChannelRead(in);
        }
    }
}
