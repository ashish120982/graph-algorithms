package com.ashdom.model;

import java.util.Deque;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import lombok.Value;
@Value
public class Graph {

	private Map<Node, Set<Edge>> adjacencyMap;
	private Map<Node, Boolean> visitedMap;

	public String depthFirstSearch(Node a, Deque<Node> stack) {
		StringBuffer value = new StringBuffer();
		stack.push(a);
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			Set<Edge> edgesOfNode = adjacencyMap.get(node);
			visitedMap.put(node, true);
			value.append(node.getData()+ " ");
			edgesOfNode.stream().forEach(edge -> {
				Node n = edge.getNode2();
				if (!visitedMap.get(n)) {
					stack.push(n);
				}
			});
		}
		return value.toString().strip();
	}

	public String breadthFirstSearch(Node a, Queue<Node> queue) {
		StringBuffer sb = new StringBuffer();
		queue.offer(a);
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			Set<Edge> edgesOfNode = adjacencyMap.get(node);
			visitedMap.put(node, true);
			sb.append(node.getData()+ " ");
			edgesOfNode.stream().forEach(edge -> {
				Node n = edge.getNode2();
				if (!visitedMap.get(n)) {
					queue.offer(n);
				}
			});
		}
		return sb.toString().strip();
	}
	
}
