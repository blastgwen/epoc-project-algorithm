package epoc.impl;

import epoc.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gwenael on 23/10/2014.
 */
public class Server {

    protected int consommation;
    protected int id;
    private List<Job> jobs = new ArrayList<Job>();
    private boolean on = false;

    public Server(int id, int cons){
        this.id = id;
        this.consommation = cons;
    }

    public int getCharges(){
        int res = 0;
        for (Job job : jobs){
            res += job.getNextCharge();
        }
        return res;
    }

    public void setJobs(List<Job> jobs){
        this.jobs = jobs;
    }

    public List<Job> getJobs(){
        return this.jobs;
    }

    public List<JobT> getWebJobs(){
        List<JobT> webJobs = new ArrayList<JobT>();
        for (Job job : jobs){
            if (job instanceof JobT)
                webJobs.add((JobT)job);
        }
        return webJobs;
    }
    public int getConsommation(){
        return this.consommation;
    }

    public int getConsommationTotal(){
        return this.consommation * this.getCharges();
    }

    public void addJob(Job job){
        this.jobs.add(job);
    }

    public void removeBatchJobs(){
       for (int i = 0; i < jobs.size(); i++){
            if (jobs.get(i) instanceof JobB){
                jobs.remove(i);
                i --;
            }
        }
    }

    public void removeJobDone(){
        for (int i = 0; i < jobs.size(); i++){
            if (jobs.get(i).isDone()){
                jobs.remove(i);
                i --;
            }
        }
    }
    @Override
    public String toString(){
        String res = "Server " +id + " [";
        for (Job job : jobs){
            res += job.toString() + ", ";
        }
        return res.substring(0, res.length() - 2) + "] : " + this.getCharges() + '\n';
    }

    public void clean(){
        jobs = new ArrayList<Job>();
    }

    public void powerOn(){
        this.on = true;
    }

    public void powerOff(){
        this.on = false;
    }
}
