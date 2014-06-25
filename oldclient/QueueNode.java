package com.runescape.revised.util.node;
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 


public class QueueNode extends Node {

	public final void unlinkSub()
	{
		if(nextNodeSub == null)
		{
		} else
		{
			nextNodeSub.prevNodeSub = prevNodeSub;
			prevNodeSub.nextNodeSub = nextNodeSub;
			prevNodeSub = null;
			nextNodeSub = null;
		}
	}

	public QueueNode()
	{
	}

	public QueueNode prevNodeSub;
	QueueNode nextNodeSub;
	public static int anInt1305;
}
