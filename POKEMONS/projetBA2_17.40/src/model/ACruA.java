package model;



public class ACruA implements IAction {
    private IPokemon thedealer;
    private IPokemon target;

    public ACruA(IPokemon target, IPokemon thedealer) 
    {
        this.thedealer = thedealer;
        this.target = target;
    }

    public void perform() 
    {
        this.thedealer.attack(this.target);
    }

    public String toString() {
        return String.format("Cru d'Aille");
    }

}
