package com.ashdom.model;

import java.util.Deque;
import java.util.Map;
import java.util.Set;

import lombok.Value;
@Value
public class Graph {

	private Map<Node, Set<Edge>> adjacencyMap;
	private Map<Node, Boolean> visitedMap;
	private Deque<Node> deque;

	public String depthFirstSearch(Node a) {
		StringBuffer value = new StringBuffer();
		deque.push(a);
		while(!deque.isEmpty()) {
			Node node = deque.pop();
			Set<Edge> edgesOfNode = adjacencyMap.get(node);
			visitedMap.put(node, true);
			value.append(node.getData()+ " ");
			edgesOfNode.stream().forEach(edge -> {
				Node n = edge.getNode2();
				if (!visitedMap.get(n)) {
					deque.push(n);
				}
			});
		}
		return value.toString().strip();
	}

	public String breadthFirstSearch(Node a) {
		StringBuffer sb = new StringBuffer();
		deque.offer(a);
		while(!deque.isEmpty()) {
			Node node = deque.poll();
			Set<Edge> edgesOfNode = adjacencyMap.get(node);
			visitedMap.put(node, true);
			sb.append(node.getData()+ " ");
			edgesOfNode.stream().forEach(edge -> {
				Node n = edge.getNode2();
				if (!visitedMap.get(n)) {
					deque.offer(n);
				}
			});
		}
		return sb.toString().strip();
	}

	public boolean hasPath(Node source, Node destination) {
		deque.push(source);
		while(!deque.isEmpty()) {
			Node node = deque.pop();
			if (node.equals(destination)) {
				return true;
			}
			Set<Edge> edges = adjacencyMap.get(node);
			visitedMap.put(node, true);
			edges.stream().forEach(edge -> {
				Node n = edge.getNode2();
				if (!visitedMap.get(n)) {
					deque.push(n);
				}
			});
		}
		return false;
	}
	
}
