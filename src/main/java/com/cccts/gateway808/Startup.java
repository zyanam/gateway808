package com.cccts.gateway808;

import com.cccts.gateway808.server.Session;
import com.cccts.gateway808.server.TcpServer;

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
