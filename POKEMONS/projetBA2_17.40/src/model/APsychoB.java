package model;


public class APsychoB implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public APsychoB(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Psycho Boost");
    }
}

