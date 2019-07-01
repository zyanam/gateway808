package com.cccts.protocol808;

import com.cccts.protocol808.bodys.MessageBody0200;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Message808 {
    public Message808() {
    }

    @Setter
    @Getter
    public byte[] originalData;

    @Setter
    @Getter
    public Msg808Head head;

    public AbstractMessage808Body body;

    /**
     * 解码
     *
     * @param in
     * @param isParseBody
     * @return 大于0为成功；小于等于0为失败；-1=校验码验证失败
     * @throws Exception
     */
    public int decode(ByteBuf in, boolean isParseBody, boolean checkCrc) throws Exception {

        /**
         * 转义还原并获取校验码
         */
        int crc = Message808Util.unEscape(in);
        int crcCode = in.getByte(in.readableBytes() - 1) & 0xFF;

        if (checkCrc) {
            if (crc != crcCode) {
                return -1;
            }
        }

        head = new Msg808Head();
        head.decode(in);

        if (isParseBody) {
            switch (head.msgID) {
                case 0x0200: {
                    body = new MessageBody0200().decode();
                }
                break;
            }
        } else {

            in.clear();
        }

        return 1;
    }

    public ByteBuf encode() {
        ByteBuf buf = Unpooled.directBuffer();

        ByteBuf bufHead = null;
        ByteBuf bufBody = null;

        if (this.body != null) {
            bufBody = this.body.encode();
        }

        bufHead = this.head.encode(bufBody);

        buf.writeBytes(bufHead);
        bufHead.release();

        buf.writeBytes(bufBody);
        bufBody.release();

        Message808Util.escape(buf);

        return buf;
    }
}
