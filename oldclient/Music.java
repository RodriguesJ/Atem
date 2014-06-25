package com.runescape.revised.client;

import com.runescape.client.SignLink;
import com.runescape.client.revised.config.definitions.ItemDef;

public class Music {
	
	public int currentSongVolume; // anInt1290
	public int currentSong;
	public final int[] songIDs; // anIntArray1207
	public int nextSong;
	public boolean songChanging;
	public int prevSong;
	
	public Music() {
		songIDs = new int[50];
		songChanging = true;
		currentSongVolume = -1;
		currentSong = -1;
	}

	private void stopMidi()
	{
		SignLink.midifade = 0;
		SignLink.midi = "stop";
	}

	private void saveMidi(boolean flag, byte abyte0[])
	{
		SignLink.midifade = flag ? 1 : 0;
		SignLink.midisave(abyte0, abyte0.length);
	}

	private boolean replayWave()
	{
			return SignLink.wavereplay();
	}
	
	private void adjustVolume(int i)
	{
		int j = Varp.cache[i].anInt709;
		if(j == 0)
			return;
		int k = variousSettings[i];
		if(j == 1)
		{
			if(k == 1)
				Rasterizer.method372(0.90000000000000002D);
			if(k == 2)
				Rasterizer.method372(0.80000000000000004D);
			if(k == 3)
				Rasterizer.method372(0.69999999999999996D);
			if(k == 4)
				Rasterizer.method372(0.59999999999999998D);
			ItemDef.mruNodes1.unlinkAll();
			welcomeScreenRaised = true;
		}
		if(j == 3)
		{
			boolean flag1 = musicEnabled;
			if(k == 0)
			{
				adjustVolume(musicEnabled, 0);
				musicEnabled = true;
			}
			if(k == 1)
			{
				adjustVolume(musicEnabled, -400);
				musicEnabled = true;
			}
			if(k == 2)
			{
				adjustVolume(musicEnabled, -800);
				musicEnabled = true;
			}
			if(k == 3)
			{
				adjustVolume(musicEnabled, -1200);
				musicEnabled = true;
			}
			if(k == 4)
				musicEnabled = false;
			if(musicEnabled != flag1 && !lowMem)
			{
				if(musicEnabled)
				{
					nextSong = currentSong;
					songChanging = true;
					onDemandFetcher.method558(2, nextSong);
				} else
				{
					stopMidi();
				}
				prevSong = 0;
			}
		}
		if(j == 4)
		{
			if(k == 0)
			{
				wave_on = true;
				setWaveVolume(0);
			}
			if(k == 1)
			{
				wave_on = true;
				setWaveVolume(-400);
			}
			if(k == 2)
			{
				wave_on = true;
				setWaveVolume(-800);
			}
			if(k == 3)
			{
				wave_on = true;
				setWaveVolume(-1200);
			}
			if(k == 4)
				wave_on = false;
		}
		if(j == 5)
			anInt1253 = k;
		if(j == 6)
			anInt1249 = k;
		if(j == 8)
		{
			splitPrivateChat = k;
			inputTaken = true;
		}
		if(j == 9)
			anInt913 = k;
	}
	
	private boolean saveWave(byte abyte0[], int i)
	{
		return abyte0 == null || SignLink.wavesave(abyte0, i);
	}
	
	private void handleMusicEvents()
	{
		for(int i = 0; i < anInt1062; i++)
			if(anIntArray1250[i] <= 0)
			{
				boolean flag1 = false;
				try
				{
					if(anIntArray1207[i] == anInt874 && anIntArray1241[i] == anInt1289)
					{
						if(!replayWave())
							flag1 = true;
					} else
					{
						RSBuffer stream = Sounds.method241(anIntArray1241[i], anIntArray1207[i]);
						if(System.currentTimeMillis() + (long)(stream.currentOffset / 22) > aLong1172 + (long)(anInt1257 / 22))
						{
							anInt1257 = stream.currentOffset;
							aLong1172 = System.currentTimeMillis();
							if(saveWave(stream.buffer, stream.currentOffset))
							{
								anInt874 = anIntArray1207[i];
								anInt1289 = anIntArray1241[i];
							} else
							{
								flag1 = true;
							}
						}
					}
				}
				catch(Exception exception) { }
				if(!flag1 || anIntArray1250[i] == -5)
				{
					anInt1062--;
					for(int j = i; j < anInt1062; j++)
					{
						anIntArray1207[j] = anIntArray1207[j + 1];
						anIntArray1241[j] = anIntArray1241[j + 1];
						anIntArray1250[j] = anIntArray1250[j + 1];
					}

					i--;
				} else
				{
					anIntArray1250[i] = -5;
				}
			} else
			{
				anIntArray1250[i]--;
			}

		if(prevSong > 0)
		{
			prevSong -= 20;
			if(prevSong < 0)
				prevSong = 0;
			if(prevSong == 0 && musicEnabled && !lowMem)
			{
				nextSong = currentSong;
				songChanging = true;
				onDemandFetcher.method558(2, nextSong);
			}
		}
	}
	
	private void setWaveVolume(int i)
	{
		SignLink.wavevol = i;
	}
	
	private void adjustVolume(boolean flag, int i)
	{
		SignLink.midivol = i;
		if(flag)
			SignLink.midi = "voladjust";
	}
}