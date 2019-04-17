package render2d.shapeNew;

import elements.MyEvent;

public interface Clickable extends Drawable{

	MyEvent getRelease();
	MyEvent getHover();
	MyEvent getOnClick();
	boolean isDown();
	void setDown(boolean b);
	
	default void release() {
		setDown(false);
		getRelease().action();
	}
	
	default void hover() {	
		getHover().action();
	}

	default void onClick() {
		if(!isDown()){
			setDown(true);
			getOnClick().action();
		}
	}
	
	public boolean contains(Point m);
	
}
