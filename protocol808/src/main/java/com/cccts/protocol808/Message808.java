package com.cccts.protocol808;

import com.cccts.protocol808.bodys.MessageBody0200;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Message808 {
    public Message808() {
    }

    @Setter
    @Getter
    public ByteBuf originalBuf;

    @Setter
    @Getter
    public Msg808Head head;

    public AbstractMessage808Body body;

    public void decode(ByteBuf in, boolean isParseBody) throws Exception {
        this.originalBuf = in;

        head = new Msg808Head();
        head.decode(this.originalBuf);

        if (isParseBody) {
            switch (head.msgID) {
                case 0x0200: {
                    body = new MessageBody0200().decode();
                }
                break;
            }
        } else {
            in.readBytes(in.readableBytes());
        }
    }
}
