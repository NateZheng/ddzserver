package com.server.java.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer implements Runnable {
	private int port;

	public NettyServer(int port) {
		super();
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void run() {
		try {// netty鏀寔3绉峳eactor,netty鎺ㄨ崘涓讳粠(boss鍜寃orker)
			EventLoopGroup bossGroup = new NioEventLoopGroup(4);// acceptor,璐熻矗tcp鎺ュ叆,鎺ュ叆璁よ瘉,鍒涚珛socketChannel绛�
			EventLoopGroup workerGroup = new NioEventLoopGroup();// netty榛樿璁剧疆:Runtime.getRuntime().availableProcessors()
																	// 璐熻矗io鐨勮鍐欏拰缂栫爜

			try {
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_KEEPALIVE, true).channel(NioServerSocketChannel.class)
						.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new NettyServerInitializer());

				ChannelFuture f = b.bind(port).sync();

				// Wait until the server socket is closed.
				// In this example, this does not happen, but you can do that to
				// gracefully
				// shut down your server.
				f.channel().closeFuture().sync();
			} finally {
				workerGroup.shutdownGracefully();
				bossGroup.shutdownGracefully();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
