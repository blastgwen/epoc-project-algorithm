package epoc.impl;

/**
 * Created by Gwenael on 08/11/2014.
 */
public class UsedEnergy {
    private int indexExecution;
    private double energyUsed;
    private double greenEnergy;
    private double wastedGreenEnergy;
    private double energyEDF;

    public UsedEnergy(int indexExecution, double energyUsed, double greenEnergy, double wastedGreenEnergy, double energyEDF) {
        this.indexExecution = indexExecution;
        this.energyUsed = energyUsed;
        this.greenEnergy = greenEnergy;
        this.wastedGreenEnergy = wastedGreenEnergy;
        this.energyEDF = energyEDF;
    }

    public double getEnergyUsed() {
        return energyUsed;
    }

    public int getIndexExecution() {
        return indexExecution;
    }

    public double getGreenEnergy() {
        return greenEnergy;
    }

    public double getWastedGreenEnergy() {
        return wastedGreenEnergy;
    }

    public double getEnergyEDF() {
        return energyEDF;
    }

    @Override
    public String toString(){
        String str =  indexExecution +" : Used(" + energyUsed +") - Green energy("+this.greenEnergy+")";
        if (wastedGreenEnergy == 0)
            str += " - Energy EDF("+this.energyEDF+") \n";
        else
            str += " - WastedEnergy("+this.wastedGreenEnergy+") \n";

        return str;
    }
}
