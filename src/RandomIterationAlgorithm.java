import java.lang.Math;
import java.util.LinkedList;
import java.util.List;


public class RandomIterationAlgorithm{
	double a, b, c, d, e, f;
	int size;
	int x = 0, y = 0;//starting Point
	int n; //count of iterations
	int k;//current transformation
	double z;
	List<Point> Points; 
	int xx;
	double[][] transform;
	RandomIterationAlgorithm(int s, int n, double[][] P) {
		Points = new LinkedList<>();
		this.transform = P;
		this.size = s;
		this.n = n;
	}
	
	public List<Point> IFS() {
		Point p = new Point();
		for (long i = 0; i<n;) {
			k = chooseIteration();
			a = transform[k][0];
			b = transform[k][1];
			c = transform[k][2];
			d = transform[k][3];
			e = transform[k][4];
			f = transform[k][5];
			xx = (int) ((a*x + b * y + e));
			y = (int) ((c*x+d*y+f));
			x = xx;
			if(x>=0 && x<=size && y>=0 && y <= size) {
				p = new Point();
				p.setPoint(x, y);
				Points.add(p);
				i++;
			}
		}
		return Points;
	}

	private int chooseIteration() {
		z = Math.random();
		if(z <= 0.33)
			return 2;
		else if(z <= 0.66)
			return 1;
		else 
			return 0;
	}
	public void setTransform(double[][] transform) { 
		this.transform = transform;
	}

}