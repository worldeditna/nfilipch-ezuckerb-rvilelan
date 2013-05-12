package model;

public interface IPokemon extends IPokemonState 
	{
	int getCooldown();
	
	void attack(IPokemon opponent);
	void receiveDamage(int damages);
	void useSkill (IPokemon opponent);
	boolean isAlive();

    void receiveHealing(int healing);
    void receiveHealing2();
    boolean useShield();
    String toString();
    int dmg();
    int hp();
    int dmg2();
    int maxhp();
	}