package com.runescape.revised.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.runescape.client.SignLink;

public class RSSocket {

	public Socket openSocket(int i) throws IOException
	{
		if(SignLink.mainapp != null)
			return SignLink.opensocket(i);
		else
			return new Socket(InetAddress.getByName(getCodeBase().getHost()), i);
	}
}