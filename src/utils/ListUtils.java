package utils;

import epoc.Job;
import epoc.impl.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Gwenael on 23/10/2014.
 */
public class ListUtils {

    public static <A extends Job> ArrayList<A> sortByDesc(ArrayList<A> list){
        Collections.sort(list, new ComparatorJobDesc());
        return list;
    }

    public static <A extends Job> ArrayList<A> selectByBeginning(ArrayList<A> list, int debut) {
        ArrayList<A> res = new ArrayList<A>();

        for (A job : list) {
            if (job.getDebut() <= debut && !job.isDone())
                res.add(job);
        }

        return res;
    }

    public static <A extends Job> ArrayList<A> sortByAsc(ArrayList<A> list){
        Collections.sort(list, new ComparatorJobAsc());
        return list;
    }

    public static ArrayList<Server> sortByConsumption(ArrayList<Server> list){
        Collections.sort(list, new ComparatorServerConsumption());
        return list;
    }
}

class ComparatorJobDesc implements Comparator<Job> {
    @Override
    public int compare(Job a, Job b) {
        return Integer.compare(b.getNextCharge(), a.getNextCharge());
    }
}

class ComparatorJobAsc implements Comparator<Job> {
    @Override
    public int compare(Job a, Job b) {
        return Integer.compare(a.getNextCharge(), b.getNextCharge());
    }
}

class ComparatorServerConsumption implements Comparator<Server> {
    @Override
    public int compare(Server a, Server b) {
        return Integer.compare(a.getConsommation(), b.getConsommation());
    }
}
