package jnetwork;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jnetwork.Graph.Entry;

/**An implementation of graph with structure of adjacency list.
 * 
 * @author wujie
 *
 * @param <K> Type of the nodes
 * @param <L> Type of the links
 * 
 */
public class Graph<K, L> {
	private HashMap<K, HashMap<K, L>> map;

	public Graph() {
		map = new HashMap<K, HashMap<K, L>>();
	}

	/**
	 * 
	 * @param node
	 * @return the hashmap of the node's adjacent nodes
	 */
	public HashMap<K, L> addVertic(K node) {
		return map.put(node, new HashMap<K, L>());
	}

	/**
	 * 
	 * @param initNode
	 * @param endNode
	 * @param linkInfo
	 * @return the added linkInfo
	 */
	public L addDiEdge(K initNode, K endNode, L linkInfo) {
		if (map.containsKey(initNode)) {
			return map.get(initNode).put(endNode, linkInfo);
		} else {
			addVertic(initNode);
			return addDiEdge(initNode, endNode, linkInfo);
		}
	}
	
	public void addEdge(K initNode,K endNode, L linkInfo) {
		addDiEdge(initNode,endNode,linkInfo);
		addDiEdge(endNode,initNode,linkInfo);
	}


	/**
	 * 
	 * @param initNode
	 * @param endNode
	 * @return linkInfo
	 */
	public L getLinkInfo(K initNode, K endNode) {
		return map.get(initNode).get(endNode);
	}


	/**
	 * 
	 * @param node
	 * @return the map of adjacent nodes of this node
	 */
	public HashMap<K, L> getAdjNodes(K node) {
		return map.get(node);
	}
	
	public static class Entry<K,L>{
		K begin;
		K end;
		L link;
		public Entry(K begin,K end,L link){
			this.begin = begin;
			this.end = end;
			this.link = link;
		}
		public K getBegin() {
			return begin;
		}
		public K getEnd() {
			return end;
		}
		public L getLink() {
			return link;
		}
	}
	
	public List<Graph.Entry<K,L>> entrySet() {
		List<Entry<K, L>> l = new LinkedList<Graph.Entry<K,L>>();
		for(java.util.Map.Entry<K, HashMap<K, L>> _m:map.entrySet()) {
			K init = _m.getKey();
			for(Map.Entry<K, L> _l:_m.getValue().entrySet()) {
				K end = _l.getKey();
				L link = _l.getValue();
				l.add(new Graph.Entry<K, L>(init, end, link));
			}
		}
		return l;
	}
}
