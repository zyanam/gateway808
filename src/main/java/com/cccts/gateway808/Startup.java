package com.cccts.gateway808;

import com.cccts.gateway808.server.Session;
import com.cccts.gateway808.server.TcpServer;
import com.cccts.helpers.Log4jHelper;

import java.util.Scanner;


public class Startup {
    public static void main(String[] args) throws Exception {

//        Config.getApplicationConfig();

        Log4jHelper.useCustomerConfig();

        Config.getApplicationConfig();

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
                    System.out.println("Session.SIMID_CHANNEL.size = " + Session.SIMID_CHANNEL.size());
                    System.out.println("Session.VALID_CHANNEL.size = "+ Session.VALID_CHANNEL.size());
                }
                break;
                default:
                {
                    System.out.printf("%-10s%s%n","count","查看在线数");
                }
                break;
            }
        }
    }
}
