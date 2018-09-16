package test;

import java.util.HashMap;
import java.util.List;

import functions.ShortestPath;
import functions.ShortestPath.Node;
import jnetwork.Graph;
import jnetwork.WeightedEdge;

public class TestSh {
	public static void main(String args[]) {
		Graph<Integer, WeightedEdge> g = new Graph<Integer,WeightedEdge>();
		g.addEdge(1, 2, new TestLink(3));
		g.addEdge(1, 3, new TestLink(1));
		g.addEdge(2, 3, new TestLink(1));
		g.addEdge(2, 4, new TestLink(1));
		g.addEdge(3, 4, new TestLink(5));
		ShortestPath<Integer,TestLink> d = new ShortestPath<Integer, TestLink>();
		HashMap<Integer,HashMap<Integer,Node<Integer>>> paths = d.allPaths(g);
		List<Integer> shortestPath = d.path(paths, 1, 4);
		for(Integer i:shortestPath) {
			System.out.println(i);
		}
	}
}
