package elements;

public abstract class MyEvent {
	public static final MyEvent EMPTY = 
			new MyEvent(){
				@Override
				public void action() {}
			};
	public void action(){}

}
