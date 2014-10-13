package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobB implements Job{
	private List<Integer> charges;
	private int maxJours;
	private int indexExecution = 0;
	
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
	
	public void incrementerExecution(){
		this.indexExecution ++;
	}
	
	public int getIndexExecution(){
		return this.indexExecution;
	}
	
	public int getNombreExecutionRestante(){
		return this.charges.size() - indexExecution; 
	}
}
