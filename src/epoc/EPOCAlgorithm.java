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
	
	public static void main(String[] args) {

		EPOCAlgorithm epoc = new EPOCAlgorithm();
		epoc.initProgram();
		System.out.println("TMLP");
		
	}
}
