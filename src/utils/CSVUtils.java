package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import epoc.impl.Server;

import org.apache.commons.csv.*;

import epoc.impl.JobB;
import epoc.impl.JobT;

public class CSVUtils {

	public static ArrayList<Integer> readGreenEnergy() throws Exception{
        ArrayList<Integer> greenEnergyData = new ArrayList<Integer>();
		try {
			CSVParser parser = new CSVParser(new FileReader("ressources/GreenEnergy.csv"), CSVFormat.RFC4180);
			for (CSVRecord csvRecord : parser) {
				int lineSize = csvRecord.size();
				for(int i = 0; i < lineSize; i++){
					greenEnergyData.add(Integer.parseInt(csvRecord.get(i)));
				}
			}
		} catch (FileNotFoundException e) {
            Exception fnf = new Exception("Fichier GreenEnergy.csv introuvable dans le dossier ressources.");
            fnf.setStackTrace(e.getStackTrace());
            throw fnf;
        } catch (IOException e) {
            throw e;
        }

		return greenEnergyData;
	}
    public static ArrayList<JobT> readJobT() throws Exception{
        ArrayList<JobT> jobTList = new ArrayList<JobT>();
        try {
            CSVParser parser = new CSVParser(new FileReader("ressources/JobT.csv"), CSVFormat.RFC4180);
            for (CSVRecord csvRecord : parser) {
                int lineSize = csvRecord.size();
                if(lineSize > 1){
                    List<Integer> jobData = new ArrayList<Integer>();
                    for(int i = 2; i < lineSize; i++){
                        jobData.add(Integer.parseInt(csvRecord.get(i)));
                    }
                    JobT job = new JobT(Integer.parseInt(csvRecord.get(0)),
                            Integer.parseInt(csvRecord.get(1)), jobData);

                    jobTList.add(job);
                }
            }
        } catch (FileNotFoundException e) {
            Exception fnf = new Exception("Fichier JobT.csv introuvable dans le dossier ressources.");
            fnf.setStackTrace(e.getStackTrace());
            throw fnf;
        } catch (IOException e) {
            throw e;
        }

        return jobTList;
    }

    public static ArrayList<JobB> readJobB() throws Exception{
        ArrayList<JobB> jobBList = new ArrayList<JobB>();
        try {
            CSVParser parser = new CSVParser(new FileReader("ressources/JobB.csv"), CSVFormat.RFC4180);
            for (CSVRecord csvRecord : parser) {
                int lineSize = csvRecord.size();
                if(lineSize > 1){
                    List<Integer> jobData = new ArrayList<Integer>();
                    for(int i = 2; i < lineSize; i++){
                        jobData.add(Integer.parseInt(csvRecord.get(i)));
                    }
                    JobB job = new JobB(Integer.parseInt(csvRecord.get(0)),
                            Integer.parseInt(csvRecord.get(1)), jobData);

                    jobBList.add(job);
                }
            }
        } catch (FileNotFoundException e) {
            Exception fnf = new Exception("Fichier JobB.csv introuvable dans le dossier ressources.");
            fnf.setStackTrace(e.getStackTrace());
            throw fnf;
        } catch (IOException e) {
            throw e;
        }

        return jobBList;
    }


    public static ArrayList<Server> readServer() throws Exception{
        ArrayList<Server> serverList = new ArrayList<Server>();
        try {
            CSVParser parser = new CSVParser(new FileReader("ressources/Server.csv"), CSVFormat.RFC4180);
            for (CSVRecord csvRecord : parser) {
                Server server = new Server(Integer.parseInt(csvRecord.get(0)),
                        Integer.parseInt(csvRecord.get(1)));
                serverList.add(server);
            }
        } catch (FileNotFoundException e) {
            Exception fnf = new Exception("Fichier Server.csv introuvable dans le dossier ressources.");
            fnf.setStackTrace(e.getStackTrace());
            throw fnf;
        } catch (IOException e) {
            throw e;
        }

        return serverList;
    }
}
