package com.runescape.client.revised.net.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class Netty {

	public Netty() {
		final ClientBootstrap clientBootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		clientBootstrap.setPipelineFactory(new ClientPipelineFactory());
		clientBootstrap.connect(new InetSocketAddress(43594));
	}
}