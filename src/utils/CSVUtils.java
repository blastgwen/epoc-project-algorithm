package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.*;

import epoc.impl.JobB;
import epoc.impl.JobT;

public class CSVUtils {

	public static List<Integer> readGreenEnergy(){
		List<Integer> greenEnergyData = new ArrayList<Integer>();
		try {
			CSVParser parser = new CSVParser(new FileReader("ressources/GreenEnergy.csv"), CSVFormat.RFC4180);
			for (CSVRecord csvRecord : parser) {
				int lineSize = csvRecord.size();
				for(int i = 0; i < lineSize; i++){
					greenEnergyData.add(Integer.parseInt(csvRecord.get(i)));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return greenEnergyData;
	}

	public static List<JobT> readJobT(){
		List<JobT> jobTList = new ArrayList<JobT>();
		try {
			CSVParser parser = new CSVParser(new FileReader("ressources/JobT.csv"), CSVFormat.RFC4180);
			for (CSVRecord csvRecord : parser) {
				List<Integer> jobData = new ArrayList<Integer>();
				int lineSize = csvRecord.size();
				for(int i = 0; i < lineSize; i++){
					jobData.add(Integer.parseInt(csvRecord.get(i)));
				}
				JobT job = new JobT(jobData);
				jobTList.add(job);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jobTList;
	}
	
	public static List<JobB> readJobB(){
		List<JobB> jobBList = new ArrayList<JobB>();
		try {
			CSVParser parser = new CSVParser(new FileReader("ressources/JobB.csv"), CSVFormat.RFC4180);
			for (CSVRecord csvRecord : parser) {
				int lineSize = csvRecord.size();
				if(lineSize > 2){
					int dateButoir = Integer.parseInt(csvRecord.get(0));
					List<Integer> jobData = new ArrayList<Integer>(lineSize-1);
					for(int i = 1; i < lineSize; i++){
						jobData.add(Integer.parseInt(csvRecord.get(i)));
					}
					JobB job = new JobB(dateButoir, jobData);
					jobBList.add(job);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jobBList;
	}

}
