package com.runescape.client;

import java.applet.Applet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class SignLink implements Runnable {

	public static final int clientVersion = 317;
	public static int userID;
	public static int storeID = 32;
	public static RandomAccessFile cacheDataFile = null;
	public static final RandomAccessFile[] cacheIndexFiles = new RandomAccessFile[5];
	public static boolean isUsingJava;
	public static final Applet applet = null;
	private static boolean isActive;
	private static int threadID;
	private static InetAddress socketAddress;
	private static int socketPort;
	private static Socket socket = null;
	private static int threadPriority = 1;
	private static Runnable threadName = null;
	private static String dnsAddress = null;
	public static String dns = null;
	private static String urlreq = null;
	private static DataInputStream urlstream = null;
	private static int fileLength;
	private static String fileName = null;
	private static byte[] fileBuffer = null;
	private static boolean isPlayingMidi;
	private static int filePosition;
	public static String midiFile = null;
	public static int midiVolume;
	public static int midiFade;
	private static boolean isPlayingWave;
	public static int waveVolume;
	public static boolean isReportingError = true;
	public static String errorName = "";

	private SignLink() {}

	public static void initialize(final InetAddress inetaddress) {
		SignLink.threadID = (int)(Math.random() * 99999999D);
		if(SignLink.isActive) {
			try {
				Thread.sleep(500L);
			} catch(final Exception _ex) {}
			SignLink.isActive = false;
		}
		SignLink.socketPort = 0;
		SignLink.threadName = null;
		SignLink.dnsAddress = null;
		SignLink.fileName = null;
		SignLink.urlreq = null;
		SignLink.socketAddress = inetaddress;
		final Thread thread = new Thread(new SignLink());
		thread.setDaemon(true);
		thread.start();
		while(!SignLink.isActive) {
			try {
				Thread.sleep(50L);
			} catch(final InterruptedException _ex) {}
		}
	}

	@Override
	public void run() {
		SignLink.isActive = true;
		final String directory = SignLink.findCacheDirectory();
		SignLink.userID = SignLink.getUserID(directory);
		try {
			final File file = new File(directory + "main_file_cache.dat");
			if(file.exists() && (file.length() > 0x3200000L)) {
				file.delete();
			}
			SignLink.cacheDataFile = new RandomAccessFile(directory + "main_file_cache.dat", "rw");
			for(int j = 0; j < 5; j++) {
				SignLink.cacheIndexFiles[j] = new RandomAccessFile(directory + "main_file_cache.idx" + j, "rw");
			}
		} catch(final Exception exception) {
			exception.printStackTrace();
		}
		for(final int id = SignLink.threadID; SignLink.threadID == id;) {
			if(SignLink.socketPort != 0) {
				try {
					SignLink.socket = new Socket(SignLink.socketAddress, SignLink.socketPort);
				} catch(final Exception _ex) {
					SignLink.socket = null;
				}
				SignLink.socketPort = 0;
			} else if(SignLink.threadName != null) {
				final Thread thread = new Thread(SignLink.threadName);
				thread.setDaemon(true);
				thread.start();
				thread.setPriority(SignLink.threadPriority);
				SignLink.threadName = null;
			} else if(SignLink.dnsAddress != null) {
				try {
					SignLink.dns = InetAddress.getByName(SignLink.dnsAddress).getHostName();
				} catch(final Exception _ex) {
					SignLink.dns = "unknown";
				}
				SignLink.dnsAddress = null;
			} else if(SignLink.fileName != null) {
				if(SignLink.fileBuffer != null) {
					try {
						final FileOutputStream fileoutputstream = new FileOutputStream(directory + SignLink.fileName);
						fileoutputstream.write(SignLink.fileBuffer, 0, SignLink.fileLength);
						fileoutputstream.close();
					} catch(final Exception _ex) {}
				}
				if(SignLink.isPlayingWave) {
					SignLink.isPlayingWave = false;
				}
				if(SignLink.isPlayingMidi) {
					SignLink.midiFile = directory + SignLink.fileName;
					SignLink.isPlayingMidi = false;
				}
				SignLink.fileName = null;
			} else if(SignLink.urlreq != null) {
				try {
					System.out.println("urlstream");
					SignLink.urlstream = new DataInputStream((new URL(SignLink.applet.getCodeBase(), SignLink.urlreq)).openStream());
				} catch(final Exception _ex) {
					SignLink.urlstream = null;
				}
				SignLink.urlreq = null;
			}
			try {
				Thread.sleep(50L);
			} catch(final Exception _ex) {}
		}
	}

	private static String findCacheDirectory() {
		return "./cache/";
	}

	private static int getUserID(final String s) {
		try {
			final File file = new File(s + "uid.dat");
			if(!file.exists() || (file.length() < 4L)) {
				final DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(s + "uid.dat"));
				dataoutputstream.writeInt((int)(Math.random() * 99999999D));
				dataoutputstream.close();
			}
		} catch(final Exception _ex) {}
		try {
			final DataInputStream datainputstream = new DataInputStream(new FileInputStream(s + "uid.dat"));
			final int i = datainputstream.readInt();
			datainputstream.close();
			return i + 1;
		} catch(final Exception _ex) {
			return 0;
		}
	}

	public static synchronized Socket openSocket(final int port) throws IOException {
		for(SignLink.socketPort = port; SignLink.socketPort != 0;) {
			try {
				Thread.sleep(50L);
			} catch(final Exception _ex) {}
		}
		if(SignLink.socket == null) {
			throw new IOException("could not open socket");
		} else {
			return SignLink.socket;
		}
	}

	public static synchronized DataInputStream openURL(final String s) throws IOException {
		for(SignLink.urlreq = s; SignLink.urlreq != null;) {
			try {
				Thread.sleep(50L);
			} catch(final Exception _ex) {}
		}
		if(SignLink.urlstream == null) {
			throw new IOException("could not open: " + s);
		} else {
			return SignLink.urlstream;
		}
	}

	public static synchronized void openDNS(final String address) {
		SignLink.dns = address;
		SignLink.dnsAddress = address;
	}

	public static synchronized void startThread(final Runnable name, final int priority) {
		SignLink.threadPriority = priority;
		SignLink.threadName = name;
	}

	public static synchronized boolean saveWaveFile(final byte[] buffer, final int i) {
		if(i > 0x1e8480) {
			return false;
		}
		if(SignLink.fileName != null) {
			return false;
		} else {
			SignLink.filePosition = (SignLink.filePosition + 1) % 5;
			SignLink.fileLength = i;
			SignLink.fileBuffer = buffer;
			SignLink.isPlayingWave = true;
			SignLink.fileName = "sound" + SignLink.filePosition + ".wav";
			return true;
		}
	}

	public static synchronized boolean replayWaveFile() {
		if(SignLink.fileName != null) {
			return false;
		} else {
			SignLink.fileBuffer = null;
			SignLink.isPlayingWave = true;
			SignLink.fileName = "sound" + SignLink.filePosition + ".wav";
			return true;
		}
	}

	public static synchronized void saveMidiFile(final byte[] buffer, final int length) {
		if(length > 0x1e8480) {
			return;
		}
		if(SignLink.fileName != null) {
		} else {
			SignLink.filePosition = (SignLink.filePosition + 1) % 5;
			SignLink.fileLength = length;
			SignLink.fileBuffer = buffer;
			SignLink.isPlayingMidi = true;
			SignLink.fileName = "jingle" + SignLink.filePosition + ".mid";
		}
	}

	public static void reportError(final String error) {
		System.out.println("Error: " + error);
	}
}