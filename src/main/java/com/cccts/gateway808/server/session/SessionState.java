package com.cccts.gateway808.server.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Data
public class SessionState {
    /**
     * 终端在线表
     * 存放有效的channel，有效表示至少收到一条有效的808数据，当收到一条有效的808数据，即代表终端上线。
     */
    public static ChannelGroup VALID_CHANNEL = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 保存ChannelId和Session的关系
     * key = ChannelId
     */
    public static ConcurrentHashMap<String, SessionState> CHANNELID_SESSION = new ConcurrentHashMap<>();

    /**
     * 保存终端和Channel的映射关系
     * key = SimID
     */
    public static ConcurrentHashMap<String, String> SIMID_CHANNEL = new ConcurrentHashMap<>();

    /**
     * 消息流水号
     */
    private Short serialNO = 0;

    @Getter
    @Setter
    private String channelIdStr;

    @Getter
    @Setter
    private Channel channel;

    @Getter
    @Setter
    private ChannelHandlerContext channelHandlerContext;

    @Getter
    @Setter
    private String simID;

    /**
     * 获取消息流水号
     *
     * @return
     */
    public int getSerialNO() {
        synchronized (this) {
            if (this.serialNO >= Short.MAX_VALUE) {
                this.serialNO = 0;
            } else {
                this.serialNO++;
            }
            return this.serialNO;
        }
    }

    public static void termianlOnline(Channel channel, String simID) {
        if(!SIMID_CHANNEL.containsKey(simID)) {
            SessionState sessionState = new SessionState();
            sessionState.channel = channel;
            sessionState.channelIdStr = channel.id().asLongText();
            sessionState.simID = simID;

            CHANNELID_SESSION.put(sessionState.channelIdStr, sessionState);
            SIMID_CHANNEL.put(simID, sessionState.channelIdStr);
        }
    }

    public static void terminalOffline(Channel channel) {

        SessionState sessionState = CHANNELID_SESSION.remove(channel.id().asLongText());
        if (sessionState != null) {
            SIMID_CHANNEL.remove(sessionState.simID);
        }
    }

    public static SessionState getSessionState(String simID) {

        if (SIMID_CHANNEL.containsKey(simID)) {
            String channelID = SIMID_CHANNEL.get(simID);
            if (CHANNELID_SESSION.containsKey(channelID)) {
                return CHANNELID_SESSION.get(channelID);
            }
            return null;
        }
        return null;
    }
}
