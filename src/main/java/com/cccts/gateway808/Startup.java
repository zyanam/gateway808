package com.cccts.gateway808;

import com.cccts.gateway808.server.Config;
import com.cccts.gateway808.server.TcpServer;
import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBufUtil;

import java.math.BigInteger;
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) throws Exception {


        new Thread(() -> {
            try {
                TcpServer tcpServer = new TcpServer(Config.TCP_PORT);
                tcpServer.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "TCPServer_thread").start();

//        for (; ; ) {
//            Scanner scanner = new Scanner(System.in);
//            String cmd = scanner.next();
//            System.out.println("输入cmd=" + cmd);
//            switch (cmd) {
//                case "state": {
//                    System.out.println("Session.ONLINE_TERMINAL.size = " + Session.ONLINE_TERMINAL.size());
//                    System.out.println("Session.RECEIVE_MSG_COUNT= "+ Session.RECEIVE_MSG_COUNT.get());
//                }
//                break;
//            }
//        }
    }
}
