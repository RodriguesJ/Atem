package com.runescape.revised.client;

import java.io.IOException;

import com.runescape.client.CacheArchive;
import com.runescape.client.Class36;
import com.runescape.client.Deque;
import com.runescape.client.SignLink;
import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.config.VarBit;
import com.runescape.client.revised.config.definitions.Floor;
import com.runescape.client.revised.config.definitions.IdentityKit;
import com.runescape.client.revised.config.definitions.ItemDef;
import com.runescape.client.revised.config.definitions.NPCDef;
import com.runescape.client.revised.config.definitions.ObjectDef;
import com.runescape.client.revised.filesystem.Decompressor;
import com.runescape.client.revised.graphics.SceneGraphic;
import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.graphics.image.RSImageProducer;
import com.runescape.client.revised.media.IndexedImage;
import com.runescape.client.revised.util.Censor;
import com.runescape.client.revised.util.MouseDetection;
import com.runescape.client.revised.util.TextClass;

public class Login {

	public int daysSinceLastLogin;
	public int loginScreenState;
	public int loginFailures;
	public int lastLoginIP; // anInt1193
	public int loginScreenCursorPos;
	public String loginMessage1;
	public String loginMessage2;
	public static String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
	public String username;
	public String password;
	public int loadingBarPercantage; // anInt1079
	
	public Login() {
		loginMessage1 = "";
		loginMessage2 = "";
		this.setUsername("Rodrigues");
		this.setPassword("jobblebradley123");
	}

	private void loadTitleScreen(GameClient client)
	{
		aBackground_966 = new IndexedImage(client.titleStreamLoader, "titlebox", 0);
		aBackground_967 = new IndexedImage(client.titleStreamLoader, "titlebutton", 0);
		aBackgroundArray1152s = new IndexedImage[12];
		int j = 0;
		try
		{
			j = Integer.parseInt(getParameter("fl_icon"));
		}
		catch(Exception _ex) { }
		if(j == 0)
		{
			for(int k = 0; k < 12; k++)
				aBackgroundArray1152s[k] = new IndexedImage(titleStreamLoader, "runes", k);

		} else
		{
			for(int l = 0; l < 12; l++)
				aBackgroundArray1152s[l] = new IndexedImage(titleStreamLoader, "runes", 12 + (l & 3));

		}
		aClass30_Sub2_Sub1_Sub1_1201 = new AbstractSprite(128, 265);
		aClass30_Sub2_Sub1_Sub1_1202 = new AbstractSprite(128, 265);
		System.arraycopy(aRSImageProducer_1110.anIntArray315, 0, aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, 33920);

		System.arraycopy(aRSImageProducer_1111.anIntArray315, 0, aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, 33920);

		flameColorBuffer1 = new int[256];
		for(int k1 = 0; k1 < 64; k1++)
			flameColorBuffer1[k1] = k1 * 0x40000;

		for(int l1 = 0; l1 < 64; l1++)
			flameColorBuffer1[l1 + 64] = 0xff0000 + 1024 * l1;

		for(int i2 = 0; i2 < 64; i2++)
			flameColorBuffer1[i2 + 128] = 0xffff00 + 4 * i2;

		for(int j2 = 0; j2 < 64; j2++)
			flameColorBuffer1[j2 + 192] = 0xffffff;

		flameColorBuffer2 = new int[256];
		for(int k2 = 0; k2 < 64; k2++)
			flameColorBuffer2[k2] = k2 * 1024;

		for(int l2 = 0; l2 < 64; l2++)
			flameColorBuffer2[l2 + 64] = 65280 + 4 * l2;

		for(int i3 = 0; i3 < 64; i3++)
			flameColorBuffer2[i3 + 128] = 65535 + 0x40000 * i3;

		for(int j3 = 0; j3 < 64; j3++)
			flameColorBuffer2[j3 + 192] = 0xffffff;

		flameColorBuffer3 = new int[256];
		for(int k3 = 0; k3 < 64; k3++)
			flameColorBuffer3[k3] = k3 * 4;

		for(int l3 = 0; l3 < 64; l3++)
			flameColorBuffer3[l3 + 64] = 255 + 0x40000 * l3;

		for(int i4 = 0; i4 < 64; i4++)
			flameColorBuffer3[i4 + 128] = 0xff00ff + 1024 * i4;

		for(int j4 = 0; j4 < 64; j4++)
			flameColorBuffer3[j4 + 192] = 0xffffff;

		currentFlameColors = new int[256];
		anIntArray1190 = new int[32768];
		anIntArray1191 = new int[32768];
		randomizeBackground(null);
		anIntArray828 = new int[32768];
		anIntArray829 = new int[32768];
		drawLoadingText(10, "Connecting to fileserver");
		if(!currentlyDrawingFlames)
		{
			drawFlames = true;
			currentlyDrawingFlames = true;
			startRunnable(this, 2);
		}
	}
	
	
	
	
	
	
	
	private void drawLogo()
	{
		byte abyte0[] = titleStreamLoader.getDataForName("title.dat");
		AbstractSprite sprite = new AbstractSprite(abyte0, this);
		aRSImageProducer_1110.initDrawingArea();
		sprite.method346(0, 0);
		aRSImageProducer_1111.initDrawingArea();
		sprite.method346(-637, 0);
		aRSImageProducer_1107.initDrawingArea();
		sprite.method346(-128, 0);
		aRSImageProducer_1108.initDrawingArea();
		sprite.method346(-202, -371);
		aRSImageProducer_1109.initDrawingArea();
		sprite.method346(-202, -171);
		aRSImageProducer_1112.initDrawingArea();
		sprite.method346(0, -265);
		aRSImageProducer_1113.initDrawingArea();
		sprite.method346(-562, -265);
		aRSImageProducer_1114.initDrawingArea();
		sprite.method346(-128, -171);
		aRSImageProducer_1115.initDrawingArea();
		sprite.method346(-562, -171);
		int ai[] = new int[sprite.myWidth];
		for(int j = 0; j < sprite.myHeight; j++)
		{
			for(int k = 0; k < sprite.myWidth; k++)
				ai[k] = sprite.myPixels[(sprite.myWidth - k - 1) + sprite.myWidth * j];

			System.arraycopy(ai, 0, sprite.myPixels, sprite.myWidth * j, sprite.myWidth);

		}

		aRSImageProducer_1110.initDrawingArea();
		sprite.method346(382, 0);
		aRSImageProducer_1111.initDrawingArea();
		sprite.method346(-255, 0);
		aRSImageProducer_1107.initDrawingArea();
		sprite.method346(254, 0);
		aRSImageProducer_1108.initDrawingArea();
		sprite.method346(180, -371);
		aRSImageProducer_1109.initDrawingArea();
		sprite.method346(180, -171);
		aRSImageProducer_1112.initDrawingArea();
		sprite.method346(382, -265);
		aRSImageProducer_1113.initDrawingArea();
		sprite.method346(-180, -265);
		aRSImageProducer_1114.initDrawingArea();
		sprite.method346(254, -171);
		aRSImageProducer_1115.initDrawingArea();
			sprite.method346(-180, -171);
			sprite = new AbstractSprite(titleStreamLoader, "logo", 0);
			aRSImageProducer_1107.initDrawingArea();
			sprite.drawSprite(382 - sprite.myWidth / 2 - 128, 18);
			sprite = null;
			System.gc();

	}
	
	
	
	
	
	private void calcFlamesPosition()
	{
		char c = '\u0100';
		for(int j = 10; j < 117; j++)
		{
			int k = (int)(Math.random() * 100D);
			if(k < 50)
				anIntArray828[j + (c - 2 << 7)] = 255;
		}
		for(int l = 0; l < 100; l++)
		{
			int i1 = (int)(Math.random() * 124D) + 2;
			int k1 = (int)(Math.random() * 128D) + 128;
			int k2 = i1 + (k1 << 7);
			anIntArray828[k2] = 192;
		}

		for(int j1 = 1; j1 < c - 1; j1++)
		{
			for(int l1 = 1; l1 < 127; l1++)
			{
				int l2 = l1 + (j1 << 7);
				anIntArray829[l2] = (anIntArray828[l2 - 1] + anIntArray828[l2 + 1] + anIntArray828[l2 - 128] + anIntArray828[l2 + 128]) / 4;
			}

		}

		anInt1275 += 128;
		if(anInt1275 > anIntArray1190.length)
		{
			anInt1275 -= anIntArray1190.length;
			int i2 = (int)(Math.random() * 12D);
			randomizeBackground(aBackgroundArray1152s[i2]);
		}
		for(int j2 = 1; j2 < c - 1; j2++)
		{
			for(int i3 = 1; i3 < 127; i3++)
			{
				int k3 = i3 + (j2 << 7);
				int i4 = anIntArray829[k3 + 128] - anIntArray1190[k3 + anInt1275 & anIntArray1190.length - 1] / 5;
				if(i4 < 0)
					i4 = 0;
				anIntArray828[k3] = i4;
			}

		}

		System.arraycopy(anIntArray969, 1, anIntArray969, 0, c - 1);

		anIntArray969[c - 1] = (int)(Math.sin((double)loopCycle / 14D) * 16D + Math.sin((double)loopCycle / 15D) * 14D + Math.sin((double)loopCycle / 16D) * 12D);
		if(anInt1040 > 0)
			anInt1040 -= 4;
		if(anInt1041 > 0)
			anInt1041 -= 4;
		if(anInt1040 == 0 && anInt1041 == 0)
		{
			int l3 = (int)(Math.random() * 2000D);
			if(l3 == 0)
				anInt1040 = 1024;
			if(l3 == 1)
				anInt1041 = 1024;
		}
	}
	
	
	
	
	
	
	private void resetImageProducers()
	{
		if(aRSImageProducer_1107 != null)
			return;
		super.fullGameScreen = null;
		aRSImageProducer_1166 = null;
		aRSImageProducer_1164 = null;
		aRSImageProducer_1163 = null;
		aRSImageProducer_1165 = null;
		aRSImageProducer_1123 = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		aRSImageProducer_1110 = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1111 = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1107 = new RSImageProducer(509, 171, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1108 = new RSImageProducer(360, 132, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1109 = new RSImageProducer(360, 200, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1112 = new RSImageProducer(202, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1113 = new RSImageProducer(203, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1114 = new RSImageProducer(74, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1115 = new RSImageProducer(75, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		if(titleStreamLoader != null)
		{
			drawLogo();
			loadTitleScreen();
		}
		welcomeScreenRaised = true;
	}
	
	
	
	
	
	void drawLoadingText(int i, String s)
	{
		anInt1079 = i;
		aString1049 = s;
		resetImageProducers();
		if(titleStreamLoader == null)
		{
			super.drawLoadingText(i, s);
			return;
		}
		aRSImageProducer_1109.initDrawingArea();
		char c = '\u0168';
		char c1 = '\310';
		byte byte1 = 20;
		chatTextDrawingArea.drawText(0xffffff, "RuneScape is loading - please wait...", c1 / 2 - 26 - byte1, c / 2);
		int j = c1 / 2 - 18 - byte1;
		DrawingArea.fillPixels(c / 2 - 152, 304, 34, 0x8c1111, j);
		DrawingArea.fillPixels(c / 2 - 151, 302, 32, 0, j + 1);
		DrawingArea.method336(30, j + 2, c / 2 - 150, 0x8c1111, i * 3);
		DrawingArea.method336(30, j + 2, (c / 2 - 150) + i * 3, 0, 300 - i * 3);
		chatTextDrawingArea.drawText(0xffffff, s, (c1 / 2 + 5) - byte1, c / 2);
		aRSImageProducer_1109.drawGraphics(171, super.graphics, 202);
		if(welcomeScreenRaised)
		{
			welcomeScreenRaised = false;
			if(!currentlyDrawingFlames)
			{
				aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
				aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
			}
			aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
			aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
			aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
			aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
			aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
			aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
		}
	}
	
	
	
	
	
	private void login(String s, String s1, boolean flag)
	{
		SignLink.errorname = s;
		try
		{
			if(!flag)
			{
				loginMessage1 = "";
				loginMessage2 = "Connecting to server...";
				drawLoginScreen(true);
			}
			socketStream = new RSSocket(this, openSocket(43594 + portOff));
			long l = TextClass.longForName(s);
			int i = (int)(l >> 16 & 31L);
			stream.currentOffset = 0;
			stream.putLEShort(14);
			stream.putLEShort(i);
			socketStream.queueBytes(2, stream.buffer);
			for(int j = 0; j < 8; j++)
				socketStream.read();

			int k = socketStream.read();
			int i1 = k;
			if(k == 0)
			{
				socketStream.flushInputStream(inStream.buffer, 8);
				inStream.currentOffset = 0;
				aLong1215 = inStream.readQWord();
				int ai[] = new int[4];
				ai[0] = (int)(Math.random() * 99999999D);
				ai[1] = (int)(Math.random() * 99999999D);
				ai[2] = (int)(aLong1215 >> 32);
				ai[3] = (int)aLong1215;
				stream.currentOffset = 0;
				stream.putLEShort(10);
				stream.writeDWord(ai[0]);
				stream.writeDWord(ai[1]);
				stream.writeDWord(ai[2]);
				stream.writeDWord(ai[3]);
				stream.writeDWord(SignLink.uid);
				stream.writeString(s);
				stream.writeString(s1);
				stream.doKeys();
				aStream_847.currentOffset = 0;
				if(flag)
					aStream_847.putLEShort(18);
				else
					aStream_847.putLEShort(16);
				aStream_847.putLEShort(stream.currentOffset + 36 + 1 + 1 + 2);
				aStream_847.putLEShort(255);
				aStream_847.putShort(317);
				aStream_847.putLEShort(lowMem ? 1 : 0);
				for(int l1 = 0; l1 < 9; l1++)
					aStream_847.writeDWord(expectedCRCs[l1]);

				aStream_847.writeBytes(stream.buffer, stream.currentOffset, 0);
				stream.encryption = new Cryption(ai);
				for(int j2 = 0; j2 < 4; j2++)
					ai[j2] += 50;

				encryption = new Cryption(ai);
				socketStream.queueBytes(aStream_847.currentOffset, aStream_847.buffer);
				k = socketStream.read();
			}
			if(k == 1)
			{
				try
				{
					Thread.sleep(2000L);
				}
				catch(Exception _ex) { }
				login(s, s1, flag);
				return;
			}
			if(k == 2)
			{
				myPrivilege = socketStream.read();
				flagged = socketStream.read() == 1;
				aLong1220 = 0L;
				anInt1022 = 0;
				mouseDetection.coordsIndex = 0;
				super.awtFocus = true;
				aBoolean954 = true;
				loggedIn = true;
				stream.currentOffset = 0;
				inStream.currentOffset = 0;
				pktType = -1;
				anInt841 = -1;
				anInt842 = -1;
				anInt843 = -1;
				pktSize = 0;
				anInt1009 = 0;
				anInt1104 = 0;
				anInt1011 = 0;
				headiconDrawType = 0;
				menuActionRow = 0;
				menuOpen = false;
				super.idleTime = 0;
				for(int j1 = 0; j1 < 100; j1++)
					chatMessages[j1] = null;

				itemSelected = 0;
				spellSelected = 0;
				loadingStage = 0;
				anInt1062 = 0;
				anInt1278 = (int)(Math.random() * 100D) - 50;
				anInt1131 = (int)(Math.random() * 110D) - 55;
				anInt896 = (int)(Math.random() * 80D) - 40;
				minimapInt2 = (int)(Math.random() * 120D) - 60;
				minimapInt3 = (int)(Math.random() * 30D) - 20;
				minimapInt1 = (int)(Math.random() * 20D) - 10 & 0x7ff;
				anInt1021 = 0;
				anInt985 = -1;
				destX = 0;
				destY = 0;
				playerCount = 0;
				npcCount = 0;
				for(int i2 = 0; i2 < maxPlayers; i2++)
				{
					playerArray[i2] = null;
					aStreamArray895s[i2] = null;
				}

				for(int k2 = 0; k2 < 16384; k2++)
					sessionNPCs[k2] = null;

				myPlayer = playerArray[myPlayerIndex] = new Player();
				aClass19_1013.removeAll();
				aClass19_1056.removeAll();
				for(int l2 = 0; l2 < 4; l2++)
				{
					for(int i3 = 0; i3 < 104; i3++)
					{
						for(int k3 = 0; k3 < 104; k3++)
							groundArray[l2][i3][k3] = null;

					}

				}

				aClass19_1179 = new Deque();
				anInt900 = 0;
				friendsCount = 0;
				dialogID = -1;
				backDialogID = -1;
				openInterfaceID = -1;
				invOverlayInterfaceID = -1;
				anInt1018 = -1;
				aBoolean1149 = false;
				tabID = 3;
				inputDialogState = 0;
				menuOpen = false;
				messagePromptRaised = false;
				clickToContinueString = null;
				anInt1055 = 0;
				anInt1054 = -1;
				aBoolean1047 = true;
				method45();
				for(int j3 = 0; j3 < 5; j3++)
					anIntArray990[j3] = 0;

				for(int l3 = 0; l3 < 5; l3++)
				{
					atPlayerActions[l3] = null;
					atPlayerArray[l3] = false;
				}

				anInt1175 = 0;
				anInt1134 = 0;
				anInt986 = 0;
				anInt1288 = 0;
				anInt924 = 0;
				anInt1188 = 0;
				anInt1155 = 0;
				anInt1226 = 0;
				resetImageProducers2();
				return;
			}
			if(k == 3)
			{
				loginMessage1 = "";
				loginMessage2 = "Invalid username or password.";
				return;
			}
			if(k == 4)
			{
				loginMessage1 = "Your account has been disabled.";
				loginMessage2 = "Please check your message-center for details.";
				return;
			}
			if(k == 5)
			{
				loginMessage1 = "Your account is already logged in.";
				loginMessage2 = "Try again in 60 secs...";
				return;
			}
			if(k == 6)
			{
				loginMessage1 = "RuneScape has been updated!";
				loginMessage2 = "Please reload this page.";
				return;
			}
			if(k == 7)
			{
				loginMessage1 = "This world is full.";
				loginMessage2 = "Please use a different world.";
				return;
			}
			if(k == 8)
			{
				loginMessage1 = "Unable to connect.";
				loginMessage2 = "Login server offline.";
				return;
			}
			if(k == 9)
			{
				loginMessage1 = "Login limit exceeded.";
				loginMessage2 = "Too many connections from your address.";
				return;
			}
			if(k == 10)
			{
				loginMessage1 = "Unable to connect.";
				loginMessage2 = "Bad session id.";
				return;
			}
			if(k == 11)
			{
				loginMessage2 = "Login server rejected session.";
				loginMessage2 = "Please try again.";
				return;
			}
			if(k == 12)
			{
				loginMessage1 = "You need a members account to login to this world.";
				loginMessage2 = "Please subscribe, or use a different world.";
				return;
			}
			if(k == 13)
			{
				loginMessage1 = "Could not complete login.";
				loginMessage2 = "Please try using a different world.";
				return;
			}
			if(k == 14)
			{
				loginMessage1 = "The server is being updated.";
				loginMessage2 = "Please wait 1 minute and try again.";
				return;
			}
			if(k == 15)
			{
				loggedIn = true;
				stream.currentOffset = 0;
				inStream.currentOffset = 0;
				pktType = -1;
				anInt841 = -1;
				anInt842 = -1;
				anInt843 = -1;
				pktSize = 0;
				anInt1009 = 0;
				anInt1104 = 0;
				menuActionRow = 0;
				menuOpen = false;
				aLong824 = System.currentTimeMillis();
				return;
			}
			if(k == 16)
			{
				loginMessage1 = "Login attempts exceeded.";
				loginMessage2 = "Please wait 1 minute and try again.";
				return;
			}
			if(k == 17)
			{
				loginMessage1 = "You are standing in a members-only area.";
				loginMessage2 = "To play on this world move to a free area first";
				return;
			}
			if(k == 20)
			{
				loginMessage1 = "Invalid loginserver requested";
				loginMessage2 = "Please try using a different world.";
				return;
			}
			if(k == 21)
			{
				for(int k1 = socketStream.read(); k1 >= 0; k1--)
				{
					loginMessage1 = "You have only just left another world";
					loginMessage2 = "Your profile will be transferred in: " + k1 + " seconds";
					drawLoginScreen(true);
					try
					{
						Thread.sleep(1000L);
					}
					catch(Exception _ex) { }
				}

				login(s, s1, flag);
				return;
			}
			if(k == -1)
			{
				if(i1 == 0)
				{
					if(loginFailures < 2)
					{
						try
						{
							Thread.sleep(2000L);
						}
						catch(Exception _ex) { }
						loginFailures++;
						login(s, s1, flag);
						return;
					} else
					{
						loginMessage1 = "No response from loginserver";
						loginMessage2 = "Please wait 1 minute and try again.";
						return;
					}
				} else
				{
					loginMessage1 = "No response from server";
					loginMessage2 = "Please try using a different world.";
					return;
				}
			} else
			{
				System.out.println("response:" + k);
				loginMessage1 = "Unexpected server response";
				loginMessage2 = "Please try using a different world.";
				return;
			}
		}
		catch(IOException _ex)
		{
			loginMessage1 = "";
		}
		loginMessage2 = "Error connecting to server.";
	}




	
	void startUp()
	{
		drawLoadingText(20, "Starting up");
		if(SignLink.sunjava)
			super.minDelay = 5;
		if(aBoolean993)
		{
 //		   rsAlreadyLoaded = true;
 //		   return;
		}
		aBoolean993 = true;
		boolean flag = true;
		if(!flag)
		{
			genericLoadingError = true;
			return;
		}
		if(SignLink.cache_dat != null)
		{
			for(int i = 0; i < 5; i++)
				decompressors[i] = new Decompressor(SignLink.cache_dat, SignLink.cache_idx[i], i + 1);

		}
		try
		{
			titleStreamLoader = streamLoaderForName(1, "title screen", "title", expectedCRCs[1], 25);
			aTextDrawingArea_1270 = new TextDrawingArea(false, "p11_full", titleStreamLoader);
			aTextDrawingArea_1271 = new TextDrawingArea(false, "p12_full", titleStreamLoader);
			chatTextDrawingArea = new TextDrawingArea(false, "b12_full", titleStreamLoader);
			TextDrawingArea aTextDrawingArea_1273 = new TextDrawingArea(true, "q8_full", titleStreamLoader);
			drawLogo();
			loadTitleScreen();
			CacheArchive streamLoader = streamLoaderForName(2, "config", "config", expectedCRCs[2], 30);
			CacheArchive streamLoader_1 = streamLoaderForName(3, "interface", "interface", expectedCRCs[3], 35);
			CacheArchive streamLoader_2 = streamLoaderForName(4, "2d graphics", "media", expectedCRCs[4], 40);
			CacheArchive streamLoader_3 = streamLoaderForName(6, "textures", "textures", expectedCRCs[6], 45);
			CacheArchive streamLoader_4 = streamLoaderForName(7, "chat system", "wordenc", expectedCRCs[7], 50);
			CacheArchive streamLoader_5 = streamLoaderForName(8, "sound effects", "sounds", expectedCRCs[8], 55);
			byteGroundArray = new byte[4][104][104];
			intGroundArray = new int[4][105][105];
			worldController = new SceneGraphic(intGroundArray);
			for(int j = 0; j < 4; j++)
				aClass11Array1230[j] = new CollisionMap();

			aClass30_Sub2_Sub1_Sub1_1263 = new AbstractSprite(512, 512);
			CacheArchive streamLoader_6 = streamLoaderForName(5, "update list", "versionlist", expectedCRCs[5], 60);
			drawLoadingText(60, "Connecting to update server");
			onDemandFetcher = new OnDemandFetcher();
			onDemandFetcher.start(streamLoader_6, this);
			Class36.method528(onDemandFetcher.getAnimCount());
			Model.method459(onDemandFetcher.getVersionCount(0), onDemandFetcher);
			if(!lowMem)
			{
				nextSong = 0;
				try
				{
					nextSong = Integer.parseInt(getParameter("music"));
				}
				catch(Exception _ex) { }
				songChanging = true;
				onDemandFetcher.method558(2, nextSong);
				while(onDemandFetcher.getNodeCount() > 0)
				{
					processOnDemandQueue();
					try
					{
						Thread.sleep(100L);
					}
					catch(Exception _ex) { }
					if(onDemandFetcher.anInt1349 > 3)
					{
						loadError();
						return;
					}
				}
			}
			drawLoadingText(65, "Requesting animations");
			int k = onDemandFetcher.getVersionCount(1);
			for(int i1 = 0; i1 < k; i1++)
				onDemandFetcher.method558(1, i1);

			while(onDemandFetcher.getNodeCount() > 0)
			{
				int j1 = k - onDemandFetcher.getNodeCount();
				if(j1 > 0)
					drawLoadingText(65, "Loading animations - " + (j1 * 100) / k + "%");
				processOnDemandQueue();
				try
				{
					Thread.sleep(100L);
				}
				catch(Exception _ex) { }
				if(onDemandFetcher.anInt1349 > 3)
				{
					loadError();
					return;
				}
			}
			drawLoadingText(70, "Requesting models");
			k = onDemandFetcher.getVersionCount(0);
			for(int k1 = 0; k1 < k; k1++)
			{
				int l1 = onDemandFetcher.getModelIndex(k1);
				if((l1 & 1) != 0)
					onDemandFetcher.method558(0, k1);
			}

			k = onDemandFetcher.getNodeCount();
			while(onDemandFetcher.getNodeCount() > 0)
			{
				int i2 = k - onDemandFetcher.getNodeCount();
				if(i2 > 0)
					drawLoadingText(70, "Loading models - " + (i2 * 100) / k + "%");
				processOnDemandQueue();
				try
				{
					Thread.sleep(100L);
				}
				catch(Exception _ex) { }
			}
			if(decompressors[0] != null)
			{
				drawLoadingText(75, "Requesting maps");
				onDemandFetcher.method558(3, onDemandFetcher.method562(0, 48, 47));
				onDemandFetcher.method558(3, onDemandFetcher.method562(1, 48, 47));
				onDemandFetcher.method558(3, onDemandFetcher.method562(0, 48, 48));
				onDemandFetcher.method558(3, onDemandFetcher.method562(1, 48, 48));
				onDemandFetcher.method558(3, onDemandFetcher.method562(0, 48, 49));
				onDemandFetcher.method558(3, onDemandFetcher.method562(1, 48, 49));
				onDemandFetcher.method558(3, onDemandFetcher.method562(0, 47, 47));
				onDemandFetcher.method558(3, onDemandFetcher.method562(1, 47, 47));
				onDemandFetcher.method558(3, onDemandFetcher.method562(0, 47, 48));
				onDemandFetcher.method558(3, onDemandFetcher.method562(1, 47, 48));
				onDemandFetcher.method558(3, onDemandFetcher.method562(0, 148, 48));
				onDemandFetcher.method558(3, onDemandFetcher.method562(1, 148, 48));
				k = onDemandFetcher.getNodeCount();
				while(onDemandFetcher.getNodeCount() > 0)
				{
					int j2 = k - onDemandFetcher.getNodeCount();
					if(j2 > 0)
						drawLoadingText(75, "Loading maps - " + (j2 * 100) / k + "%");
					processOnDemandQueue();
					try
					{
						Thread.sleep(100L);
					}
					catch(Exception _ex) { }
				}
			}
			k = onDemandFetcher.getVersionCount(0);
			for(int k2 = 0; k2 < k; k2++)
			{
				int l2 = onDemandFetcher.getModelIndex(k2);
				byte byte0 = 0;
				if((l2 & 8) != 0)
					byte0 = 10;
				else
				if((l2 & 0x20) != 0)
					byte0 = 9;
				else
				if((l2 & 0x10) != 0)
					byte0 = 8;
				else
				if((l2 & 0x40) != 0)
					byte0 = 7;
				else
				if((l2 & 0x80) != 0)
					byte0 = 6;
				else
				if((l2 & 2) != 0)
					byte0 = 5;
				else
				if((l2 & 4) != 0)
					byte0 = 4;
				if((l2 & 1) != 0)
					byte0 = 3;
				if(byte0 != 0)
					onDemandFetcher.method563(byte0, 0, k2);
			}

			onDemandFetcher.method554(isMembers);
			if(!lowMem)
			{
				int l = onDemandFetcher.getVersionCount(2);
				for(int i3 = 1; i3 < l; i3++)
					if(onDemandFetcher.method569(i3))
						onDemandFetcher.method563((byte)1, 2, i3);

			}
			drawLoadingText(80, "Unpacking media");
			invBack = new IndexedImage(streamLoader_2, "invback", 0);
			chatBack = new IndexedImage(streamLoader_2, "chatback", 0);
			mapBack = new IndexedImage(streamLoader_2, "mapback", 0);
			backBase1 = new IndexedImage(streamLoader_2, "backbase1", 0);
			backBase2 = new IndexedImage(streamLoader_2, "backbase2", 0);
			backHmid1 = new IndexedImage(streamLoader_2, "backhmid1", 0);
			for(int j3 = 0; j3 < 13; j3++)
				sideIcons[j3] = new IndexedImage(streamLoader_2, "sideicons", j3);

			compass = new AbstractSprite(streamLoader_2, "compass", 0);
			mapEdge = new AbstractSprite(streamLoader_2, "mapedge", 0);
			mapEdge.method345();
			try
			{
				for(int k3 = 0; k3 < 100; k3++)
					mapScenes[k3] = new IndexedImage(streamLoader_2, "mapscene", k3);

			}
			catch(Exception _ex) { }
			try
			{
				for(int l3 = 0; l3 < 100; l3++)
					mapFunctions[l3] = new AbstractSprite(streamLoader_2, "mapfunction", l3);

			}
			catch(Exception _ex) { }
			try
			{
				for(int i4 = 0; i4 < 20; i4++)
					hitMarks[i4] = new AbstractSprite(streamLoader_2, "hitmarks", i4);

			}
			catch(Exception _ex) { }
			try
			{
				for(int j4 = 0; j4 < 20; j4++)
					headIcons[j4] = new AbstractSprite(streamLoader_2, "headicons", j4);

			}
			catch(Exception _ex) { }
			mapFlag = new AbstractSprite(streamLoader_2, "mapmarker", 0);
			mapMarker = new AbstractSprite(streamLoader_2, "mapmarker", 1);
			for(int k4 = 0; k4 < 8; k4++)
				crosses[k4] = new AbstractSprite(streamLoader_2, "cross", k4);

			mapDotItem = new AbstractSprite(streamLoader_2, "mapdots", 0);
			mapDotNPC = new AbstractSprite(streamLoader_2, "mapdots", 1);
			mapDotPlayer = new AbstractSprite(streamLoader_2, "mapdots", 2);
			mapDotFriend = new AbstractSprite(streamLoader_2, "mapdots", 3);
			mapDotTeam = new AbstractSprite(streamLoader_2, "mapdots", 4);
			scrollBar1 = new IndexedImage(streamLoader_2, "scrollbar", 0);
			scrollBar2 = new IndexedImage(streamLoader_2, "scrollbar", 1);
			redStone1 = new IndexedImage(streamLoader_2, "redstone1", 0);
			redStone2 = new IndexedImage(streamLoader_2, "redstone2", 0);
			redStone3 = new IndexedImage(streamLoader_2, "redstone3", 0);
			redStone1_2 = new IndexedImage(streamLoader_2, "redstone1", 0);
			redStone1_2.method358();
			redStone2_2 = new IndexedImage(streamLoader_2, "redstone2", 0);
			redStone2_2.method358();
			redStone1_3 = new IndexedImage(streamLoader_2, "redstone1", 0);
			redStone1_3.method359();
			redStone2_3 = new IndexedImage(streamLoader_2, "redstone2", 0);
			redStone2_3.method359();
			redStone3_2 = new IndexedImage(streamLoader_2, "redstone3", 0);
			redStone3_2.method359();
			redStone1_4 = new IndexedImage(streamLoader_2, "redstone1", 0);
			redStone1_4.method358();
			redStone1_4.method359();
			redStone2_4 = new IndexedImage(streamLoader_2, "redstone2", 0);
			redStone2_4.method358();
			redStone2_4.method359();
			for(int l4 = 0; l4 < 2; l4++)
				modIcons[l4] = new IndexedImage(streamLoader_2, "mod_icons", l4);

			AbstractSprite sprite = new AbstractSprite(streamLoader_2, "backleft1", 0);
			backLeftIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backleft2", 0);
			backLeftIP2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backright1", 0);
			backRightIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backright2", 0);
			backRightIP2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backtop1", 0);
			backTopIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backvmid1", 0);
			backVmidIP1 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backvmid2", 0);
			backVmidIP2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backvmid3", 0);
			backVmidIP3 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new AbstractSprite(streamLoader_2, "backhmid2", 0);
			backVmidIP2_2 = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			int i5 = (int)(Math.random() * 21D) - 10;
			int j5 = (int)(Math.random() * 21D) - 10;
			int k5 = (int)(Math.random() * 21D) - 10;
			int l5 = (int)(Math.random() * 41D) - 20;
			for(int i6 = 0; i6 < 100; i6++)
			{
				if(mapFunctions[i6] != null)
					mapFunctions[i6].method344(i5 + l5, j5 + l5, k5 + l5);
				if(mapScenes[i6] != null)
					mapScenes[i6].method360(i5 + l5, j5 + l5, k5 + l5);
			}

			drawLoadingText(83, "Unpacking textures");
			Rasterizer.method368(streamLoader_3);
			Rasterizer.method372(0.80000000000000004D);
			Rasterizer.method367();
			drawLoadingText(86, "Unpacking config");
			AbstractAnimation.unpackConfig(streamLoader);
			ObjectDef.unpackConfig(streamLoader);
			Floor.unpackConfig(streamLoader);
			ItemDef.unpackConfig(streamLoader);
			NPCDef.unpackConfig(streamLoader);
			IdentityKit.unpackConfig(streamLoader);
			AbstractGraphic.unpackConfig(streamLoader);
			Varp.unpackConfig(streamLoader);
			VarBit.unpackConfig(streamLoader);
			ItemDef.isMembers = isMembers;
			if(!lowMem)
			{
				drawLoadingText(90, "Unpacking sounds");
				byte abyte0[] = streamLoader_5.getDataForName("sounds.dat");
				RSBuffer stream = new RSBuffer(abyte0);
				Sounds.unpack(stream);
			}
			drawLoadingText(95, "Unpacking interfaces");
			TextDrawingArea aclass30_sub2_sub1_sub4s[] = {
					aTextDrawingArea_1270, aTextDrawingArea_1271, chatTextDrawingArea, aTextDrawingArea_1273
			};
			RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, streamLoader_2);
			drawLoadingText(100, "Preparing game engine");
			for(int j6 = 0; j6 < 33; j6++)
			{
				int k6 = 999;
				int i7 = 0;
				for(int k7 = 0; k7 < 34; k7++)
				{
					if(mapBack.aByteArray1450[k7 + j6 * mapBack.anInt1452] == 0)
					{
						if(k6 == 999)
							k6 = k7;
						continue;
					}
					if(k6 == 999)
						continue;
					i7 = k7;
					break;
				}

				anIntArray968[j6] = k6;
				anIntArray1057[j6] = i7 - k6;
			}

			for(int l6 = 5; l6 < 156; l6++)
			{
				int j7 = 999;
				int l7 = 0;
				for(int j8 = 25; j8 < 172; j8++)
				{
					if(mapBack.aByteArray1450[j8 + l6 * mapBack.anInt1452] == 0 && (j8 > 34 || l6 > 34))
					{
						if(j7 == 999)
							j7 = j8;
						continue;
					}
					if(j7 == 999)
						continue;
					l7 = j8;
					break;
				}

				anIntArray1052[l6 - 5] = j7 - 25;
				anIntArray1229[l6 - 5] = l7 - j7;
			}

			Rasterizer.method365(479, 96);
			anIntArray1180 = Rasterizer.anIntArray1472;
			Rasterizer.method365(190, 261);
			anIntArray1181 = Rasterizer.anIntArray1472;
			Rasterizer.method365(512, 334);
			anIntArray1182 = Rasterizer.anIntArray1472;
			int ai[] = new int[9];
			for(int i8 = 0; i8 < 9; i8++)
			{
				int k8 = 128 + i8 * 32 + 15;
				int l8 = 600 + k8 * 3;
				int i9 = Rasterizer.anIntArray1470[k8];
				ai[i8] = l8 * i9 >> 16;
			}

			SceneGraphic.method310(500, 800, 512, 334, ai);
			Censor.loadConfig(streamLoader_4);
			mouseDetection = new MouseDetection(this);
			startRunnable(mouseDetection, 10);
			GameObject.clientInstance = this;
			ObjectDef.clientInstance = this;
			NPCDef.clientInstance = this;
			return;
		}
		catch(Exception exception)
		{
			SignLink.reporterror("loaderror " + aString1049 + " " + anInt1079);
		}
		loadingError = true;
	}



	
	private void doFlamesDrawing()
	{
		char c = '\u0100';
		if(anInt1040 > 0)
		{
			for(int i = 0; i < 256; i++)
				if(anInt1040 > 768)
					currentFlameColors[i] = method83(flameColorBuffer1[i], flameColorBuffer2[i], 1024 - anInt1040);
				else
				if(anInt1040 > 256)
					currentFlameColors[i] = flameColorBuffer2[i];
				else
					currentFlameColors[i] = method83(flameColorBuffer2[i], flameColorBuffer1[i], 256 - anInt1040);

		} else
		if(anInt1041 > 0)
		{
			for(int j = 0; j < 256; j++)
				if(anInt1041 > 768)
					currentFlameColors[j] = method83(flameColorBuffer1[j], flameColorBuffer3[j], 1024 - anInt1041);
				else
				if(anInt1041 > 256)
					currentFlameColors[j] = flameColorBuffer3[j];
				else
					currentFlameColors[j] = method83(flameColorBuffer3[j], flameColorBuffer1[j], 256 - anInt1041);

		} else
		{
			System.arraycopy(flameColorBuffer1, 0, currentFlameColors, 0, 256);

		}
		System.arraycopy(aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, aRSImageProducer_1110.anIntArray315, 0, 33920);

		int i1 = 0;
		int j1 = 1152;
		for(int k1 = 1; k1 < c - 1; k1++)
		{
			int l1 = (anIntArray969[k1] * (c - k1)) / c;
			int j2 = 22 + l1;
			if(j2 < 0)
				j2 = 0;
			i1 += j2;
			for(int l2 = j2; l2 < 128; l2++)
			{
				int j3 = anIntArray828[i1++];
				if(j3 != 0)
				{
					int l3 = j3;
					int j4 = 256 - j3;
					j3 = currentFlameColors[j3];
					int l4 = aRSImageProducer_1110.anIntArray315[j1];
					aRSImageProducer_1110.anIntArray315[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00) + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
				} else
				{
					j1++;
				}
			}

			j1 += j2;
		}

		aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
		System.arraycopy(aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, aRSImageProducer_1111.anIntArray315, 0, 33920);

		i1 = 0;
		j1 = 1176;
		for(int k2 = 1; k2 < c - 1; k2++)
		{
			int i3 = (anIntArray969[k2] * (c - k2)) / c;
			int k3 = 103 - i3;
			j1 += i3;
			for(int i4 = 0; i4 < k3; i4++)
			{
				int k4 = anIntArray828[i1++];
				if(k4 != 0)
				{
					int i5 = k4;
					int j5 = 256 - k4;
					k4 = currentFlameColors[k4];
					int k5 = aRSImageProducer_1111.anIntArray315[j1];
					aRSImageProducer_1111.anIntArray315[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00) + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
				} else
				{
					j1++;
				}
			}

			i1 += 128 - k3;
			j1 += 128 - k3 - i3;
		}

		aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
	}
	
	
	
	
	
	private void drawLoginScreen(boolean flag)
	{
		resetImageProducers();
		aRSImageProducer_1109.initDrawingArea();
		aBackground_966.method361(0, 0);
		char c = '\u0168';
		char c1 = '\310';
		if(loginScreenState == 0)
		{
			int i = c1 / 2 + 80;
			aTextDrawingArea_1270.method382(0x75a9a9, c / 2, onDemandFetcher.statusString, i, true);
			i = c1 / 2 - 20;
			chatTextDrawingArea.method382(0xffff00, c / 2, "Welcome to RuneScape", i, true);
			i += 30;
			int l = c / 2 - 80;
			int k1 = c1 / 2 + 20;
			aBackground_967.method361(l - 73, k1 - 20);
			chatTextDrawingArea.method382(0xffffff, l, "New User", k1 + 5, true);
			l = c / 2 + 80;
			aBackground_967.method361(l - 73, k1 - 20);
			chatTextDrawingArea.method382(0xffffff, l, "Existing User", k1 + 5, true);
		}
		if(loginScreenState == 2)
		{
			int j = c1 / 2 - 40;
			if(loginMessage1.length() > 0)
			{
				chatTextDrawingArea.method382(0xffff00, c / 2, loginMessage1, j - 15, true);
				chatTextDrawingArea.method382(0xffff00, c / 2, loginMessage2, j, true);
				j += 30;
			} else
			{
				chatTextDrawingArea.method382(0xffff00, c / 2, loginMessage2, j - 7, true);
				j += 30;
			}
			chatTextDrawingArea.method389(true, c / 2 - 90, 0xffffff, "Username: " + myUsername + ((loginScreenCursorPos == 0) & (loopCycle % 40 < 20) ? "@yel@|" : ""), j);
			j += 15;
			chatTextDrawingArea.method389(true, c / 2 - 88, 0xffffff, "Password: " + TextClass.passwordAsterisks(myPassword) + ((loginScreenCursorPos == 1) & (loopCycle % 40 < 20) ? "@yel@|" : ""), j);
			j += 15;
			if(!flag)
			{
				int i1 = c / 2 - 80;
				int l1 = c1 / 2 + 50;
				aBackground_967.method361(i1 - 73, l1 - 20);
				chatTextDrawingArea.method382(0xffffff, i1, "Login", l1 + 5, true);
				i1 = c / 2 + 80;
				aBackground_967.method361(i1 - 73, l1 - 20);
				chatTextDrawingArea.method382(0xffffff, i1, "Cancel", l1 + 5, true);
			}
		}
		if(loginScreenState == 3)
		{
						chatTextDrawingArea.method382(0xffff00, c / 2, "Create a free account", c1 / 2 - 60, true);
			int k = c1 / 2 - 35;
			chatTextDrawingArea.method382(0xffffff, c / 2, "To create a new account you need to", k, true);
			k += 15;
			chatTextDrawingArea.method382(0xffffff, c / 2, "go back to the main RuneScape webpage", k, true);
			k += 15;
			chatTextDrawingArea.method382(0xffffff, c / 2, "and choose the red 'create account'", k, true);
			k += 15;
			chatTextDrawingArea.method382(0xffffff, c / 2, "button at the top right of that page.", k, true);
			k += 15;
			int j1 = c / 2;
			int i2 = c1 / 2 + 50;
			aBackground_967.method361(j1 - 73, i2 - 20);
			chatTextDrawingArea.method382(0xffffff, j1, "Cancel", i2 + 5, true);
		}
		aRSImageProducer_1109.drawGraphics(171, super.graphics, 202);
		if(welcomeScreenRaised)
		{
			welcomeScreenRaised = false;
			aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
			aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
			aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
			aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
			aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
			aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
		}
	}

	private void drawFlames()
	{
		drawingFlames = true;
		try
		{
			long l = System.currentTimeMillis();
			int i = 0;
			int j = 20;
			while(currentlyDrawingFlames) 
			{
				anInt1208++;
				calcFlamesPosition();
				calcFlamesPosition();
				doFlamesDrawing();
				if(++i > 10)
				{
					long l1 = System.currentTimeMillis();
					int k = (int)(l1 - l) / 10 - j;
					j = 40 - k;
					if(j < 5)
						j = 5;
					i = 0;
					l = l1;
				}
				try
				{
					Thread.sleep(j);
				}
				catch(Exception _ex) { }
			}
		}
		catch(Exception _ex) { }
		drawingFlames = false;
	}
	
	
	
	private void processLoginScreenInput()
	{
		if(loginScreenState == 0)
		{
			int i = super.myWidth / 2 - 80;
			int l = super.myHeight / 2 + 20;
			l += 20;
			if(super.clickMode3 == 1 && super.saveClickX >= i - 75 && super.saveClickX <= i + 75 && super.saveClickY >= l - 20 && super.saveClickY <= l + 20)
			{
				loginScreenState = 3;
				loginScreenCursorPos = 0;
			}
			i = super.myWidth / 2 + 80;
			if(super.clickMode3 == 1 && super.saveClickX >= i - 75 && super.saveClickX <= i + 75 && super.saveClickY >= l - 20 && super.saveClickY <= l + 20)
			{
				loginMessage1 = "";
				loginMessage2 = "Enter your username & password.";
				loginScreenState = 2;
				loginScreenCursorPos = 0;
			}
		} else
		{
			if(loginScreenState == 2)
			{
				int j = super.myHeight / 2 - 40;
				j += 30;
				j += 25;
				if(super.clickMode3 == 1 && super.saveClickY >= j - 15 && super.saveClickY < j)
					loginScreenCursorPos = 0;
				j += 15;
				if(super.clickMode3 == 1 && super.saveClickY >= j - 15 && super.saveClickY < j)
					loginScreenCursorPos = 1;
				j += 15;
				int i1 = super.myWidth / 2 - 80;
				int k1 = super.myHeight / 2 + 50;
				k1 += 20;
				if(super.clickMode3 == 1 && super.saveClickX >= i1 - 75 && super.saveClickX <= i1 + 75 && super.saveClickY >= k1 - 20 && super.saveClickY <= k1 + 20)
				{
					loginFailures = 0;
					login(myUsername, myPassword, false);
					if(loggedIn)
						return;
				}
				i1 = super.myWidth / 2 + 80;
				if(super.clickMode3 == 1 && super.saveClickX >= i1 - 75 && super.saveClickX <= i1 + 75 && super.saveClickY >= k1 - 20 && super.saveClickY <= k1 + 20)
				{
					loginScreenState = 0;
 //				   myUsername = "";
 //				   myPassword = "";
				}
				do
				{
					int l1 = readChar(-796);
					if(l1 == -1)
						break;
					boolean flag1 = false;
					for(int i2 = 0; i2 < validUserPassChars.length(); i2++)
					{
						if(l1 != validUserPassChars.charAt(i2))
							continue;
						flag1 = true;
						break;
					}

					if(loginScreenCursorPos == 0)
					{
						if(l1 == 8 && myUsername.length() > 0)
							myUsername = myUsername.substring(0, myUsername.length() - 1);
						if(l1 == 9 || l1 == 10 || l1 == 13)
							loginScreenCursorPos = 1;
						if(flag1)
							myUsername += (char)l1;
						if(myUsername.length() > 12)
							myUsername = myUsername.substring(0, 12);
					} else
					if(loginScreenCursorPos == 1)
					{
						if(l1 == 8 && myPassword.length() > 0)
							myPassword = myPassword.substring(0, myPassword.length() - 1);
						if(l1 == 9 || l1 == 10 || l1 == 13)
							loginScreenCursorPos = 0;
						if(flag1)
							myPassword += (char)l1;
						if(myPassword.length() > 20)
							myPassword = myPassword.substring(0, 20);
					}
				} while(true);
				return;
			}
			if(loginScreenState == 3)
			{
				int k = super.myWidth / 2;
				int j1 = super.myHeight / 2 + 50;
				j1 += 20;
				if(super.clickMode3 == 1 && super.saveClickX >= k - 75 && super.saveClickX <= k + 75 && super.saveClickY >= j1 - 20 && super.saveClickY <= j1 + 20)
					loginScreenState = 0;
			}
		}
	}
}