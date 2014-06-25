package com.runescape.client.revised.client;

import java.applet.AppletContext;
import java.awt.Component;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.runescape.client.CacheArchive;
import com.runescape.client.Class36;
import com.runescape.client.RSApplet;
import com.runescape.client.SignLink;
import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.config.definitions.IdentityKit;
import com.runescape.client.revised.config.definitions.ItemDef;
import com.runescape.client.revised.config.definitions.NPCDef;
import com.runescape.client.revised.config.definitions.ObjectDef;
import com.runescape.client.revised.constants.Skills;
import com.runescape.client.revised.filesystem.Decompressor;
import com.runescape.client.revised.graphics.SceneGraphic;
import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.graphics.image.RSImageProducer;
import com.runescape.client.revised.media.IndexedImage;
import com.runescape.client.revised.media.RSFont;
import com.runescape.client.revised.media.map.TileSetting;
import com.runescape.client.revised.model.entity.player.RSPlayer;
import com.runescape.client.revised.util.MouseDetection;
import com.runescape.client.revised.util.encryption.Cryption;

public final class GameClient extends RSApplet {

	private static final long serialVersionUID = 4538442394730273243L;
	private Chat chat;
	private Memory memory;
	private RevisionType revisionType;
	private Camera camera;
	private LoginRunnable login;

	public void setChat(final Chat chat) {
		this.chat = chat;
	}

	public Chat getChat() {
		return this.chat;
	}

	public void setMemory(final Memory memory) {
		this.memory = memory;
	}

	public Memory getMemory() {
		return this.memory;
	}

	public void setRevisionType(final RevisionType revisionType) {
		this.revisionType = revisionType;
	}

	public RevisionType getRevisionType() {
		return this.revisionType;
	}

	public void setCamera(final Camera camera) {
		this.camera = camera;
	}

	public Camera getCamera() {
		return this.camera;
	}

	public void setLogin(final LoginRunnable login) {
		this.login = login;
	}

	public LoginRunnable getLogin() {
		return this.login;
	}

	/*
	 * Initialize the parameters.
	 */
	public void init()
	{
		/*
		 * Set the
		 */
		GameClient.nodeID = Integer.parseInt(this.getParameter("nodeid"));
		GameClient.portOff = Integer.parseInt(this.getParameter("portoff"));
		/*
		 * 
		 */
		final String s = this.getParameter("lowmem");
		if((s != null) && s.equals("1")) {
			setLowMem();
		} else {
			setHighMem();
		}
		final String s1 = this.getParameter("free");
		GameClient.isMembers = !((s1 != null) && s1.equals("1"));
		initClientFrame(503, 765);
	}

	public void startRunnable(final Runnable runnable, int i)
	{
		if(i > 10) {
			i = 10;
		}
		if(SignLink.applet != null)
		{
			SignLink.startThread(runnable, i);
		} else
		{
			super.startRunnable(runnable, i);
		}
	}

	private void unlinkMRUNodes()
	{
		ObjectDef.modelCache.unlinkAll();
		ObjectDef.modelCache2.unlinkAll();
		NPCDef.mruNodes.unlinkAll();
		ItemDef.mruNodes2.unlinkAll();
		ItemDef.mruNodes1.unlinkAll();
		RSPlayer.mruNodes.unlinkAll();
		AbstractGraphic.aMRUNodes_415.unlinkAll();
	}

	int method42(final int i, final int j, final int k)
	{
		final int l = k >> 7;
		final int i1 = j >> 7;
		if((l < 0) || (i1 < 0) || (l > 103) || (i1 > 103)) {
			return 0;
		}
		int j1 = i;
		if((j1 < 3) && ((this.tileSettings[1][l][i1] & 2) == 2)) {
			j1++;
		}
		final int k1 = k & 0x7f;
		final int l1 = j & 0x7f;
		final int i2 = ((this.intGroundArray[j1][l][i1] * (128 - k1)) + (this.intGroundArray[j1][l + 1][i1] * k1)) >> 7;
		final int j2 = ((this.intGroundArray[j1][l][i1 + 1] * (128 - k1)) + (this.intGroundArray[j1][l + 1][i1 + 1] * k1)) >> 7;
		return ((i2 * (128 - l1)) + (j2 * l1)) >> 7;
	}


	private void method45()
	{
		aBoolean1031 = true;
		for(int j = 0; j < 7; j++)
		{
			anIntArray1065[j] = -1;
			for(int k = 0; k < IdentityKit.length; k++)
			{
				if(IdentityKit.cache[k].notSelectable || (IdentityKit.cache[k].bodyPartID != (j + (aBoolean1047 ? 0 : 7)))) {
					continue;
				}
				anIntArray1065[j] = k;
				break;
			}
		}
	}

	public void processGameLoop()
	{
		if(this.rsAlreadyLoaded || this.loadingError || this.genericLoadingError) {
			return;
		}
		GameClient.loopCycle++;
		if(!this.loggedIn) {
			processLoginScreenInput();
		} else {
			this.mainGameProcessor();
		}
		this.processOnDemandQueue();
	}

	public static void main(final String args[]) {
		try {
			System.out.println("RS2 user client - release #" + 317);
			GameClient.nodeID = 10;
			GameClient.portOff = 0;
			setLowMem();
			GameClient.isMembers = false;
			SignLink.storeID = 32;
			SignLink.initialize(InetAddress.getLocalHost());
			final GameClient gameClient = new GameClient();
			gameClient.createClientFrame(503, 765);
		} catch(final Exception exception) {}
	}


	private int method54()
	{
		for(int i = 0; i < aByteArrayArray1183.length; i++)
		{
			if((aByteArrayArray1183[i] == null) && (anIntArray1235[i] != -1)) {
				return -1;
			}
			if((this.aByteArrayArray1247[i] == null) && (this.anIntArray1236[i] != -1)) {
				return -2;
			}
		}

		boolean flag = true;
		for(int j = 0; j < aByteArrayArray1183.length; j++)
		{
			final byte abyte0[] = this.aByteArrayArray1247[j];
			if(abyte0 != null)
			{
				int k = ((anIntArray1234[j] >> 8) * 64) - this.baseX;
				int l = ((anIntArray1234[j] & 0xff) * 64) - this.baseY;
				if(aBoolean1159)
				{
					k = 10;
					l = 10;
				}
				flag &= Region.method189(k, abyte0, l);
			}
		}

		if(!flag) {
			return -3;
		}
		if(aBoolean1080)
		{
			return -4;
		} else
		{
			this.loadingStage = 2;
			Region.anInt131 = this.plane;
			updateWorldObjects();
			this.stream.putPacketID(121);
			return 0;
		}
	}

	public AppletContext getAppletContext()
	{
		if(SignLink.mainapp != null) {
			return SignLink.mainapp.getAppletContext();
		} else {
			return super.getAppletContext();
		}
	}

	private void processOnDemandQueue()
	{
		do
		{
			OnDemandData onDemandData;
			do
			{
				onDemandData = this.onDemandFetcher.getNextNode();
				if(onDemandData == null) {
					return;
				}
				if(onDemandData.dataType == 0)
				{
					Model.method460(onDemandData.buffer, onDemandData.ID);
					if((this.onDemandFetcher.getModelIndex(onDemandData.ID) & 0x62) != 0)
					{
						this.needDrawTabArea = true;
						if(this.backDialogID != -1) {
							this.inputTaken = true;
						}
					}
				}
				if((onDemandData.dataType == 1) && (onDemandData.buffer != null)) {
					Class36.method529(onDemandData.buffer);
				}
				if((onDemandData.dataType == 2) && (onDemandData.ID == nextSong) && (onDemandData.buffer != null)) {
					saveMidi(songChanging, onDemandData.buffer);
				}
				if((onDemandData.dataType == 3) && (this.loadingStage == 1))
				{
					for(int i = 0; i < aByteArrayArray1183.length; i++)
					{
						if(anIntArray1235[i] == onDemandData.ID)
						{
							aByteArrayArray1183[i] = onDemandData.buffer;
							if(onDemandData.buffer == null) {
								anIntArray1235[i] = -1;
							}
							break;
						}
						if(this.anIntArray1236[i] != onDemandData.ID) {
							continue;
						}
						this.aByteArrayArray1247[i] = onDemandData.buffer;
						if(onDemandData.buffer == null) {
							this.anIntArray1236[i] = -1;
						}
						break;
					}

				}
			} while((onDemandData.dataType != 93) || !this.onDemandFetcher.method564(onDemandData.ID));
			Region.method173(new RSBuffer(onDemandData.buffer), this.onDemandFetcher);
		} while(true);
	}

	private void mainGameProcessor()
	{
		if(anInt1104 > 1) {
			anInt1104--;
		}
		if(this.anInt1011 > 0) {
			this.anInt1011--;
		}
		for(int j = 0; j < 5; j++) {
			if(!parsePacket()) {
				break;
			}
		}
		if(!this.loggedIn) {
			return;
		}
		synchronized(this.mouseDetection.syncObject)
		{
			if(flagged)
			{
				if((super.clickMode3 != 0) || (mouseDetection.coordsIndex >= 40))
				{
					stream.putPacketID(45);
					stream.putLEShort(0);
					int j2 = stream.currentOffset;
					int j3 = 0;
					for(int j4 = 0; j4 < mouseDetection.coordsIndex; j4++)
					{
						if((j2 - stream.currentOffset) >= 240) {
							break;
						}
						j3++;
						int l4 = mouseDetection.coordsY[j4];
						if(l4 < 0) {
							l4 = 0;
						} else
							if(l4 > 502) {
								l4 = 502;
							}
						int k5 = mouseDetection.coordsX[j4];
						if(k5 < 0) {
							k5 = 0;
						} else
							if(k5 > 764) {
								k5 = 764;
							}
						int i6 = (l4 * 765) + k5;
						if((mouseDetection.coordsY[j4] == -1) && (mouseDetection.coordsX[j4] == -1))
						{
							k5 = -1;
							l4 = -1;
							i6 = 0x7ffff;
						}
						if((k5 == anInt1237) && (l4 == anInt1238))
						{
							if(anInt1022 < 2047) {
								anInt1022++;
							}
						} else
						{
							int j6 = k5 - anInt1237;
							anInt1237 = k5;
							int k6 = l4 - anInt1238;
							anInt1238 = l4;
							if((anInt1022 < 8) && (j6 >= -32) && (j6 <= 31) && (k6 >= -32) && (k6 <= 31))
							{
								j6 += 32;
								k6 += 32;
								stream.putShort((anInt1022 << 12) + (j6 << 6) + k6);
								anInt1022 = 0;
							} else
								if(anInt1022 < 8)
								{
									stream.writeDWordBigEndian(0x800000 + (anInt1022 << 19) + i6);
									anInt1022 = 0;
								} else
								{
									stream.writeDWord(0xc0000000 + (anInt1022 << 19) + i6);
									anInt1022 = 0;
								}
						}
					}

					stream.writeBytes(stream.currentOffset - j2);
					if(j3 >= mouseDetection.coordsIndex)
					{
						mouseDetection.coordsIndex = 0;
					} else
					{
						mouseDetection.coordsIndex -= j3;
						for(int i5 = 0; i5 < mouseDetection.coordsIndex; i5++)
						{
							mouseDetection.coordsX[i5] = mouseDetection.coordsX[i5 + j3];
							mouseDetection.coordsY[i5] = mouseDetection.coordsY[i5 + j3];
						}

					}
				}
			} else
			{
				mouseDetection.coordsIndex = 0;
			}
		}
		if(super.clickMode3 != 0)
		{
			long l = (super.aLong29 - aLong1220) / 50L;
			if(l > 4095L) {
				l = 4095L;
			}
			aLong1220 = super.aLong29;
			int k2 = super.saveClickY;
			if(k2 < 0) {
				k2 = 0;
			} else
				if(k2 > 502) {
					k2 = 502;
				}
			int k3 = super.saveClickX;
			if(k3 < 0) {
				k3 = 0;
			} else
				if(k3 > 764) {
					k3 = 764;
				}
			final int k4 = (k2 * 765) + k3;
			int j5 = 0;
			if(super.clickMode3 == 2) {
				j5 = 1;
			}
			final int l5 = (int)l;
			this.stream.putPacketID(241);
			this.stream.writeDWord((l5 << 20) + (j5 << 19) + k4);
		}
		if(anInt1016 > 0) {
			anInt1016--;
		}
		if((super.keyArray[1] == 1) || (super.keyArray[2] == 1) || (super.keyArray[3] == 1) || (super.keyArray[4] == 1)) {
			aBoolean1017 = true;
		}
		if(aBoolean1017 && (anInt1016 <= 0))
		{
			anInt1016 = 20;
			aBoolean1017 = false;
			this.stream.putPacketID(86);
			this.stream.putShort(anInt1184);
			this.stream.method432(minimapInt1);
		}
		if(super.awtFocus && !aBoolean954)
		{
			aBoolean954 = true;
			this.stream.putPacketID(3);
			this.stream.putLEShort(1);
		}
		if(!super.awtFocus && aBoolean954)
		{
			aBoolean954 = false;
			this.stream.putPacketID(3);
			this.stream.putLEShort(0);
		}
		loadingStages();
		this.method115();
		handleMusicEvents();
		anInt1009++;
		if(anInt1009 > 750) {
			this.dropClient();
		}
		updatePlayerInstances();
		forceNPCUpdateBlock();
		resetSpokenText();
		anInt945++;
		if(this.crossType != 0)
		{
			this.crossIndex += 20;
			if(this.crossIndex >= 400) {
				this.crossType = 0;
			}
		}
		if(this.atInventoryInterfaceType != 0)
		{
			this.atInventoryLoopCycle++;
			if(this.atInventoryLoopCycle >= 15)
			{
				if(this.atInventoryInterfaceType == 2) {
					this.needDrawTabArea = true;
				}
				if(this.atInventoryInterfaceType == 3) {
					this.inputTaken = true;
				}
				this.atInventoryInterfaceType = 0;
			}
		}
		if(this.activeInterfaceType != 0)
		{
			this.anInt989++;
			if((super.mouseX > (anInt1087 + 5)) || (super.mouseX < (anInt1087 - 5)) || (super.mouseY > (anInt1088 + 5)) || (super.mouseY < (anInt1088 - 5))) {
				this.aBoolean1242 = true;
			}
			if(super.clickMode2 == 0)
			{
				if(this.activeInterfaceType == 2) {
					this.needDrawTabArea = true;
				}
				if(this.activeInterfaceType == 3) {
					this.inputTaken = true;
				}
				this.activeInterfaceType = 0;
				if(this.aBoolean1242 && (this.anInt989 >= 5))
				{
					this.lastActiveInvInterface = -1;
					processRightClick();
					if((this.lastActiveInvInterface == anInt1084) && (this.mouseInvInterfaceIndex != anInt1085))
					{
						final RSInterface class9 = RSInterface.interfaceCache[anInt1084];
						int j1 = 0;
						if((anInt913 == 1) && (class9.anInt214 == 206)) {
							j1 = 1;
						}
						if(class9.inv[this.mouseInvInterfaceIndex] <= 0) {
							j1 = 0;
						}
						if(class9.aBoolean235)
						{
							final int l2 = anInt1085;
							final int l3 = this.mouseInvInterfaceIndex;
							class9.inv[l3] = class9.inv[l2];
							class9.invStackSizes[l3] = class9.invStackSizes[l2];
							class9.inv[l2] = -1;
							class9.invStackSizes[l2] = 0;
						} else
							if(j1 == 1)
							{
								int i3 = anInt1085;
								for(final int i4 = this.mouseInvInterfaceIndex; i3 != i4;) {
									if(i3 > i4)
									{
										class9.swapInventoryItems(i3, i3 - 1);
										i3--;
									} else
										if(i3 < i4)
										{
											class9.swapInventoryItems(i3, i3 + 1);
											i3++;
										}
								}

							} else
							{
								class9.swapInventoryItems(anInt1085, this.mouseInvInterfaceIndex);
							}
						this.stream.putPacketID(214);
						this.stream.method433(anInt1084);
						this.stream.method424(j1);
						this.stream.method433(anInt1085);
						this.stream.method431(this.mouseInvInterfaceIndex);
					}
				} else
					if(((anInt1253 == 1) || menuHasAddFriend(menuActionRow - 1)) && (menuActionRow > 2)) {
						determineMenuSize();
					} else
						if(menuActionRow > 0) {
							doAction(menuActionRow - 1);
						}
				this.atInventoryLoopCycle = 10;
				super.clickMode3 = 0;
			}
		}
		if(SceneGraphic.anInt470 != -1)
		{
			final int k = SceneGraphic.anInt470;
			final int k1 = SceneGraphic.anInt471;
			final boolean flag = doWalkTo(0, 0, 0, 0, GameClient.myPlayer.smallY[0], 0, 0, k1, GameClient.myPlayer.smallX[0], true, k);
			SceneGraphic.anInt470 = -1;
			if(flag)
			{
				this.crossX = super.saveClickX;
				this.crossY = super.saveClickY;
				this.crossType = 1;
				this.crossIndex = 0;
			}
		}
		if((super.clickMode3 == 1) && (clickToContinueString != null))
		{
			clickToContinueString = null;
			this.inputTaken = true;
			super.clickMode3 = 0;
		}
		processMenuClick();
		processMainScreenClick();
		processTabClick();
		processChatModeClick();
		if((super.clickMode2 == 1) || (super.clickMode3 == 1)) {
			this.anInt1213++;
		}
		if(this.loadingStage == 2) {
			this.checkGameUsages();
		}
		if((this.loadingStage == 2) && aBoolean1160) {
			calcCameraPos();
		}
		for(int i1 = 0; i1 < 5; i1++) {
			anIntArray1030[i1]++;
		}

		manageTextInputs();
		super.idleTime++;
		if(super.idleTime > 4500)
		{
			this.anInt1011 = 250;
			super.idleTime -= 500;
			this.stream.putPacketID(202);
		}
		this.anInt988++;
		if(this.anInt988 > 500)
		{
			this.anInt988 = 0;
			final int l1 = (int)(Math.random() * 8D);
			if((l1 & 1) == 1) {
				anInt1278 += anInt1279;
			}
			if((l1 & 2) == 2) {
				anInt1131 += anInt1132;
			}
			if((l1 & 4) == 4) {
				anInt896 += anInt897;
			}
		}
		if(anInt1278 < -50) {
			anInt1279 = 2;
		}
		if(anInt1278 > 50) {
			anInt1279 = -2;
		}
		if(anInt1131 < -55) {
			anInt1132 = 2;
		}
		if(anInt1131 > 55) {
			anInt1132 = -2;
		}
		if(anInt896 < -40) {
			anInt897 = 1;
		}
		if(anInt896 > 40) {
			anInt897 = -1;
		}
		this.anInt1254++;
		if(this.anInt1254 > 500)
		{
			this.anInt1254 = 0;
			final int i2 = (int)(Math.random() * 8D);
			if((i2 & 1) == 1) {
				minimapInt2 += anInt1210;
			}
			if((i2 & 2) == 2) {
				minimapInt3 += anInt1171;
			}
		}
		if(minimapInt2 < -60) {
			anInt1210 = 2;
		}
		if(minimapInt2 > 60) {
			anInt1210 = -2;
		}
		if(minimapInt3 < -20) {
			anInt1171 = 1;
		}
		if(minimapInt3 > 10) {
			anInt1171 = -1;
		}
		this.anInt1010++;
		if(this.anInt1010 > 50) {
			this.stream.putPacketID(0);
		}
		try
		{
			if((this.socketStream != null) && (this.stream.currentOffset > 0))
			{
				this.socketStream.queueBytes(this.stream.currentOffset, this.stream.buffer);
				this.stream.currentOffset = 0;
				this.anInt1010 = 0;
			}
		}
		catch(final IOException _ex)
		{
			this.dropClient();
		}
		catch(final Exception exception)
		{
			resetLogout();
		}
	}

	private void method63()
	{
		SpawnObjectNode class30_sub1 = (SpawnObjectNode)aClass19_1179.reverseGetFirst();
		for(; class30_sub1 != null; class30_sub1 = (SpawnObjectNode)aClass19_1179.reverseGetNext()) {
			if(class30_sub1.anInt1294 == -1)
			{
				class30_sub1.anInt1302 = 0;
				this.method89(class30_sub1);
			} else
			{
				class30_sub1.unlink();
			}
		}

	}


	private void method65(final int i, final int j, final int k, final int l, final RSInterface class9, final int i1, final boolean flag,
			final int j1)
	{
		int anInt992;
		if(this.aBoolean972) {
			anInt992 = 32;
		} else {
			anInt992 = 0;
		}
		this.aBoolean972 = false;
		if((k >= i) && (k < (i + 16)) && (l >= i1) && (l < (i1 + 16)))
		{
			class9.scrollPosition -= this.anInt1213 * 4;
			if(flag)
			{
				this.needDrawTabArea = true;
			}
		} else
			if((k >= i) && (k < (i + 16)) && (l >= ((i1 + j) - 16)) && (l < (i1 + j)))
			{
				class9.scrollPosition += this.anInt1213 * 4;
				if(flag)
				{
					this.needDrawTabArea = true;
				}
			} else
				if((k >= (i - anInt992)) && (k < (i + 16 + anInt992)) && (l >= (i1 + 16)) && (l < ((i1 + j) - 16)) && (this.anInt1213 > 0))
				{
					int l1 = ((j - 32) * j) / j1;
					if(l1 < 8) {
						l1 = 8;
					}
					final int i2 = l - i1 - 16 - (l1 / 2);
					final int j2 = j - 32 - l1;
					class9.scrollPosition = ((j1 - j) * i2) / j2;
					if(flag) {
						this.needDrawTabArea = true;
					}
					this.aBoolean972 = true;
				}
	}


	private CacheArchive streamLoaderForName(final int i, final String s, final String s1, final int j, final int k)
	{
		byte abyte0[] = null;
		int l = 5;
		try
		{
			if(this.decompressors[0] != null) {
				abyte0 = this.decompressors[0].decompress(i);
			}
		}
		catch(final Exception _ex) { }
		if(abyte0 != null)
		{
			//		aCRC32_930.reset();
			//		aCRC32_930.update(abyte0);
			//		int i1 = (int)aCRC32_930.getValue();
			//		if(i1 != j)
		}
		if(abyte0 != null)
		{
			final CacheArchive streamLoader = new CacheArchive(abyte0);
			return streamLoader;
		}
		final int j1 = 0;
		while(abyte0 == null)
		{
			String s2 = "Unknown error";
			drawLoadingText(k, "Requesting " + s);
			try
			{
				int k1 = 0;
				final DataInputStream datainputstream = this.openJagGrabInputStream(s1 + j);
				final byte abyte1[] = new byte[6];
				datainputstream.readFully(abyte1, 0, 6);
				final RSBuffer stream = new RSBuffer(abyte1);
				stream.currentOffset = 3;
				final int i2 = stream.read3Bytes() + 6;
				int j2 = 6;
				abyte0 = new byte[i2];
				System.arraycopy(abyte1, 0, abyte0, 0, 6);

				while(j2 < i2)
				{
					int l2 = i2 - j2;
					if(l2 > 1000) {
						l2 = 1000;
					}
					final int j3 = datainputstream.read(abyte0, j2, l2);
					if(j3 < 0)
					{
						s2 = "Length error: " + j2 + "/" + i2;
						throw new IOException("EOF");
					}
					j2 += j3;
					final int k3 = (j2 * 100) / i2;
					if(k3 != k1) {
						drawLoadingText(k, "Loading " + s + " - " + k3 + "%");
					}
					k1 = k3;
				}
				datainputstream.close();
				try
				{
					if(this.decompressors[0] != null) {
						this.decompressors[0].method234(abyte0.length, abyte0, i);
					}
				}
				catch(final Exception _ex)
				{
					this.decompressors[0] = null;
				}
				//			 if(abyte0 != null)
				//			{
				//				aCRC32_930.reset();
				//			aCRC32_930.update(abyte0);
				//			int i3 = (int)aCRC32_930.getValue();
				//			if(i3 != j)
				//				{
				//					abyte0 = null;
				//					j1++;
				//					s2 = "Checksum error: " + i3;
				//			}
				//			}
				//
			}
			catch(final IOException ioexception)
			{
				if(s2.equals("Unknown error")) {
					s2 = "Connection error";
				}
				abyte0 = null;
			}
			catch(final NullPointerException _ex)
			{
				s2 = "Null error";
				abyte0 = null;
				if(!SignLink.reporterror) {
					return null;
				}
			}
			catch(final ArrayIndexOutOfBoundsException _ex)
			{
				s2 = "Bounds error";
				abyte0 = null;
				if(!SignLink.reporterror) {
					return null;
				}
			}
			catch(final Exception _ex)
			{
				s2 = "Unexpected error";
				abyte0 = null;
				if(!SignLink.reporterror) {
					return null;
				}
			}
			if(abyte0 == null)
			{
				for(int l1 = l; l1 > 0; l1--)
				{
					if(j1 >= 3)
					{
						drawLoadingText(k, "Game updated - please reload page");
						l1 = 10;
					} else
					{
						drawLoadingText(k, s2 + " - Retrying in " + l1);
					}
					try
					{
						Thread.sleep(1000L);
					}
					catch(final Exception _ex) { }
				}

				l *= 2;
				if(l > 60) {
					l = 60;
				}
				this.aBoolean872 = !this.aBoolean872;
			}

		}

		final CacheArchive streamLoader_1 = new CacheArchive(abyte0);
		return streamLoader_1;
	}

	/*
	 * Stop the client and reload it.
	 */
	private void dropClient()
	{
		if(this.anInt1011 > 0)
		{

			/*
			 * Reset the logout.
			 */
			resetLogout();
			return;
		}
		aRSImageProducer_1165.initDrawingArea();
		aTextDrawingArea_1271.drawText(0, "Connection lost", 144, 257);
		aTextDrawingArea_1271.drawText(0xffffff, "Connection lost", 143, 256);
		aTextDrawingArea_1271.drawText(0, "Please wait - attempting to reestablish", 159, 257);
		aTextDrawingArea_1271.drawText(0xffffff, "Please wait - attempting to reestablish", 158, 256);
		aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
		anInt1021 = 0;
		this.destX = 0;
		final RSSocket rsSocket = this.socketStream;
		this.loggedIn = false;
		loginFailures = 0;
		login(myUsername, myPassword, true);
		if(!this.loggedIn) {
			resetLogout();
		}
		try
		{
			rsSocket.close();
		}
		catch(final Exception _ex)
		{
		}
	}

	public void run()
	{

		/*
		 * If we are drawing the flames.
		 */
		if(drawFlames) {

			/*
			 * Draw the flames.
			 */
			drawFlames();

			/*
			 * Otherwise.
			 */
		} else {

			/*
			 * Run the thread/start the game.
			 */
			super.run();
		}
	}

	/*
	 * Debugging information.
	 */
	private void printDebug()
	{
		System.out.println("============");
		System.out.println("flame-cycle:" + flameCycle);
		if(this.onDemandFetcher != null) {
			System.out.println("Od-cycle:" + this.onDemandFetcher.onDemandCycle);
		}
		System.out.println("loop-cycle:" + GameClient.loopCycle);
		System.out.println("draw-cycle:" + GameClient.drawCycle);
		System.out.println("ptype:" + this.pktType);
		System.out.println("psize:" + this.pktSize);
		if(this.socketStream != null) {
			this.socketStream.printDebug();
		}
		super.shouldDebug = true;
	}

	Component getGameComponent()
	{
		if(SignLink.mainapp != null) {
			return SignLink.mainapp;
		}
		if(super.gameFrame != null) {
			return super.gameFrame;
		} else {
			return this;
		}
	}


	private void resetImageProducers2()
	{
		if(this.aRSImageProducer_1166 != null) {
			return;
		}
		this.nullLoader();
		super.fullGameScreen = null;
		this.aRSImageProducer_1107 = null;
		this.aRSImageProducer_1108 = null;
		this.aRSImageProducer_1109 = null;
		this.aRSImageProducer_1110 = null;
		this.aRSImageProducer_1111 = null;
		this.aRSImageProducer_1112 = null;
		this.aRSImageProducer_1113 = null;
		this.aRSImageProducer_1114 = null;
		this.aRSImageProducer_1115 = null;
		this.aRSImageProducer_1166 = new RSImageProducer(479, 96, this.getGameComponent());
		aRSImageProducer_1164 = new RSImageProducer(172, 156, this.getGameComponent());
		DrawingArea.setAllPixelsToZero();
		mapBack.method361(0, 0);
		aRSImageProducer_1163 = new RSImageProducer(190, 261, this.getGameComponent());
		aRSImageProducer_1165 = new RSImageProducer(512, 334, this.getGameComponent());
		DrawingArea.setAllPixelsToZero();
		this.aRSImageProducer_1123 = new RSImageProducer(496, 50, this.getGameComponent());
		this.aRSImageProducer_1124 = new RSImageProducer(269, 37, this.getGameComponent());
		this.aRSImageProducer_1125 = new RSImageProducer(249, 45, this.getGameComponent());
		this.welcomeScreenRaised = true;
	}

	@SuppressWarnings("unused")
	private String getDocumentBaseHost()
	{
		if(SignLink.mainapp != null) {
			return SignLink.mainapp.getDocumentBase().getHost().toLowerCase();
		}
		if(super.gameFrame != null) {
			return "";
		} else {
			return "";
		}
	}


	private void method89(final SpawnObjectNode class30_sub1)
	{
		int i = 0;
		int j = -1;
		int k = 0;
		int l = 0;
		if(class30_sub1.anInt1296 == 0) {
			i = this.worldController.method300(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		}
		if(class30_sub1.anInt1296 == 1) {
			i = this.worldController.method301(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		}
		if(class30_sub1.anInt1296 == 2) {
			i = this.worldController.method302(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		}
		if(class30_sub1.anInt1296 == 3) {
			i = this.worldController.method303(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		}
		if(i != 0)
		{
			final int i1 = this.worldController.method304(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298, i);
			j = (i >> 14) & 0x7fff;
			k = i1 & 0x1f;
			l = i1 >> 6;
		}
		class30_sub1.anInt1299 = j;
		class30_sub1.anInt1301 = k;
		class30_sub1.anInt1300 = l;
	}



	private String interfaceIntToString(final int j)
	{
		if(j < 0x3b9ac9ff) {
			return String.valueOf(j);
		} else {
			return "*";
		}
	}

	public URL getCodeBase()
	{
		if(SignLink.mainapp != null) {
			return SignLink.mainapp.getCodeBase();
		}
		try
		{
			if(super.gameFrame != null) {
				return new URL("http://" + this.server + ":" + (80 + GameClient.portOff));
			}
		}
		catch(final Exception _ex) { }
		return super.getCodeBase();
	}



	private void randomizeBackground(final IndexedImage background)
	{
		final int j = 256;
		for(int k = 0; k < this.anIntArray1190.length; k++) {
			this.anIntArray1190[k] = 0;
		}

		for(int l = 0; l < 5000; l++)
		{
			final int i1 = (int)(Math.random() * 128D * j);
			this.anIntArray1190[i1] = (int)(Math.random() * 256D);
		}

		for(int j1 = 0; j1 < 20; j1++)
		{
			for(int k1 = 1; k1 < (j - 1); k1++)
			{
				for(int i2 = 1; i2 < 127; i2++)
				{
					final int k2 = i2 + (k1 << 7);
					this.anIntArray1191[k2] = (this.anIntArray1190[k2 - 1] + this.anIntArray1190[k2 + 1] + this.anIntArray1190[k2 - 128] + this.anIntArray1190[k2 + 128]) / 4;
				}

			}

			final int ai[] = this.anIntArray1190;
			this.anIntArray1190 = this.anIntArray1191;
			this.anIntArray1191 = ai;
		}

		if(background != null)
		{
			int l1 = 0;
			for(int j2 = 0; j2 < background.anInt1453; j2++)
			{
				for(int l2 = 0; l2 < background.anInt1452; l2++) {
					if(background.aByteArray1450[l1++] != 0)
					{
						final int i3 = l2 + 16 + background.anInt1454;
						final int j3 = j2 + 16 + background.anInt1455;
						final int k3 = i3 + (j3 << 7);
						this.anIntArray1190[k3] = 0;
					}
				}

			}
		}
	}

	private void checkGameUsages()
	{
		try
		{
			final int j = GameClient.myPlayer.x + anInt1278;
			final int k = GameClient.myPlayer.y + anInt1131;
			if(((this.anInt1014 - j) < -500) || ((this.anInt1014 - j) > 500) || ((this.anInt1015 - k) < -500) || ((this.anInt1015 - k) > 500))
			{
				this.anInt1014 = j;
				this.anInt1015 = k;
			}
			if(this.anInt1014 != j) {
				this.anInt1014 += (j - this.anInt1014) / 16;
			}
			if(this.anInt1015 != k) {
				this.anInt1015 += (k - this.anInt1015) / 16;
			}
			if(super.keyArray[1] == 1) {
				this.anInt1186 += (-24 - this.anInt1186) / 2;
			} else
				if(super.keyArray[2] == 1) {
					this.anInt1186 += (24 - this.anInt1186) / 2;
				} else {
					this.anInt1186 /= 2;
				}
			if(super.keyArray[3] == 1) {
				this.anInt1187 += (12 - this.anInt1187) / 2;
			} else
				if(super.keyArray[4] == 1) {
					this.anInt1187 += (-12 - this.anInt1187) / 2;
				} else {
					this.anInt1187 /= 2;
				}
			minimapInt1 = (minimapInt1 + (this.anInt1186 / 2)) & 0x7ff;
			anInt1184 += this.anInt1187 / 2;
			if(anInt1184 < 128) {
				anInt1184 = 128;
			}
			if(anInt1184 > 383) {
				anInt1184 = 383;
			}
			final int l = this.anInt1014 >> 7;
						final int i1 = this.anInt1015 >> 7;
				final int j1 = this.method42(this.plane, this.anInt1015, this.anInt1014);
				int k1 = 0;
				if((l > 3) && (i1 > 3) && (l < 100) && (i1 < 100))
				{
					for(int l1 = l - 4; l1 <= (l + 4); l1++)
					{
						for(int k2 = i1 - 4; k2 <= (i1 + 4); k2++)
						{
							int l2 = this.plane;
							if((l2 < 3) && ((this.tileSettings[1][l1][k2] & 2) == 2)) {
								l2++;
							}
							final int i3 = j1 - this.intGroundArray[l2][l1][k2];
							if(i3 > k1) {
								k1 = i3;
							}
						}

					}

				}
				anInt1005++;
				if(anInt1005 > 1512)
				{
					anInt1005 = 0;
					this.stream.putPacketID(77);
					this.stream.putLEShort(0);
					final int i2 = this.stream.currentOffset;
					this.stream.putLEShort((int)(Math.random() * 256D));
					this.stream.putLEShort(101);
					this.stream.putLEShort(233);
					this.stream.putShort(45092);
					if((int)(Math.random() * 2D) == 0) {
						this.stream.putShort(35784);
					}
					this.stream.putLEShort((int)(Math.random() * 256D));
					this.stream.putLEShort(64);
					this.stream.putLEShort(38);
					this.stream.putShort((int)(Math.random() * 65536D));
					this.stream.putShort((int)(Math.random() * 65536D));
					this.stream.writeBytes(this.stream.currentOffset - i2);
				}
				int j2 = k1 * 192;
				if(j2 > 0x17f00) {
					j2 = 0x17f00;
				}
				if(j2 < 32768) {
					j2 = 32768;
				}
				if(j2 > this.anInt984)
				{
					this.anInt984 += (j2 - this.anInt984) / 24;
					return;
				}
				if(j2 < this.anInt984)
				{
					this.anInt984 += (j2 - this.anInt984) / 80;
				}
		}
		catch(final Exception _ex)
		{
			SignLink.reporterror("glfc_ex " + GameClient.myPlayer.x + "," + GameClient.myPlayer.y + "," + this.anInt1014 + "," + this.anInt1015 + "," + this.anInt1069 + "," + this.anInt1070 + "," + this.baseX + "," + this.baseY);
			throw new RuntimeException("eek");
		}
	}

	public void processDrawing()
	{
		if(this.rsAlreadyLoaded || this.loadingError || this.genericLoadingError)
		{
			showErrorScreen();
			return;
		}
		GameClient.drawCycle++;
		if(!this.loggedIn) {
			drawLoginScreen(false);
		} else {
			drawGameScreen();
		}
		this.anInt1213 = 0;
	}


	private void method115()
	{
		if(this.loadingStage == 2)
		{
			for(SpawnObjectNode class30_sub1 = (SpawnObjectNode)aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (SpawnObjectNode)aClass19_1179.reverseGetNext())
			{
				if(class30_sub1.anInt1294 > 0) {
					class30_sub1.anInt1294--;
				}
				if(class30_sub1.anInt1294 == 0)
				{
					if((class30_sub1.anInt1299 < 0) || Region.method178(class30_sub1.anInt1299, class30_sub1.anInt1301))
					{
						this.method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1300, class30_sub1.anInt1301, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1299);
						class30_sub1.unlink();
					}
				} else
				{
					if(class30_sub1.anInt1302 > 0) {
						class30_sub1.anInt1302--;
					}
					if((class30_sub1.anInt1302 == 0) && (class30_sub1.anInt1297 >= 1) && (class30_sub1.anInt1298 >= 1) && (class30_sub1.anInt1297 <= 102) && (class30_sub1.anInt1298 <= 102) && ((class30_sub1.anInt1291 < 0) || Region.method178(class30_sub1.anInt1291, class30_sub1.anInt1293)))
					{
						this.method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1292, class30_sub1.anInt1293, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1291);
						class30_sub1.anInt1302 = -1;
						if((class30_sub1.anInt1291 == class30_sub1.anInt1299) && (class30_sub1.anInt1299 == -1)) {
							class30_sub1.unlink();
						} else
							if((class30_sub1.anInt1291 == class30_sub1.anInt1299) && (class30_sub1.anInt1292 == class30_sub1.anInt1300) && (class30_sub1.anInt1293 == class30_sub1.anInt1301)) {
								class30_sub1.unlink();
							}
					}
				}
			}
		}
	}


	private void nullLoader()
	{
		currentlyDrawingFlames = false;
		while(drawingFlames)
		{
			currentlyDrawingFlames = false;
			try
			{
				Thread.sleep(50L);
			}
			catch(final Exception _ex) { }
		}
		this.aBackground_966 = null;
		this.aBackground_967 = null;
		this.aBackgroundArray1152s = null;
		currentFlameColors = null;
		flameColorBuffer1 = null;
		flameColorBuffer2 = null;
		flameColorBuffer3 = null;
		this.anIntArray1190 = null;
		this.anIntArray1191 = null;
		this.anIntArray828 = null;
		this.anIntArray829 = null;
		this.aClass30_Sub2_Sub1_Sub1_1201 = null;
		this.aClass30_Sub2_Sub1_Sub1_1202 = null;
	}

	public String getParameter(final String s)
	{
		if(SignLink.mainapp != null) {
			return SignLink.mainapp.getParameter(s);
		} else {
			return super.getParameter(s);
		}
	}


	private DataInputStream openJagGrabInputStream(final String s)
			throws IOException
			{
		//	   if(!aBoolean872)
		//		   if(signlink.mainapp != null)
		//			   return signlink.openurl(s);
		//		   else
		//			   return new DataInputStream((new URL(getCodeBase(), s)).openStream());
		if(this.aSocket832 != null)
		{
			try
			{
				this.aSocket832.close();
			}
			catch(final Exception _ex) { }
			this.aSocket832 = null;
		}
		this.aSocket832 = openSocket(43595);
		this.aSocket832.setSoTimeout(10000);
		final java.io.InputStream inputstream = this.aSocket832.getInputStream();
		final OutputStream outputstream = this.aSocket832.getOutputStream();
		outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
		return new DataInputStream(inputstream);
			}


	private void method142(final int i, final int j, final int k, final int l, final int i1, final int j1, final int k1
			)
	{
		if((i1 >= 1) && (i >= 1) && (i1 <= 102) && (i <= 102))
		{
			if(GameClient.lowMem && (j != this.plane)) {
				return;
			}
			int i2 = 0;
			if(j1 == 0) {
				i2 = this.worldController.method300(j, i1, i);
			}
			if(j1 == 1) {
				i2 = this.worldController.method301(j, i1, i);
			}
			if(j1 == 2) {
				i2 = this.worldController.method302(j, i1, i);
			}
			if(j1 == 3) {
				i2 = this.worldController.method303(j, i1, i);
			}
			if(i2 != 0)
			{
				final int i3 = this.worldController.method304(j, i1, i, i2);
				final int j2 = (i2 >> 14) & 0x7fff;
				final int k2 = i3 & 0x1f;
				final int l2 = i3 >> 6;
					if(j1 == 0)
					{
						this.worldController.method291(i1, j, i, (byte)-119);
						final ObjectDef class46 = ObjectDef.forID(j2);
						if(class46.isUnwalkable) {
							aClass11Array1230[j].method215(l2, k2, class46.aBoolean757, i1, i);
						}
					}
					if(j1 == 1) {
						this.worldController.method292(i, j, i1);
					}
					if(j1 == 2)
					{
						this.worldController.method293(j, i1, i);
						final ObjectDef class46_1 = ObjectDef.forID(j2);
						if(((i1 + class46_1.width) > 103) || ((i + class46_1.width) > 103) || ((i1 + class46_1.height) > 103) || ((i + class46_1.height) > 103)) {
							return;
						}
						if(class46_1.isUnwalkable) {
							aClass11Array1230[j].method216(l2, class46_1.width, i1, i, class46_1.height, class46_1.aBoolean757);
						}
					}
					if(j1 == 3)
					{
						this.worldController.method294(j, i, i1);
						final ObjectDef class46_2 = ObjectDef.forID(j2);
						if(class46_2.isUnwalkable && class46_2.hasActions) {
							aClass11Array1230[j].method218(i, i1);
						}
					}
			}
			if(k1 >= 0)
			{
				int j3 = j;
				if((j3 < 3) && ((this.tileSettings[1][i1][i] & 2) == 2)) {
					j3++;
				}
				Region.method188(this.worldController, k, i, l, j3, aClass11Array1230[j], this.intGroundArray, i1, k1, j);
			}
		}
	}


	private GameClient()
	{
		this.server = "127.0.0.1";
		this.anIntArrayArray825 = new int[104][104];
		this.groundArray = new Deque[4][104][104];
		this.aStream_834 = new RSBuffer(new byte[5000]);
		sessionNPCs = new NPC[16384];
		npcIndices = new int[16384];
		this.anIntArray840 = new int[1000];
		this.aStream_847 = RSBuffer.create();
		this.wave_on = true;
		this.openInterfaceID = -1;
		this.aBoolean872 = false;
		this.anIntArray873 = new int[5];
		this.anInt874 = -1;
		this.reportAbuseInput = "";
		this.playerID = -1;
		this.inputString = "";
		this.maxPlayers = 2048;
		this.myPlayerIndex = 2047;
		this.playerArray = new Player[this.maxPlayers];
		this.playerIndices = new int[this.maxPlayers];
		this.anIntArray894 = new int[this.maxPlayers];
		this.aStreamArray895s = new RSBuffer[this.maxPlayers];
		this.nextViewRotationOffset = 1;
		this.anIntArrayArray901 = new int[104][104];
		this.anInt902 = 0x766654;
		this.animatedPixels = new byte[16384];
		this.loadingError = false;
		this.anInt927 = 0x332d25;
		this.anIntArray928 = new int[5];
		this.anIntArrayArray929 = new int[104][104];
		this.sideIcons = new IndexedImage[13];
		this.wasFocused = true;
		this.spriteDrawX = -1;
		this.spriteDrawY = -1;
		this.compassHingeSize = new int[33];
		this.anIntArray969 = new int[256];
		this.decompressors = new Decompressor[5];
		this.variousSettings = new int[2000];
		this.aBoolean972 = false;
		this.cachedChatAmmount = 50;
		this.anIntArray976 = new int[this.cachedChatAmmount];
		this.anIntArray977 = new int[this.cachedChatAmmount];
		this.anIntArray978 = new int[this.cachedChatAmmount];
		this.anIntArray979 = new int[this.cachedChatAmmount];
		this.textColorEffect = new int[this.cachedChatAmmount];
		this.textDrawType = new int[this.cachedChatAmmount];
		this.anIntArray982 = new int[this.cachedChatAmmount];
		this.aStringArray983 = new String[this.cachedChatAmmount];
		this.anInt985 = -1;
		this.hitMarks = new AbstractSprite[20];
		this.charEditColors = new int[5];
		this.aBoolean994 = false;
		this.anInt1002 = 0x23201b;
		this.projectileDeque = new Deque();
		this.currentStatusInterface = -1;
		this.char_edit_model_changed = false;
		this.dialogID = -1;
		this.maxStats = new int[Skills.skillsCount];
		this.anIntArray1045 = new int[2000];
		this.char_edit_gender = true;
		this.flashingSideBarID = -1;
		this.stillGraphicDeque = new Deque();
		this.aClass9_1059 = new RSInterface();
		anInt1063 = 0x4d4233;
		this.char_edit_idkits = new int[7];
		this.markPosX = new int[1000];
		this.markPosY = new int[1000];
		this.inStream = RSBuffer.create();
		this.expectedCRCs = new int[9];
		this.headIcons = new AbstractSprite[20];
		this.tabAreaAltered = false;
		this.atPlayerActions = new String[5];
		this.atPlayerArray = new boolean[5];
		this.constructionMapInformation = new int[4][13][13];
		this.nextYOffsetChange = 2;
		this.markGraphic = new AbstractSprite[1000];
		this.tutorialIsland = false;
		this.isDialogueInterface = false;
		this.crosses = new AbstractSprite[8];
		this.musicEnabled = true;
		this.needDrawTabArea = false;
		this.loggedIn = false;
		this.canMute = false;
		this.inCutscene = false;
		this.genericLoadingError = false;
		this.reportAbuseInterfaceID = -1;
		this.gameObjectSpawnDeque = new Deque();
		this.invOverlayInterfaceID = -1;
		this.stream = RSBuffer.create();
		this.customLowestYaw = new int[5];
		this.scrollMax = 78;
		this.promptInput = "";
		this.modIcons = new IndexedImage[2];
		this.tabID = 3;
		this.inputTaken = false;
		this.tileSettings = new TileSetting[4];
		this.anIntArray1240 = new int[100];
		this.anIntArray1241 = new int[50];
		this.aBoolean1242 = false;
		this.anIntArray1250 = new int[50];
		this.rsAlreadyLoaded = false;
		this.welcomeScreenRaised = false;
		this.messagePromptRaised = false;
		this.backDialogID = -1;
		this.nextXOffsetChange = 2;
		this.bigX = new int[4000];
		this.bigY = new int[4000];
	}

	public String server;
	public long aLong824;
	public int[][] anIntArrayArray825;
	public Deque[][][] groundArray;
	public int[] anIntArray828;
	public int[] anIntArray829;
	public Socket aSocket832;
	public RSBuffer aStream_834;
	public int anInt839;
	public int[] anIntArray840;
	public int anInt841;
	public int anInt842;
	public int anInt843;
	public RSBuffer aStream_847;
	public boolean wave_on; // aBoolean848
	public static int anticheat10; // anInt849
	public static int anticheat11; // anInt854
	public int headiconDrawType; // anIntArray855
	public int openInterfaceID;
	public int myPrivilege;
	public IndexedImage redStone1_3;
	public IndexedImage redStone2_3;
	public IndexedImage redStone3_2;
	public IndexedImage redStone1_4;
	public IndexedImage redStone2_4;
	public boolean aBoolean872;
	public final int[] anIntArray873;
	public int anInt874;
	public int weight;
	public MouseDetection mouseDetection;
	public String reportAbuseInput;
	public int playerID; // unknownInt10
	public int anInt886;
	public String inputString;
	public final int maxPlayers;
	public final int myPlayerIndex;
	public Player[] playerArray;
	public int playerCount;
	public int[] playerIndices;
	public int sessionNpcsAwaitingUpdatePtr; // anInt893
	public int[] anIntArray894;
	public RSBuffer[] aStreamArray895s;
	public int viewRotationOffset; // anInt896
	public int nextViewRotationOffset; // anInt897
	public int network_friends_server_status; // anInt900
	public int[][] anIntArrayArray901;
	public final int anInt902;
	public RSImageProducer backLeftIP1;
	public RSImageProducer backLeftIP2;
	public RSImageProducer backRightIP1;
	public RSImageProducer backRightIP2;
	public RSImageProducer backTopIP1;
	public RSImageProducer backVmidIP1;
	public RSImageProducer backVmidIP2;
	public RSImageProducer backVmidIP3;
	public RSImageProducer backVmidIP2_2;
	public byte[] animatedPixels; // aByteArray912
	public int bankInsertMode; // anInt913
	public int crossX;
	public int crossY;
	public int crossIndex;
	public int crossType;
	public int plane;
	public static int antiCheat13; // anInt924
	public boolean loadingError;
	public final int anInt927;
	public final int[] anIntArray928;
	public int[][] anIntArrayArray929;
	public AbstractSprite char_edit_inactive_button; // aClass30_Sub2_Sub1_Sub1_931
	public AbstractSprite char_edit_active_button; // aClass30_Sub2_Sub1_Sub1_932
	public int otherPlayerID; // anInt933
	public int headIconX; // anInt934
	public int headIconY; // anInt935
	public int headIconHeight; // anInt936
	public int arrowDrawTileX; // anInt937
	public int arrowDrawTileY; // anInt938
	public static int antiCheat14; // anInt940
	public int animationTimePassed; // anInt945
	public SceneGraphic worldController;
	public IndexedImage[] sideIcons;
	public long aLong953;
	public boolean wasFocused; // aBoolean954
	public static int nodeID = 10;
	public static int portOff;
	public static boolean isMembers = true;
	public static boolean lowMem;
	public int spriteDrawX;
	public int spriteDrawY;
	public IndexedImage aBackground_966;
	public IndexedImage aBackground_967;
	public final int[] compassHingeSize; // anIntArray968
	public final int[] anIntArray969;
	public final Decompressor[] decompressors;
	public int variousSettings[];
	public boolean aBoolean972;
	public final int cachedChatAmmount; // anInt975
	public final int[] anIntArray976;
	public final int[] anIntArray977;
	public final int[] anIntArray978;
	public final int[] anIntArray979;
	public final int[] textColorEffect; // anIntArray980
	public final int[] textDrawType; // anIntArray981
	public final int[] anIntArray982;
	public final String[] aStringArray983;
	public int anInt984;
	public int anInt985;
	public static int antiCheat15; // anInt986
	public AbstractSprite[] hitMarks;
	public int anInt988;
	public int anInt989;
	public final int[] charEditColors; // anIntArray990
	public static boolean clientRunning; // aBoolean993
	public final boolean aBoolean994;
	public int anInt995;
	public int anInt996;
	public int anInt997;
	public int anInt998;
	public int anInt999;
	public Cryption encryption;
	public final int anInt1002;
	public static final int[][] playerBodyRecolours = { // anIntArrayArray1003
		{
			6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433,
			2983, 54193
		}, {
			8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153,
			56621, 4783, 1341, 16578, 35003, 25239
		}, {
			25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094,
			10153, 56621, 4783, 1341, 16578, 35003
		}, {
			4626, 11146, 6439, 12, 4758, 10270
		}, {
			4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574
		}
	};
	public static int antiCheat1; // anInt1005
	public int pktSize;
	public int pktType;
	public int timeoutCounter; // anInt1009
	public int anInt1010;
	public int anInt1011;
	public Deque projectileDeque; // aClass19_1013
	public int anInt1014;
	public int anInt1015;
	public int currentStatusInterface; // anInt1018
	public static final int[] XP_FOR_LEVEL; // anIntArray1019
	public int sameClickCounter; // anInt1022
	public int loadingStage;
	public IndexedImage scrollBar1;
	public IndexedImage scrollBar2;
	public int anInt1026;
	public IndexedImage backBase1;
	public IndexedImage backBase2;
	public IndexedImage backHmid1;
	public boolean char_edit_model_changed; // aBoolean1031
	public int baseX;
	public int baseY;
	public int anInt1036;
	public int anInt1037;
	public int anInt1039;
	public int anInt1040;
	public int anInt1041;
	public int dialogID;
	public final int[] maxStats;
	public final int[] anIntArray1045;
	public int isMember; // anInt1046
	public boolean char_edit_gender; // aBoolean1047
	public int anInt1048;
	public String loadingBarText; // aString1049
	public static int regionLoadedCounter; // anInt1051
	public CacheArchive titleStreamLoader;
	public int flashingSideBarID; // anInt1054
	public int anInt1055;
	public Deque stillGraphicDeque; // aClass19_1056
	public final RSInterface aClass9_1059;
	public static int drawCycle; // anInt1061
	public int anInt1062;
	public final int barFillColor; //anInt1063
	public final int[] char_edit_idkits; // anIntArray1065
	public int mouseInvInterfaceIndex;
	public int lastActiveInvInterface;
	public OnDemandFetcher onDemandFetcher;
	public int anInt1069;
	public int anInt1070;
	public int[] markPosX; // anIntArray1072
	public int[] markPosY; // anIntArray1073
	public RSBuffer inStream;
	public int moveItemFrameID; // anInt1084
	public int moveItemStartSlot; // anInt1085
	public int activeInterfaceType;
	public int last_mouse_x; // anInt1087
	public int last_mouse_y; // anInt1088
	public int anInt1089;
	public final int[] expectedCRCs;
	public AbstractSprite[] headIcons;
	public static int anticheat2; // anInt1097
	public int anInt1098;
	public int anInt1099;
	public int anInt1100;
	public int anInt1101;
	public int anInt1102;
	public boolean tabAreaAltered;
	public int systemUpdateTime; // anInt1104
	public RSImageProducer aRSImageProducer_1107;
	public RSImageProducer aRSImageProducer_1108;
	public RSImageProducer aRSImageProducer_1109;
	public RSImageProducer aRSImageProducer_1110;
	public RSImageProducer aRSImageProducer_1111;
	public RSImageProducer aRSImageProducer_1112;
	public RSImageProducer aRSImageProducer_1113;
	public RSImageProducer aRSImageProducer_1114;
	public RSImageProducer aRSImageProducer_1115;
	public static int mouseClickCounter; // anInt1117
	public int membersInt;
	public AbstractSprite compass;
	public RSImageProducer aRSImageProducer_1123;
	public RSImageProducer aRSImageProducer_1124;
	public RSImageProducer aRSImageProducer_1125;
	public static RSPlayer myPlayer;
	public final String[] atPlayerActions;
	public final boolean[] atPlayerArray;
	public final int[][][] constructionMapInformation; // anIntArrayArrayArray1129
	public final int[] tabInterfaceIDs = {
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1
	};
	public int nextYOffsetChange; // anInt1132
	public static int anticheat3; // anInt1134
	public int spellSelected;
	public int selectedSpellID; // anInt1137
	public int spellUsableOn;
	public String spellTooltip;
	public AbstractSprite[] markGraphic; // aClass30_Sub2_Sub1_Sub1Array1140
	public boolean tutorialIsland; // aBoolean1141
	public static int anticheat4; // anInt1142
	public IndexedImage redStone1;
	public IndexedImage redStone2;
	public IndexedImage redStone3;
	public IndexedImage redStone1_2;
	public IndexedImage redStone2_2;
	public int energy;
	public boolean isDialogueInterface; // aBoolean1149
	public AbstractSprite[] crosses;
	public boolean musicEnabled;
	public IndexedImage[] aBackgroundArray1152s;
	public boolean needDrawTabArea;
	public int unreadMessages;
	public static int anticheat5; // anInt1155
	public static boolean fpsOn = true;
	public boolean loggedIn;
	public boolean canMute;
	public boolean inCutscene; // aBoolean1160
	public static int loopCycle;
	public RSImageProducer tabAreaDrawingTarget; // aRSImageProducer_1163
	public RSImageProducer gameScreenCanvas; // aRSImageProducer_1165
	public RSImageProducer aRSImageProducer_1166;
	public int daysSinceRecovChange;
	public RSSocket socketStream;
	public int anInt1169;
	public long aLong1172;
	public static int anticheat6; // anInt1175
	public boolean genericLoadingError;
	public final int[] anIntArray1177 = {
			0, 0, 0, 0, 1, 1, 1, 1, 1, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 3
	};
	public int reportAbuseInterfaceID;
	public Deque gameObjectSpawnDeque; // aClass19_1179
	public int[] anIntArray1181;
	public int[] anIntArray1182;
	public byte[][] terrainData; // aByteArrayArray1183
	public int anInt1186;
	public int anInt1187;
	public static int anticheat7; // anInt1188
	public int invOverlayInterfaceID;
	public int[] anIntArray1190;
	public int[] anIntArray1191;
	public RSBuffer stream;
	public IndexedImage invBack;
	public AbstractSprite aClass30_Sub2_Sub1_Sub1_1201;
	public AbstractSprite aClass30_Sub2_Sub1_Sub1_1202;
	public final int[] customLowestYaw; // anIntArray1203
	public static final int[] skinColours = { // anIntArray1204
		9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145,
		58654, 5027, 1457, 16565, 34991, 25486
	};
	public static boolean flagged;
	public int scrollMax; // anInt1211
	public String promptInput;
	public int anInt1213;
	public int[][][] intGroundArray;
	public long aLong1215;
	public final IndexedImage[] modIcons;
	public long lastPressedTime; // aLong1220
	public int tabID;
	public int headiconNpcID; // anInt1222
	public boolean inputTaken;
	public int inputDialogState;
	public static int anticheat8; // anInt1226
	public TileSetting[] tileSettings; // aClass11Array1230
	public static int anIntArray1232[];
	public int[] terrainIndices; // anIntArray1235
	public int[] anIntArray1236;
	public int oldX; // anInt1237
	public int oldY; // anInt1238
	public final int anInt1239 = 100;
	public final int[] anIntArray1240;
	public final int[] anIntArray1241;
	public boolean aBoolean1242;
	public int atInventoryLoopCycle;
	public int atInventoryInterface;
	public int atInventoryIndex;
	public int atInventoryInterfaceType;
	public byte[][] aByteArrayArray1247;
	public int tradeMode;
	public final int[] anIntArray1250;
	public int onTutorialIsland; // anInt1251
	public final boolean rsAlreadyLoaded;
	public int oneMouseButton; // anInt1253
	public int anInt1254;
	public boolean welcomeScreenRaised;
	public boolean messagePromptRaised;
	public int anInt1257;
	public byte[][][] tileSettingBits; // byteGroundArray
	public int destX;
	public int destY;
	public int anInt1264;
	public int rendersPerSecond; // anInt1265
	public int bigRegionX; // anInt1268
	public int bigRegionY; // anInt1269
	public RSFont smallFont; // smallText
	public RSFont plainFont; // aTextDrawingArea_1271
	public RSFont boldFont; // chatTextDrawingArea
	public int anInt1275;
	public int backDialogID;
	public int nextXOffsetChange; // anInt1279
	public int[] bigX;
	public int[] bigY;
	public int itemSelected;
	public int lastItemSelectedSlot; // anInt1283
	public int lastItemSelectedInterface; // anInt1284
	public int lastItemSelected; // anInt1285
	public String selectedItemName;
	public static int anticheat9; // anInt1288
	public static int anInt1290;

	static
	{
		XP_FOR_LEVEL = new int[99];
		int i = 0;
		for(int level = 0; level < 99; level++)
		{
			final int levelLocal = level + 1;
			final int i1 = (int) (levelLocal + (300D * Math.pow(2D, levelLocal / 7D)));
			i += i1;
			GameClient.XP_FOR_LEVEL[level] = i / 4;
		}
		GameClient.anIntArray1232 = new int[32];
		i = 2;
		for(int k = 0; k < 32; k++)
		{
			GameClient.anIntArray1232[k] = i - 1;
			i += i;
		}
	}
}