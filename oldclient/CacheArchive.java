package com.runescape.client;

public class CacheArchive {

	public CacheArchive(final byte abyte0[])
	{
		RSBuffer stream = new RSBuffer(abyte0);
		final int i = stream.read3Bytes();
		final int j = stream.read3Bytes();
		if(j != i)
		{
			final byte abyte1[] = new byte[i];
			BZ2InputStream.method225(abyte1, i, abyte0, j, 6);
			this.aByteArray726 = abyte1;
			stream = new RSBuffer(this.aByteArray726);
			this.aBoolean732 = true;
		} else
		{
			this.aByteArray726 = abyte0;
			this.aBoolean732 = false;
		}
		this.dataSize = stream.readUnsignedWord();
		this.anIntArray728 = new int[this.dataSize];
		this.anIntArray729 = new int[this.dataSize];
		this.anIntArray730 = new int[this.dataSize];
		this.anIntArray731 = new int[this.dataSize];
		int k = stream.currentOffset + (this.dataSize * 10);
		for(int l = 0; l < this.dataSize; l++)
		{
			this.anIntArray728[l] = stream.readDWord();
			this.anIntArray729[l] = stream.read3Bytes();
			this.anIntArray730[l] = stream.read3Bytes();
			this.anIntArray731[l] = k;
			k += this.anIntArray730[l];
		}
	}

	public byte[] getDataForName(String s)
	{
		byte abyte0[] = null; //was a parameter
		int i = 0;
		s = s.toUpperCase();
		for(int j = 0; j < s.length(); j++) {
			i = ((i * 61) + s.charAt(j)) - 32;
		}

		for(int k = 0; k < this.dataSize; k++) {
			if(this.anIntArray728[k] == i)
			{
				if(abyte0 == null) {
					abyte0 = new byte[this.anIntArray729[k]];
				}
				if(!this.aBoolean732)
				{
					BZ2InputStream.method225(abyte0, this.anIntArray729[k], this.aByteArray726, this.anIntArray730[k], this.anIntArray731[k]);
				} else
				{
					System.arraycopy(this.aByteArray726, this.anIntArray731[k], abyte0, 0, this.anIntArray729[k]);

				}
				return abyte0;
			}
		}

		return null;
	}

	private final byte[] aByteArray726;
	private final int dataSize;
	private final int[] anIntArray728;
	private final int[] anIntArray729;
	private final int[] anIntArray730;
	private final int[] anIntArray731;
	private final boolean aBoolean732;
}