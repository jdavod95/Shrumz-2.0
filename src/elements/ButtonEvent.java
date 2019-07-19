package elements;

public class ButtonEvent {
	private Action click;
	private Action hover;
	private Action release;
	private Action hold;
	
	public ButtonEvent() {
		click = Action.EMPTY;
		hover = Action.EMPTY;
		release = Action.EMPTY;
		hold = Action.EMPTY;
	}
	
	public ButtonEvent setClick(Action click) {
		this.click = click;
		return this;
	}

	public ButtonEvent setHover(Action hover) {
		this.hover = hover;
		return this;
	}

	public ButtonEvent setRelease(Action release) {
		this.release = release;
		return this;
	}

	public ButtonEvent setHold(Action hold) {
		this.hold = hold;
		return this;
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
