package com.cccts.protocol808;

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


    public boolean decode(ByteBuf in) throws Exception {
        //反转义
        this.originalBuf = Message808Util.unEscape(in);

        msg808Head = new Msg808Head();
        msg808Head.decode(this.originalBuf);
        return true;
    }
}
