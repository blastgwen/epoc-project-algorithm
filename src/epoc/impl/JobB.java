package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobB implements Job{
	private List<Integer> charges;
	private int debut;
	protected int indexExecution = 0;

    private int id;
	
	private boolean done = false;

    // GETTER /

    public JobB(int id, int debut, List<Integer> charges){
        this.charges = charges;
        this.id = id;
        this.debut = debut;
    }

	@Override
     public List<Integer> getCharges() {
        return this.charges;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getDebut() {
        return this.debut;
    }
	
	public int getIndexExecution(){
		return this.indexExecution;
	}

    @Override
    public int getChargeAt(int index) {
        return this.charges.get(index);
    }


    // General Method

    @Override
	public int getNextCharge() {
		return charges.get(indexExecution);
	}

    @Override
	public boolean isDone(){
		return this.done;
	}

    @Override
    public void execute() {
        if (indexExecution < charges.size() - 1)
            this.indexExecution ++;
        else
            this.done = true;
    }


    @Override
    public String toString(){
        return "JobB:" +this.id + "["  + indexExecution + ":" + this.charges.get(indexExecution) + "]";
    }
}
