package com.cccts.protocol808;

import com.cccts.pojos.TwoTuple;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;

public final class Message808Util {
    /**
     * 反转义
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static TwoTuple<ByteBuf, Integer> unEscape(ByteBuf in) throws Exception {
        Integer crcCode = 0;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            while (in.isReadable()) {
                byte b = in.readByte();
                if (b == 0x7D) {
                    b = in.readByte();
                    if (b == 0x01) {
                        baos.write(0x7D);
                        crcCode ^= 0x7D;
                    } else if (b == 0x02) {
                        baos.write(0x7E);
                        crcCode ^= 0x7E;
                    }
                } else {
                    baos.write(b);
                    if(in.readableBytes() > 1){
                        crcCode ^= b;
                    }
                }
            }

            return new TwoTuple<>(Unpooled.copiedBuffer(baos.toByteArray()), crcCode & 0xFF);

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (baos != null) {
                baos.close();
            }
        }
    }
}
