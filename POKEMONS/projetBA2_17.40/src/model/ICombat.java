package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ICombat implements IGameState

	{

		public static Map<String, ArrayList<IAction>> atts = new HashMap<String, ArrayList<IAction>>();
		public static Map<String, ArrayList<IAction>> attsADV = new HashMap<String, ArrayList<IAction>>();
		private IPokemon player;
		private List<IPokemon> monsters;
		private List<IItem> items;
	    private boolean finished;
	    private int the_monster=0;
	    private int cd_time;
	    private String contre;
	    public boolean superA;


		public ICombat(IPokemon challenger, List<IPokemon> monsters, List<IItem> inventory) 
		{
			this.player = challenger;
			this.monsters = monsters;
			this.items = inventory;
	        this.finished = (!this.player.isAlive() || this.monsters.isEmpty());
		}

	    public ICombat nextState(IAction action) 
	    {
	        action.perform();
	        filtreCheck();
	        return this;
	    }
	    public void fightADV()
	    {
	    	fight (this.player, this.monsters.get(the_monster));
	    	filtreCheck();
	    }
	    public void filtreCheck()
	    {
	    	this.monsters = filterMonsters(this.monsters);
	        this.items = filterInventory(this.items);
	        if(this.monsters.isEmpty() || !this.player.isAlive()) {
	            this.finished = true;
	            if(this.hasWon()){
	        	this.items.get(0).charger(2);
	    		this.items.get(1).charger(4);
	    		this.items = filterInventory(this.items);}
	        }
	        else
	        	this.finished=false;
	    }

	    public boolean isFinished() 
	    {
	        return finished;
	    }

	    private void fight(IPokemon player, IPokemon monster) 
		{
	    	readTXT2();
	    	String act=monster.toString();
	    	List<IAction> actsADV=attsADV.get(act);
	    	Random rn = new Random();
			int i = rn.nextInt(actsADV.size()+1);
			if(i==actsADV.size())
				i-=1;
			if (player.isAlive()) {
				IAction a=actsADV.get(i);
				a.perform();
				contre=a.toString();
			}
		}
	    public String contreatt()
	    {
	    	return this.contre;
	    }
	    public List<IAction> getPossibleMoves(int turns, int skill_used) 
	    {
	    	boolean superB=false;
	    	readTXT();
	    	List<IAction> acts=atts.get(player.toString());
	    	cd_time=player.getCooldown();
	    	boolean cd=cooldown(cd_time,turns,skill_used);
	        List<IAction> actions = new ArrayList<IAction>();
	        int j=0;
	        if(this.player.isAlive()) {
	            actions.add(new NoAction());
	            while(j<acts.size())
	            		{
	            	actions.add(acts.get(j));
	            	j++;
	            		}
	            
	            if(!cd){
	        	superB=true;
	            actions.add(new UseSkill(this.player,monsters.get(the_monster)));}
	            else{
	            actions.add(new NoAction());}
	            for(IItem i : this.items) 
	            {
	                actions.add(new ApplyItem(i, this.player));
	            }
	        }
            superA=superB;
	        return actions;
	    }

		private List<IItem> filterInventory(List<IItem> i2) 
		{
			List<IItem> l = new ArrayList<IItem>();
			for (IItem i : i2) {
				{
					l.add(i);
				}
			}
			return l;
		}

		private List<IPokemon> filterMonsters(List<IPokemon> m2) 
		{
			List<IPokemon> l = new ArrayList<IPokemon>();
			for (IPokemon c: m2) {
				if(c.isAlive()) {
					l.add(c);
				}
			}
			return l;
		}

		public boolean hasWon() 
		{
			return this.finished && this.player.isAlive();
		}

	    public IPokemon getPlayer() 
	    {
	        return this.player;
	    }

	    public IPokemon getOpponent() 
	    {
	    	if(this.monsters.size()!=0)
	        return this.monsters.get(the_monster);
	    	else return null;
	    }

	    public List<IPokemonState> getFutureOpponents() 
	    
	    {
	        List<IPokemonState> others = new ArrayList<IPokemonState>();
	        others.addAll(this.monsters.subList(1, this.monsters.size()));
	        return others;
	    }
	    
	    private boolean cooldown(int x, int t, int u)
	    {
		boolean cooldown = false;
		if(u!=0)
			if(t-u<x)
				cooldown=true;
		return cooldown;
	    }

	
		public int returnHP() {
			int hp=player.hp();
			return hp;
		}

		public int returnMAXhp() {
			return player.maxhp();
		}

		public int returnHPADV() {
			   int i=0;
			   if(this.monsters.size()>0)
			   return this.monsters.get(this.the_monster).hp();
			   else return i;
			  }

	
		  public int returnMAXHPADV() {
			   int i=0;
			   if(this.monsters.size()>0)
			   return this.monsters.get(this.the_monster).maxhp();
			   else return i;
			  }
		  
		public void readTXT(){
				
			
					ArrayList<ArrayList<IAction>> attas = new ArrayList<ArrayList<IAction>>();
					ArrayList<IAction> row = new ArrayList<IAction>();
					row.add(new AttackElec(this.monsters.get(the_monster), this.player)); row.add(new AGriffe(this.monsters.get(the_monster), this.player));row.add(new Attack(this.monsters.get(the_monster), this.player));row.add(new UseShield( this.player));	//pika
					ArrayList<IAction> row2 = new ArrayList<IAction>();
					row2.add(new AttackFeu(this.monsters.get(the_monster), this.player));row2.add(new AttackDeflagration(this.monsters.get(the_monster), this.player));row2.add(new UseShield(this.player));row2.add(new AGriffe(this.monsters.get(the_monster), this.player));//dracofeu
					ArrayList<IAction> row3 = new ArrayList<IAction>();
					row3.add(new AFeuille(this.monsters.get(the_monster), this.player));row3.add(new AHerbe(this.monsters.get(the_monster), this.player));row3.add(new ACharge(this.monsters.get(the_monster), this.player));row3.add(new ABalleG(this.monsters.get(the_monster), this.player));//florisard
					ArrayList<IAction> row4 = new ArrayList<IAction>();
					row4.add(new APierre(this.monsters.get(the_monster), this.player));row4.add(new Ampleur(this.player, this.player));row4.add(new AEboulement(this.monsters.get(the_monster), this.player));row4.add(new AVantardise(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row5 = new ArrayList<IAction>();
					row5.add(new APointO(this.monsters.get(the_monster), this.player));	row5.add(new ARafPsy(this.monsters.get(the_monster), this.player));	row5.add(new APsychoB(this.monsters.get(the_monster), this.player));	row5.add(new AChocM(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row6 = new ArrayList<IAction>();
					row6.add(new AHydroC(this.monsters.get(the_monster), this.player));row6.add(new ASurf(this.monsters.get(the_monster), this.player));row6.add(new ACascade(this.monsters.get(the_monster), this.player));row6.add(new AQdF(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row7 = new ArrayList<IAction>();
					row7.add(new AQdF(this.monsters.get(the_monster), this.player)); row7.add(new AUtiM(this.monsters.get(the_monster), this.player)); row7.add(new ACruA(this.monsters.get(the_monster), this.player)); row7.add(new AttackDeflagration(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row8 = new ArrayList<IAction>();
					row8.add(new AttackFeu(this.monsters.get(the_monster), this.player));row8.add(new AGriffe(this.monsters.get(the_monster), this.player));row8.add(new Attack(this.monsters.get(the_monster), this.player));row8.add(new UseShield( this.player));
					ArrayList<IAction> row9 = new ArrayList<IAction>();
					row9.add(new ACharge(this.monsters.get(the_monster), this.player));row9.add(new ApissO(this.monsters.get(the_monster), this.player));row9.add(new AEcume(this.monsters.get(the_monster), this.player));row9.add(new ACascade(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row10 = new ArrayList<IAction>();
					row10.add(new AAcide(this.monsters.get(the_monster), this.player));row10.add(new AQueueP(this.monsters.get(the_monster), this.player));row10.add(new AChocM(this.monsters.get(the_monster), this.player));row10.add(new AToutR(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row11 = new ArrayList<IAction>();
					row11.add(new AMetamorph());row11.add(new AMetamorph());row11.add(new AMetamorph());row11.add(new AMetamorph());
					ArrayList<IAction> row12 = new ArrayList<IAction>();
					row12.add(new ACruA(this.monsters.get(the_monster), this.player));row12.add(new ATornade(this.monsters.get(the_monster), this.player));row12.add(new AToxik(this.monsters.get(the_monster), this.player));row12.add(new AOndeF(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row13 = new ArrayList<IAction>();
					row13.add(new AGriffe(this.monsters.get(the_monster), this.player));row13.add(new ABelier(this.monsters.get(the_monster), this.player));row13.add(new UseShield(null));row13.add(new AUtiM(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row14 = new ArrayList<IAction>();
					row14.add(new APoingK(this.monsters.get(the_monster), this.player));row14.add(new Attack(null,null));row14.add(new APierre(this.monsters.get(the_monster), this.player));row14.add(new AEboulement(this.monsters.get(the_monster), this.player));
					ArrayList<IAction> row15 = new ArrayList<IAction>();
					row15.add(new AChocM(this.monsters.get(the_monster), this.player));row15.add(new AGriffe(this.monsters.get(the_monster), this.player));row15.add(new AMeteores(this.monsters.get(the_monster), this.player));row15.add(new AForce(this.monsters.get(the_monster), this.player));
		
					attas.add(row); attas.add(row2); attas.add(row3); attas.add(row4); attas.add(row5); attas.add(row6); 
					attas.add(row7); attas.add(row8);attas.add(row9);attas.add(row10);attas.add(row11);attas.add(row12);
					attas.add(row13);attas.add(row14);attas.add(row15);
					
					try{								
					 BufferedReader reader = new BufferedReader(new FileReader("Data/pokeFAIL.txt"));
					 String line=reader.readLine();
					 int index=0;
					 while(line!=null && index<attas.size())
					 {
						atts.put(line, attas.get(index));
						line=reader.readLine();
						index++;
					 }
				reader.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (IOException i) {
					i.printStackTrace();
				}
				catch (Exception e) {
					System.out.println(e.toString());
				} 
		}

public void readTXT2(){
		
	
			ArrayList<ArrayList<IAction>> attasADV = new ArrayList<ArrayList<IAction>>();
			ArrayList<IAction> row = new ArrayList<IAction>();
			row.add(new AttackElec(this.player,this.monsters.get(the_monster))); row.add(new AGriffe(this.player,this.monsters.get(the_monster)));row.add(new Attack(this.player,this.monsters.get(the_monster)));row.add(new UseShield(this.monsters.get(the_monster)));	//pika
			ArrayList<IAction> row2 = new ArrayList<IAction>();
			row2.add(new AttackFeu(this.player,this.monsters.get(the_monster)));row2.add(new AttackDeflagration(this.player,this.monsters.get(the_monster)));row2.add(new UseShield(this.monsters.get(the_monster)));row2.add(new AGriffe(this.player,this.monsters.get(the_monster)));//dracofeu
			ArrayList<IAction> row3 = new ArrayList<IAction>();
			row3.add(new AFeuille(this.player,this.monsters.get(the_monster)));row3.add(new AHerbe(this.player,this.monsters.get(the_monster)));row3.add(new ACharge(this.player,this.monsters.get(the_monster)));row3.add(new ABalleG(this.player,this.monsters.get(the_monster)));//florisard
			ArrayList<IAction> row4 = new ArrayList<IAction>();
			row4.add(new APierre(this.player,this.monsters.get(the_monster)));row4.add(new Ampleur(this.monsters.get(the_monster),this.monsters.get(the_monster)));row4.add(new AEboulement(this.player,this.monsters.get(the_monster)));row4.add(new AVantardise(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row5 = new ArrayList<IAction>();
			row5.add(new APointO(this.player,this.monsters.get(the_monster)));	row5.add(new ARafPsy(this.player,this.monsters.get(the_monster)));	row5.add(new APsychoB(this.player,this.monsters.get(the_monster)));	row5.add(new AChocM(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row6 = new ArrayList<IAction>();
			row6.add(new AHydroC(this.player,this.monsters.get(the_monster)));row6.add(new ASurf(this.player,this.monsters.get(the_monster)));row6.add(new ACascade(this.player,this.monsters.get(the_monster)));row6.add(new AQdF(this.monsters.get(the_monster), this.player));
			ArrayList<IAction> row7 = new ArrayList<IAction>();
			row7.add(new AQdF(this.player,this.monsters.get(the_monster))); row7.add(new AUtiM(this.player,this.monsters.get(the_monster))); row7.add(new ACruA(this.player,this.monsters.get(the_monster))); row7.add(new AttackDeflagration(this.monsters.get(the_monster), this.player));
			ArrayList<IAction> row8 = new ArrayList<IAction>();
			row8.add(new AttackFeu(this.player,this.monsters.get(the_monster)));row8.add(new AGriffe(this.player,this.monsters.get(the_monster)));row8.add(new Attack(this.player,this.monsters.get(the_monster)));row8.add(new UseShield(this.monsters.get(the_monster)));
			ArrayList<IAction> row9 = new ArrayList<IAction>();
			row9.add(new ACharge(this.player,this.monsters.get(the_monster)));row9.add(new ApissO(this.player,this.monsters.get(the_monster)));row9.add(new AEcume(this.player,this.monsters.get(the_monster)));row9.add(new ACascade(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row10 = new ArrayList<IAction>();
			row10.add(new AAcide(this.player,this.monsters.get(the_monster)));row10.add(new AQueueP(this.player,this.monsters.get(the_monster)));row10.add(new AChocM(this.player,this.monsters.get(the_monster)));row10.add(new AToutR(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row11 = new ArrayList<IAction>();
			row11.add(new AMetamorph());row11.add(new AMetamorph());row11.add(new AMetamorph());row11.add(new AMetamorph());
			ArrayList<IAction> row12 = new ArrayList<IAction>();
			row12.add(new ACruA(this.player,this.monsters.get(the_monster)));row12.add(new ATornade(this.player,this.monsters.get(the_monster)));row12.add(new AToxik(this.player,this.monsters.get(the_monster)));row12.add(new AOndeF(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row13 = new ArrayList<IAction>();
			row13.add(new AGriffe(this.player,this.monsters.get(the_monster)));row13.add(new ABelier(this.player,this.monsters.get(the_monster)));row13.add(new UseShield(this.monsters.get(the_monster)));row13.add(new AUtiM(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row14 = new ArrayList<IAction>();
			row14.add(new APoingK(this.player,this.monsters.get(the_monster)));row14.add(new Attack(this.player,this.monsters.get(the_monster)));row14.add(new APierre(this.player,this.monsters.get(the_monster)));row14.add(new AEboulement(this.player,this.monsters.get(the_monster)));
			ArrayList<IAction> row15 = new ArrayList<IAction>();
			row15.add(new AChocM(this.player,this.monsters.get(the_monster)));row15.add(new AGriffe(this.player,this.monsters.get(the_monster)));row15.add(new AMeteores(this.player,this.monsters.get(the_monster)));row15.add(new AForce(this.player,this.monsters.get(the_monster)));

			attasADV.add(row); attasADV.add(row2); attasADV.add(row3); attasADV.add(row4); attasADV.add(row5); attasADV.add(row6); 
			attasADV.add(row7); attasADV.add(row8);attasADV.add(row9);attasADV.add(row10);attasADV.add(row11);attasADV.add(row12);
			attasADV.add(row13);attasADV.add(row14);attasADV.add(row15);
			
			try{								
			 BufferedReader reader = new BufferedReader(new FileReader("Data/pokeFAIL.txt"));
			 
			 String line=reader.readLine();

			 int index=0;
			 while(line!=null && index<attasADV.size())
			 {
				attsADV.put(line, attasADV.get(index));
				line=reader.readLine();
				index++;
			 }
		
		reader.close();

		}
	
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		} 
		

}

public boolean superA() {

	return superA;
}
}


	



