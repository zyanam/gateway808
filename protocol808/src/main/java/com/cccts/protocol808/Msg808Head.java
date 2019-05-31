package com.cccts.protocol808;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Msg808Head {

    @Getter
    @Setter
    public int msgID;

    @Getter
    @Setter
    public int bodyAttribute;

    @Getter
    @Setter
    public String simID;

    @Getter
    @Setter
    public int serialNO;

    @Setter
    @Getter
    public PackageState packageState;

    public Msg808Head decode(ByteBuf in){
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

    @Data
    public class PackageState {
        @Getter
        @Setter
        public int total;

        @Getter
        @Setter
        public int current;
    }
}
