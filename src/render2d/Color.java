package render2d;

public class Color {

	public static final Color GRAY = new Color(128,128,128);
	public static final Color BLACK = new Color(0,0,0);
	public static final Color WHITE = new Color(255,255,255);
	public static final Color MAGENTA = new Color(255,0,255);
	
	public static final double COLBITS = 255;
	private int r, g, b;
	private double opacity;
	
	public Color(int r, int g, int b) {
		this(r, g, b, 1.0); 
	}
	
	public Color(Color c, double opacity) {
		this(c.getR(), c.getG(), c.getB(), opacity); 
	}
	public Color(int r, int g, int b, double opacity) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.opacity = opacity;
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

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public double getRVal(){
		return r/COLBITS;
	}
	
	public double getGVal(){
		return g/COLBITS;
	}
	
	public double getBVal(){
		return b/COLBITS;
	}

	@Override
	public String toString() {
		return "Color [r=" + getRVal() + ", g=" + getGVal() + ", b=" + getBVal() + ", opacity=" + opacity + "]";
	}
	
	
}
