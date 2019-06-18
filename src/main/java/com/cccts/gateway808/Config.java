package com.cccts.gateway808;


import com.cccts.helpers.YmlHelper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public final class Config {

    /**
     * 808协议服务监听端口
     */
    public static int TCP_PORT = 8443;
    /**
     * 终端在没有读写数据的情况下，超时断开时间(秒)
     */
    public static int TERMINAL_OVERTIME = 120;

    public static void getApplicationConfig() throws IOException {
        String s = System.getProperty("user.dir")
                + System.getProperty("file.separator")
                +"conf"
                + System.getProperty("file.separator")
                +"application.yml";
        File file = new File(s);
        YmlHelper yml = new YmlHelper(file);
        Object obj = yml.getFromYml("server");
        if(obj instanceof LinkedHashMap){
            LinkedHashMap<String,Object> lhm = (LinkedHashMap<String, Object>)obj;
            TCP_PORT =  Integer.parseInt(lhm.get("tcp_port").toString());
            TERMINAL_OVERTIME = Integer.parseInt(lhm.get("terminal_overtime").toString());
        }
    }
}
