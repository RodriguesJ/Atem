package com.runescape.client.revised.packets.server;

public class Fifty {

	/**
	 * 
		if(pktType == 50)
		{
			long l4 = inStream.readQWord();
			int i18 = inStream.readUnsignedByte();
			String s7 = TextClass.fixName(TextClass.nameForLong(l4));
			for(int k24 = 0; k24 < friendsCount; k24++)
			{
				if(l4 != friendsListAsLongs[k24])
					continue;
				if(friendsNodeIDs[k24] != i18)
				{
					friendsNodeIDs[k24] = i18;
					needDrawTabArea = true;
					if(i18 > 0)
						pushMessage(s7 + " has logged in.", 5, "");
					if(i18 == 0)
						pushMessage(s7 + " has logged out.", 5, "");
				}
				s7 = null;
				break;
			}

			if(s7 != null && friendsCount < 200)
			{
				friendsListAsLongs[friendsCount] = l4;
				friendsList[friendsCount] = s7;
				friendsNodeIDs[friendsCount] = i18;
				friendsCount++;
				needDrawTabArea = true;
			}
			for(boolean flag6 = false; !flag6;)
			{
				flag6 = true;
				for(int k29 = 0; k29 < friendsCount - 1; k29++)
					if(friendsNodeIDs[k29] != nodeID && friendsNodeIDs[k29 + 1] == nodeID || friendsNodeIDs[k29] == 0 && friendsNodeIDs[k29 + 1] != 0)
					{
						int j31 = friendsNodeIDs[k29];
						friendsNodeIDs[k29] = friendsNodeIDs[k29 + 1];
						friendsNodeIDs[k29 + 1] = j31;
						String s10 = friendsList[k29];
						friendsList[k29] = friendsList[k29 + 1];
						friendsList[k29 + 1] = s10;
						long l32 = friendsListAsLongs[k29];
						friendsListAsLongs[k29] = friendsListAsLongs[k29 + 1];
						friendsListAsLongs[k29 + 1] = l32;
						needDrawTabArea = true;
						flag6 = false;
					}

			}

			pktType = -1;
			return true;
		}
	 */
}