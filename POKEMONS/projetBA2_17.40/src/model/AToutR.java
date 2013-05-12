package model;

public class AToutR implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AToutR(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Attacker  adversaire %s.", this.target);
    }
}