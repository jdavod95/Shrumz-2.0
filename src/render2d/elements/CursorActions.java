package render2d.elements;

public class CursorActions {
	private Action click;
	private Action hover;
	private Action release;
	private Action hold;
	
	public CursorActions() {
		click = Action.EMPTY;
		hover = Action.EMPTY;
		release = Action.EMPTY;
		hold = Action.EMPTY;
	}
	
	protected void setClick(Action click) {
		this.click = click;
	}

	protected void setHover(Action hover) {
		this.hover = hover;
	}

	protected void setRelease(Action release) {
		this.release = release;
	}

	protected void setHold(Action hold) {
		this.hold = hold;
	}

	public Action getClick() {
		return click;
	}

	public Action getHover() {
		return hover;
	}

	public Action getRelease() {
		return release;
	}

	public Action getHold() {
		return hold;
	}
	
}
