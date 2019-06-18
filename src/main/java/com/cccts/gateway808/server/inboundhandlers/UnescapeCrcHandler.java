package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.pojos.TwoTuple;
import com.cccts.protocol808.Message808Util;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * 反转义，以及验证校验码
 */
public class UnescapeCrcHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        int crcCode = msg.getUnsignedByte(msg.readableBytes() - 1);

        ServerLog.DEBUG_LOG.trace("反转义");
        TwoTuple<ByteBuf, Integer> re = Message808Util.unEscape(msg);

        ServerLog.DEBUG_LOG.trace("验证校验码;计算的校验码=" + Integer.toHexString(re.getSecond()) +
                ";消息中的校验码=" + Integer.toHexString(crcCode));

        if(re.getSecond() != crcCode){
            //校验码验证失败
           ServerLog.DEBUG_LOG.trace("校验码验证失败。");
           ctx.close();
        }else {
            ctx.fireChannelRead(re.getFirst());
        }
    }
}
