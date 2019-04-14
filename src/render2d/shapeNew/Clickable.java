package render2d.shapeNew;

public interface Clickable {
	
	public abstract void release();
	public void onClick();
	public void hover();
	public abstract boolean contains(Point m);
	
}
