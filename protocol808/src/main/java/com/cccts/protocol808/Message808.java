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
    public Msg808Head msg808Head;

    public AbstractMessage808Body msg808Body;

    public void decode(ByteBuf in, boolean isParseBody) throws Exception {
        this.originalBuf = in;

        msg808Head = new Msg808Head();
        msg808Head.decode(this.originalBuf);

        if (isParseBody) {
            switch (msg808Head.msgID) {
                case 0x0200: {
                    msg808Body = new MessageBody0200().decode();
                }
                break;
            }
        } else {
            in.readBytes(in.readableBytes());
        }
    }
}
