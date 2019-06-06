package com.cccts.gateway808.server;

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

        System.out.println(LocalDateTime.now() + "反转义");

        TwoTuple<ByteBuf, Integer> re = Message808Util.unEscape(msg);

        System.out.println("crc=" + re.getSecond());

        ctx.fireChannelRead(re.getFirst());
    }
}
