package jnetwork;

/**If you want to perform some shrotest-path like algorithms on a graph, the
 * links of it must implement this interface.
 *
 * @author wujie
 *
 */
@FunctionalInterface
public interface WeightedLink {
	public float getWeight();
}
