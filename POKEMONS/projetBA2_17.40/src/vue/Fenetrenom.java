package vue;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Fenetrenom extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Panneau1 pan = new Panneau1();
	public List<String> pokpoke;
	private JPanel p = new JPanel();
	private JLabel label = new JLabel("<html>Bonjour à toi cher joueur, nous allons passer un petit temps ensemble... <br>Je m'appelle Dr. Chen et je suis la pour te guider dans ce jeux.<br>Je te souhaites un bon jeu de la part de toute l'équipe des programmeurs!</html>");
	private JLabel label2 = new JLabel("<html>Tout d'abord je vais devoir te demander ton nom: <br>Clique ensuite sur suivant...</html>");
	private TextField nom = new TextField("Entre ton nom ici",35); 
	private JButton suivant = new JButton("Suivant");
	private Font f= new Font("DIAlog", Font.PLAIN,18);
	public static String joueur;
	public Fenetrenom(){ 
		label.setVerticalAlignment(SwingConstants.TOP);
		label2.setVerticalAlignment(SwingConstants.TOP);
		getPan().setLayout(null); 
		label.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		label.setBounds( 34, 402, 642, 92 );
		label2.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		label2.setBounds( 34, 492, 440, 92 );
		nom.setBounds(480,495,190,22);
		suivant.setBounds (574, 517, 95,20);
		suivant.setFont(f);
		suivant.setFocusPainted(false);
		this.setTitle("Pokémon");
	    this.setSize(750, 590);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    suivant.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	setVisible(false);
	            joueur= nom.getText();
	    	    if (joueur.equals("Entre ton nom ici"))
	    	    	joueur= "Sacha";
	    	    Fenpoke fenpoke= new Fenpoke(joueur);
	    	    pokpoke=Fenpoke.getPokemon();
	    	    fenpoke.setResizable(false);
	    	    
	        }
	    });
	    getPan().add(suivant);
	    getPan().add(label);
	    getPan().add(label2);
	    getPan().add(nom);
	    this.setContentPane(getPan());
	    this.setVisible(true);
	  }   
	public static String nomjoueur()
	{
		return joueur;
	}
	
	public List<String> getPokemon()
	{
		return pokpoke;
	}
	public JPanel getP() {
		return p;
	}
	public void setP(JPanel p) {
		this.p = p;
	}
	public Panneau1 getPan() {
		return pan;
	}
	public void setPan(Panneau1 pan) {
		this.pan = pan;
	}
}
