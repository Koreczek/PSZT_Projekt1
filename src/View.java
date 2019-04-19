import java.awt.*;
import java.util.List;
import javax.swing.JFrame;

public class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7776938012789326912L;
	int size;
	public List<Point> pointsFromFile;
	public List<Point> generetedPoints;
	public View(int s, List<Point> p1, List<Point> p2){
		pointsFromFile = p1;
		generetedPoints = p2;
		
		this.size = s;
		this.setTitle("PSZT");
		this.setSize(size, size);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paint(Graphics g) {
		draw(g);
	}
	private void draw(Graphics g) { 
		for(int index = 0; index < pointsFromFile.size(); index++) {
			putPixel(g, pointsFromFile.get(index).getX(), pointsFromFile.get(index).getY() );
		}
		for(int index = 0; index < generetedPoints.size(); index++) {
			putPixel(g, generetedPoints.get(index).getX(), generetedPoints.get(index).getY() );
		}
	}
	private void putPixel(Graphics g, int x2, int y2) {
		g.fillRect(x2,y2,1,1);	
	}
	
}
