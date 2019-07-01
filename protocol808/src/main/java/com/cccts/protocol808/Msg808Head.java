package com.cccts.protocol808;

import com.cccts.helpers.HexStringHelper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Msg808Head {

    @Getter
    @Setter
    public int msgID;

    private int bodyAttribute;

    @Getter
    @Setter
    public String simID;

    @Getter
    @Setter
    public int serialNO;

    @Setter
    @Getter
    public PackageState packageState;

    public Msg808Head decode(ByteBuf in) {
        this.msgID = in.readUnsignedShort();
        this.bodyAttribute = in.readUnsignedShort();

        byte[] bs = new byte[6];
        in.readBytes(bs);
        this.simID = ByteBufUtil.hexDump(bs);
        this.serialNO = in.readUnsignedShort();

        if ((this.bodyAttribute & 8192) == 8192) {
            //分包
            this.packageState = new PackageState();
            this.packageState.total = in.readUnsignedShort();
            this.packageState.current = in.readUnsignedShort();
        }

        return this;
    }

    public ByteBuf encode(ByteBuf bufBody) {
        ByteBuf buf = Unpooled.directBuffer();
        buf.writeShort(msgID);

        int bodyLength = bufBody.readableBytes();
        bodyAttribute = bodyAttribute | bodyLength;
        if(packageState != null){
            bodyAttribute = bodyAttribute | 4096;
        }

        buf.writeShort(bodyAttribute);
        buf.writeBytes(HexStringHelper.hexStringToBytes(simID));
        buf.writeShort(serialNO);

        if(this.packageState != null){
           buf.writeBytes(this.packageState.encode());
        }
        return buf;
    }

    @Data
    public class PackageState {
        @Getter
        @Setter
        public int total;

        @Getter
        @Setter
        public int current;

        public ByteBuf encode(){
            ByteBuf buf  = Unpooled.directBuffer();
            buf.writeShort(total);
            buf.writeShort(current);
            return buf;
        }
    }
}
