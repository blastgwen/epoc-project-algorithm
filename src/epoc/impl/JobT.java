package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobT implements Job{
	private int charge;

	@Override
    public int getCharge() {
        return this.charge;
    }
	
	public JobT(int charge){
		this.charge = charge;
	}
	
}
