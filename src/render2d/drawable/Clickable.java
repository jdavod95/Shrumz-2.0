package render2d.drawable;

import render2d.elements.Action;
import render2d.elements.CursorActions;
import render2d.elements.Point;

public abstract class Clickable{
	
	private static final Action BASE_ACTION = Action.EMPTY;
	
	private boolean isDown;

	private Action click;
	private Action release;
	private Action hover;

	protected Clickable(Action click, Action release, Action hover) {
		this.isDown = false;
		this.click = click;
		this.release = release;
		this.hover = hover;
	}
	
	protected Clickable(CursorActions action) {
		this(action.getClick(), action.getRelease(), action.getHover());
	}
	
	public Clickable() {
		this(BASE_ACTION, BASE_ACTION, BASE_ACTION);
	}
	
	public boolean isDown() {
		return isDown;
	}

	public Action getRelease() {
		return release;
	}
	
	public Action getHover() {
		return hover;
	}
	
	public Action getClick() {
		return click;
	}
	
	public final void release() {
		isDown = false;
		release.run();
	}
	
	public final void hover() {	
		hover.run();
	}

	public final void click() {
		if(!isDown){
			isDown = true;
			click.run();
		}
	}
	
	public abstract boolean contains(Point m);
	
}
