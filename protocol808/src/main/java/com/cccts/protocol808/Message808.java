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


    public boolean decode(ByteBuf in) throws Exception{
        this.originalBuf = in;
        //反转义
        ByteBuf bb = Message808Util.unEscape(in);
       return true;
    }
}
