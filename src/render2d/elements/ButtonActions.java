package render2d.elements;

public class ButtonActions extends CursorActions{

	private Button button;
	private Action buttonFunction;
	
	public ButtonActions(Button butt, Action function) {
		this.button = butt;
		this.buttonFunction = function;
	}

	@Override
	protected void actionClick() {
		button.skin.setCurrentFrame(1);
		button.label.getPos().add(Button.NUDGE);
		buttonFunction.run();
		button.down = true;
	}
	
	@Override
	protected void actionRelease() {
		button.skin.setCurrentFrame(0);
		button.label.getPos().subtract(Button.NUDGE);
		button.down = false;
	}
	
}
