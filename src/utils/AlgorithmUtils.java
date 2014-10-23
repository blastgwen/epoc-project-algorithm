package utils;

import epoc.impl.JobT;

import java.util.*;

/**
 * Created by Gwenael on 23/10/2014.
 */
public class AlgorithmUtils {

    public static List<List<JobT>> algorithmeForWebJob(List<JobT> list, int nbServer){

        List<List<JobT>> newList = new ArrayList<List<JobT>>();

        // Create a List for each server

        int index = nbServer;
        while (index -- != 0){
            newList.add(new ArrayList<JobT>());
        }
        System.out.println(" ----- FIRST LIST ------ \n" + ListUtils.ListToString(list) + '\n');
        list = ListUtils.sortByDesc(list);
        System.out.println(" ----- DESC LIST ------ \n" + ListUtils.ListToString(list) + '\n');

        int n = 0;
        for (JobT job : list){
            //  IF > 50 %
            if (job.getCharge() > 50){
                if (n + 1 > nbServer){
                    System.out.println(" -- REMOVE JOB : " + job.getCharge());
                } else {
                    (newList.get(n)).add(job);
                    n ++;
                }
            }
            // ELSE < 50 %
            else {
                int u = 0;
                while (u < nbServer &&
                        job.getCharge() > 100 - ListUtils.getCharges(newList.get(u))){
                    u ++;
                }
                if (u < nbServer){
                    (newList.get(u)).add(job);
                }
            }
        }

        return newList;
    }

}
