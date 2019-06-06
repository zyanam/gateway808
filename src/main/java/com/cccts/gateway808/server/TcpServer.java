package com.cccts.gateway808.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpServer {
    private int port;

    public TcpServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(100);
        EventLoopGroup workerGroup = new NioEventLoopGroup(200);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 50000)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new TcpServerHandlerInitializer());
//                    .childHandler(new TestInitializer());

            System.out.println("service start,port=" + this.port);

            bootstrap.bind(port).sync()
                    .channel().closeFuture().sync();


        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
