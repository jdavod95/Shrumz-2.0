package elements.clickable;

import org.lwjgl.util.Point;


public interface Clickable {

	public abstract boolean getVis();
	public abstract void release();
	public void onClick();
	public void hover();
	public abstract boolean contains(Point m);
	
}
