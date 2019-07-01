package com.cccts.protocol808;

import com.cccts.pojos.TwoTuple;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;


public final class Message808Util {
    /**
     * 转义还原
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static Integer unEscape(ByteBuf in) throws Exception {
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
                    if (in.readableBytes() > 1) {
                        crcCode ^= b;
                    }
                }
            }

            in.discardReadBytes();
            in.writeBytes(baos.toByteArray());


//            return new TwoTuple<>(bf, crcCode & 0xFF);
            return crcCode & 0xFF;

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (baos != null) {
                baos.close();
            }
        }
    }

    /**
     * 转义、加校验码、加头尾标识符
     *
     * @param in
     */
    public static void escape(ByteBuf in) {
        ByteBuf buf = Unpooled.directBuffer();
        buf.writeByte(0x7E);
        Integer crc = 0;
        while (in.isReadable()) {
            byte b = in.readByte();
            crc ^= b;
            if (b == 0x7E) {
                buf.writeByte(0x7D);
                buf.writeByte(0x02);
            } else if (b == 0x7D) {
                buf.writeByte(0x7D);
                buf.writeByte(0x01);
            } else {
                buf.writeByte(b);
            }
        }
        buf.writeByte(crc);
        buf.writeByte(0x7E);
        in.writeBytes(buf);
        buf.release();
    }
}
