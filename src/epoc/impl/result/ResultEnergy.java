package epoc.impl.result;

/**
 * Created by Gwenael on 08/11/2014.
 */
public class ResultEnergy {
    private double energyUsed;
    private double greenEnergy;
    private double wastedGreenEnergy;
    private double energyEDF;

    public ResultEnergy(double energyUsed, double greenEnergy, double wastedGreenEnergy, double energyEDF) {
        this.energyUsed = energyUsed;
        this.greenEnergy = greenEnergy;
        this.wastedGreenEnergy = wastedGreenEnergy;
        this.energyEDF = energyEDF;
    }

    @Override
    public String toString(){
        String str =  "----- Energie utilisée : Used(" + energyUsed +") - Green energy("+this.greenEnergy+")";
        if (wastedGreenEnergy == 0)
            str += " - Energy EDF("+this.energyEDF+") \n";
        else
            str += " - WastedEnergy("+this.wastedGreenEnergy+") \n";

        return str;
    }

	public double getEnergyUsed() {
		return energyUsed;
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
    
    
}
