package com.runescape.client;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.runescape.client.revised.graphics.image.RSImageProducer;

public class RSApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

	private static final long serialVersionUID = 1L;

	public final void createClientFrame(final int height, final int width) {
		this.myWidth = width;
		this.myHeight = height;
		this.gameFrame = new RSFrame(this, this.myWidth, this.myHeight);
		this.graphics = this.getGameComponent().getGraphics();
		this.fullGameScreen = new RSImageProducer(this.myWidth, this.myHeight, this.getGameComponent());
		this.startRunnable(this, 1);
	}

	protected final void initClientFrame(final int height, final int width) {
		this.myWidth = width;
		this.myHeight = height;
		this.graphics = this.getGameComponent().getGraphics();
		this.fullGameScreen = new RSImageProducer(this.myWidth, this.myHeight, this.getGameComponent());
		this.startRunnable(this, 1);
	}

	@Override
	public void run() {
		this.getGameComponent().addMouseListener(this);
		this.getGameComponent().addMouseMotionListener(this);
		this.getGameComponent().addKeyListener(this);
		this.getGameComponent().addFocusListener(this);
		if(this.gameFrame != null) {
			this.gameFrame.addWindowListener(this);
		}
		this.drawLoadingText(0, "Loading...");
		this.startUp();
		int opos = 0;
		int ratio = 256;
		int del = 1;
		int count = 0;
		int intex = 0;
		for(int optim = 0; optim < 10; optim++) {
			this.optims[optim] = System.currentTimeMillis();
		}

		while(this.gameState >= 0)
		{
			if(this.gameState > 0)
			{
				this.gameState--;
				if(this.gameState == 0)
				{
					this.exit();
					return;
				}
			}
			ratio = 300;
			del = 1;
			final long currentTime = System.currentTimeMillis();
			if(currentTime > this.optims[opos]) {
				ratio = (int)((2560 * this.delayTime) / (currentTime - this.optims[opos]));
			}
			if(ratio < 25) {
				ratio = 25;
			}
			if(ratio > 256)
			{
				ratio = 256;
				del = (int)(this.delayTime - ((currentTime - this.optims[opos]) / 10L));
			}
			if(del > this.delayTime) {
				del = this.delayTime;
			}
			this.optims[opos] = currentTime;
			opos = (opos + 1) % 10;
			if(del > 1)
			{
				for(int optim = 0; optim < 10; optim++) {
					if(this.optims[optim] != 0L) {
						this.optims[optim] += del;
					}
				}

			}
			if(del < this.minDelay) {
				del = this.minDelay;
			}
			try
			{
				Thread.sleep(del);
			}
			catch(final InterruptedException _ex)
			{
				intex++;
			}
			for(; count < 256; count += ratio)
			{
				this.clickType = this.eventMouseButtonPressed;
				this.saveClickX = this.eventClickX;
				this.saveClickY = this.eventClickY;
				this.clickTime = this.lastClick;
				this.eventMouseButtonPressed = 0;
				this.processGameLoop();
				this.readIndex = this.writeIndex;
			}

			count &= 0xff;
			if(this.delayTime > 0) {
				this.fps = (1000 * ratio) / (this.delayTime * 256);
			}
			this.processDrawing();
			if(this.shouldDebug)
			{
				System.out.println("ntime:" + currentTime);
				for(int l2 = 0; l2 < 10; l2++)
				{
					final int i3 = ((opos - l2 - 1) + 20) % 10;
					System.out.println("otim" + i3 + ":" + this.optims[i3]);
				}

				System.out.println("fps:" + this.fps + " ratio:" + ratio + " count:" + count);
				System.out.println("del:" + del + " deltime:" + this.delayTime + " mindel:" + this.minDelay);
				System.out.println("intex:" + intex + " opos:" + opos);
				this.shouldDebug = false;
				intex = 0;
			}
		}
		if(this.gameState == -1) {
			this.exit();
		}
	}

	private void exit() {
		this.gameState = -2;
		this.cleanUpForQuit();
		if(this.gameFrame != null) {
			try {
				Thread.sleep(1000L);
			} catch(final Exception _ex) {}
			try {
				System.exit(0);
			} catch(final Throwable _ex) {}
		}
	}

	public final void method4(final int i)
	{
		this.delayTime = 1000 / i;
	}

	@Override
	public final void start()
	{
		if(this.gameState >= 0) {
			this.gameState = 0;
		}
	}

	@Override
	public final void stop()
	{
		if(this.gameState >= 0) {
			this.gameState = 4000 / this.delayTime;
		}
	}

	@Override
	public final void destroy()
	{
		this.gameState = -1;
		try
		{
			Thread.sleep(5000L);
		}
		catch(final Exception _ex) { }
		if(this.gameState == -1) {
			this.exit();
		}
	}

	@Override
	public final void update(final Graphics g)
	{
		if(this.graphics == null) {
			this.graphics = g;
		}
		this.shouldClearScreen = true;
		this.raiseWelcomeScreen();
	}

	@Override
	public final void paint(final Graphics g)
	{
		if(this.graphics == null) {
			this.graphics = g;
		}
		this.shouldClearScreen = true;
		this.raiseWelcomeScreen();
	}

	@Override
	public final void mousePressed(final MouseEvent mouseevent)
	{
		int i = mouseevent.getX();
		int j = mouseevent.getY();
		if(this.gameFrame != null)
		{
			i -= 4;
			j -= 22;
		}
		this.idleTime = 0;
		this.eventClickX = i;
		this.eventClickY = j;
		this.lastClick = System.currentTimeMillis();
		if(mouseevent.isMetaDown())
		{
			this.eventMouseButtonPressed = 2;
			this.mouseButtonPressed = 2;
		} else
		{
			this.eventMouseButtonPressed = 1;
			this.mouseButtonPressed = 1;
		}
	}

	@Override
	public final void mouseReleased(final MouseEvent mouseevent)
	{
		this.idleTime = 0;
		this.mouseButtonPressed = 0;
	}

	@Override
	public final void mouseClicked(final MouseEvent mouseevent)
	{
	}

	@Override
	public final void mouseEntered(final MouseEvent mouseevent)
	{
	}

	@Override
	public final void mouseExited(final MouseEvent mouseevent)
	{
		this.idleTime = 0;
		this.mouseEventX = -1;
		this.mouseEventY = -1;
	}

	@Override
	public final void mouseDragged(final MouseEvent mouseevent)
	{
		int x = mouseevent.getX();
		int y = mouseevent.getY();
		if(this.gameFrame != null)
		{
			x -= 4;
			y -= 22;
		}
		this.idleTime = 0;
		this.mouseEventX = x;
		this.mouseEventY = y;
	}

	@Override
	public final void mouseMoved(final MouseEvent mouseevent)
	{
		int x = mouseevent.getX();
		int y = mouseevent.getY();
		if(this.gameFrame != null)
		{
			x -= 4;
			y -= 22;
		}
		this.idleTime = 0;
		this.mouseEventX = x;
		this.mouseEventY = y;
	}

	@Override
	public final void keyPressed(final KeyEvent keyevent)
	{
		this.idleTime = 0;
		final int keyCode = keyevent.getKeyCode();
		int keyChar = keyevent.getKeyChar();
		if(keyChar < 30) {
			keyChar = 0;
		}
		if(keyCode == 37) {
			keyChar = 1;
		}
		if(keyCode == 39) {
			keyChar = 2;
		}
		if(keyCode == 38) {
			keyChar = 3;
		}
		if(keyCode == 40) {
			keyChar = 4;
		}
		if(keyCode == 17) {
			keyChar = 5;
		}
		if(keyCode == 8) {
			keyChar = 8;
		}
		if(keyCode == 127) {
			keyChar = 8;
		}
		if(keyCode == 9) {
			keyChar = 9;
		}
		if(keyCode == 10) {
			keyChar = 10;
		}
		if((keyCode >= 112) && (keyCode <= 123)) {
			keyChar = (1008 + keyCode) - 112;
		}
		if(keyCode == 36) {
			keyChar = 1000;
		}
		if(keyCode == 35) {
			keyChar = 1001;
		}
		if(keyCode == 33) {
			keyChar = 1002;
		}
		if(keyCode == 34) {
			keyChar = 1003;
		}
		if((keyChar > 0) && (keyChar < 128)) {
			this.keyArray[keyChar] = 1;
		}
		if(keyChar > 4)
		{
			this.charQueue[this.writeIndex] = keyChar;
			this.writeIndex = (this.writeIndex + 1) & 0x7f;
		}
	}

	@Override
	public final void keyReleased(final KeyEvent keyevent)
	{
		this.idleTime = 0;
		final int keyCode = keyevent.getKeyCode();
		char keyChar = keyevent.getKeyChar();
		if(keyChar < '\036') {
			keyChar = '\0';
		}
		if(keyCode == 37) {
			keyChar = '\001';
		}
		if(keyCode == 39) {
			keyChar = '\002';
		}
		if(keyCode == 38) {
			keyChar = '\003';
		}
		if(keyCode == 40) {
			keyChar = '\004';
		}
		if(keyCode == 17) {
			keyChar = '\005';
		}
		if(keyCode == 8) {
			keyChar = '\b';
		}
		if(keyCode == 127) {
			keyChar = '\b';
		}
		if(keyCode == 9) {
			keyChar = '\t';
		}
		if(keyCode == 10) {
			keyChar = '\n';
		}
		if((keyChar > 0) && (keyChar < '\200')) {
			this.keyArray[keyChar] = 0;
		}
	}

	@Override
	public final void keyTyped(final KeyEvent keyevent) {}

	final int readChar()
	{
		int character = -1;
		if(this.writeIndex != this.readIndex)
		{
			character = this.charQueue[this.readIndex];
			this.readIndex = (this.readIndex + 1) & 0x7f;
		}
		return character;
	}

	@Override
	public final void focusGained(final FocusEvent focusevent) {
		this.awtFocus = true;
		this.shouldClearScreen = true;
		this.raiseWelcomeScreen();
	}

	@Override
	public final void focusLost(final FocusEvent focusevent) {
		this.awtFocus = false;
		for(int i = 0; i < 128; i++) {
			this.keyArray[i] = 0;
		}

	}

	@Override
	public final void windowActivated(final WindowEvent windowevent) {}

	@Override
	public final void windowClosed(final WindowEvent windowevent) {}

	@Override
	public final void windowClosing(final WindowEvent windowevent) {
		this.destroy();
	}

	@Override
	public final void windowDeactivated(final WindowEvent windowevent) {}

	@Override
	public final void windowDeiconified(final WindowEvent windowevent) {}

	@Override
	public final void windowIconified(final WindowEvent windowevent) {}

	@Override
	public final void windowOpened(final WindowEvent windowevent) {}

	void startUp() {}

	void processGameLoop() {}

	void cleanUpForQuit() {}

	void processDrawing() {}

	void raiseWelcomeScreen() {}

	Component getGameComponent() {
		if(this.gameFrame != null) {
			return this.gameFrame;
		} else {
			return this;
		}
	}

	public void startRunnable(final Runnable runnable, final int priority) {
		final Thread thread = new Thread(runnable);
		thread.start();
		thread.setPriority(priority);
	}

	void drawLoadingText(final int i, final String s)
	{
		while(this.graphics == null)
		{
			this.graphics = this.getGameComponent().getGraphics();
			try
			{
				this.getGameComponent().repaint();
			}
			catch(final Exception _ex) { }
			try
			{
				Thread.sleep(1000L);
			}
			catch(final Exception _ex) { }
		}
		final Font font = new Font("Helvetica", 1, 13);
		final FontMetrics fontmetrics = this.getGameComponent().getFontMetrics(font);
		final Font font1 = new Font("Helvetica", 0, 13);
		this.getGameComponent().getFontMetrics(font1);
		if(this.shouldClearScreen)
		{
			this.graphics.setColor(Color.black);
			this.graphics.fillRect(0, 0, this.myWidth, this.myHeight);
			this.shouldClearScreen = false;
		}
		final Color color = new Color(140, 17, 17);
		final int j = (this.myHeight / 2) - 18;
		this.graphics.setColor(color);
		this.graphics.drawRect((this.myWidth / 2) - 152, j, 304, 34);
		this.graphics.fillRect((this.myWidth / 2) - 150, j + 2, i * 3, 30);
		this.graphics.setColor(Color.black);
		this.graphics.fillRect(((this.myWidth / 2) - 150) + (i * 3), j + 2, 300 - (i * 3), 30);
		this.graphics.setFont(font);
		this.graphics.setColor(Color.white);
		this.graphics.drawString(s, (this.myWidth - fontmetrics.stringWidth(s)) / 2, j + 22);
	}

	public RSApplet() {
		this.delayTime = 20;
		this.minDelay = 1;
		this.optims = new long[10];
		this.shouldDebug = false;
		this.shouldClearScreen = true;
		this.awtFocus = true;
		this.keyArray = new int[128];
		this.charQueue = new int[128];
	}

	public int gameState;
	public int delayTime;
	public int minDelay;
	public final long[] optims;
	public int fps;
	public boolean shouldDebug;
	public int myWidth;
	public int myHeight;
	public Graphics graphics;
	protected RSImageProducer fullGameScreen;
	protected RSFrame gameFrame;
	private boolean shouldClearScreen;
	protected boolean awtFocus;
	public int idleTime;
	protected int mouseButtonPressed;
	public int mouseEventX;
	public int mouseEventY;
	public int eventMouseButtonPressed;
	public int eventClickX;
	public int eventClickY;
	public long lastClick;
	public int clickType;
	public int saveClickX;
	public int saveClickY;
	public long clickTime;
	protected final int[] keyArray;
	public final int[] charQueue;
	public int readIndex;
	public int writeIndex;
	public static int anInt34;
}