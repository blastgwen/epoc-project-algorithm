package epoc;

import java.util.*;

import epoc.impl.Server;
import utils.AlgorithmUtils;
import utils.CSVUtils;

import epoc.impl.JobB;
import epoc.impl.JobT;
import utils.ListUtils;

public class EPOCAlgorithm {
	private int indexExecution = 0;
	
	private List<JobB> listJobB;
	private List<JobT> listJobT;
    private List<Server> listServer;
	private List<Integer> listGreenEnergy;
	
	private List<Integer> wastedEnergy = new ArrayList<Integer>();
	
	/**
	 * Initialise le programme et les données
	 * Importe les différents fichiers
	 */
	public void initProgram(){
		listGreenEnergy = CSVUtils.readGreenEnergy();
		listJobT = CSVUtils.readJobT();
        listServer = CSVUtils.readServer();
		listJobB = CSVUtils.readJobB();
	}


    /**
     * DO the third algortihm and take care of the execution
     */
    public void execProgramme() {

        List<Server> oldServers = new ArrayList<Server>();
        List<Server> newServers = new ArrayList<Server>();

        while (!isFinished()){
            oldServers = newServers;

            // TODO: increment all the job done and remove batchJob
            for (Server server : newServers){


                server.removeBatchJobs();
            }

            // select the job you can run
            List<JobB> jobBs = ListUtils.selectByBeginning(listJobB, indexExecution);
            List<JobT> jobTs = ListUtils.selectByBeginning(listJobT, indexExecution);


            newServers = doOneIteration(newServers, jobBs, jobTs);

            indexExecution ++;
        }
    }

    private List<Server> doOneIteration(List<Server> servers, List<JobB> batchs, List<JobT> webs){



        return servers;
    }

    private boolean isFinished(){

        for (JobB job : listJobB){
            if (!job.isDone())
                return false;
        }

        for (JobT job : listJobT){
            if (!job.isDone())
                return false;
        }

        return true;
    }

    private int getTotalEnergyUsed(){
        int totalEnergyUsed = 0;
        for (Server server : listServer){
            totalEnergyUsed += server.getConsommationTotal();
        }

        return totalEnergyUsed;
    }
}
