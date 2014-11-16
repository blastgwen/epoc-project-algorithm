package epoc;

import java.util.List;

public interface Job extends Cloneable{

    // GETTER
	List<Integer> getCharges();
    int getId();
    int getDebut();

    // general methods
    boolean isDone();
    void execute();
    int getNextCharge();
    int getIndexExecution();
    int getChargeAt(int index);
    Object clone() throws CloneNotSupportedException;
}
