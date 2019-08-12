package render2d.elements;

public class ButtonActions extends CursorActions{

	public ButtonActions(Button butt, Action function) {
		setClick(()->{
				butt.skin.setCurrentFrame(1);
				butt.label.getPos().add(Button.NUDGE);
				function.run();
				butt.down = true;
			});
		
		setRelease(()->{
				butt.skin.setCurrentFrame(0);
				butt.label.getPos().subtract(Button.NUDGE);
				butt.down = false;
		});
		
		
		
	}
	
}
