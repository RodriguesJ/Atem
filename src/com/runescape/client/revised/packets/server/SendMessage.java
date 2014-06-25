package com.runescape.client.revised.packets.server;

public class SendMessage {

	/**
	 * 
		if(pktType == 253)
		{
			String s = inStream.readString();
			if(s.endsWith(":tradereq:"))
			{
				String s3 = s.substring(0, s.indexOf(":"));
				long l17 = TextClass.longForName(s3);
				boolean flag2 = false;
				for(int j27 = 0; j27 < ignoreCount; j27++)
				{
					if(ignoreListAsLongs[j27] != l17)
						continue;
					flag2 = true;
					break;
				}

				if(!flag2 && anInt1251 == 0)
					pushMessage("wishes to trade with you.", 4, s3);
			} else
			if(s.endsWith(":duelreq:"))
			{
				String s4 = s.substring(0, s.indexOf(":"));
				long l18 = TextClass.longForName(s4);
				boolean flag3 = false;
				for(int k27 = 0; k27 < ignoreCount; k27++)
				{
					if(ignoreListAsLongs[k27] != l18)
						continue;
					flag3 = true;
					break;
				}

				if(!flag3 && anInt1251 == 0)
					pushMessage("wishes to duel with you.", 8, s4);
			} else
			if(s.endsWith(":chalreq:"))
			{
				String s5 = s.substring(0, s.indexOf(":"));
				long l19 = TextClass.longForName(s5);
				boolean flag4 = false;
				for(int l27 = 0; l27 < ignoreCount; l27++)
				{
					if(ignoreListAsLongs[l27] != l19)
						continue;
					flag4 = true;
					break;
				}

				if(!flag4 && anInt1251 == 0)
				{
					String s8 = s.substring(s.indexOf(":") + 1, s.length() - 9);
					pushMessage(s8, 8, s5);
				}
			} else
			{
				pushMessage(s, 0, "");
			}
			pktType = -1;
//serverMessage(s);

			return true;
		}
	 */
}