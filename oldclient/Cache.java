package com.runescape.revised.util.node;

import com.runescape.client.SignLink;


public final class Cache {

	public Cache(int i)
	{
		emptyNodeSub = new QueueNode();
		nodeSubList = new NodeSubList();
		initialCount = i;
		spaceLeft = i;
		nodeCache = new NodeCache();
	}

	public QueueNode insertFromCache(long l)
	{
		QueueNode nodeSub = (QueueNode) nodeCache.findNodeByID(l);
		if(nodeSub != null)
		{
			nodeSubList.insertHead(nodeSub);
		}
		return nodeSub;
	}

	public void removeFromCache(QueueNode nodeSub, long l)
	{
		try
		{
			if(spaceLeft == 0)
			{
				QueueNode nodeSub_1 = nodeSubList.popTail();
				nodeSub_1.unlink();
				nodeSub_1.unlinkSub();
				if(nodeSub_1 == emptyNodeSub)
				{
					QueueNode nodeSub_2 = nodeSubList.popTail();
					nodeSub_2.unlink();
					nodeSub_2.unlinkSub();
				}
			} else
			{
				spaceLeft--;
			}
			nodeCache.removeFromCache(nodeSub, l);
			nodeSubList.insertHead(nodeSub);
			return;
		}
		catch(RuntimeException runtimeexception)
		{
			SignLink.reporterror("47547, " + nodeSub + ", " + l + ", " + (byte)2 + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	public void unlinkAll()
	{
		do
		{
			QueueNode nodeSub = nodeSubList.popTail();
			if(nodeSub != null)
			{
				nodeSub.unlink();
				nodeSub.unlinkSub();
			} else
			{
				spaceLeft = initialCount;
				return;
			}
		} while(true);
	}

	private final QueueNode emptyNodeSub;
	private final int initialCount;
	private int spaceLeft;
	private final NodeCache nodeCache;
	private final NodeSubList nodeSubList;
}
