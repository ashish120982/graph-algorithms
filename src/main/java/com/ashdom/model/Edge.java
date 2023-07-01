package com.ashdom.model;

import lombok.Value;

@Value
public class Edge {
	private Node node1;
	private Node node2;
	private boolean isDirected;
}
