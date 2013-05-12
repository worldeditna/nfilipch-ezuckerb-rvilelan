package vue;
import javax.swing.JFrame;

import model.DataCarte;



public class Carte extends JFrame
{

	private static final long serialVersionUID = 1L;
public Carte()
  {	
	 DataCarte a=new DataCarte();
	 Bord b=new Bord();
	 
	  add(b); 
	  b.run(a);

    setTitle("PokEmon");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(696,716);
    setLocationRelativeTo(null);
    setVisible(true);

    
}
}