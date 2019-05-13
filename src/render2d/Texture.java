package render2d;

public class Texture {

	private String name;
	private int frameSize;
	private int rows;
	private int columns;
	private int id;
	
	public Texture(String name, int frameSize, int rows, int columns, int id) {
		this.name = name;
		this.frameSize = frameSize;
		this.rows = rows;
		this.columns = columns;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getFrameSize() {
		return frameSize;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getId() {
		return id;
	}
	
}
