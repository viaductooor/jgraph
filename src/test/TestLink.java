package test;

import jnetwork.WeightedEdge;

public class TestLink implements WeightedEdge{
	private float flow;
	
	public TestLink(float flow) {
		this.flow = flow;
	}

	public float getFlow() {
		return flow;
	}

	public void setFlow(float flow) {
		this.flow = flow;
	}

	@Override
	public float getWeight() {
		// TODO Auto-generated method stub
		return this.flow;
	}

	@Override
	public void setWeight(float w) {
		// TODO Auto-generated method stub
		this.flow = w;
	}

}
