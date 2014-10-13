package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobB implements Job{
	private List<Integer> charges;
	private int dateButoir;
	private int indexExecution = 0;
	
	private boolean done = false;
	
	@Override
	public List<Integer> getCharges() {
		return this.charges;
	}
	
	public int getDateButoire(){
		return this.dateButoir;
	}
	
	public JobB(int dateButoir, List<Integer> list){
		this.charges = list;
		this.dateButoir = dateButoir;
	}
	
	public void incrementerExecution(){
		if (indexExecution < charges.size() - 1)
			this.indexExecution ++;
		else 
			this.done = true;
	}
	
	public int getIndexExecution(){
		return this.indexExecution;
	}
	
	public int getNombreExecutionRestante(){
		return this.charges.size() - indexExecution; 
	}

	public Integer getNextCharge() {
		return charges.get(indexExecution);
	}
	
	public boolean isDone(){
		return this.done;
	}
}
