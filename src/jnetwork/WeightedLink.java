package jnetwork;

/**If you want to perform some shortest path like algorithms on a graph, the
 * links of it must implement this interface.
 *
 * @author John Smith
 *
 */
@FunctionalInterface
public interface WeightedLink {
	public float getWeight();
}
