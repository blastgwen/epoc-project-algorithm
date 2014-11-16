package epoc;

import java.util.*;

import epoc.impl.*;
import epoc.impl.result.ResultAlgorithm;
import epoc.impl.result.ResultEnergy;
import epoc.impl.result.ResultIteration;
import utils.AlgorithmUtils;
import utils.CSVUtils;

import utils.ListUtils;

public class EPOCAlgorithm {
	private int indexExecution = 0;
	
	private ArrayList<JobB> listJobB;
	private ArrayList<JobT> listJobT;
    private ArrayList<Server> listServer;
	private ArrayList<Integer> listGreenEnergy;
    private ArrayList<ResultIteration> listIteration;

	
	/**
	 * Initialise le programme et les données
	 * Importe les différents fichiers
	 */
	public void initProgram(){
		listGreenEnergy = CSVUtils.readGreenEnergy();
		listJobT = CSVUtils.readJobT();
        listServer = CSVUtils.readServer();
        listServer = ListUtils.sortByConsumption(listServer);
		listJobB = CSVUtils.readJobB();
        listIteration = new ArrayList<ResultIteration>();
	}


    /**
     * DO the third algortihm and take care of the execution of the others
     */
    public void execProgramme() throws CloneNotSupportedException {

        ArrayList<Server> newServers = listServer;

        while (!isFinished()){
            // remove batch job at each incrementation
            for (Server server : newServers){
                server.removeBatchJobs();
                server.removeJobDone();
            }

            // Fing webjob to move
            ArrayList<JobT> aBouger = new ArrayList<JobT>();
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
            ResultAlgorithm resultMove = AlgorithmUtils.algorithmForWebJobMove(newServers, aBouger);
            newServers = resultMove.getServers();

            // move the wbjob to optimize in minimum server
            // step in addition
            ArrayList<JobT> webs = new ArrayList<JobT>();
            for (Server server : newServers){
                webs.addAll(server.getWebJobs());
                server.clean();
            }
            ResultAlgorithm resultWeb = AlgorithmUtils.algorithmForWebJob(newServers, webs);
            newServers = resultWeb.getServers();

            // select the job you can run
            ArrayList<JobB> jobBs = ListUtils.selectByBeginning(listJobB, indexExecution);
            ArrayList<JobT> jobTs = ListUtils.selectByBeginning(listJobT, indexExecution);
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
            ArrayList<Job> newJobs = new ArrayList<Job>();
            for (JobT jobT : jobTs){
                newJobs.add((JobT) jobT.clone());
            }
            for (JobB jobB : jobBs){
                newJobs.add((JobB) jobB.clone());
            }

            ResultAlgorithm finalResult = doOneIteration(newServers, jobBs, jobTs);
            newServers = finalResult.getServers();
            ResultAlgorithm cloned = (ResultAlgorithm) finalResult.clone();

            // increment the index for all jobs
            List<Job> finishedJobs = new ArrayList<Job>();
            double usedEnergy = 0;
            for (Server serv : newServers){
                List<Job> jobs = serv.getJobs();
                if (jobs.size() <= 0){
                    serv.powerOff();
                } else {
                    // increment
                    serv.powerOn();
                    // calculate
                    usedEnergy += serv.getConsommationTotal();

                    for (Job job : jobs){
                        job.execute();
                        if (job.isDone()){
                            finishedJobs.add(job);
                        }
                    }
                }
            }

            // calculate used energy
            double green = listGreenEnergy.get(indexExecution);
            ResultEnergy ener;
            if (green - usedEnergy <= 0){
                // consumn EDF
                ener = new ResultEnergy(usedEnergy, green, 0, usedEnergy - green);
            } else {
                // wasted green energy
                ener = new ResultEnergy(usedEnergy, green, green - usedEnergy, 0);
            }

            listIteration.add(new ResultIteration(indexExecution, newJobs, cloned, finishedJobs, ener));
            indexExecution ++;
        }

        System.out.println(" TOUS LES JOBS SONT FINIS");
    }

    private ResultAlgorithm doOneIteration(ArrayList<Server> servers, ArrayList<JobB> batchs, ArrayList<JobT> webs) throws CloneNotSupportedException {
        ResultAlgorithm resultWeb = AlgorithmUtils.algorithmForWebJob(servers, webs);
        servers = resultWeb.getServers();

        ResultAlgorithm batchJob = AlgorithmUtils.algorithmForBatchJob(servers, batchs);
        ArrayList<Job> rejects = resultWeb.getRejects();
        rejects.addAll(batchJob.getRejects());

        return new ResultAlgorithm(batchJob.getServers(), rejects);
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

    public List<ResultIteration> getListIteration() {
        return listIteration;
    }
}
