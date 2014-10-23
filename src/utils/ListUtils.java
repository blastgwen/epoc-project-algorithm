package utils;

import epoc.Job;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Gwenael on 23/10/2014.
 */
public class ListUtils {

    public static <A extends Job> List<A> sortByDesc(List<A> list){
        Collections.sort(list, new ComparatorJobDesc());
        return list;
    }

    public static <A extends Job> int getCharges(List<A> list){
        int res = 0;
        for (Job job : list){
            res += job.getCharge();
        }
        return res;
    }

    public static <A extends Job> String ListToString(List<A> list){
        String res = "[";
        for (A job : list){
            res += job.getCharge() + ", ";
        }
        return res.substring(0, res.length() - 2) + "]";
    }
}

class ComparatorJobDesc implements Comparator<Job> {
    @Override
    public int compare(Job a, Job b) {
        return (int)Integer.compare(b.getCharge(), a.getCharge());
    }
}
