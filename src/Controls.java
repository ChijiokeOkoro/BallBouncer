import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;

public class Controls extends JPanel implements KeyListener, ActionListener{

	private boolean play = false; // when true the game starts playing 
	
	private int score = 0;        // keeps track of the score
	
	private int totalBlocks;      // total blocks displayed on the board
	
	private Timer timer;          
	private int delay = 1;
	
	private int playerX = 310;   // centers the paddle that hits the ball

	private int ballPosX = 350;   // starting position of the ball
	private int ballPosY = 350;
	
	private int ballXdir = -1 * (new Random().nextInt(3) + 3 ); // controls the direction and speed of the ball
	private int ballYdir = -1 * (new Random().nextInt(3) + 3);
	
	private BlockCreation blocks;
	
	public Controls() {
		// creates a random amount of blocks each game
		blocks = new BlockCreation(new Random().nextInt(30) + 1, new Random().nextInt(30) + 1);
		
		// calculates total blocks
		totalBlocks = blocks.row * blocks.col;
		
		// allows user to interact in the game
		addKeyListener(this);
		setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		// background 
		g.setColor(Color.darkGray);
		g.fillRect(1, 1, 700, 600);
		
		// drawing map
		blocks.draw((Graphics2D)g);
		
		// score
		g.setColor(Color.green);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 20);

		// win the game
		if(totalBlocks == 0) {
			ballXdir = 0;
			ballYdir = 0;
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("You Win, Score: " + score, 200, 300);
			
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Press Enter to Restart", 250, 350);

		}
		
		// restart game
		if(ballPosY > 570) {
			ballXdir = 0;
			ballYdir = 0;
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score, 150, 300);
			
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Press Enter to Restart", 250, 350);

		}
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
				ballYdir *= -1;
			
			for(int i = 0 ; i < blocks.map.length; i++) {
				for(int j = 0; j < blocks.map[0].length; j++) {
					if (blocks.map[i][j] == 1) {
						int blockX = j * blocks.blockWid + 80; 
						int blockY = i * blocks.blockHei + 50;
						
						Rectangle rect = new Rectangle(blockX, blockY, blocks.blockWid, blocks.blockHei);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						
						if(ballRect.intersects(rect)) {
							blocks.map[i][j] = 0;
							totalBlocks--;
							score += 5;
							
							if(ballPosX+19 < blockX || ballPosX + 1 >= rect.x + rect.width)
								ballXdir *= -1;
							else
								ballYdir *= -1;
						}
					}
				}
			}
		
				
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if(ballPosX < 0 || ballPosX > 670)	ballXdir *= -1;
			if(ballPosY < 0)	ballYdir *= -1;
			
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600)
				playerX = 600;
			else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 10)
				playerX = 10;
			else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXdir = -1 * (new Random().nextInt(3) + 2);
				ballYdir = -1 * (new Random().nextInt(3) + 2);
				playerX = 310;
				score = 0;
				blocks = new BlockCreation(new Random().nextInt(30) + 1, new Random().nextInt(30) + 1);
				totalBlocks = blocks.row * blocks.col;
			}
		}
		
	}

	public void moveRight() {
		play = true;
		playerX += 50;
	}
	public void moveLeft() {
		play = true;
		playerX -= 50;
	}
}
