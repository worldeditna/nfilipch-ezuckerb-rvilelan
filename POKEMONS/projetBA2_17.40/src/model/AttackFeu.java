package model;

public class AttackFeu implements IAction {
	   private IPokemon thedealer;
	    private IPokemon target;

	    public AttackFeu(IPokemon target, IPokemon thedealer) 
	    {
	        this.thedealer = thedealer;
	        this.target = target;
	    }

	    public void perform() 
	    {
	        this.thedealer.attack(this.target);
	    }

	public String toString() {
        return String.format("Attaque de Feu");}
}
