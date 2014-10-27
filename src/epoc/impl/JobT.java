package epoc.impl;

import java.util.List;

import epoc.Job;

public class JobT implements Job{
    private List<Integer> charges;
    private int debut;
    protected int indexExecution = 0;

    private int id;

    private boolean done = false;

    // GETTER / CONSTRUCTOR
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

    @Override
    public int getNextCharge() {
        return this.charges.get(this.indexExecution);
    }

    @Override
    public int getIndexExecution() {
        return this.indexExecution;
    }

    @Override
    public int getChargeAt(int index) {
        return this.charges.get(index);
    }

    @Override
    public boolean isDone() {
        return this.isDone();
    }

    public JobT(int id, int debut, List<Integer> charges){
		this.id = id;
        this.charges = charges;
        this.debut = debut;
	}

    // general methods
    @Override
    public String toString(){
        return "JobT:" +this.id + "[" + this.charges.get(indexExecution) + "]";
    }


    @Override
    public void execute() {
        if (indexExecution < charges.size() - 1)
            this.indexExecution ++;
        else
            this.done = true;
    }
	
}
