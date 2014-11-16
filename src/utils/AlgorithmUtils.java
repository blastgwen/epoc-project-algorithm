package utils;

import epoc.Job;
import epoc.impl.JobB;
import epoc.impl.JobT;
import epoc.impl.result.ResultAlgorithm;
import epoc.impl.Server;

import java.util.*;

/**
 * Created by Gwenael on 23/10/2014.
 */
public class AlgorithmUtils {

    /**
     * Premier algorithme
     *
     * @param listServer
     * @param listJob list of jobT
     * @return a list of server full of Job
     */
    public static ResultAlgorithm algorithmForWebJob(ArrayList<Server> listServer, ArrayList<JobT> listJob) throws CloneNotSupportedException {

        listJob = ListUtils.sortByDesc(listJob);
        ArrayList<Job> rejects = new ArrayList<Job>();

        int n = 0;
        for (JobT job : listJob) {
            //  IF > 50 %
            if (job.getNextCharge() > 50 && listServer.get(n).getCharges() < 50) {
                if (n + 1 > listServer.size() || job.getNextCharge() > 100) {
                    rejects.add((Job)job.clone());
                } else {
                    listServer.get(n).addJob(job);
                    n++;
                }
            }
            // ELSE < 50 %
            else {
                int u = 0;
                while (u < listServer.size() &&
                        job.getNextCharge() > 100 - listServer.get(u).getCharges()) {
                    u++;
                }
                if (u < listServer.size()) {
                    listServer.get(u).addJob(job);
                } else {
                    rejects.add((Job)job.clone());
                }
            }
        }

        return new ResultAlgorithm(listServer, rejects);
    }

    /**
     *
     * @param listServer
     * @param listJob list of jobB
     * @return a list of server full of Job
     */
    public static ResultAlgorithm algorithmForBatchJob(ArrayList<Server> listServer, ArrayList<JobB> listJob) throws CloneNotSupportedException {

        listJob = ListUtils.sortByDesc(listJob);
        ArrayList<Job> rejects = new ArrayList<Job>();

        for (JobB job : listJob){
            int u = 0;
            while (u < listServer.size() &&
                    job.getNextCharge() > 100 - listServer.get(u).getCharges()) {
                u ++;
            }
            if (u < listServer.size()) {
                listServer.get(u).addJob(job);
            } else {
                rejects.add((Job)job.clone());
            }
        }

        return new ResultAlgorithm(listServer, rejects);
    }

    public static ResultAlgorithm algorithmForWebJobMove(ArrayList<Server> listServer, ArrayList<JobT> listJob) throws CloneNotSupportedException {

        listJob = ListUtils.sortByDesc(listJob);
        ArrayList<Job> rejects = new ArrayList<Job>();

        for (JobT job : listJob) {
            int u = 0;
            while (u < listServer.size() &&
                    job.getNextCharge() > 100 - listServer.get(u).getCharges()) {
                u++;
            }
            if (u < listServer.size()) {
                listServer.get(u).addJob(job);
            } else {
                rejects.add((Job)job.clone());
            }
        }

        return new ResultAlgorithm(listServer, rejects);
    }
}
