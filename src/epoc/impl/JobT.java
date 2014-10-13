package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobT implements Job{
	private List<Integer> charges;

	@Override
	public List<Integer> getCharges() {
		return this.charges;
	}
	
	public JobT(List<Integer> list){
		this.charges = list;
	}
	
	public Integer getChargeAt(int index) {
		return charges.get(index);
	}
}
