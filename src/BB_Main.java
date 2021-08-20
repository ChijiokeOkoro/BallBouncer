import javax.swing.JFrame;

public class BB_Main {
	public static void main(String[] args) {
		
		// creates the frame of the game
		JFrame game = new JFrame();
		
		// creates the gameplay and displays it on the JFrame
		Controls gameplay = new Controls();
		game.add(gameplay);
		
		// creates the bounds for the display 
		game.setBounds(1,1,700,600);
		
		// display name
		game.setTitle("Ball Bouncer");
	
		// display functionality
		game.setResizable(false);
		game.setVisible(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
