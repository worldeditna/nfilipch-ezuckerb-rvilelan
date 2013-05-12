package vue;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.List;
  
public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panneau pan= new Panneau ();
	private JButton bouton = new JButton("Commencer à jouer");
	private Font f= new Font("DIAlog", Font.PLAIN,24);
	private List<String> pokpoke;
public Fenetre(){  
	pan.setLayout(null);         
    this.setTitle("Pokémon");
    this.setSize(700, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    bouton.setBounds(0,430,695,30);
    bouton.setFont(f);
    bouton.setFocusPainted(false);
    bouton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
        	setVisible(false);
            Fenetrenom fennom= new Fenetrenom();
            fennom.setResizable(false);
            pokpoke=fennom.getPokemon();
        }
    });
    pan.add(bouton);
   this.setContentPane(pan);
   this.setVisible(true);
  } 

public List<String> getPokemon()
	{
	return pokpoke;
	}

}