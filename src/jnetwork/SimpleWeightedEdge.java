package jnetwork;

import file.Excelable;

public class SimpleWeightedEdge implements WeightedEdge,Excelable{
	public float weight;

	public SimpleWeightedEdge(float weight) {
		this.weight = weight;
	}
	@Override
	public float getWeight() {
		return weight;
	}

	@Override
	public void setWeight(float w) {
		weight = w;
	}
	@Override
	public String[] header() {
		return new String[] {"weight"};
	}
	@Override
	public float[] items() {
		return new float[] {weight};
	}
	
}
