package utils;

import epoc.impl.JobB;
import epoc.impl.JobT;
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
    public static List<Server> algorithmForWebJob(List<Server> listServer, List<JobT> listJob) {

        System.out.println(" ----- FIRST LIST ------ \n" + listJob + '\n');
        listJob = ListUtils.sortByDesc(listJob);
        System.out.println(" ----- DESC LIST ------ \n" + listJob + '\n');

        int n = 0;
        for (JobT job : listJob) {
            //  IF > 50 %
            if (job.getNextCharge() > 50) {
                if (n + 1 > listServer.size() || job.getNextCharge() > 100) {
                    System.out.println(" -- REMOVE JOB : " + job.getNextCharge());
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
                }
            }
        }

        return listServer;
    }

    /**
     *
     * @param listServer
     * @param listJob list of jobB
     * @return a list of server full of Job
     */
    public static List<Server> algorithmForBatchJob(List<Server> listServer, List<JobB> listJob) {
        System.out.println(" ----- FIRST LIST ------ \n" + listJob + '\n');
        listJob = ListUtils.sortByDesc(listJob);
        System.out.println(" ----- DESC LIST ------ \n" + listJob + '\n');

        for (JobB job : listJob){
            int u = 0;
            while (u < listServer.size() &&
                    job.getNextCharge() > 100 - listServer.get(u).getCharges()) {
                u ++;
            }
            if (u < listServer.size()) {
                listServer.get(u).addJob(job);
            }
        }

        return listServer;
    }
}
