package model;


public class AOndeF implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AOndeF(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
        this.thedealer.receiveHealing(this.thedealer.dmg());
    }

    public String toString() {
        return String.format("Onde de folie");
    }
}


