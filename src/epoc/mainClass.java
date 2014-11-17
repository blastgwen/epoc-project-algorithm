package epoc;

import utils.LoggerUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Gwenael on 06/11/2014.
 */
public class mainClass {
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
    	try{
	        EPOCAlgorithm algo = new EPOCAlgorithm();
	        algo.initProgram();
	        algo.execProgramme();
	
	        LoggerUtils.logGeneral(algo.getListIteration());
	        LoggerUtils.logServers(algo.getListIteration());
	        LoggerUtils.logRejects(algo.getListIteration());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
