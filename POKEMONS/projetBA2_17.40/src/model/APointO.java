package model;



public class APointO implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public APointO(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Point Ombre");
    }
}


