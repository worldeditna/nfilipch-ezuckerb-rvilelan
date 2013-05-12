package model;


public class AMeteores implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AMeteores(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Meteores");
    }
}
