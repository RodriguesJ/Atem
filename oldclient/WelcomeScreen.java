package com.runescape.revised.client;

import com.runescape.client.SignLink;
import com.runescape.client.revised.config.definitions.IdentityKit;
import com.runescape.client.revised.util.TextClass;

public class WelcomeScreen {

	private void drawFriendsListOrWelcomeScreen(RSInterface class9)
	{
		int j = class9.anInt214;
		if(j >= 1 && j <= 100 || j >= 701 && j <= 800)
		{
			if(j == 1 && anInt900 == 0)
			{
				class9.message = "Loading friend list";
				class9.atActionType = 0;
				return;
			}
			if(j == 1 && anInt900 == 1)
			{
				class9.message = "Connecting to friendserver";
				class9.atActionType = 0;
				return;
			}
			if(j == 2 && anInt900 != 2)
			{
				class9.message = "Please wait...";
				class9.atActionType = 0;
				return;
			}
			int k = friendsCount;
			if(anInt900 != 2)
				k = 0;
			if(j > 700)
				j -= 601;
			else
				j--;
			if(j >= k)
			{
				class9.message = "";
				class9.atActionType = 0;
				return;
			} else
			{
				class9.message = friendsList[j];
				class9.atActionType = 1;
				return;
			}
		}
		if(j >= 101 && j <= 200 || j >= 801 && j <= 900)
		{
			int l = friendsCount;
			if(anInt900 != 2)
				l = 0;
			if(j > 800)
				j -= 701;
			else
				j -= 101;
			if(j >= l)
			{
				class9.message = "";
				class9.atActionType = 0;
				return;
			}
			if(friendsNodeIDs[j] == 0)
				class9.message = "@red@Offline";
			else
			if(friendsNodeIDs[j] == nodeID)
				class9.message = "@gre@World-" + (friendsNodeIDs[j] - 9);
			else
				class9.message = "@yel@World-" + (friendsNodeIDs[j] - 9);
			class9.atActionType = 1;
			return;
		}
		if(j == 203)
		{
			int i1 = friendsCount;
			if(anInt900 != 2)
				i1 = 0;
			class9.scrollMax = i1 * 15 + 20;
			if(class9.scrollMax <= class9.height)
				class9.scrollMax = class9.height + 1;
			return;
		}
		if(j >= 401 && j <= 500)
		{
			if((j -= 401) == 0 && anInt900 == 0)
			{
				class9.message = "Loading ignore list";
				class9.atActionType = 0;
				return;
			}
			if(j == 1 && anInt900 == 0)
			{
				class9.message = "Please wait...";
				class9.atActionType = 0;
				return;
			}
			int j1 = ignoreCount;
			if(anInt900 == 0)
				j1 = 0;
			if(j >= j1)
			{
				class9.message = "";
				class9.atActionType = 0;
				return;
			} else
			{
				class9.message = TextClass.fixName(TextClass.nameForLong(ignoreListAsLongs[j]));
				class9.atActionType = 1;
				return;
			}
		}
		if(j == 503)
		{
			class9.scrollMax = ignoreCount * 15 + 20;
			if(class9.scrollMax <= class9.height)
				class9.scrollMax = class9.height + 1;
			return;
		}
		if(j == 327)
		{
			class9.anInt270 = 150;
			class9.anInt271 = (int)(Math.sin((double)loopCycle / 40D) * 256D) & 0x7ff;
			if(aBoolean1031)
			{
				for(int k1 = 0; k1 < 7; k1++)
				{
					int l1 = anIntArray1065[k1];
					if(l1 >= 0 && !IdentityKit.cache[l1].method537())
						return;
				}

				aBoolean1031 = false;
				Model aclass30_sub2_sub4_sub6s[] = new Model[7];
				int i2 = 0;
				for(int j2 = 0; j2 < 7; j2++)
				{
					int k2 = anIntArray1065[j2];
					if(k2 >= 0)
						aclass30_sub2_sub4_sub6s[i2++] = IdentityKit.cache[k2].method538();
				}

				Model model = new Model(i2, aclass30_sub2_sub4_sub6s);
				for(int l2 = 0; l2 < 5; l2++)
					if(anIntArray990[l2] != 0)
					{
						model.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
						if(l2 == 1)
							model.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
					}

				model.method469();
				model.method470(AbstractAnimation.anims[myPlayer.anInt1511].anIntArray353[0]);
				model.method479(64, 850, -30, -50, -30, true);
				class9.anInt233 = 5;
				class9.mediaID = 0;
				RSInterface.method208(aBoolean994, model);
			}
			return;
		}
		if(j == 324)
		{
			if(aClass30_Sub2_Sub1_Sub1_931 == null)
			{
				aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
				aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
			}
			if(aBoolean1047)
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
				return;
			} else
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
				return;
			}
		}
		if(j == 325)
		{
			if(aClass30_Sub2_Sub1_Sub1_931 == null)
			{
				aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
				aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
			}
			if(aBoolean1047)
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
				return;
			} else
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
				return;
			}
		}
		if(j == 600)
		{
			class9.message = reportAbuseInput;
			if(loopCycle % 20 < 10)
			{
				class9.message += "|";
				return;
			} else
			{
				class9.message += " ";
				return;
			}
		}
		if(j == 613)
			if(myPrivilege >= 1)
			{
				if(canMute)
				{
					class9.textColor = 0xff0000;
					class9.message = "Moderator option: Mute player for 48 hours: <ON>";
				} else
				{
					class9.textColor = 0xffffff;
					class9.message = "Moderator option: Mute player for 48 hours: <OFF>";
				}
			} else
			{
				class9.message = "";
			}
		if(j == 650 || j == 655)
			if(anInt1193 != 0)
			{
				String s;
				if(daysSinceLastLogin == 0)
					s = "earlier today";
				else
				if(daysSinceLastLogin == 1)
					s = "yesterday";
				else
					s = daysSinceLastLogin + " days ago";
				class9.message = "You last logged in " + s + " from: " + SignLink.dns;
			} else
			{
				class9.message = "";
			}
		if(j == 651)
		{
			if(unreadMessages == 0)
			{
				class9.message = "0 unread messages";
				class9.textColor = 0xffff00;
			}
			if(unreadMessages == 1)
			{
				class9.message = "1 unread message";
				class9.textColor = 65280;
			}
			if(unreadMessages > 1)
			{
				class9.message = unreadMessages + " unread messages";
				class9.textColor = 65280;
			}
		}
		if(j == 652)
			if(daysSinceRecovChange == 201)
			{
				if(membersInt == 1)
					class9.message = "@yel@This is a non-members world: @whi@Since you are a member we";
				else
					class9.message = "";
			} else
			if(daysSinceRecovChange == 200)
			{
				class9.message = "You have not yet set any password recovery questions.";
			} else
			{
				String s1;
				if(daysSinceRecovChange == 0)
					s1 = "Earlier today";
				else
				if(daysSinceRecovChange == 1)
					s1 = "Yesterday";
				else
					s1 = daysSinceRecovChange + " days ago";
				class9.message = s1 + " you changed your recovery questions";
			}
		if(j == 653)
			if(daysSinceRecovChange == 201)
			{
				if(membersInt == 1)
					class9.message = "@whi@recommend you use a members world instead. You may use";
				else
					class9.message = "";
			} else
			if(daysSinceRecovChange == 200)
				class9.message = "We strongly recommend you do so now to secure your account.";
			else
				class9.message = "If you do not remember making this change then cancel it immediately";
		if(j == 654)
		{
			if(daysSinceRecovChange == 201)
				if(membersInt == 1)
				{
					class9.message = "@whi@this world but member benefits are unavailable whilst here.";
					return;
				} else
				{
					class9.message = "";
					return;
				}
			if(daysSinceRecovChange == 200)
			{
				class9.message = "Do this from the 'account management' area on our front webpage";
				return;
			}
			class9.message = "Do this from the 'account management' area on our front webpage";
		}
	}
	
	
	
	public void raiseWelcomeScreen()
	{
		welcomeScreenRaised = true;
	}
}