package com.ashdom.model;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GraphTests {

	private Graph graph;
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
		graph = new Graph(adjacencyMap, visitedMap, new ArrayDeque<>());
	}

	@Test
	void testBreadthFirstSearch() {
		Assertions.assertEquals("a c b e d f", graph.breadthFirstSearch(startNode));
	}

	@Test
	void testDepthFirstSearch() {
		Assertions.assertEquals("a b d f c e", graph.depthFirstSearch(startNode));
	}

	private static Stream<Arguments> provideNodesToTest() {
		return Stream.of(Arguments.of(new Node("f"), new Node("k"), true),
				Arguments.of(new Node("f"), new Node("h"), true), Arguments.of(new Node("j"), new Node("f"), false));
	}

	@ParameterizedTest
	@MethodSource("provideNodesToTest")
	void testInputNodesHavePath(Node source, Node destination, boolean expected) {
		// given
		Map<Node, Set<Edge>> adjacencyMap = new LinkedHashMap<>();
		Map<Node, Boolean> visitedMap = new HashMap<>();
		Node f = new Node("f");
		visitedMap.put(f, false);
		Node g = new Node("g");
		visitedMap.put(g, false);
		Edge edgeFG = new Edge(f, g, true);
		Node h = new Node("h");
		visitedMap.put(h, false);
		Edge edgeGH = new Edge(g, h, true);
		Node i = new Node("i");
		visitedMap.put(i, false);
		Node j = new Node("j");
		visitedMap.put(j, false);
		Edge edgeFI = new Edge(f, i, true);
		Edge edgeIG = new Edge(i, g, true);
		Edge edgeJI = new Edge(j, i, true);
		Node k = new Node("k");
		visitedMap.put(k, false);
		Edge edgeIK = new Edge(i, k, true);
		Set<Edge> edgesOfF = new HashSet<>();
		edgesOfF.add(edgeFG);
		edgesOfF.add(edgeFI);
		adjacencyMap.put(f, edgesOfF);
		Set<Edge> edgesOfG = new HashSet<>();
		edgesOfG.add(edgeGH);
		adjacencyMap.put(g, edgesOfG);
		Set<Edge> edgesOfH = new HashSet<>();
		adjacencyMap.put(h, edgesOfH);
		Set<Edge> edgesOfI = new HashSet<>();
		edgesOfI.add(edgeIG);
		edgesOfI.add(edgeIK);
		adjacencyMap.put(i, edgesOfI);
		Set<Edge> edgesOfJ = new HashSet<>();
		edgesOfJ.add(edgeJI);
		adjacencyMap.put(j, edgesOfJ);
		adjacencyMap.put(k, new HashSet<>());
		graph = new Graph(adjacencyMap, visitedMap, new ArrayDeque<>());
		Assertions.assertEquals(expected, graph.hasPath(source, destination));
	}

}
