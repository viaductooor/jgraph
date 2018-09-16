package test;

import java.util.function.Supplier;

import jnetwork.Graph;

public class Test {
	public static void main(String[] args) {
		Supplier<TestLink> defaltLinkBuilder = ()->new TestLink(1);
		Graph<Integer, TestLink> g = new Graph<Integer,TestLink>();
		g.addEdge(1, 2, defaltLinkBuilder.get());
		g.addEdge(1,3,defaltLinkBuilder.get());
		g.addEdge(2,4,defaltLinkBuilder.get());
		for(Graph.Entry<Integer, TestLink> entry:g.entrySet()) {
			System.out.println(entry.getBegin()+" "+entry.getEnd()+" "+entry.getLink().getWeight());
		}
	}
}
