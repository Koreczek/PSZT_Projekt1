import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main{
	static double[][] p1 = {{0.85, 0.04, -0.04, 0.85, 0, 1.6},{-0.15,0.28,0.26,0.24,0,0.44},{0.20,-0.26,0.23,0.22,0.5,1.6},{0,0,0,0.16,0,0}};
	static double[][] p2 = {{0.85, 0.04, -0.04, 0.85, 0, 1.6},{-0.15,0.28,0.26,0.24,0,0.44},{0.20,-0.26,0.23,0.22,0.5,1.6},{0,0,0,0.16,0,0}};
	static List<Point> X = new ArrayList<>();
	static List<Point> Y = new ArrayList<>();
	static List<Point> Z = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		RandomIterationAlgorithm RIA = new RandomIterationAlgorithm();
		X = RIA.IFS(p1);
		Y = RIA.IFS(p2);
		marge(0.35);
		System.out.println(Z.get(0).getX());
	}
	private static void marge(double a) {
		Point p = new Point();
		int n = X.size();
		int x, y;
		for(int i = 0; i<n;i++) {
			x = (int) (a*X.get(i).getX() + (1-a)*Y.get(i).getX());
			y = (int) (a*X.get(i).getY() + (1-a)*Y.get(i).getY());
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
	private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {
		 
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
 
        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
 
        return result;
    }
	

}
