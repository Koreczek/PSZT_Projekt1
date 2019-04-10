import java.lang.Math;
import java.util.LinkedList;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class RandomIterationAlgorithm{
	double a, b, c, d, e, f;
	int size = 200;
	int x = 0, y = 0;//starting Point
	int n; //count of iterations
	int k;//current transformation
	double z;
	List<Point> Points; 
	int xx;
	double[][] P = {{0.85, 0.04, -0.04, 0.85, 0, 1.6},{-0.15,0.28,0.26,0.24,0,0.44},{0.20,-0.26,0.23,0.22,0.5,1.6},{0,0,0,0.16,0,0}};
	RandomIterationAlgorithm() {
		n = 400;
		Points = new LinkedList<>(); 
	}
	
	//public void paint(Graphics g){
		//IFS(g);
	//}
	public List<Point> IFS(double[][] P) {
		Point p = new Point();
		for (long i = 0; i<10; i++) {
		k = chooseIteration();
		a = P[k][0] *Math.cos(P[k][2]*Math.PI/180);
		b = P[k][1]*Math.sin(P[k][3]*Math.PI/180);
		c = P[k][0]*Math.sin(P[k][2]*Math.PI/180);
		d = P[k][1]*Math.cos(P[k][3]*Math.PI/180);
		//a = P[k][0];
		//b = P[k][1];
		//c = P[k][2];
		//d = P[k][3];
		e = P[k][4];
		f = P[k][5];
		xx = (int) ((a*x + b * y + e));
		y = (int) ((c*x+d*y+f));
		x = xx;
		System.out.println(a + " , " + b  + " , " + c  + " , " + d  + " , " + e + " , " + f + " k=" + k);
		System.out.println(x + " , " + y);
		p.setPoint(x, y);
		Points.add(p);
		//putPixel(g ,x , y);
		}
		return Points;
	}
	private int chooseIteration() {
		z = Math.random();
		if (z <= 0.25)
			return 3;
		else if(z <= 0.5)
			return 2;
		else if(z <= 0.75)
			return 1;
		else 
			return 0;
	}

}