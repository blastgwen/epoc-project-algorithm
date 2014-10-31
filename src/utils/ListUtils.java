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

    public static <A extends Job> List<A> sortByAsc(List<A> list){
        Collections.sort(list, new ComparatorJobAsc());
        return list;
    }
}

class ComparatorJobDesc implements Comparator<Job> {
    @Override
    public int compare(Job a, Job b) {
        return (int)Integer.compare(b.getNextCharge(), a.getNextCharge());
    }
}

class ComparatorJobAsc implements Comparator<Job> {
    @Override
    public int compare(Job a, Job b) {
        return (int)Integer.compare(a.getNextCharge(), b.getNextCharge());
    }
}
