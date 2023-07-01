package com.ashdom.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GraphTests {
	
	private Graph graph;
	private Deque<Node> deque;
	private Node startNode;
	
	@BeforeEach
	void init() {
		Map<Node, Set<Edge>> adjacencyMap = new LinkedHashMap<>();
		Map<Node, Boolean> visitedMap = new HashMap<>();
		Node a = new Node("a");
		startNode = a;
		visitedMap.put(a, false);
		Node b = new Node("b");
		visitedMap.put(b, false);
		Edge edgeAB = new Edge(a, b, true);
		Node c = new Node("c");
		visitedMap.put(c, false);
		Edge edgeAC = new Edge(a, c, true);
		Node d = new Node("d");
		visitedMap.put(d, false);
		Node e = new Node("e");
		visitedMap.put(e, false);
		Edge edgeCE = new Edge(c, e, true);
		Edge edgeBD = new Edge(b, d, true);
		Node f = new Node("f");
		visitedMap.put(f, false);
		Edge edgeDF = new Edge(d, f, true);
		Set<Edge> edgesOfA = new HashSet<>();
		edgesOfA.add(edgeAB);
		edgesOfA.add(edgeAC);
		adjacencyMap.put(a, edgesOfA);
		Set<Edge> edgesOfB = new HashSet<>();
		edgesOfB.add(edgeBD);
		adjacencyMap.put(b, edgesOfB);
		Set<Edge> edgesOfC = new HashSet<>();
		edgesOfC.add(edgeCE);
		adjacencyMap.put(c, edgesOfC);
		Set<Edge> edgesOfD = new HashSet<>();
		edgesOfD.add(edgeDF);
		adjacencyMap.put(d, edgesOfD);
		adjacencyMap.put(e, new HashSet<>());
		adjacencyMap.put(f, new HashSet<>());
		graph = new Graph(adjacencyMap,visitedMap);
		deque = new ArrayDeque<>();
	}

	@Test
	void testBreadthFirstSearch() {
		Assertions.assertEquals("a c b e d f", graph.breadthFirstSearch(startNode, deque));
	}
	
	@Test
	void testDepthFirstSearch() {
		Assertions.assertEquals("a b d f c e", graph.depthFirstSearch(startNode, deque));
	}
	
}
