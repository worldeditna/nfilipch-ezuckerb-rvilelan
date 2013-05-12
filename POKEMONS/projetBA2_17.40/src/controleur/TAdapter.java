package controleur;

import java.awt.Event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



import javax.swing.Timer;

import model.*;

public class TAdapter extends KeyAdapter{
	public boolean ingame=false; 
	private DataCarte carteee;
	private Timer timer;


	public TAdapter(DataCarte a, Timer b){
	     carteee=a;
	     timer=b;
	}	
	
    public void keyPressed(KeyEvent e) {

      int key = e.getKeyCode();

      if (ingame)
      {
        if (key == KeyEvent.VK_LEFT)
        {
          carteee.reqdx=-1;
          carteee.reqdy=0;
        }
        else if (key == KeyEvent.VK_RIGHT)
        {
          carteee.reqdx=1;
          carteee.reqdy=0;
        }
        else if (key == KeyEvent.VK_UP)
        {
          carteee.reqdx=0;
          carteee.reqdy=-1;
        }
        else if (key == KeyEvent.VK_DOWN)
        {
          carteee.reqdx=0;
          carteee.reqdy=1;
        }
        else if (key == KeyEvent.VK_ESCAPE && timer.isRunning())
        {
          ingame=false;
        }
        else if (key == KeyEvent.VK_SPACE) {
            if (timer.isRunning())
                timer.stop();
            else timer.start();
        }
        
      }
      else
      {
    	  if (key == 's' || key == 'S')
          {

              ingame=true;
              carteee.GameInit();
            }
          if(key == 'n' || key == 'N')
          {
          	timer.start();
          	carteee.checkCombat();
          }
          else if(key == 'o' || key == 'O')
          {
        	  
        	  
          	timer.start();
          	carteee.combat=true;
          	carteee.checkCombat();
          }
          
        
      }
  }

      public void keyReleased(KeyEvent e) {
          int key = e.getKeyCode();

          if (key == Event.LEFT || key == Event.RIGHT || 
             key == Event.UP ||  key == Event.DOWN)
          {
        	
            carteee.reqdx=0;
            carteee.reqdy=0;
          }
     }

  }




