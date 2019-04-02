package render2d;

public class Color {

	public static final Color GRAY = new Color(128,128,128);
	public static final Color BLACK = new Color(0,0,0);
	public static final Color WHITE = new Color(255,255,255);
	
	private int r, g, b;

	public Color(int r, int g, int b) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	
}
