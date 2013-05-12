package model;


public class AHydroC implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AHydroC(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Hydro Canon");
    }
}

