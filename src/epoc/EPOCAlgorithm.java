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
        listEnergy = new ArrayList<UsedEnergy>();
	}


    /**
     * DO the third algortihm and take care of the execution of the others
     */
    public void execProgramme() {

        List<Server> newServers = listServer;

        while (!isFinished()){
            System.out.println(" ----------- ITERATION N°" + indexExecution);
            // remove batch job at each incrementation
            for (Server server : newServers){
                server.removeBatchJobs();
                server.removeJobDone();
            }
            System.out.println(" ------ ETAT des Server (sans batchJob)");
            System.out.println(newServers);

            // TODO: move webjob in running in different server (algorithm 3)
            // Fing webjob to move
            List<JobT> aBouger = new ArrayList<JobT>();
            for (Server server : newServers){
                if (server.getCharges() > 100){
                    List<JobT> webs = ListUtils.sortByAsc(server.getWebJobs());
                    int i = 0;
                    while(server.getCharges() > 100){
                        server.getJobs().remove(webs.get(i));
                        aBouger.add(webs.get(i));
                        i ++;
                    }
                }
            }
            // move webjob into other server
            newServers = AlgorithmUtils.algorithmForWebJobMove(newServers, aBouger);
            System.out.println(" ------ ETAT des Server (après déplacement)");
            System.out.println(newServers);

            // select the job you can run
            List<JobB> jobBs = ListUtils.selectByBeginning(listJobB, indexExecution);
            List<JobT> jobTs = ListUtils.selectByBeginning(listJobT, indexExecution);
            // suppression des doublons et webjobs déjà lancé
            for (Server server : newServers){
                for (JobT job : server.getWebJobs()){
                    for (int i=0; i < jobTs.size(); i++){
                        if (job.getId() == jobTs.get(i).getId()){
                            jobTs.remove(i);
                            i --;
                        }
                    }
                }
            }
            System.out.println(" ------ WebJob A placer");
            System.out.println(jobTs);
            System.out.println(" ------ BatchJob A placer");
            System.out.println(jobBs);

            newServers = doOneIteration(newServers, jobBs, jobTs);

            System.out.println(" ----- Après algorithme");
            System.out.println(newServers);

            // increment the index for all jobs
            System.out.println(" ----- Incrémentation ");
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
                // consumn EDF
                ener = new UsedEnergy(indexExecution, usedEnergy, green, 0, usedEnergy - green);
            } else {
                // wasted green energy
                ener = new UsedEnergy(indexExecution, usedEnergy, green, green - usedEnergy, 0);
            }

            listEnergy.add(ener);
            indexExecution ++;
        }

        System.out.println(" TOUS LES JOBS SONT FINIS");
        System.out.println(listEnergy);
    }

    private List<Server> doOneIteration(List<Server> servers, List<JobB> batchs, List<JobT> webs){
        servers = AlgorithmUtils.algorithmForWebJob(servers, webs);

        servers = AlgorithmUtils.algorithmForBatchJob(servers, batchs);

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
