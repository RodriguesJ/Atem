package com.runescape.client;

import com.runescape.client.revised.model.RSModel;
import com.runescape.client.revised.model.VertexNormal;
import com.runescape.client.revised.util.node.QueueNode;

public class Renderable extends QueueNode {

	public void method443(final int i, final int j, final int k, final int l, final int i1, final int j1, final int k1,
			final int l1, final int i2)
	{
		final RSModel model = this.getRotatedModel();
		if(model != null)
		{
			this.modelHeight = model.modelHeight;
			model.method443(i, j, k, l, i1, j1, k1, l1, i2);
		}
	}

	public RSModel getRotatedModel() {
		return null;
	}

	public Renderable() {
		this.modelHeight = 1000;
	}

	public VertexNormal aClass33Array1425[];
	public int modelHeight;
}