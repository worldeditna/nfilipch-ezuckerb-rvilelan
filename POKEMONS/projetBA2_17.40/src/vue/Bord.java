package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.*;
import controleur.TAdapter;

public class Bord extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	Image eau,joueurbas,joueurgauche,joueurdroit,joueurhaut,portegrotte,solgrotte;
    Image arbre1,arbre2,arbre3,arbre4;
    Image hopg, roche, chemin, adversaire, herbe,hopd,hopd2,hopg2, toithopd,toithopd2,toithopg2, toithopg, rocheeau, adversaire2, adversaire3, adversaire4, adversaire5;
    public int scrsize;
    DataCarte debut;
    TAdapter clavier;
    Timer timer;
    int ennemi;
   

    
	public void run(DataCarte b)
	{
		
		scrsize = 17*40;
		GetImages();
		debut = b;
		TAdapter a=new TAdapter(debut,timer);
		
		addKeyListener(a);
		
		clavier=a;
		setFocusable(true);
        setBackground(Color.black);
       

        timer = new Timer(40, this);
        timer.start();

		
	}
	public void addNotify() {
        super.addNotify();
        debut.GameInit();
    
    }
	public void paint(Graphics g)
    {

      super.paint(g);

      Graphics2D g2d = (Graphics2D) g;

      DrawMaze(g2d,debut.blocksize,debut.screendata);
     
      if (clavier.ingame ||debut.continu==true)
        PlayGame(g2d);
      else
          ShowIntroScreen(g2d);
   
    }
	public void ShowIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, scrsize / 2 - 30, scrsize - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, scrsize / 2 - 30, scrsize - 100, 50);

        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (scrsize - metr.stringWidth(s)) / 2, scrsize / 2);
    }
	
	
	 public void PlayGame(Graphics2D g2d) {
		
         debut.movePlayer();
         drawPlayer(g2d,debut.posx,debut.posy,debut.pos2x,debut.pos2y);
         dialogueCombat(g2d);
         if(debut.combat==true)
         {

         if(debut.entre==true)
         {

         WindowCombat(); 

         }
         }
     
     }
	 //DIALOGUES S'OUVRES
	public void dialogueCombat(Graphics g2d)
	{
		
		
			if (debut.dialogue1==true && !debut.used)
			{
				ScreenCombat(g2d);
				ennemi=1;
		
			}
			else if(debut.dialogue2==true  && !debut.used)
			{
				ScreenCombat(g2d);
				ennemi=2;
			}
			else if(debut.dialogue3==true  && !debut.used)
			{
				ScreenCombat(g2d);
				ennemi=3;
			}
			else if(debut.dialogue4==true  && !debut.used)
			{
				ScreenCombat(g2d);
				ennemi=4;
			}
			else if(debut.dialogue5==true  && !debut.used)
			{
				ScreenCombat(g2d);
				ennemi=5;
			}
			else if(debut.dialogue6==true  && !debut.used)
			{
				ScreenCombat(g2d);
				ennemi=6;
			}
				
		}
	public void WindowCombat(){
		if (ennemi==1)
		{ 
		List<IPokemon> save=debut.opp1();
		Confrontation conf1= new Confrontation(debut.returnMap(),debut.opp1(),debut.returnINV());
		conf1.setResizable(false);
		debut.setOpp1(save);
		}
		else if (ennemi==2)
		{
		List<IPokemon> save=debut.opp2();
			Confrontation conf2= new Confrontation(debut.returnMap(),save,debut.returnINV());
			conf2.setResizable(false);
		}
		else if (ennemi==3)
		{
		List<IPokemon> save=debut.opp3();
			Confrontation conf3= new Confrontation(debut.returnMap(),save,debut.returnINV());
			conf3.setResizable(false);
		}
		else if (ennemi==4)
		{
		List<IPokemon> save=debut.opp4();
			Confrontation conf4= new Confrontation(debut.returnMap(),save,debut.returnINV());
			conf4.setResizable(false);
		}
		else if (ennemi==5)
		{
		List<IPokemon> save=debut.opp5();
			Confrontation conf5= new Confrontation(debut.returnMap(),save,debut.returnINV());
			conf5.setResizable(false);
		}
		else if (ennemi==6)
		{
		List<IPokemon> save=debut.opp6();
			Confrontation conf6= new Confrontation(debut.returnMap(),save,debut.returnINV());
			conf6.setResizable(false);
		}
		debut.finCombat(); 
	}
	public void ScreenCombat(Graphics g2d)
	{
		if (timer.isRunning())
		{
            timer.stop();
		}
		
		
		g2d.setColor(new Color(5, 32, 48));
        g2d.fillRect(40, scrsize-100, scrsize - 40, 100);
        g2d.setColor(Color.white);
        g2d.drawRect(40, scrsize-100, scrsize - 40, 100);

        String texte = "Salut dresseur pokémon! veux tu combattre? (o/n)";
        Font small = new Font("Helvetica", Font.BOLD, 16);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(texte, (scrsize - metr.stringWidth(texte)) / 2, scrsize -20);
        TAdapter t=new TAdapter(debut,timer);
		
		addKeyListener(t);
		
		clavier=t;
		
	}
	
	
	public void DrawMaze(Graphics2D g2d,int blocksize,int screendata[]) {
        short i = 0;
        int x, y;

        for (y = 0; y <scrsize; y += blocksize) {
            for (x = 0; x <scrsize; x += blocksize) {
            	
                g2d.setStroke(new BasicStroke(2));

                if (screendata[i] == 6 || screendata[i]==15 || screendata[i] ==17 || screendata[i] ==50 || screendata[i] ==52 || screendata[i] ==53 || screendata[i] == 4 || screendata[i] == 29 || screendata[i] == 30 || screendata[i] == 32 ) // draws left
                {
                    g2d.drawImage(chemin, x , y ,blocksize,blocksize, this);
                }
                if (screendata[i] == 1 || screendata[i]==23) // draws top
                {
                	g2d.drawImage(eau, x, y ,blocksize,blocksize, this);
                }
                if (screendata[i] == 2 || screendata[i]==14 || screendata[i]==51) // draws right
                {
                	g2d.drawImage(herbe, x , y ,blocksize,blocksize, this);
                }
                if (screendata[i] == 3 || screendata[i]==24) // draws bottom
                {
                	g2d.drawImage(roche, x, y,blocksize,blocksize, this);
                }
                
                if (screendata[i] == 5) // draws point
                {
                	g2d.drawImage(hopg, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 7) // draws point
                {
                	g2d.drawImage(hopg2, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 8) // draws point
                {
                	g2d.drawImage(hopd2, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 9) // draws point
                {
                	g2d.drawImage(hopd, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 10) // draws point
                {
                	g2d.drawImage(toithopg, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 11) // draws point
                {
                	g2d.drawImage(toithopg2, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 12) // draws point
                {
                	g2d.drawImage(toithopd2, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 13) // draws point
                {
                	g2d.drawImage(toithopd, x, y,blocksize,blocksize, this);
                }
                
                if (screendata[i] == 18) // draws point
                {
                	g2d.drawImage(rocheeau, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 19) // draws point
                {
                	g2d.drawImage(arbre1, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 20) // draws point
                {
                	g2d.drawImage(arbre2, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 21) // draws point
                {
                	g2d.drawImage(arbre3, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 22) // draws point
                {
                	g2d.drawImage(arbre4, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 25) // draws point
                {
                	g2d.drawImage(portegrotte, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 27 || screendata[i]==28 || screendata[i]==26 || screendata[i]==54 || screendata[i]==55 || screendata[i]==56 || screendata[i]==31) // draws point
                {
                	g2d.drawImage(solgrotte, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 4 || screendata[i]==56) // draws point
                {
                	g2d.drawImage(adversaire, x, y,blocksize,blocksize, this);
                  
                }
                if (screendata[i] == 29) // draws point
                {
                	g2d.drawImage(adversaire5, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 30) // draws point
                {
                	g2d.drawImage(adversaire2, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 31) // draws point
                {
                	g2d.drawImage(adversaire3, x, y,blocksize,blocksize, this);
                }
                if (screendata[i] == 32) // draws point
                {
                	g2d.drawImage(adversaire4, x, y,blocksize,blocksize, this);
                }

                i++;
            }
        }
    }
	public void drawPlayer(Graphics2D g2d,int posx,int posy,int pos2x, int pos2y) {
        if (posx == -1)
        {
            drawPlayerLeft(g2d,debut.joueurx, debut.joueury, debut.blocksize);
        debut.pos2x=debut.posx;
        debut.pos2y=0;
       
    }
        else if (posx == 1)
        {
            drawPlayerRight(g2d,debut.joueurx, debut.joueury, debut.blocksize);
        debut.pos2x=debut.posx;
        debut.pos2y=0;
        
        }
        else if (posy == -1)
        {
            drawPlayerUp(g2d,debut.joueurx, debut.joueury, debut.blocksize);
        debut.pos2y=debut.posy;
        debut.pos2x=0;
        
        }
        else if (posy == 1)
        {
            drawPlayerDown(g2d,debut.joueurx, debut.joueury, debut.blocksize);
        debut.pos2y=debut.posy;
        debut.pos2x=0;
        
        }

    }
    
    public void drawPlayerDown(Graphics2D g2d,int joueurx, int joueury, int blocksize) {
   
            g2d.drawImage(joueurbas, joueurx , joueury, blocksize,blocksize,this);
    	}
    public void drawPlayerLeft(Graphics2D g2d,int joueurx, int joueury, int blocksize) {

        g2d.drawImage(joueurgauche, joueurx , joueury, blocksize,blocksize,this);
	}
    public void drawPlayerRight(Graphics2D g2d,int joueurx, int joueury, int blocksize) {
    
        g2d.drawImage(joueurdroit, joueurx , joueury, blocksize,blocksize,this);
	}
    public void drawPlayerUp(Graphics2D g2d,int joueurx, int joueury, int blocksize) {
   
        g2d.drawImage(joueurhaut, joueurx , joueury, blocksize,blocksize,this);
	}
	public void GetImages()
    {

      eau = new ImageIcon("Images/Carte/tile-49.png").getImage();
      chemin = new ImageIcon("Images/Carte/tile-2.png").getImage();
      roche = new ImageIcon("Images/Carte/tile-12.png").getImage();
      herbe = new ImageIcon("Images/Carte/tile-17.png").getImage();
      hopg = new ImageIcon("Images/Carte/hopg.png").getImage();
      adversaire = new ImageIcon("Images/Bonhomme/BaldMan.png").getImage();
      adversaire2 = new ImageIcon("Images/Bonhomme/Beauty.png").getImage();
      adversaire3 = new ImageIcon("Images/Bonhomme/BirdKeeperL.png").getImage();
      adversaire4 = new ImageIcon("Images/Bonhomme/Mom.png").getImage();
      adversaire5 = new ImageIcon("Images/Bonhomme/BugCatcher.png").getImage();
      joueurbas = new ImageIcon("Images/Bonhomme/Down.png").getImage();
      hopd = new ImageIcon("Images/Carte/hopd.png").getImage();
      hopd2 = new ImageIcon("Images/Carte/hopd2.png").getImage();
      hopg2 = new ImageIcon("Images/Carte/hopg2.png").getImage();
      toithopd = new ImageIcon("Images/Carte/toithopd.png").getImage();
      toithopd2 = new ImageIcon("Images/Carte/toithopd2.png").getImage();
      toithopg2 = new ImageIcon("Images/Carte/toithopg2.png").getImage();
      toithopg = new ImageIcon("Images/Carte/toithopg.png").getImage();
      rocheeau = new ImageIcon("Images/Carte/tile-41.png").getImage();
      arbre1 = new ImageIcon("Images/Carte/tile-547.png").getImage();
      arbre3 = new ImageIcon("Images/Carte/tile-548.png").getImage();
      arbre2 = new ImageIcon("Images/Carte/tile-555.png").getImage();
      arbre4 = new ImageIcon("Images/Carte/tile-556.png").getImage();
      joueurhaut = new ImageIcon("Images/Bonhomme/Up.png").getImage();
      joueurdroit = new ImageIcon("Images/Bonhomme/Right.png").getImage();
      joueurgauche = new ImageIcon("Images/Bonhomme/Left2.png").getImage();
      portegrotte = new ImageIcon("Images/Carte/tile-27.png").getImage();
      solgrotte = new ImageIcon("Images/Carte/tile-14.png").getImage();

    }
	
	public void actionPerformed(ActionEvent e) {
	    repaint();  
	}
	
}



