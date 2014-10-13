package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobB implements Job{
	private List<Integer> charges;
	private int maxJours;
	
	@Override
	public List<Integer> getCharges() {
		return this.charges;
	}
	
	public int getMaxJours(){
		return this.maxJours;
	}
	
	public JobB(List<Integer> list, int jours){
		this.charges = list;
		this.maxJours = jours;
	}
}
