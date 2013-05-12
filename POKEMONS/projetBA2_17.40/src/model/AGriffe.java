package model;


public class AGriffe implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AGriffe(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Griffe");
    }
}
