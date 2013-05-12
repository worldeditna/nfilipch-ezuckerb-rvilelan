package model;

public interface IItem extends IItemState {
	boolean usable();
	void use(IPokemon myPokemon);
	String toString();
	void charger(int quentite);
}
