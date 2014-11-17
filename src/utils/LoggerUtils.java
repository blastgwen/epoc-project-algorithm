package utils;

import epoc.impl.Server;
import epoc.impl.result.ResultAlgorithm;
import epoc.impl.result.ResultIteration;

import java.io.*;
import java.util.List;

/**
 * Created by Gwenael on 17/11/2014.
 */
public class LoggerUtils {

    public static void logGeneral(List<ResultIteration> lists) throws FileNotFoundException {
        File folder = new File("results");
        folder.mkdir();

        PrintWriter writer = new PrintWriter("results/logs.txt");
        for (int i =0; i < lists.size(); i++){
            writer.println(lists.get(i));
        }
        writer.close();
    }

    public static void logServers(List<ResultIteration> lists) throws IOException {
        File folder = new File("results");
        folder.mkdir();

        for (Server server : lists.get(0).getAlgorithm().getServers()){
            new File("results/server-" + server.getId() + ".txt").delete();
            new File("results/server-" + server.getId() + ".txt").createNewFile();
        }

        for (ResultIteration res : lists){
            for (Server serv : res.getAlgorithm().getServers()){
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("results/server-" + serv.getId() + ".txt", true)));
                out.println(" ------- Iteration n°" + res.getIndexExecution());
                out.println(serv.toString());
                out.println();
                out.close();
            }
        }
    }

    public static void logRejects(List<ResultIteration> lists) throws IOException {
        File folder = new File("results");
        folder.mkdir();
        new File("results/rejetcs.txt").delete();
        new File("results/rejetcs.txt").createNewFile();

        for (ResultIteration res : lists) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("results/rejetcs.txt", true)));
            out.println(" ------- Iteration n°" + res.getIndexExecution());
            out.println(res.getAlgorithm().getRejects());
            out.println();
            out.close();
        }
    }
}
