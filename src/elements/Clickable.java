package elements;

import org.lwjgl.util.Point;

import render2d.shape.Shape;

public interface Clickable {

	public abstract Shape getShape();
	public abstract boolean getVis();
	public abstract void release();
	public void action();
	public void hover();
	public abstract boolean contains(Point m);
	
}
