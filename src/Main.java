import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{
	static double[][] p1 = {{0.5, 0, 0, 0.5, 0, 0},{0.5,0,0,0.5,400,0},{0,-0.5,0.5,0,800,400}};
	static double[][] p2 = {{0.5, 0, 0, 0.5, 0, 0},{0.5,0,0,0.5,800,0},{0.5,0,0,0.5,400,692}};
	static List<Point> X = new ArrayList<>();
	static List<Point> Y = new ArrayList<>();
	static List<Point> F = new ArrayList<>();
	static List<Point> Z = new ArrayList<>();
	static int size = 800;
	static int iterations = 10000;
	
	public static void main(String[] args) throws IOException{
		RandomIterationAlgorithm RIA1 = new RandomIterationAlgorithm(size, iterations, p1);
		RandomIterationAlgorithm RIA2 = new RandomIterationAlgorithm(size, iterations, p2);
		X = RIA1.IFS();
		Y = RIA2.IFS();
		marge(0.5);
		Loadingf PointList = new Loadingf("png.png");
		F = PointList.writetoList();
		View view = new View(size, F, Z);
		System.out.println(measureDistanceFromSetToSet(Z, F));
		while(true) {
			
		}
		
		
		
	}
	private static void marge(double a) {
		Point p = new Point();
		int n = X.size();
		int x, y;
		for(int i = 0; i<n;i++) {
			x = (int) (a*X.get(i).getX() + (1-a)*Y.get(i).getX());
			y = (int) (a*X.get(i).getY() + (1-a)*Y.get(i).getY());
			p = new Point();
			p.setPoint(x, y);
			Z.add(p);
		}
		
	}
	private static int measureDistanceFromSetToSet(List<Point> x, List<Point> y){
		int n = x.size();
		int sum = 0;
		for(int i = 0; i<n;i++) {
			sum += measureDistanceFromPointToSet(x.get(i), y);
		}
		return sum;
	}
	private static int measureDistanceFromPointToSet(Point p1, List<Point> y){
		int n = y.size();
		int dist = 0;
		int min = 10000;
		for(int i = 0; i<n;i++) {
			dist = measureDistanceFromPointToPoint(p1, y.get(i));
			if (dist < min)
				min = dist;
		}
		return min;
	}
	private static int measureDistanceFromPointToPoint(Point p1, Point p2) {
		int k = Math.abs(p1.getX()-p2.getX());
		int m = Math.abs(p1.getY()-p2.getY());
		if (k>m)
			return k;
		return m;
		
	}
	

}
