package model;


	public class AAcide implements IAction {
	    private IPokemon thedealer;
	    private IPokemon target;

	    public AAcide(IPokemon target, IPokemon thedealer) 
	    {
	        this.thedealer = thedealer;
	        this.target = target;
	    }

	    public void perform() 
	    {
	        this.thedealer.attack(this.target);
	    }

	    public String toString() {
	        return String.format("Acide");
	    }
	}
