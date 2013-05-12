package model;

public class ATornade implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public ATornade(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Tornade");
    }
}
