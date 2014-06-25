package com.runescape.revised.client;

import com.runescape.client.SignLink;
import com.runescape.client.revised.util.TextClass;

public class Ignore {
	
	public int ignoreCount;
	public final long[] ignoreListAsLongs;
	
	public Ignore() {
		ignoreListAsLongs = new long[100];
	}

	private void addIgnore(GameClient client, long l)
	{
		try
		{
			if(l == 0L)
				return;
			if(ignoreCount >= 100)
			{
				client.getChat().pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
				return;
			}
			String s = TextClass.fixName(TextClass.nameForLong(l));
			for(int j = 0; j < client.ignoreCount; j++)
				if(client.ignoreListAsLongs[j] == l)
				{
					client.getChat().pushMessage(s + " is already on your ignore list", 0, "");
					return;
				}
			for(int k = 0; k < client.friendsCount; k++)
				if(client.friendsListAsLongs[k] == l)
				{
					client.getChat().pushMessage("Please remove " + s + " from your friend list first", 0, "");
					return;
				}

			client.ignoreListAsLongs[client.ignoreCount++] = l;
			client.needDrawTabArea = true;
			client.stream.putPacketID(133);
			client.stream.writeQWord(l);
			return;
		}
		catch(RuntimeException runtimeexception)
		{
			SignLink.reporterror("45688, " + l + ", " + 4 + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}
	
	
	
	
	
	
	private void delIgnore(GameClient client, long l)
	{
		try
		{
			if(l == 0L)
				return;
			for(int j = 0; j < client.ignoreCount; j++)
				if(client.ignoreListAsLongs[j] == l)
				{
					client.ignoreCount--;
					client.needDrawTabArea = true;
					System.arraycopy(client.ignoreListAsLongs, j + 1, client.ignoreListAsLongs, j, client.ignoreCount - j);

					client.stream.putPacketID(74);
					client.stream.writeQWord(l);
					return;
				}

			return;
		}
		catch(RuntimeException runtimeexception)
		{
			SignLink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}
}