package model;



public class AttackDeflagration implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public AttackDeflagration(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Vantardise");
    }
}