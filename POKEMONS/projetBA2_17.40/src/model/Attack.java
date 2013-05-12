package model;

public class Attack implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public Attack(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Attaque éclair");//"Attacker  adversaire %s.", this.target);
    }
}