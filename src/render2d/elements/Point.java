package render2d.elements;

public class Point {

	private int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setXY(Point point) {
		setXY(point.getX(), point.getY());
	}
	public void add(int x, int y) {
		setXY(
			getX() + x,
			getY() + y
			);
	}
	
	public void add(Point p) {
		setXY(
			getX() + p.getX(),
			getY() + p.getY()
			);
	}
	
	public void subtract(int x, int y) {
		setXY(
			getX() - x,
			getY() - y
			);
	}
	
	public void subtract(Point p) {
		setXY(
			getX() - p.getX(),
			getY() - p.getY()
			);
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point getNew() {
		return new Point(x, y);
	}
	
	public Point getNew(int offsetX, int offsetY) {
		return new Point(x + offsetX, y + offsetY);
	}
	
	public Point getNew(Point offset) {
		return getNew(offset.getX(), offset.getY());
	}

	public boolean equals(Point p) {
		return p.getX() == getX()
			&& p.getY() == getY();
	}
}
