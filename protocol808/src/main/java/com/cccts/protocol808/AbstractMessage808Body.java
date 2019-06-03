package com.cccts.protocol808;

import io.netty.buffer.ByteBuf;

public abstract class AbstractMessage808Body {
    public abstract AbstractMessage808Body decode();

    public abstract ByteBuf encode(AbstractMessage808Body msgBody);
}
