package com.cccts.protocol808;

import com.cccts.protocol808.bodys.MessageBody8001;
import io.netty.buffer.ByteBuf;
import org.junit.Test;

import static org.junit.Assert.*;

public class Message808Test {

    @Test
    public void encode() {
        Message808 respMsg = new Message808();
        respMsg.head = new Msg808Head();
        respMsg.head.msgID = 0x8001;
        respMsg.head.simID = "013412341234";
        respMsg.head.serialNO = 1111;

        MessageBody8001 msg8001 = new MessageBody8001();
        msg8001.answerSerialNO = 1;
        msg8001.answerMsgID = 0x0200;
        msg8001.answerResult = 0;

        respMsg.body = msg8001;

        ByteBuf bf = respMsg.encode();
    }
}