package test;

import jnetwork.WeightedLink;

public class TestLink implements WeightedLink{
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

}
