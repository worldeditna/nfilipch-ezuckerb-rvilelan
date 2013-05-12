package vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fenpoke  extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panneau2 p = new Panneau2();
	private JLabel texte= new JLabel("<html>Tu vas pouvoir dès à présent choisir tes nouveaux compagnons de jeu, à savoir, tes 3 Pokémons!<br> Réfléchit bien à tes choix car ils te suivront tout au long de l'aventure sinon ils te seront attribué automatiquement. <br> Clique sur les 3 Pokémons que tu veux puis appuie sur ACCES AU MONDE POKEMON.  <br> Il ne me reste plus qu'à te souhaiter bonne chance!</html>");
	public static List <String> animal=new ArrayList<String>();
	private JLabel choix= new JLabel();
	private JButton monde = new JButton("ACCES AU MONDE POKEMON"); 
	private JButton pokemon1 = new JButton(new ImageIcon("Images/Pokemons/pikachu.png"));
	private JButton pokemon2 = new JButton(new ImageIcon("Images/Pokemons/dracofeu.png"));
	private JButton pokemon3 = new JButton(new ImageIcon("Images/Pokemons/tortank.png"));
	private JButton pokemon4 = new JButton(new ImageIcon("Images/Pokemons/florisard.png"));
	private JButton pokemon5 = new JButton(new ImageIcon("Images/Pokemons/onix.png"));
	private JButton pokemon6 = new JButton(new ImageIcon("Images/Pokemons/ho-ho.png"));
	private JButton pokemon7 = new JButton(new ImageIcon("Images/Pokemons/mewto.png"));
	private JButton pokemon8 = new JButton(new ImageIcon("Images/Pokemons/pokemon8.png"));
	
	public Fenpoke(String s){
	pokemon1.setBounds(349,38, 109, 109); //(273, 38, 372, 436) 436 div 4 =109 longueur et 372 de largeur div 2= 186-109 51,3 entre chaque 
	pokemon1.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Pikachu");
        	choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");
        	}}});      
	pokemon2.setBounds(459, 38, 109, 109);
	pokemon2.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Dracofeu");choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	pokemon3.setBounds(349, 148, 109, 109);
	pokemon3.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Tortank");choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	pokemon4.setBounds(459,148,109,109);
	pokemon4.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Florisard");choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	pokemon5.setBounds(349,258,109,109);
	pokemon5.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Onix");choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	pokemon6.setBounds(459,258,109,109);
	pokemon6.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Ho-ho");choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	pokemon7.setBounds(349,368,109,107);
	pokemon7.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        	animal.add("Mewto");choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	pokemon8.setBounds(459,368,109,107);
	pokemon8.addActionListener( new ActionListener(){
		
        public void actionPerformed(ActionEvent e){
        	if(animal.size()<3)
        	{
        		Random rn = new Random();
        		int i = rn.nextInt(7)+1;
        		if(i==1)
        			animal.add("Pikachu");
        		else if (i==2)
        			animal.add("Dracofeu");
        		else if (i==3)
        			animal.add("Tortank");
        		else if (i==4)
        			animal.add("Florisard");
       			else if (i==5)
       				animal.add("Onix");
     			else if (i==6)
     				animal.add("Ho-ho");
        		else if (i==7) 
        			animal.add("Mewto");
        	choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");}}});
	JLabel bravo= new JLabel("Bravo "+s+",");
	choix.setText("Choisit trois Pokémons");
	//choix.updateUI();
	choix.setBounds( 30, 250, 250, 38 ); //195
	choix.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
	choix.setForeground(Color.WHITE);
	//choix.setBorder(BorderFactory.createLineBorder(Color.black));
	p.setLayout(null);
	bravo.setBounds( 25, 300, 195, 15 );
	texte.setBounds( 25, 314, 195, 217 );
	monde.setBounds(273,475, 372,40);
	monde.setFocusPainted(false);
	monde.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	monde.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	int res=animal.size();
        	if(res<3)
        	{
        		animal.add("Pikachu");
        		if(res<2)
        		{
        			animal.add("Dracofeu");
        			if(res<1)
        			{
        				animal.add("Tortank");
        			}
        		}
        	}
        	choix.setText("<HTML><BODY>"+"Pokémon choisit (#3): "+"<BR>"+animal+"</BODY></HTML>");
        	setVisible(false);

        	 new Carte();
        }});
	bravo.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
	texte.setFont(new Font("Arial", Font.CENTER_BASELINE, 13));
	this.setTitle("Pokémon");
    this.setSize(750, 590);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    p.add(bravo);
    p.add(texte);
    p.add(monde);
    p.add(pokemon1);
    p.add(pokemon2);
    p.add(pokemon3);
    p.add(pokemon4);
    p.add(pokemon5);
    p.add(pokemon6);
    p.add(pokemon7);
    p.add(pokemon8);
    p.add(choix);
    this.setContentPane(p);
    this.setVisible(true);
	}
	public static List<String> getPokemon()
	{
		return animal;
	}

}