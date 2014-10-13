package epoc;

import java.util.List;

import epoc.impl.JobB;
import epoc.impl.JobT;

public class EPOCAlgortihm {
	private int indexExecution = 0;
	
	private List<JobB> listJobB;
	private List<JobT> listJobT;
	private List<Integer> listGreenEnergy;
	
	private int wastedEnergy = 0;
	
	/**
	 * Initialise le programme et les données
	 * Importe les différents fichiers
	 */
	private void initProgram(){
		
	}
	
	public static void main(String[] args) {

		EPOCAlgortihm epoc = new EPOCAlgortihm();
		epoc.initProgram();

	}

}
