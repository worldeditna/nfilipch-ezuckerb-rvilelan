package model;

public class UseShield implements IAction 
{
    private IPokemon target;

    public UseShield(IPokemon target) 
    {
        this.target = target;
    }

    public void perform() {
       this.target.useShield();
    }

	public String toString() {
        return String.format("Passer en defense");
    }
}