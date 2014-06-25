package com.runescape.client;


public class Deque {

	public Deque() {
		this.head = new Node();
		this.head.prev = this.head;
		this.head.next = this.head;
	}

	public void insertHead(final Node node) {
		if (node.next != null) {
			node.unlink();
		}
		node.next = this.head.next;
		node.prev = this.head;
		node.next.prev = node;
		node.prev.next = node;
	}

	public void insertTail(final Node node)
	{
		if(node.next != null) {
			node.unlink();
		}
		node.next = this.head;
		node.prev = this.head.prev;
		node.next.prev = node;
		node.prev.next = node;
	}

	public Node popHead()
	{
		final Node node = this.head.prev;
		if(node == this.head)
		{
			return null;
		} else
		{
			node.unlink();
			return node;
		}
	}

	public Node reverseGetFirst()
	{
		final Node node = this.head.prev;
		if(node == this.head)
		{
			this.current = null;
			return null;
		} else
		{
			this.current = node.prev;
			return node;
		}
	}

	public Node getFirst()
	{
		final Node node = this.head.next;
		if(node == this.head)
		{
			this.current = null;
			return null;
		} else
		{
			this.current = node.next;
			return node;
		}
	}

	public Node reverseGetNext()
	{
		final Node node = this.current;
		if(node == this.head)
		{
			this.current = null;
			return null;
		} else
		{
			this.current = node.prev;
			return node;
		}
	}

	public Node getNext()
	{
		final Node node = this.current;
		if(node == this.head)
		{
			this.current = null;
			return null;
		}
		this.current = node.next;
		return node;
	}

	public void removeAll()
	{
		if(this.head.prev == this.head) {
			return;
		}
		do
		{
			final Node node = this.head.prev;
			if(node == this.head) {
				return;
			}
			node.unlink();
		} while(true);
	}

	private final Node head;
	private Node current;
}
