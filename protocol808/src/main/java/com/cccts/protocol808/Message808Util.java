package com.cccts.protocol808;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;

public class Message808Util {
    /**
     * 反转义
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static ByteBuf unEscape(ByteBuf in) throws Exception {
        int checkCode = 0;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            while (in.isReadable()) {
                byte b = in.readByte();
                if (b == 0x7D) {
                    b = in.readByte();
                    if (b == 0x01) {
                        baos.write(0x7D);
                        checkCode ^= 0x7D;
                    } else if (b == 0x02) {
                        baos.write(0x7E);
                        checkCode ^= 0x7E;
                    }
                } else {
                    baos.write(b);
                    checkCode ^= b;
                }
            }

            return Unpooled.copiedBuffer(baos.toByteArray());

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (baos != null) {
                baos.close();
            }
        }
    }
}
