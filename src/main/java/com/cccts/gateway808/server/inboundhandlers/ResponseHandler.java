package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.gateway808.server.session.SessionState;
import com.cccts.protocol808.Message808;
import com.cccts.protocol808.Msg808Head;
import com.cccts.protocol808.bodys.MessageBody8001;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 回复处理器，用于回复终端通用应答
 */
public class ResponseHandler extends SimpleChannelInboundHandler<Message808> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//        System.out.println(cause.getMessage());

        ServerLog.DEBUG_LOG.error(cause.getMessage());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message808 msg) throws Exception {
        switch (msg.head.msgID) {
            case 0x0100: {
                //注册，需要调用RPC
            }
            break;
            case 0x0102: {
                //鉴权，需要调用RPC
            }
            break;
            default:
            {
                SessionState sessionState = SessionState.getSessionState(msg.head.simID);

                int serialNO = 0;
                if(sessionState != null){
                    serialNO = sessionState.getSerialNO();
                }

                //默认通用应答
                Message808 respMsg = new Message808();
                respMsg.head = new Msg808Head();
                respMsg.head.msgID = 0x8001;
                respMsg.head.simID = msg.head.simID;
                respMsg.head.serialNO = serialNO;

                MessageBody8001 msg8001 = new MessageBody8001();
                msg8001.answerSerialNO = msg.head.serialNO;
                msg8001.answerMsgID = msg.head.msgID;
                msg8001.answerResult = 0;

                respMsg.body = msg8001;

                ctx.writeAndFlush(respMsg);
            }
        }
    }
}
