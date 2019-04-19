import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{
	static double[][] p1 = {{0.5, 0, 0, 0.5, 0, 0},{0.5,0,0,0.5,400,0},{0,-0.5,0.5,0,800,400}};//
	static double[][] p2 = {{0.5, 0, 0, 0.5, 0, 0},{0.5,0,0,0.5,200,0},{0.5,0,0,0.5,100,173}};//Trojkat Sierpinskiego
	static List<Point> X = new ArrayList<>();
	static List<Point> Y = new ArrayList<>();
	static List<Point> F = new ArrayList<>();
	static List<Point> Z = new ArrayList<>();
	static int size = 400; //Rozmiar okna
	static int iterations = 3000; // Liczba Iteracji w RandomIterationAlgorithm
	static double a = 0.5;
	static RandomIterationAlgorithm RIA1 = new RandomIterationAlgorithm(size, iterations, p1);
	static RandomIterationAlgorithm RIA2 = new RandomIterationAlgorithm(size, iterations, p2);
	public static void main(String[] args) throws IOException{		
		X = RIA1.IFS();//Ustawianie listy punktow wygenerowanych
		Y = RIA2.IFS();
		marge(a);//Zlaczenie wygenerowanych list
		Loadingf PointList = new Loadingf("png2.png");
		F = PointList.writetoList();//Wczytanie z obrazka listy czarnych pikseli
		View view = new View(size, F, Z); //Okno wyswietla 
		Evolution(view); //Szukanie rozwiazania optymalnego
		System.out.println("Ostateczne tablice przekrztalcen:");
		for(int i=0; i< p1.length; i++) {
			for(int j=0; j < p1[i].length;j++) {
				System.out.print(p1[i][j] + " ");
			}
			System.out.println();
		}
		for(int i=0; i< p2.length; i++) {
			for(int j=0; j < p2[i].length;j++) {
				System.out.print(p2[i][j] + " "); 
			}
			System.out.println();
		}
		
	}
	public static void Evolution(View v){ 
		double[][] pr1 = new double[p1.length][p1[1].length];
		double[][] pr2 = new double[p2.length][p1[1].length];
		for(int i=0; i< p1.length; i++) {
			for(int j=0; j < p1[i].length;j++) {
				pr1[i][j] = p1[i][j]; 
			}
		}
		int distance = measureDistanceFromSetToSet(Z, F);
		while(distance > iterations*22) {
			for(int i=0; i< p1.length; i++) {
				for(int j=0; j < p1[i].length;j++) {
					pr1[i][j] = p1[i][j]; 
				}
			}
			for(int i=0; i< p2.length; i++) {
				for(int j=0; j < p2[i].length;j++) {
					pr2[i][j] = p2[i][j]; 
				}
			}
			mixed();
			RIA1.setTransform(p1);
			RIA2.setTransform(p2);
			X.clear();
			X = RIA1.IFS();
			Y.clear();
			Y = RIA2.IFS();
			marge(a);
			if(distance > measureDistanceFromSetToSet(Z, F)) {
				v.repaint();
				distance = measureDistanceFromSetToSet(Z, F);
			}
			else {					//Jesli algorytm nie poprawil wynikow wracamy do poprzedniego rozwiazania
				for(int i=0; i< p1.length; i++) {
					for(int j=0; j < p1[i].length;j++) {
						p1[i][j] = pr1[i][j]; 
					}
				}
				for(int i=0; i< p2.length; i++) {
					for(int j=0; j < p2[i].length;j++) {
						p2[i][j] = pr2[i][j]; 
					}
				}
			}
			System.out.println(distance);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void mixed() {
		double modifier = Math.random()/5 - 0.1;
		Random generator = new Random();
		int i = generator.nextInt(p1.length);
		int j = generator.nextInt(p1[1].length);
		double k = p1[i][j]*(1-modifier)+modifier;
		p1[i][j] = p2[i][j]*(1-modifier)+modifier;
		p2[i][j] = k;

		
	}
	private static void marge(double a) {
		Point p = new Point();
		int n = X.size();
		int x, y;
		Z.clear();
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
