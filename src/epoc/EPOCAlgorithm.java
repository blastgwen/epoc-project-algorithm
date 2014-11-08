package epoc;

import java.util.*;

import epoc.impl.Server;
import epoc.impl.UsedEnergy;
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
    private List<UsedEnergy> listEnergy;
	
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
     * DO the third algortihm and take care of the execution of the others
     */
    public void execProgramme() {

        List<Server> newServers = new ArrayList<Server>();

        while (!isFinished()){
            // remove batch job at each incrementation
            for (Server server : newServers){
                server.removeBatchJobs();
            }

            // TODO: move webjob in running in different server (algorithm 3)


            // select the job you can run
            List<JobB> jobBs = ListUtils.selectByBeginning(listJobB, indexExecution);
            List<JobT> jobTs = ListUtils.selectByBeginning(listJobT, indexExecution);

            newServers = doOneIteration(newServers, jobBs, jobTs);

            // increment the index for all jobs
            double usedEnergy = 0;
            for (Server serv : newServers){
                List<Job> jobs = serv.getJobs();
                if (jobs.size() <= 0){
                    serv.powerOff();
                } else {
                    // increment
                    serv.powerOn();
                    for (Job job : jobs){
                        job.execute();
                    }

                    // calculate
                    usedEnergy += serv.getConsommationTotal();
                }
            }

            // calculate used energy
            double green = listGreenEnergy.get(indexExecution);
            UsedEnergy ener;
            if (green - usedEnergy <= 0){
                // consmun EDF
                ener = new UsedEnergy(indexExecution, usedEnergy, green, 0, usedEnergy - green);
            } else {
                // wasted green energy
                ener = new UsedEnergy(indexExecution, usedEnergy, green, green - usedEnergy, 0);
            }

            listEnergy.add(ener);
            indexExecution ++;
        }
    }

    private List<Server> doOneIteration(List<Server> servers, List<JobB> batchs, List<JobT> webs){
        // TODO: launch the first algorithm


        // TODO: launch the second algorithms


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
