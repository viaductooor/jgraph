package test;

import java.util.HashMap;
import java.util.List;

import javax.swing.SwingWorker;

import file.ExcelUtils;
import functions.ShortestPath;
import functions.ShortestPath.Node;
import jnetwork.Graph;
import jnetwork.SimpleWeightedEdge;
import jnetwork.WeightedEdge;

public class TestSh {
	public static void main(String args[]) {
		Graph<String,SimpleWeightedEdge> graph = ExcelUtils.readSimpleCSV("examples/simplegraph.csv", 0);
		for(Graph.Entry<String, SimpleWeightedEdge> e:graph.entrySet()) {
			System.out.println(e.getBegin()+" "+e.getEnd()+" "+e.getLink().weight);
		}
		ShortestPath<String,SimpleWeightedEdge> sp = new ShortestPath<String, SimpleWeightedEdge>();
		HashMap<String,HashMap<String,Node<String>>> allPaths = sp.allPaths(graph);
		System.out.println(sp.shortestPath(allPaths, "6", "1"));
		ExcelUtils.writeGraph(graph, "examples/output.xls");
	}
}
