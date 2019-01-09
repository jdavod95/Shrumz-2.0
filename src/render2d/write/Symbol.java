package render2d.write;

public class Symbol {

	public int x;
	public int y;
	public int id;
	public int width;
	public static int heigth = 67;
	
	public int getX() {
		return x;
	}

	public int getW() {
		return width;
	}

	public void setW(int width) {
		this.width = width;
	}

	public static int getH() {
		return heigth;
	}

	public static void setH(int heigth) {
		Symbol.heigth = heigth;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Symbol(int id, int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
	}

	
}
