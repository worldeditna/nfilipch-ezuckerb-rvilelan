package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;
public class DataCarte{
	 	
	
	private static Map<String, String> stats = new HashMap<String, String>();
	private List<IItem> inventory    ;
	private List<IPokemon> opponents1 ;
	private List<IPokemon> opponents2 ;
	private List<IPokemon> opponents3 ;
	private List<IPokemon> opponents4 ;
	private List<IPokemon> opponents5 ;
	private List<IPokemon> opponents6;
	
	
    
	public  int nrofblocks= 17;
	public int blocksize = 40;
	public int[] screendata= new int[nrofblocks * nrofblocks];
	
	int carte=1;
    int carte2;
    int joueurspeed = 8;
    
    public int joueurx, joueury;
    public int joueurdx, joueurdy;
    public int reqdx, reqdy, viewdx, viewdy;
    int speed;
    public int posx,pos2x=0;
    public int posy,pos2y=1;
    public boolean dialogue1,dialogue2,dialogue3,dialogue4,dialogue5,dialogue6=false;
    public boolean continu=false;
    public boolean used=false;
    public boolean combat=false;
    public boolean entre=false;
    
    
   
  

	 final int leveldata[] =
		    { 0, 0, 0, 0, 0, 0, 0, 0, 16, 16, 16, 16, 0, 0, 0, 0, 0,
		      0, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 2, 2, 2, 2, 14,
		      0, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 2, 2, 2, 2, 14,
		      0, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 2, 2, 2, 2, 14,
		      0, 1, 1, 6, 6, 6, 6, 6, 6, 15, 15, 6, 2, 2, 2, 2, 14,
		      0, 3, 3, 6, 6, 6, 6, 6, 6, 15, 4, 6, 2, 2, 2, 2, 14,
		      0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 50, 6, 2, 2, 2, 2, 14,
		      0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 50, 6, 2, 2, 2, 2, 14,
		      0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 15, 15, 15, 15, 15, 2, 14,
		      0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 15, 10, 11, 12, 13, 2,14,
		      0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 15, 5, 7, 8, 9, 2, 14,
		      0, 2, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 2, 14,
		      0, 2, 2, 6, 6, 6, 6, 6, 15, 15, 6, 6, 6, 6, 6, 2, 14,
		      0, 2, 2, 6, 6, 6, 6, 6, 15, 29, 6, 6, 6, 6, 6, 2, 14,
		      0, 2, 2, 6, 6, 2, 2, 2, 2, 51, 2, 2, 2, 2, 2, 2, 14,
		      0, 2, 2, 6, 6, 2, 2, 2, 2, 51, 2, 2, 2, 2, 2, 2, 14,
		      0, 14, 14, 15, 15, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,};
		    
		    final int leveldata2[] =
		        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		          0, 3, 3, 3, 3, 19, 21, 19, 21, 19, 21, 19, 21, 19, 21, 3, 24,
		          0, 3, 3, 25, 3, 20, 22, 20, 22, 20, 22, 20, 22, 20, 22, 14, 24,
		          0, 3, 3, 6, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 14, 24,
		          0, 3, 3, 6, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 14, 24,
		          0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 15, 15, 2, 2, 2, 14, 24,
		          0, 3, 3, 15, 6, 6, 6, 6, 6, 6, 15, 30, 2, 2, 2, 14, 24,
		          0, 3, 3, 32, 53, 53, 6, 6, 6, 6, 6, 52, 2, 2, 2, 14, 24,
		          0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 52, 2, 2, 2, 14, 24,
		          0, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 3, 3, 15, 15, 15, 15, 15, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 3, 3, 3, 3, 3, 3, 3, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 3, 3, 3, 3, 3, 3, 3, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 18, 18, 18, 18, 18, 18, 18, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 2, 2, 2, 14, 24,
		          0, 23, 23, 23, 23, 23, 23, 23, 17, 17, 17, 17, 14, 14, 14, 14, 24,};
		    
		    final int leveldata3[] =
		        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		          0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
		          0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		          0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		          0, 3, 1, 1, 27, 27, 28, 56, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 55, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 55, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 54, 54, 54, 28, 31, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 1, 1, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 24,
		          0, 3, 3, 3, 28, 28, 28, 28, 28, 28, 28, 28, 27, 27, 27, 28, 24,
		          0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 28, 28, 27, 28, 24,
		          0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 26, 28, 24,};

public void checkCombat(){
		    
	if(combat==true){
		entre=true;
	}
	continu=true;
	dialogue1=dialogue2=dialogue3=dialogue4=dialogue5=dialogue6=false;
	used=true;
	
}

public void GameInit() {
		
        joueurx=7*blocksize;
        joueury=7*blocksize;
        for (int i = 0; i < nrofblocks * nrofblocks; i++){
            screendata[i] = leveldata[i];
        }
  
    	readTXT();
		 this.inventory = generate_inventory();
		 this.opponents1 = generate_opponents();
		 this.opponents2 = generate_opponents();
		 this.opponents3 = generate_opponents();
		 this.opponents4 = generate_opponents();
		 this.opponents5 = generate_opponents();
		 this.opponents6 = generate_opponents();
        
        LevelInit();
  
    }
	public void LevelInit() {
        int i;
      
        if (carte==1 && carte2==2){
        for (i = 0; i < nrofblocks * nrofblocks; i++){
            screendata[i] = leveldata[i];
        joueurx=9*blocksize;
        joueury=1*blocksize;
        }
        
        }
        if (carte==2 && carte2==1){
        	for (i = 0; i < nrofblocks * nrofblocks; i++)
                screendata[i] = leveldata2[i];
        	joueurx=9*blocksize;
            joueury=16*blocksize;
        }
        if (carte==2 && carte2==3){
        	for (i = 0; i < nrofblocks * nrofblocks; i++)
                screendata[i] = leveldata2[i];
        	joueurx=4*blocksize;
            joueury=4*blocksize;
        }
        if (carte==3){
        	for (i = 0; i < nrofblocks * nrofblocks; i++)
                screendata[i] = leveldata3[i];
        	joueurx=14*blocksize;
            joueury=16*blocksize;
        }

       
    }
	public void movePlayer() {
		 this.opponents1 = generate_opponents();
		 this.opponents2 = generate_opponents();
		 this.opponents3 = generate_opponents();
		 this.opponents4 = generate_opponents();
		 this.opponents5 = generate_opponents();
		 this.opponents6 = generate_opponents();
		
		int pos1;

        	joueurdx = reqdx;
            joueurdy = reqdy;
            
            if (reqdx==0 && reqdy==0){
            	posx=pos2x;
            	posy=pos2y;
            }
            else{
            posx=joueurdx;
            posy=joueurdy;
            }
            viewdx=joueurx;
            viewdy=joueury;
            
    
        viewdx = joueurx + joueurspeed * joueurdx;
        viewdy = joueury + joueurspeed * joueurdy;
        pos1= viewdx / blocksize + nrofblocks * (viewdy / blocksize);
        
        if(screendata[pos1]==0 || screendata[pos1] ==1 || screendata[pos1] ==3 || screendata[pos1] ==14 || screendata[pos1] ==15 || screendata[pos1] ==19 || screendata[pos1] ==21 || screendata[pos1] ==18 || screendata[pos1] ==23 || screendata[pos1] ==24 || screendata[pos1] ==28 || screendata[pos1] ==29 || screendata[pos1] ==30 || screendata[pos1] ==31 || screendata[pos1] ==32 || screendata[pos1] ==4 || screendata[pos1] ==5 || screendata[pos1] ==7 || screendata[pos1] ==8 || screendata[pos1] ==9 || screendata[pos1] ==10 || screendata[pos1] ==11 || screendata[pos1] ==12 || screendata[pos1] ==13 ){
        	joueurdx=0;
        	joueurdy=0;
    }
        else if(screendata[pos1] ==16)
        {
        	carte2=1;
        	carte=2;
        	LevelInit();
            
        	
        }
        else if(screendata[pos1]==17){
        	carte2=2;
        	carte=1;
        	LevelInit();
        }
        else if(screendata[pos1]==25){
        	carte2=2;
        	carte=3;
        	LevelInit();
        }
        else if(screendata[pos1] ==26)
        {
        	carte2=3;
        	carte=2;
        	LevelInit();
     
        }//lorsque le joueur arrive dans ces positions, dialogue prend vrai pour que la fenetre s'ouvre
        else if(screendata[pos1] ==50 && !used)
        {
        	dialogue1=true;
        	
        }
        else if(screendata[pos1] ==51 && !used)
        {
        	dialogue2=true;
        	
        }
        else if(screendata[pos1] ==52 && !used)
        {
        	dialogue3=true;
        	
        }
        else if(screendata[pos1] ==53 && !used)
        {
        	dialogue4=true;
        }
        else if(screendata[pos1] ==54 && !used)
        {
        	dialogue5=true;
        	
        }
        else if(screendata[pos1] ==55 && !used)
        {
        	dialogue6=true;
        	
        }
        else if(screendata[pos1] !=50 && screendata[pos1]!=51 && screendata[pos1]!=52 && screendata[pos1]!=53 && screendata[pos1]!=54 && screendata[pos1]!=55)
        {
        	this.used =false;
        }
        
        joueurx=joueurx + joueurspeed * joueurdx;
        joueury=joueury + joueurspeed * joueurdy;

        reqdx=reqdy=0;
       
        joueurdx = 0;
        joueurdy = 0;
        
        
    }

	private static List<IItem> generate_inventory() 
	{
		List<IItem> inv = new ArrayList<IItem>();
		inv.add(new HealPotion(1));
		inv.add(new CurePotion(40,4));
		return inv;
	}
	
	private static List<IPokemon> generate_opponents() {
		List<IPokemon> opps = new ArrayList<IPokemon>();
        opps.add(new creatPokemon(stats,"Pikachu"));
        opps.add(new creatPokemon(stats,"Dracofeu"));
        opps.add(new creatPokemon(stats,"Florisard"));
        opps.add(new creatPokemon(stats,"Onix"));
        opps.add(new creatPokemon(stats,"Mewto"));
        opps.add(new creatPokemon(stats,"Tortank"));
        opps.add(new creatPokemon(stats,"Ho-ho"));
        opps.add(new creatPokemon(stats,"Lugia"));
        opps.add(new creatPokemon(stats,"Magicarpe"));
        opps.add(new creatPokemon(stats,"Smogo"));
        opps.add(new creatPokemon(stats,"Metamorph"));
        opps.add(new creatPokemon(stats,"Nosferapti"));
        opps.add(new creatPokemon(stats,"Nidoran"));
        opps.add(new creatPokemon(stats,"Macogneur"));
        opps.add(new creatPokemon(stats,"Ronddoudou"));
        Collections.shuffle(opps);
        List<IPokemon> returnOPPS=opps.subList(0,3);
        return returnOPPS;
	}
	
	
	private static void readTXT(){
		
		
			try{												
				 BufferedReader reader = new BufferedReader(new FileReader("Data/pokeFAIL.txt"));
				 BufferedReader reader2 = new BufferedReader(new FileReader("Data/stats.txt"));
				 
				 String line=reader.readLine();
				 String line2=reader2.readLine();
				 while(line!=null && line2!=null)
				 {
					stats.put(line,line2);
					line=reader.readLine();
					line2=reader2.readLine();
				 }
			
			reader.close();
			reader2.close();
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

	 public List<IItem> returnINV()    {
		return this.inventory;
	}
	public List<IPokemon> opp1(){
		return this.opponents1;
	}
	public List<IPokemon> opp2(){
		return this.opponents2;
	}
	public List<IPokemon> opp3(){
		return this.opponents3;
	}
	public List<IPokemon> opp4(){
		return this.opponents4;
	}
	public List<IPokemon> opp5(){
		return this.opponents5;
	}
	public List<IPokemon> opp6(){
		return this.opponents6;
	}
	public Map<String, String> returnMap(){
		return DataCarte.stats;
	}
	public void setOpp1(List<IPokemon> a){
		 this.opponents1=a;
	
	}
	public void setOpp2(List<IPokemon> a){
		 this.opponents2=a;
	}
	public  void setOpp3(List<IPokemon> a){
		 this.opponents3=a;
	}
	public void setOpp4(List<IPokemon> a){
		 this.opponents4=a;
	}
	public void setOpp5(List<IPokemon> a){
		 this.opponents5=a;
	}
	public void setOpp6(List<IPokemon> a){
		 this.opponents6=a;
	}
	public boolean returnUSED(){
		
		return this.used;
	}
	public void setUsed(boolean b) {
		this.used=b;
		
	}
	public void finCombat(){
		combat=false;
		entre=false;
		checkCombat();
		}
	
}


