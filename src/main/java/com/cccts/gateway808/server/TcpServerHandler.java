package com.cccts.gateway808.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (!msg.isReadable()) {
            return;
        }

//        byte[] bs = new byte[msg.readableBytes()];
//        msg.readBytes(bs);

        try {
            System.out.println("收到" + ByteBufUtil.hexDump(msg));

//            Session.RECEIVE_MSG_COUNT.getAndIncrement();
//
//            Message808 msg808 = new Message808(msg, false);

//            Session session = new Session();
//            session.simID = msg808.msgHead.simID;
//            session.currentCtx = ctx;

//            Session.ONLINE_TERMINAL.put(session.simID, session);
//            Session session = Session.ONLINE_TERMINAL.get(ctx.channel().id().asLongText());
//            if (session != null) {
//                session.simID = msg808.msgHead.simID;
//            }

//            System.out.println(msg808.toString());
        } finally {
//            msg.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        terminalOffline(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        Session session = new Session();
//        session.currentCtx = ctx;
//
//        Session.ONLINE_TERMINAL.put(ctx.channel().id().asLongText(), session);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        terminalOffline(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        terminalOffline(ctx);
    }

    public void terminalOffline(ChannelHandlerContext ctx)
    {
//        String chID = ctx.channel().id().asLongText();
//        if (Session.ONLINE_TERMINAL.containsKey(chID)) {
//            Session.ONLINE_TERMINAL.remove(chID);
//        }
//        ctx.close();
    }
}