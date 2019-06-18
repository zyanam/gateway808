package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.gateway808.server.Session;
import com.cccts.protocol808.Message808;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 终端会话状态处理，上线、离线；
 */
public class SessionHandler extends SimpleChannelInboundHandler<Message808> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message808 msg) throws Exception {
        Session.SIMID_CHANNEL.put(msg.head.simID,ctx.channel().id());
        Session.VALID_CHANNEL.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SessionHandler.handlerRemoved");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SessionHandler.channelUnregistered");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SessionHandler.channelInactive");
    }
}
