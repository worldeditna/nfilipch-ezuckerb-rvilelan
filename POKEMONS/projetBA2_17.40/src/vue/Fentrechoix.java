package vue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.*;

import java.util.List;
import java.util.Map;
public class Fentrechoix extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanneauConfrontation pan= new PanneauConfrontation ();
	public List<String> troispok;
	private JLabel choisir= new JLabel();
	private JButton s= new JButton ();
	public static String pokemon;

	
	public Fentrechoix(final Map<String,String> zMap, final List<IPokemon> opponents, final List<IItem> inventory)
	{
		troispok=Fenpoke.getPokemon();
    	final String nom1=troispok.get(0);
    	final String nom2=troispok.get(1);
    	final String nom3=troispok.get(2);
    	
    	
    	choisir.setText("Choisit un de tes trois Pokémon pour ce combat:");
		s.setText("Suivant");
		
		ImageIcon image= new ImageIcon("Images/Pokemons/"+nom1+".png");
		Image i1= image.getImage();
		Image i2= i1.getScaledInstance(90,80, 0);
		ImageIcon i3= new ImageIcon(i2);
		
		ImageIcon image1= new ImageIcon("Images/Pokemons/"+nom2+".png");
		Image i11= image1.getImage();
		Image i21= i11.getScaledInstance(90,80, 0);
		ImageIcon i31= new ImageIcon(i21);
		
		ImageIcon image2= new ImageIcon("Images/Pokemons/"+nom3+".png");
		Image i12= image2.getImage();
		Image i22= i12.getScaledInstance(90,80, 0);
		ImageIcon i32= new ImageIcon(i22);
		
		final JButton pok1= new JButton (i3);
		final JButton pok2= new JButton (i31);
		final JButton pok3= new JButton (i32);
		
		pok1.setBounds(30,357,90,90);
		pok1.setFocusPainted(false);
		pok1.addActionListener( new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	pokemon=nom1; choisir.setText("Choisit un de tes trois Pokémon pour ce combat:      "+nom1);}});
		
		pok2.setBounds(120,357,90,90);
		pok2.setFocusPainted(false);
		pok2.addActionListener( new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	pokemon=nom2; choisir.setText("Choisit un de tes trois Pokémon pour ce combat:      "+nom2);}});
		
		pok3.setBounds(210,357,90,90);
		pok3.setFocusPainted(false);
		pok3.addActionListener( new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	pokemon=nom3;choisir.setText("Choisit un de tes trois Pokémon pour ce combat:      "+nom3);}});
		
		choisir.setBounds(30,335,630,20);
	    choisir.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	    //choisir.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    s.setBounds(530,420,120,25);
	    s.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    s.setFocusPainted(false);
	    s.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	if(pokemon!=null)
	    	{
	    	s.setVisible(false);
	    	choisir.setVisible(false);
	    	pok1.setVisible(false);
	    	pok2.setVisible(false);
	    	pok3.setVisible(false);
	    	setVisible(false);
	    	IPokemon pokemonTOUR=new creatPokemon(zMap,pokemon);
	    	ICombatTerminal term = new ICombatTerminal();
    		term.setResizable(false);
    		IGameState tour = new ICombat(pokemonTOUR, opponents, inventory);
    		term.run(tour);
	    	}
    		
	        }});
	    pan.add(s);
		pan.add(choisir);	
		pan.add(pok1);
		pan.add(pok2);
		pan.add(pok3);
		pan.setLayout(null);

		
		this.setTitle("Combat");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(pan);
	    this.setVisible(true);
	}
	public static String nompokemon()
	{
		return pokemon;
	}

	

}
