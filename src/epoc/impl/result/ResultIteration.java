package epoc.impl.result;

import epoc.Job;

import java.util.List;

/**
 * Created by Gwen on 16/11/2014.
 */
public class ResultIteration {
    private int indexExecution;
    private List<Job> newJobs;
    private ResultAlgorithm algorithm;
    private List<Job> finishedJobs;
    private ResultEnergy energy;

    public ResultIteration(int indexExecution, List<Job> newJobs, ResultAlgorithm algorithm, List<Job> finishedJobs, ResultEnergy energy) {
        this.algorithm = algorithm;
        this.newJobs = newJobs;
        this.finishedJobs = finishedJobs;
        this.energy = energy;
        this.indexExecution = indexExecution;
    }

    @Override
    public String toString() {
        return "--------------------- ResultIteration n°" + indexExecution + " ---------------------" +
                "\n----- Nouveaux Jobs :" + newJobs +
                "\n" + algorithm +
                "\n----- Jobs Terminés: " + finishedJobs +
                "\n" + energy +
                "----------------------------------------------------------------\n";
    }

    public int getIndexExecution() {
        return indexExecution;
    }

    public List<Job> getNewJobs() {
        return newJobs;
    }

    public ResultAlgorithm getAlgorithm() {
        return algorithm;
    }

    public List<Job> getFinishedJobs() {
        return finishedJobs;
    }

    public ResultEnergy getEnergy() {
        return energy;
    }
}
