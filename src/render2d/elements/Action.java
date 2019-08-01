package render2d.elements;

public abstract class Action {
	public static final Action EMPTY = 
			new Action(){
				@Override
				public void run() {}
			};
			
	public void run(){}

}
