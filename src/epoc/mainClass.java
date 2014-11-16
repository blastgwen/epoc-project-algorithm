package epoc;

/**
 * Created by Gwenael on 06/11/2014.
 */
public class mainClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        EPOCAlgorithm algo = new EPOCAlgorithm();
        algo.initProgram();
        algo.execProgramme();
        for (int i =0; i < algo.getListIteration().size(); i++){
            System.out.println(algo.getListIteration().get(i));
        }
    }
}
