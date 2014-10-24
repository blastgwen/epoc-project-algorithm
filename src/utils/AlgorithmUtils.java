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
     * @param listJob
     * @return listServer avec une liste de job
     */
    public static List<Server> algorithmForWebJob(List<Server> listServer, List<JobT> listJob) {

        System.out.println(" ----- FIRST LIST ------ \n" + listJob + '\n');
        listJob = ListUtils.sortByDesc(listJob);
        System.out.println(" ----- DESC LIST ------ \n" + listJob + '\n');

        int n = 0;
        for (JobT job : listJob) {
            //  IF > 50 %
            if (job.getCharge() > 50) {
                if (n + 1 > listServer.size()) {
                    System.out.println(" -- REMOVE JOB : " + job.getCharge());
                } else {
                    listServer.get(n).addJob(job);
                    n++;
                }
            }
            // ELSE < 50 %
            else {
                int u = 0;
                while (u < listServer.size() &&
                        job.getCharge() > 100 - listServer.get(u).getCharges()) {
                    u++;
                }
                if (u < listServer.size()) {
                    listServer.get(u).addJob(job);
                }
            }
        }

        return listServer;
    }


    public static List<Server> algorithmForBatchJob(List<Server> listServer, List<JobB> listJob) {
        System.out.println(" ----- FIRST LIST ------ \n" + listJob + '\n');
        listJob = ListUtils.sortByDesc(listJob);
        System.out.println(" ----- DESC LIST ------ \n" + listJob + '\n');

        for (JobB job : listJob){
            int u = 0;
            while (u < listServer.size() &&
                    job.getCharge() > 100 - listServer.get(u).getCharges()) {
                u ++;
            }
            if (u < listServer.size()) {
                listServer.get(u).addJob(job);
            }
        }

        return listServer;
    }
}
