package com.cccts.protocol808.bodys;

import com.cccts.protocol808.AbstractMessage808Body;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 平台通用应答
 */
@Data
public class MessageBody8001 extends AbstractMessage808Body {
    /**
     * 应答流水号
     */
    @Setter
    @Getter
    public long answerSerialNO;

    /**
     * 应答ID
     */
    @Setter
    @Getter
    public long answerMsgID;

    /**
     * 应答结果；0=成功/确认;1=失败;2=消息有误;3=不支持;4=报警处理确认;
     */
    @Setter
    @Getter
    public byte answerResult;

    @Override
    public AbstractMessage808Body decode() {
        return null;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf buf = Unpooled.directBuffer();
        buf.writeShort((int) answerSerialNO);
        buf.writeShort((int) answerMsgID);
        buf.writeByte(answerResult);
        return buf;
    }
}
