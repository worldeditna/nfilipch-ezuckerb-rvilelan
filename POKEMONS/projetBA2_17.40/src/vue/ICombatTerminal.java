package vue;



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import model.IAction;
import model.IGameState;
import model.IPokemon;

public class ICombatTerminal extends JFrame {
  
	private static final long serialVersionUID = 1L;
	public int turns=0;
    private int used_skill=0;
    public int index;
    public boolean clicked ;
    private PanneauCombat pan= new PanneauCombat ();
	String pokemon=Fentrechoix.nompokemon();
	String qqc;
	String pokeadv;
	String adv=Confrontation.nomfinaladv();
	String joueur=Fenetrenom.nomjoueur();
	private JProgressBar vieadv = new JProgressBar();
	private JProgressBar viesacha = new JProgressBar();
	double v1, v2 ,v3,v11,v22,v33;
    int reponse, reponse1;
    private String lettre,a,b,c,d,m,f;
	public List<String> troispok;
	private JLabel choisir= new JLabel();
	private JButton s= new JButton ();
	JLabel pokeadverssaire= new JLabel();
	JLabel imagepokeadv= new JLabel();



    public ICombatTerminal() 
    {

        this.setTitle("Combat");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pan.setLayout(null);
	    this.setContentPane(pan);
	    this.setVisible(true);
    }
    public void suite (IGameState game)
    {
    	turns++;
    	if(!game.isFinished())
    	{
    		String avant=" ";
    		String apres="Un pokeball VIDE";
    		IAction a = this.chooseAction(game.getPossibleMoves(turns,used_skill));
    		
    		if(index==5 && game.superA())
    		{
    			used_skill=turns;}
    		
    		avant=game.getOpponent().toString();
    		game = game.nextState(a);
    		game.filtreCheck();
    		
    		if(!game.isFinished()){
        	if(game.getOpponent()!=null)
        			apres=game.getOpponent().toString();
        	if(!(avant.equals(apres)))
        	{
        	pokeadv= apres;
        	lancementpokemonadv(game);
        	}
        	else
        	{
        		running(game);
        	}
        }
        else
        affichagefinal(game);
    	}
    	else
            affichagefinal(game);
    }
 
    public void affichagechoix (final IGameState game,List<IAction> actions)
    {
    	final JButton off= new JButton("Attaquer "+pokeadv);
		off.setBounds(30,342,317,100);
	    off.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
	    off.setFocusPainted(false);
	    pan.add(off);
	    
	    
	    final JButton potion= new JButton("<html>Utiliser une Potion</html>");
		potion.setBounds(347,342,317,100);
	    potion.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
	    potion.setFocusPainted(false);
	    pan.add(potion);
	    
	    off.addActionListener( new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	off.setVisible(false);
	        	potion.setVisible(false);
	        	affichageattaque(game, game.getPossibleMoves(turns,used_skill));
	        }});  
	    
	    potion.addActionListener( new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	off.setVisible(false);
	        	potion.setVisible(false);
	        	affichagepotion(game, game.getPossibleMoves(turns,used_skill));
	        	}});   
    }
    public void running(IGameState game)
    {
    	affichagevie(game);
        if(!game.isFinished())
        {
        	affichagechoix(game, game.getPossibleMoves(turns,used_skill));
        }
       else
        {
        	affichagefinal(game);
        }
    }
    
    public void run(IGameState game) 
    {
    	IPokemon qqc2=game.getOpponent();
    	pokeadv=qqc2.toString();
    	affichagefixe();
    	lancementpokemonadv(game);
    }

    public void affichagefinal(IGameState game)
    {
    	JLabel finalite= new JLabel();
		JButton suivant= new JButton ("Suivant");
		if(game.hasWon()) 
		{
			finalite.setText("Vous avez gagné!");
		}
		else 
		{
			finalite.setText("Vous avez perdu!");
		}
		finalite.setBounds(30,345,630,60);
	    finalite.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setBounds(530,420,120,25);
	    suivant.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setFocusPainted(false);
	    suivant.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	setVisible(false);
	    }});
	    pan.add(suivant);
	    
		pan.add(finalite);
    }
    public void affichagepotion(final IGameState game,List<IAction> actions)
    {   
		final JButton precedent= new JButton("Précedent");
		final JButton pot1= new JButton();
		final JButton pot2= new JButton();
		final JLabel erreur= new JLabel("Vous n'avez plus de potion!");
		
		precedent.setBounds(30,423,100,20);
		precedent.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		precedent.setFocusPainted(false);
		pan.add(precedent);
		precedent.addActionListener( new ActionListener(){
		public void actionPerformed(ActionEvent e){
			        precedent.setVisible(false);
			        pot1.setVisible(false);
			       	pot2.setVisible(false);
			       	erreur.setVisible(false);
			       	affichagechoix(game, game.getPossibleMoves(turns,used_skill));}});
		
		if(actions.size()==6)
		{
		erreur.setBounds(30,340,630,40);
		erreur.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		pan.add(erreur);
		}
		
    	if(actions.size()>=7)
        {
    	String p1=actions.get(6).toString();
    	pot1.setText(p1);
    	pot1.setBounds(30,340,317,70);
		pot1.setFont(new Font("Arial", Font.CENTER_BASELINE, 19));
		pot1.setFocusPainted(false);
	    pan.add(pot1);
	    pot1.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				        precedent.setVisible(false);
				        erreur.setVisible(false);
				        pot1.setVisible(false);
				       	pot2.setVisible(false);
				       	index=6;
				       	potioneffet(game,true);
				       	}});
        }
    	if(actions.size()==8)
        {
    	String p2=actions.get(7).toString();
    	pot2.setText(p2);
	    pot2.setBounds(347,340,317,70);
	    pot2.setFont(new Font("Arial", Font.CENTER_BASELINE, 19));
	    pot2.setFocusPainted(false);
	    pan.add(pot2);
	    pot2.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				        precedent.setVisible(false);
				        erreur.setVisible(false);
				        pot1.setVisible(false);
				       	pot2.setVisible(false);
				       	index=7;
				       	potioneffet(game,false);
				       	}});
        }
    }
    public void affichageattaque(final IGameState game,List<IAction> actions)
    {
    	a=actions.get(0).toString();
        b=actions.get(1).toString();
    	c=actions.get(2).toString();
    	d=actions.get(3).toString();
    	m=actions.get(4).toString();
    	f=actions.get(5).toString();
    	
        final JButton off1= new JButton(a);
        final JButton off2= new JButton(b);
        final JButton off3= new JButton(c);
		final JButton off4= new JButton(d);
		final JButton off5= new JButton(m);
		final JButton off6= new JButton(f);
		final JButton precedent= new JButton("Précedent");
		
		precedent.setBounds(30,423,100,20);
		precedent.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		precedent.setFocusPainted(false);
		pan.add(precedent);
        
		 precedent.addActionListener( new ActionListener(){
		 public void actionPerformed(ActionEvent e){
		        precedent.setVisible(false);
		        off1.setVisible(false);
		       	off2.setVisible(false);
		       	off3.setVisible(false);
		       	off4.setVisible(false);
		       	off5.setVisible(false);
		       	off6.setVisible(false);
		       	affichagechoix(game, game.getPossibleMoves(turns,used_skill));
		       	 }});
		
        off1.setBounds(30,337,317,28);
	    off1.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	    off1.setFocusPainted(false);
	    pan.add(off1);
	    off1.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	precedent.setVisible(false);
	        	off1.setVisible(false);
	        	off2.setVisible(false);
	        	off3.setVisible(false);
	        	off4.setVisible(false);
	        	off5.setVisible(false);
	        	off6.setVisible(false);
	            index=0;
	            explicationattaque(game,a);}});
	    
	   off2.setBounds(347,337,317,28);
	   off2.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	   off2.setFocusPainted(false);
	   pan.add(off2);
	   off2.addActionListener( new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	precedent.setVisible(false);
	        	off1.setVisible(false);
	        	off2.setVisible(false);
	        	off3.setVisible(false);
	        	off4.setVisible(false);
	        	off5.setVisible(false);
	        	off6.setVisible(false);
	        	index=1;
	        	explicationattaque(game,b);}});
	   
	   off3.setBounds(30,365,317,28);
	   off3.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	   off3.setFocusPainted(false);
	   pan.add(off3);
	   off3.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	precedent.setVisible(false);
	        	off1.setVisible(false);
	        	off2.setVisible(false);
	        	off3.setVisible(false);
	        	off4.setVisible(false);
	        	off5.setVisible(false);
	        	off6.setVisible(false);
	            index=2;
	            explicationattaque(game,c);}});
	    
	   off4.setBounds(347,365,317,28);
	   off4.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	   off4.setFocusPainted(false);
	   pan.add(off4);
	   off4.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	precedent.setVisible(false);
	        	off1.setVisible(false);
	        	off2.setVisible(false);
	        	off3.setVisible(false);
	        	off4.setVisible(false);
	        	off5.setVisible(false);
	        	off6.setVisible(false);
	            index=3;
	            explicationattaque(game,d);}});
	   
	   off5.setBounds(30,393,317,28);
	   off5.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	   off5.setFocusPainted(false);
	   pan.add(off5);
	   off5.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	precedent.setVisible(false);
	        	off1.setVisible(false);
	        	off2.setVisible(false);
	        	off3.setVisible(false);
	        	off4.setVisible(false);
	        	off5.setVisible(false);
	        	off6.setVisible(false);
	            index=4;
	            explicationattaque(game,m);}});
	    
	   off6.setBounds(347,393,317,28);
	   off6.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	   off6.setFocusPainted(false);
	   pan.add(off6);
	   off6.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	precedent.setVisible(false);
	        	off1.setVisible(false);
	        	off2.setVisible(false);
	        	off3.setVisible(false);
	        	off4.setVisible(false);
	        	off5.setVisible(false);
	        	off6.setVisible(false);
	            index=5;
	            explicationattaque(game,f);}});
    }

    public void affichagevie(IGameState game)
    {
        v1=game.returnHPADV();
         v2=game.returnMAXHPADV();
        v3=v1/v2*100;
        reponse= (int)v3;
        if(reponse<=45)
        {
        	vieadv.setForeground(Color.ORANGE);
        	if(reponse<=20)
        	{
    		vieadv.setForeground(Color.RED);
        	}
        }
    	else
    	{
    		vieadv.setForeground(new Color(159, 232, 85));
    	}
    	vieadv.setValue(reponse);
	    vieadv.updateUI();
		
		v11=game.returnHP();
	    v22=game.returnMAXhp();
	    v33=v11/v22*100;
	    reponse1=(int)v33;
	    if(reponse1<=45)
	    {
	    	viesacha.setForeground(Color.ORANGE);
        	if(reponse1<=20)
        	{
    		viesacha.setForeground(Color.RED);
        	}
	    }
    	else
    	{
    		viesacha.setForeground(new Color(159, 232, 85));
    	}
		viesacha.setValue(reponse1);
		viesacha.updateUI();
    }
    

    public void choixpokemondepart(final IGameState game)
    {
    	troispok=Fenpoke.getPokemon();
    	final String nom1=troispok.get(0);
    	final String nom2=troispok.get(1);
    	final String nom3=troispok.get(2);
    	
    	
    	choisir.setText("Choisit un de tes trois Pokémon pour ce combat:");
		s.setText("Suivant");
		final JButton pok1= new JButton (new ImageIcon("Images/Pokemons/"+nom1+".png"));
		final JButton pok2= new JButton (new ImageIcon("Images/Pokemons/"+nom2+".png"));
		final JButton pok3= new JButton (new ImageIcon("Images/Pokemons/"+nom3+".png"));
		
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

	    s.setBounds(530,420,120,25);
	    s.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    s.setFocusPainted(false);
	    s.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	s.setVisible(false);
	    	choisir.setVisible(false);
	    	pok1.setVisible(false);
	    	pok2.setVisible(false);
	    	pok3.setVisible(false);
	        affichagefixe();
	        running(game);
	        }});
	    pan.add(s);
		pan.add(choisir);	
		pan.add(pok1);
		pan.add(pok2);
		pan.add(pok3);
    }
    public void lancementpokemonadv(final IGameState game)
    {
    	pokeadverssaire.setText(pokeadv);
		pokeadverssaire.setBounds(60,55,200,35);
		pokeadverssaire.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));

		ImageIcon pokj1= new ImageIcon ("Images/Pokemons/"+pokeadv+".png");
		Image image=pokj1.getImage();
		Image image1= image.getScaledInstance(170,170, 0);
		ImageIcon image2= new ImageIcon(image1);

		imagepokeadv.setIcon(image2);
		
		imagepokeadv.setBounds(420,20,180,180);
		
		pan.add(imagepokeadv);
		
    	final JLabel lancement= new JLabel();
		final JButton suivant= new JButton ("Suivant");
		lancement.setText(adv+" lance "+pokeadv+"!");
		lancement.setBounds(30,345,630,60);
		lancement.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setBounds(530,420,120,25);
	    suivant.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setFocusPainted(false);
	    suivant.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	running(game);
	    	suivant.setVisible(false);
	    	lancement.setVisible(false);
	    }});
	    pan.add(suivant);
		pan.add(lancement);
		pan.add(imagepokeadv);
		pan.add(pokeadverssaire);
    }
    public void potioneffet(final IGameState game,boolean b)
    {
    	final JLabel texteeffet= new JLabel();
    	if(!b)
    	{
    		texteeffet.setText("<HTML><BODY>"+joueur+" utilise la Potion Soins sur "+pokemon+" et lui fait gagner 40 points de vie."+"</BODY></HTML>");
    	}
    	else
    	{
    		texteeffet.setText("<HTML><BODY>"+joueur+" utilise la Potion Soins Complet sur "+pokemon+" et lui fait regagner tout ses points de vie."+"</BODY></HTML>");
    	}
		texteeffet.setBounds(30,340,630,40);
		texteeffet.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		final JButton suivant= new JButton ("Suivant");
		suivant.setBounds(530,420,120,25);
	    suivant.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setFocusPainted(false);
	    suivant.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
        	suivant.setVisible(false);
        	texteeffet.setVisible(false);
        	suite(game);
	    }});
		pan.add(texteeffet);
		pan.add(suivant);
    }
    public void contreattaque(final IGameState game)
    {
    	String contreA;
    	if(game.contreatt()!=null){
    		contreA=game.contreatt();}
    	else{
		contreA=getLettre();}
		final JLabel texcontreatt= new JLabel("<HTML><BODY>"+pokeadv+" utilise "+contreA+"!"+"</BODY></HTML>");
		final JButton suivant= new JButton ("Suivant");
		texcontreatt.setBounds(30,340,630,60);
		texcontreatt.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		
		suivant.setBounds(530,420,120,25);
	    suivant.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setFocusPainted(false);
	    suivant.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	texcontreatt.setVisible(false);
        	suivant.setVisible(false);
        	suite(game);
        	}});
	    
		pan.add(texcontreatt);
		pan.add(suivant);
    }
    public void explicationattaque(final IGameState game,String lettre)
    {
		final JLabel texatt= new JLabel("<HTML><BODY>"+pokemon+" utilise "+lettre+"!"+"<BR>"+"</BODY></HTML>");
		final JButton suivant= new JButton ("Suivant");
		texatt.setBounds(30,340,630,60);
		texatt.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		
		suivant.setBounds(530,420,120,25);
	    suivant.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
	    suivant.setFocusPainted(false);
	    suivant.addActionListener( new ActionListener(){
	    public void actionPerformed(ActionEvent e){
        	texatt.setVisible(false);
        	suivant.setVisible(false);
        	game.fightADV();
        	if(game.contreatt()!=null || getLettre()!=null)
        	contreattaque(game);
        	else
        	 suite(game);
	    }});
	   
		pan.add(texatt);
		pan.add(suivant);
    }
    public void affichagefixe()
    {	
    	vieadv.setBackground(Color.white);
	    vieadv.setBorderPainted(false);
	    vieadv.setForeground(new Color(159, 232, 85));
	    vieadv.setBounds(151,95,139,12);
	    pan.add(vieadv);
	    
	    viesacha.setBackground(Color.white);
		viesacha.setBorderPainted(false);
		viesacha.setForeground(new Color(159, 232, 85));
		viesacha.setBounds(504,260,139,12);
		pan.add(viesacha);
		
    	final JLabel pokejoueur= new JLabel (pokemon);
    	pokejoueur.setBounds(412,220,100,35);
    	pokejoueur.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
    	pan.add(pokejoueur);

    	ImageIcon imgsacha= new ImageIcon("Images/Adv/sachados.png");
		Image isach= imgsacha.getImage();
		Image isach1= isach.getScaledInstance(90,80, 0);
		ImageIcon isach2= new ImageIcon(isach1);
		
		ImageIcon imgadv= new ImageIcon("Images/Adv/"+adv+".png");
		Image iadv= imgadv.getImage();
		Image iadv1= iadv.getScaledInstance(80,80, 0);
		ImageIcon iadv2= new ImageIcon(iadv1);
		
		ImageIcon pokj= new ImageIcon ("Images/Pokemons/"+pokemon+"dos.png");
		Image img=pokj.getImage();
		Image img1= img.getScaledInstance(170,170, 0);
		ImageIcon img2= new ImageIcon(img1);

		JLabel imagepokejoueur= new JLabel(img2);
		JLabel imagesacha= new JLabel(isach2);
		JLabel imageadv= new JLabel(iadv2);
		
		imagepokejoueur.setBounds(95,148,180,180);
		imageadv.setBounds(600,110,90,90);
		imagesacha.setBounds(7,238,90,90);
		
		if(pokemon.equals("Pikachu"))
		{
			imagepokejoueur.setBounds(95,190,150,135);
			pan.add(imagepokejoueur);
		}
		if(pokemon.equals("Dracofeu"))
		{
			imagepokejoueur.setBounds(95,169,170,160);
			pan.add(imagepokejoueur);
		}
		if(pokemon.equals("Florisard"))
		{
			imagepokejoueur.setBounds(95,187,170,140);
			pan.add(imagepokejoueur);
		}
		if(pokemon.equals("Ho-ho"))
		{
			imagepokejoueur.setBounds(95,156,170,170);
			pan.add(imagepokejoueur);
		}
		if(pokemon.equals("Mewto"))
		{
			imagepokejoueur.setBounds(95,156,170,170);
			pan.add(imagepokejoueur);
		}
		if(pokemon.equals("Onix"))
		{
			imagepokejoueur.setBounds(95,149,170,178);
			pan.add(imagepokejoueur);
		}
		if(pokemon.equals("Tortank"))
		{
			imagepokejoueur.setBounds(95,189,180,137);
			pan.add(imagepokejoueur);
		}
		
		pan.add(imagesacha);
	    pan.add(imageadv);
    }
    
    private IAction chooseAction(List<IAction> actions) 
    {
        return actions.get(index);
    }
	public String getLettre() {
		return lettre;
	}
	public void setLettre(String lettre) {
		this.lettre = lettre;
	}
}
