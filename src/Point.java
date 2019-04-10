
public class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		setPoint(x, y);
	}
	
	public Point() {
		setPoint(0,0);
	}

	void setPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	int getX () {
		return this.x;
	}
	int getY() {
		return this.y;
	}
	
}
