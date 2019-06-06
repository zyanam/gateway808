package com.cccts.gateway808.server;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    /**
     * 终端在线表
     * 存放有效的channel，有效表示至少收到一条有效的808数据，当收到一条有效的808数据，即代表终端上线。
     */
    public static ChannelGroup VALID_CHANNEL = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 保存终端和Channel的映射关系
     * K = SimID
     */
    public static ConcurrentHashMap<String,ChannelId> SIMID_CHANNEL = new ConcurrentHashMap<>();
}
