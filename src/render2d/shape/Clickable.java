package render2d.shape;

import elements.MyEvent;
import elements.Point;

public interface Clickable extends Drawable{

	Shape getShape();	// ?????
	
	boolean isDown();
	void setDown(boolean b);
	
	default MyEvent getRelease() {
		return MyEvent.EMPTY;
	}
	
	default MyEvent getHover() {
		return MyEvent.EMPTY;
	}
	
	default MyEvent getClick() {
		return MyEvent.EMPTY;
	}
	
	default void release() {
		setDown(false);
		getRelease().action();
	}
	
	default void hover() {	
		getHover().action();
	}

	default void click() {
		if(!isDown()){
			setDown(true);
			getClick().action();
		}
	}
	
	public boolean contains(Point m);
	
}
