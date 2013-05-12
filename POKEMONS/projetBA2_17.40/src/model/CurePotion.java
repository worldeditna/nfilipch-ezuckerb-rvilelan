package model;

public class CurePotion implements IItem {
	private int strength;
	private int charges;

	public CurePotion(int s, int c) {
		this.strength = s;
		this.charges = c;
	}

	@Override
	public void use(IPokemon p) {
		if(usable())
		p.receiveHealing(this.strength);
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
		return String.format("Soins (Soin: %3d, #: %d)", this.strength, this.charges);
	}

	@Override
	public void charger(int quentite) {
		this.charges+=quentite;
		
	}


}