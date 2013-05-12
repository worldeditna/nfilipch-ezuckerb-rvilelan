package model;



public class Ampleur implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public Ampleur(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.target.receiveHealing(this.thedealer.dmg());
    }

    public String toString() {
        return String.format("Ampleur");
    }
}

