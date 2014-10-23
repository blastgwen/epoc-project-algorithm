package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobT implements Job{
    private int id;
	private int charge;

	@Override
    public int getCharge() {
        return this.charge;
    }
	
	public JobT(int id, int charge){
		this.id = id;
        this.charge = charge;
	}

    @Override
    public String toString(){
        return this.id + ":[" + this.charge + "]";
    }
	
}
