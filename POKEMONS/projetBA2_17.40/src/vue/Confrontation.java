package vue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import java.util.List;
import java.util.Map;
import java.util.Random;

import model.*;

public class Confrontation extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanneauConfrontation pan= new PanneauConfrontation ();
	public static String a;
	public static String adv;
	private boolean victoire;
	
	public Confrontation(final Map<String,String> laMap, final List<IPokemon> opponents, final List<IItem> inventory)
	
	{
		String joueur=Fenetrenom.nomjoueur();
		adv=Confrontation.nomadv();

		
		JLabel texte= new JLabel(adv+" provoque "+joueur+" en duel!");
		texte.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		texte.setBounds(40,340,500,50);
		
		JLabel nomsacha= new JLabel(joueur);
		nomsacha.setBounds(415,225,100,35);
		nomsacha.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
		
		JLabel nomadv= new JLabel(adv);
		nomadv.setBounds(60,63,100,35);
		nomadv.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
		
		ImageIcon imgsacha= new ImageIcon("Images/Adv/sacha.png");
		Image isach= imgsacha.getImage();
		Image isach1= isach.getScaledInstance(170,170, 0);
		ImageIcon isach2= new ImageIcon(isach1);
		
		ImageIcon imgadv= new ImageIcon("Images/Adv/"+adv+".png");
		Image iadv= imgadv.getImage();
		Image iadv1= iadv.getScaledInstance(170,170, 0);
		ImageIcon iadv2= new ImageIcon(iadv1);
		
		JLabel imagesacha= new JLabel(isach2);
		imagesacha.setBounds(95,148,180,180);
		//imagesacha.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel imageadv= new JLabel(iadv2);
		imageadv.setBounds(440,20,180,180);
		//imageadv.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton suivant= new JButton("Suivant");
		suivant.setBounds(530,420,120,25);
	    suivant.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setFocusPainted(false);
	    suivant.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        { 	setVisible(false);
	        	Fentrechoix chose= new Fentrechoix(laMap, opponents, inventory);
	        	chose.setResizable(false);

	        }
	    });
		
		pan.setLayout(null);
		
		pan.add(suivant);
		pan.add(imagesacha);
		pan.add(imageadv);
		pan.add(nomsacha);
		pan.add(nomadv);
		pan.add(texte);
		
		this.setTitle("Combat");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(pan);
	    this.setVisible(true);
	
	
	}
    public static String nomadv()
    {
    	Random rn = new Random();
		int i = rn.nextInt(6)+1;
		if(i==1)
			a= "Karateka";
		else if (i==2)
			a= "Pierre";
		else if (i==3)
			a= "Rockeur";
		else if (i==4)
			a= "Mamie";
		else if (i==5)
			a= "Fillette";
		else if (i==6)
    	    a= "Biker";
		
		return a;
    }
    public static String nomfinaladv()
    {
    return adv;
}
    
public boolean returnV(){
	return victoire;
}


}
