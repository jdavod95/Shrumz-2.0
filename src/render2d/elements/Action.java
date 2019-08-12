package render2d.elements;

@FunctionalInterface
public interface Action {
	public static final Action EMPTY = () -> {};
	public void run();
}
