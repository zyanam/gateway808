package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.gateway808.server.session.SessionState;
import com.cccts.protocol808.Message808;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 终端会话状态处理，上线、离线；
 */
public class SessionHandler extends SimpleChannelInboundHandler<Message808> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message808 msg) throws Exception {
       SessionState.termianlOnline(ctx.channel(),msg.head.simID);
       ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);

        ServerLog.DEBUG_LOG.error(cause.getMessage());
        ctx.close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("SessionHandler.handlerRemoved");
        SessionState.terminalOffline(ctx.channel());
    }
}
