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
        
        Double maxEDF = 0.0;
        Double sumGreenEnergy = 0.0;
        for(ResultIteration res : lists){
        	sumGreenEnergy += res.getEnergy().getWastedGreenEnergy();
        	if(res.getEnergy().getEnergyEDF() > maxEDF){
        		maxEDF = res.getEnergy().getEnergyEDF();
        	}
        }
        
        writer.println("------------------------ BILAN ENERGETIQUE ------------------------");
        writer.println("Energie verte perdue : " + sumGreenEnergy);
        writer.println("Max abonnement EDF : " + maxEDF);
        
        
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
        new File("results/rejects.txt").delete();
        new File("results/rejects.txt").createNewFile();

        for (ResultIteration res : lists) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("results/rejects.txt", true)));
            out.println(" ------- Iteration n°" + res.getIndexExecution());
            out.println(res.getAlgorithm().getRejects());
            out.println();
            out.close();
        }
    }
}
