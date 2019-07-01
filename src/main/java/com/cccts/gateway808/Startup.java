package com.cccts.gateway808;

import com.cccts.gateway808.server.TcpServer;
import com.cccts.gateway808.server.session.SessionState;
import com.cccts.helpers.Log4jHelper;
import io.netty.buffer.CompositeByteBuf;
import io.netty.util.ResourceLeakDetector;

import java.util.Scanner;


public class Startup {
    public static void main(String[] args) throws Exception {



//        Config.getApplicationConfig();

        Log4jHelper.useCustomerConfig();

        Config.getApplicationConfig();

        System.setProperty("io.netty.leakDetection.maxRecords", "100");
        System.setProperty("io.netty.leakDetection.acquireAndReleaseOnly", "true");
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);

        new Thread(() -> {
            try {
                TcpServer tcpServer = new TcpServer(Config.TCP_PORT);
                tcpServer.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "TCPServer_thread").start();

        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            switch (cmd) {
                case "count": {
                    System.out.println("SessionState.SIMID_CHANNEL.size = " + SessionState.SIMID_CHANNEL.size());
                    System.out.println("SessionState.CHANNELID_SESSION.size = "+ SessionState.CHANNELID_SESSION.size());
                }
                break;
                case "config":{
                    System.out.printf("%s:%s%n","Config.TCP_PORT",Config.TCP_PORT);
                    System.out.printf("%s:%s%n","Config.TERMINAL_OVERTIME",Config.TERMINAL_OVERTIME);
                    System.out.printf("%s:%s%n","Config.CHECK_CRC",Config.CHECK_CRC);
                }
                break;
                default:
                {
                    System.out.printf("%-10s%s%n","count","查看在线数");
                    System.out.printf("%-10s%s%n","config","查看运行配置");
                }
                break;
            }
        }

    }
}
