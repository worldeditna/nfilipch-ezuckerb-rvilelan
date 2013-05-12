package vue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
  
public class PanneauConfrontation extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void paintComponent(Graphics g){
    try {
      Image img = ImageIO.read(new File("Images/Autres/joueurvsadv.jpg"));
      //Pour une image de fond prenne toute la fen�tre
      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }                
  }               
}