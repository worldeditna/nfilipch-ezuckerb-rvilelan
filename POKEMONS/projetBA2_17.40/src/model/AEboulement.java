package model;

public class AEboulement implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AEboulement(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Eboulement");
    }
}

