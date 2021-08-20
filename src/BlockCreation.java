import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BlockCreation {
	
	public int map[][];
	public int blockWid;
	public int blockHei;
	public int row;
	public int col;
	
	public BlockCreation(int row, int col){
		map = new int[row][col];
		for(int i = 0 ; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++)
				map[i][j] = 1;
		}
		
		this.row = row;
		this.col = col;
		blockWid = 540 / col;
		blockHei = 200 / row;
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0 ; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 1) {
					g.setColor(Color.blue);
					g.fillRect(j * blockWid + 80, i * blockHei + 50, blockWid, blockHei);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.white);
					g.drawRect(j * blockWid + 80, i * blockHei + 50, blockWid, blockHei);
					
				}
			}
		}
	}
	
}
