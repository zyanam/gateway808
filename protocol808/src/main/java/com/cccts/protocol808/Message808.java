package com.cccts.protocol808;

import com.cccts.protocol808.bodys.MessageBody0200;
import io.netty.buffer.ByteBuf;
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

    public void decode(ByteBuf in, boolean isParseBody) throws Exception {
//        this.originalData = new byte[in.readableBytes()];
//        in.getBytes(0,this.originalData);

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
//            in.release();

//            ByteBuf bf = in.readBytes(in.readableBytes());
//            bf.release();
//            in.release();
//            System.out.println("in.refCnt="+in.refCnt());

        }
    }
}
