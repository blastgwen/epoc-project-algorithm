package epoc;

import java.util.List;
import utils.CSVUtils;

import epoc.impl.JobB;
import epoc.impl.JobT;

public class EPOCAlgorithm {
	private int indexExecution = 0;
	
	private List<JobB> listJobB;
	private List<JobT> listJobT;
	private List<Integer> listGreenEnergy;
	
	private Integer EDF = 15;
	
	private int wastedEnergy = 0;
	
	/**
	 * Initialise le programme et les données
	 * Importe les différents fichiers
	 */
	private void initProgram(){
		listGreenEnergy = CSVUtils.readGreenEnergy();
		listJobT = CSVUtils.readJobT();
		listJobB = CSVUtils.readJobB();
	}
	
	/**
	 * Do one iteration
	 */
	private void doOneIteration(){
		int maxEnergie = listGreenEnergy.get(indexExecution);
		int energieUsed = 0;
		
		System.out.println("------- Iteration " + indexExecution + "-------");
		System.out.println("Energie Verte : " + maxEnergie);
		
		System.out.println("Job T");
		for (JobT job : listJobT) {
			System.out.println("\t" + job.getChargeAt(indexExecution));
			energieUsed += job.getChargeAt(indexExecution);
		}
		
		System.out.println("Energie restante :" + (maxEnergie - energieUsed));
		
		System.out.println("Job B");
		int i = 1;
		for (JobB job : listJobB) {
			if (!job.isDone()){
				int charge = job.getNextCharge();
				if (charge<= (maxEnergie - energieUsed)){
					System.out.println("\tJob n°" + i + " : "+ charge + " ( itetation " + job.getIndexExecution() + " )");
					energieUsed += charge;
					job.incrementerExecution();
				} else {
					System.out.println("\tJob n°" + i + " : impossible à executer ( " + charge + " )");
				}
			} else {
				System.out.println("\tJob n°" + i + " terminé ");	
			}
			i ++;
		}
		
		indexExecution++;
		System.out.println("Energie perdu à cette itération : " + (maxEnergie - energieUsed));
		this.wastedEnergy += maxEnergie - energieUsed;
	}
	
	private void execProgramme(){
		
		for (int i = 0; i < listGreenEnergy.size(); i ++){
			doOneIteration();
		}
	}
	public static void main(String[] args) {

		EPOCAlgorithm epoc = new EPOCAlgorithm();
		epoc.initProgram();
		epoc.execProgramme();
		
		System.out.println("Energie totale perdue : " + epoc.wastedEnergy);
	}
}
