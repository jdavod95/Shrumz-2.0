package render2d.elements;

public abstract class CursorActions {
	private Action click;
	private Action hover;
	private Action release;
	private Action hold;
	
	public CursorActions() {
		click = this::actionClick;
		hover = this::actionHover;
		release = this::actionRelease;
		hold = this::actionHold;
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

	protected void actionClick() {}
	protected void actionHover() {}
	protected void actionRelease() {}
	protected void actionHold() {}
}
