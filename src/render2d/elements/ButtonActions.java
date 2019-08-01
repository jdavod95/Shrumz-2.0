package render2d.elements;

public class ButtonActions extends CursorActions{

	public ButtonActions(Button butt, Action function) {
		setClick(
			new Action(){
			@Override
			public void run() {
				butt.skin.setCurrentFrame(1);
				butt.label.getPos().add(Button.NUDGE);
				function.run();
				butt.down = true;
			}
		});
		setRelease(
		new Action(){
			@Override
			public void run() {
				butt.skin.setCurrentFrame(0);
				butt.label.getPos().subtract(Button.NUDGE);
				butt.down = false;
			}
		});
		
		
		
	}
	
}
