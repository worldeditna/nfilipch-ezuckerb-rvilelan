package model;



public class AHerbe implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AHerbe(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Tranche Herbe");
    }
}
