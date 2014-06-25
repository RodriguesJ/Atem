package com.runescape.revised.client;

import com.runescape.client.SignLink;
import com.runescape.client.revised.util.TextClass;

public class Friend {
	
	public int[] friendsNodeIDs;
	public int friendsCount;
	public long[] friendsListAsLongs;
	public int friendsListAction;
	public String[] friendsList;
	
	public Friend() {
		friendsList = new String[200];
		friendsListAsLongs = new long[200];
		friendsNodeIDs = new int[200];
	}

	private void addFriend(long l)
	{
		try
		{
			if(l == 0L)
				return;
			if(friendsCount >= 100 && anInt1046 != 1)
			{
				pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
				return;
			}
			if(friendsCount >= 200)
			{
				pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
				return;
			}
			String s = TextClass.fixName(TextClass.nameForLong(l));
			for(int i = 0; i < friendsCount; i++)
				if(friendsListAsLongs[i] == l)
				{
					pushMessage(s + " is already on your friend list", 0, "");
					return;
				}
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l)
				{
					pushMessage("Please remove " + s + " from your ignore list first", 0, "");
					return;
				}

			if(s.equals(myPlayer.name))
			{
				return;
			} else
			{
				friendsList[friendsCount] = s;
				friendsListAsLongs[friendsCount] = l;
				friendsNodeIDs[friendsCount] = 0;
				friendsCount++;
				needDrawTabArea = true;
				stream.putPacketID(188);
				stream.writeQWord(l);
				return;
			}
		}
		catch(RuntimeException runtimeexception)
		{
			SignLink.reporterror("15283, " + (byte)68 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}
	
	
	
	
	
	
	
	
	private boolean isFriendOrSelf(String s)
	{
		if(s == null)
			return false;
		for(int i = 0; i < friendsCount; i++)
			if(s.equalsIgnoreCase(friendsList[i]))
				return true;
		return s.equalsIgnoreCase(myPlayer.name);
	}
	
	
	
	
	
	

	private void delFriend(long l)
	{
		try
		{
			if(l == 0L)
				return;
			for(int i = 0; i < friendsCount; i++)
			{
				if(friendsListAsLongs[i] != l)
					continue;
				friendsCount--;
				needDrawTabArea = true;
				for(int j = i; j < friendsCount; j++)
				{
					friendsList[j] = friendsList[j + 1];
					friendsNodeIDs[j] = friendsNodeIDs[j + 1];
					friendsListAsLongs[j] = friendsListAsLongs[j + 1];
				}

				stream.putPacketID(215);
				stream.writeQWord(l);
				break;
			}
		}
		catch(RuntimeException runtimeexception)
		{
			SignLink.reporterror("18622, " + false + ", " + l + ", " + runtimeexception.toString());
			throw new RuntimeException();
		}
	}
}