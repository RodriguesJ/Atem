package com.runescape.client.revised.client.camera;

import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.model.RSModel;

public class Camera {

	public int xCameraPos;
	public int zCameraPos;
	public int yCameraPos;
	public int yCameraCurve;
	public int xCameraCurve;
	public final boolean[] useCustomCamera; // aBooleanArray876
	public int cameraPacketDelay; // anInt1016
	public boolean cameraPacketWrite; // aBoolean1017
	public final int[] cameraTransVars2; // anIntArray1030
	public int cameraOffsetY; // anInt1131
	public int cameraY; // anInt1184
	public int cameraX; // minimapInt1
	public int cameraOffsetX; // anInt1278

	public Camera() {
		this.useCustomCamera = new boolean[5];
		this.cameraPacketWrite = false;
		this.cameraTransVars2 = new int[5];
		this.cameraY = 128;
	}

	private int setCameraLocation()
	{
		int j = 3;
		if(this.yCameraCurve < 310)
		{
			int k = this.xCameraPos >> 7;
		int l = this.yCameraPos >> 7;
		final int i1 = GameClient.myPlayer.x >> 7;
		final int j1 = GameClient.myPlayer.y >> 7;
		if((tileSettingBits[plane][k][l] & 4) != 0) {
			j = plane;
		}
		int k1;
		if(i1 > k) {
			k1 = i1 - k;
		} else {
			k1 = k - i1;
		}
		int l1;
		if(j1 > l) {
			l1 = j1 - l;
		} else {
			l1 = l - j1;
		}
		if(k1 > l1)
		{
			final int i2 = (l1 * 0x10000) / k1;
			int k2 = 32768;
			while(k != i1)
			{
				if(k < i1) {
					k++;
				} else
					if(k > i1) {
						k--;
					}
				if((tileSettingBits[plane][k][l] & 4) != 0) {
					j = plane;
				}
				k2 += i2;
				if(k2 >= 0x10000)
				{
					k2 -= 0x10000;
					if(l < j1) {
						l++;
					} else
						if(l > j1) {
							l--;
						}
					if((tileSettingBits[plane][k][l] & 4) != 0) {
						j = plane;
					}
				}
			}
		} else
		{
			final int j2 = (k1 * 0x10000) / l1;
			int l2 = 32768;
			while(l != j1)
			{
				if(l < j1) {
					l++;
				} else
					if(l > j1) {
						l--;
					}
				if((tileSettingBits[plane][k][l] & 4) != 0) {
					j = plane;
				}
				l2 += j2;
				if(l2 >= 0x10000)
				{
					l2 -= 0x10000;
					if(k < i1) {
						k++;
					} else
						if(k > i1) {
							k--;
						}
					if((tileSettingBits[plane][k][l] & 4) != 0) {
						j = plane;
					}
				}
			}
		}
		}
		if((tileSettingBits[plane][GameClient.myPlayer.x >> 7][GameClient.myPlayer.y >> 7] & 4) != 0) {
			j = plane;
		}
		return j;
	}

	private int resetCameraHeight()
	{
		final int j = method42(plane, this.yCameraPos, this.xCameraPos);
		if(((j - this.zCameraPos) < 800) && ((tileSettingBits[plane][this.xCameraPos >> 7][this.yCameraPos >> 7] & 4) != 0)) {
			return plane;
		} else {
			return 3;
		}
	}




	private void setCameraPos(final GameClient client, final int j, final int k, final int l, final int i1, final int j1, final int k1)
	{
		final int l1 = (2048 - k) & 0x7ff;
		final int i2 = (2048 - j1) & 0x7ff;
		int j2 = 0;
		int k2 = 0;
		int l2 = j;
		if(l1 != 0)
		{
			final int i3 = Model3D.modelIntArray1[l1];
			final int k3 = Model3D.modelIntArray2[l1];
			final int i4 = ((k2 * k3) - (l2 * i3)) >> 16;
			l2 = ((k2 * i3) + (l2 * k3)) >> 16;
			k2 = i4;
		}
		if(i2 != 0)
		{
			final int j3 = Model3D.modelIntArray1[i2];
			final int l3 = Model3D.modelIntArray2[i2];
			final int j4 = ((l2 * j3) + (j2 * l3)) >> 16;
			l2 = ((l2 * l3) - (j2 * j3)) >> 16;
			j2 = j4;
		}
		this.xCameraPos = l - j2;
		this.zCameraPos = i1 - k2;
		this.yCameraPos = k1 - l2;
		this.yCameraCurve = k;
		this.xCameraCurve = j1;
	}




	private void moveCameraWithPlayer()
	{
		anInt1265++;
		showOtherPlayers(true);
		showNPCs(true);
		showOtherPlayers(false);
		showNPCs(false);
		createProjectiles();
		createStationaryGraphics();
		if(!aBoolean1160)
		{
			int i = anInt1184;
			if((anInt984 / 256) > i) {
				i = anInt984 / 256;
			}
			if(aBooleanArray876[4] && ((anIntArray1203[4] + 128) > i)) {
				i = anIntArray1203[4] + 128;
			}
			final int k = (minimapInt1 + anInt896) & 0x7ff;
			this.setCameraPos(600 + (i * 3), i, anInt1014, method42(plane, myPlayer.y, myPlayer.x) - 50, k, anInt1015);
		}
		int j;
		if(!aBoolean1160) {
			j = this.setCameraLocation();
		} else {
			j = this.resetCameraHeight();
		}
		final int l = this.xCameraPos;
		final int i1 = this.zCameraPos;
		final int j1 = this.yCameraPos;
		final int k1 = this.yCameraCurve;
		final int l1 = this.xCameraCurve;
		for(int i2 = 0; i2 < 5; i2++) {
			if(aBooleanArray876[i2])
			{
				final int j2 = (int)(((Math.random() * (double)((anIntArray873[i2] * 2) + 1)) - (double)anIntArray873[i2]) + (Math.sin((double)anIntArray1030[i2] * ((double)anIntArray928[i2] / 100D)) * (double)anIntArray1203[i2]));
				if(i2 == 0) {
					this.xCameraPos += j2;
				}
				if(i2 == 1) {
					this.zCameraPos += j2;
				}
				if(i2 == 2) {
					this.yCameraPos += j2;
				}
				if(i2 == 3) {
					this.xCameraCurve = (this.xCameraCurve + j2) & 0x7ff;
				}
				if(i2 == 4)
				{
					this.yCameraCurve += j2;
					if(this.yCameraCurve < 128) {
						this.yCameraCurve = 128;
					}
					if(this.yCameraCurve > 383) {
						this.yCameraCurve = 383;
					}
				}
			}
		}

		final int k2 = Rasterizer.anInt1481;
		RSModel.aBoolean1684 = true;
		RSModel.anInt1687 = 0;
		RSModel.anInt1685 = super.mouseX - 4;
		RSModel.anInt1686 = super.mouseY - 4;
		DrawingArea.setAllPixelsToZero();
		worldController.method313(this.xCameraPos, this.yCameraPos, this.xCameraCurve, this.zCameraPos, j, this.yCameraCurve);
		worldController.clearObj5Cache();
		updateEntities();
		drawHeadIcon();
		writeBackgroundTexture(k2);
		draw3dScreen();
		aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
		this.xCameraPos = l;
		this.zCameraPos = i1;
		this.yCameraPos = j1;
		this.yCameraCurve = k1;
		this.xCameraCurve = l1;
	}





	private void calcCameraPos(final GameClient client)
	{
		int i = (client.anInt1098 * 128) + 64;
		int j = (client.anInt1099 * 128) + 64;
		int k = method42(client.plane, j, i) - anInt1100;
		if(this.xCameraPos < i)
		{
			this.xCameraPos += anInt1101 + (((i - this.xCameraPos) * anInt1102) / 1000);
			if(this.xCameraPos > i) {
				this.xCameraPos = i;
			}
		}
		if(this.xCameraPos > i)
		{
			this.xCameraPos -= anInt1101 + (((this.xCameraPos - i) * anInt1102) / 1000);
			if(this.xCameraPos < i) {
				this.xCameraPos = i;
			}
		}
		if(this.zCameraPos < k)
		{
			this.zCameraPos += anInt1101 + (((k - this.zCameraPos) * anInt1102) / 1000);
			if(this.zCameraPos > k) {
				this.zCameraPos = k;
			}
		}
		if(this.zCameraPos > k)
		{
			this.zCameraPos -= anInt1101 + (((this.zCameraPos - k) * anInt1102) / 1000);
			if(this.zCameraPos < k) {
				this.zCameraPos = k;
			}
		}
		if(this.yCameraPos < j)
		{
			this.yCameraPos += anInt1101 + (((j - this.yCameraPos) * anInt1102) / 1000);
			if(this.yCameraPos > j) {
				this.yCameraPos = j;
			}
		}
		if(this.yCameraPos > j)
		{
			this.yCameraPos -= anInt1101 + (((this.yCameraPos - j) * anInt1102) / 1000);
			if(this.yCameraPos < j) {
				this.yCameraPos = j;
			}
		}
		i = (anInt995 * 128) + 64;
		j = (anInt996 * 128) + 64;
		k = method42(plane, j, i) - anInt997;
		final int l = i - this.xCameraPos;
		final int i1 = k - this.zCameraPos;
		final int j1 = j - this.yCameraPos;
		final int k1 = (int)Math.sqrt((l * l) + (j1 * j1));
		int l1 = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
		final int i2 = (int)(Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
		if(l1 < 128) {
			l1 = 128;
		}
		if(l1 > 383) {
			l1 = 383;
		}
		if(this.yCameraCurve < l1)
		{
			this.yCameraCurve += anInt998 + (((l1 - this.yCameraCurve) * anInt999) / 1000);
			if(this.yCameraCurve > l1) {
				this.yCameraCurve = l1;
			}
		}
		if(this.yCameraCurve > l1)
		{
			this.yCameraCurve -= anInt998 + (((this.yCameraCurve - l1) * anInt999) / 1000);
			if(this.yCameraCurve < l1) {
				this.yCameraCurve = l1;
			}
		}
		int j2 = i2 - this.xCameraCurve;
		if(j2 > 1024) {
			j2 -= 2048;
		}
		if(j2 < -1024) {
			j2 += 2048;
		}
		if(j2 > 0)
		{
			this.xCameraCurve += anInt998 + ((j2 * anInt999) / 1000);
			this.xCameraCurve &= 0x7ff;
		}
		if(j2 < 0)
		{
			this.xCameraCurve -= anInt998 + ((-j2 * anInt999) / 1000);
			this.xCameraCurve &= 0x7ff;
		}
		int k2 = i2 - this.xCameraCurve;
		if(k2 > 1024) {
			k2 -= 2048;
		}
		if(k2 < -1024) {
			k2 += 2048;
		}
		if(((k2 < 0) && (j2 > 0)) || ((k2 > 0) && (j2 < 0))) {
			this.xCameraCurve = i2;
		}
	}
}