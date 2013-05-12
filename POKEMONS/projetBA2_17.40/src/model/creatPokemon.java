package model;

import java.util.Map;
import java.util.StringTokenizer;


public class creatPokemon implements IPokemon{

		private String data;
		private int min_damage;
		private int max_damage;
	    private int max_hit_points;
	    private int cooldown;
		private boolean shield;
	    private int hit_points;
	    private String pokemonName;
	    public int nuke;
	    private int degatINT;
	    
		
		public creatPokemon( Map<String,String> pokemonS,String name )
		{
			this.data=pokemonS.get(name);
			this.pokemonName=name;
			this.shield=false;
			
			StringTokenizer tokens=new StringTokenizer(data,",");	
			while (tokens.hasMoreTokens()) 
			{ 
				this.min_damage = Integer.parseInt(tokens.nextElement().toString());
				this.max_damage = Integer.parseInt(tokens.nextElement().toString());
				this.max_hit_points = Integer.parseInt(tokens.nextElement().toString());
				this.cooldown = Integer.parseInt(tokens.nextElement().toString());
				this.nuke = Integer.parseInt(tokens.nextElement().toString());
			  
			}	
			
			this.hit_points=this.max_hit_points;
			double damages =(this.min_damage+this.max_damage)/2;
			Double d=damages;
			Integer i=d.intValue();
			degatINT =i;

		}

		
		public void attack(IPokemon opponent) {
			
			opponent.receiveDamage(degatINT);
		}

		public boolean isAlive() {
		
			return this.hit_points > 0;
		}

	   
	    public void receiveHealing(int healing) {
	        
	    	int diff=this.max_hit_points-this.hit_points;
	    	if(healing>diff)
	    		this.hit_points=this.max_hit_points;
	    	else
	    	this.hit_points += healing;
	    }

	 
	    public void receiveHealing2() { 	
	        this.hit_points = this.max_hit_points;
	    }
	    

	    public String toString() {
	        return String.format(this.pokemonName);
	    }

	
		public void receiveDamage(int damages) {
			if(!shield)
		        this.hit_points -= damages;
		    	else
		    		this.hit_points-=(damages-5);
		  	shield=false;
		}

		
		public void useSkill(IPokemon opponent) 
		{

			int nukes = this.min_damage;
			opponent.receiveDamage(nukes);
		}

		@Override
		public boolean useShield() 
		{
			shield=true;
			return shield;
		}
		
		public int getCooldown() {

			return cooldown;
		}

	

		public int dmg() {
			
			return degatINT;
		}


		public int hp() {
			
			return this.hit_points;
		}
	public int maxhp() {
			
			return this.max_hit_points;
		}

		public int dmg2() {
			return nuke;
		}


	}


