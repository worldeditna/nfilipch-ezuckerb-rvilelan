package model;

public class HealPotion implements IItem 
{
	private int charges;

	public HealPotion(int charges) 
	{
		this.charges = charges;
	}

	@Override
	public void use(IPokemon myPokemon) 
	{
		if(usable())
		myPokemon.receiveHealing2();
		this.charges -= 1;
		if(this.charges<0)
			this.charges=0;
	}

	@Override
	public boolean usable() {
		return charges > 0;
	}

	@Override
	public String toString() {
		return String.format("Soins Complet (#: %d)", this.charges);
	}

	@Override
	public void charger(int quentite) {
		this.charges+=quentite;
		
	}

}