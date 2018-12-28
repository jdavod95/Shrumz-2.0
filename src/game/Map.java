package game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Point;

import elements.Cursor;

public class Map {

	static final int mx = 5;
	static final int my = 95;
	
	static int dx,dy;
	static Tile[][] table;
	static List<Point> spread = new ArrayList<>();
	
	public static void set(int x, int y){
		table = new Tile[x][y];
		dx = x;
		dy = y;
		
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j] = new Tile(
						mx+i*Tile.getScale(),
						my+j*Tile.getScale()
					);
	}
	
	public static void load(int x, int y, int scale){
		Tile.setScale(scale);
		set(x,y);
	}
	
	public static void reScale(int scale){
		int scaledif = Tile.getScale() - scale;
		Tile.setScale(scale);
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j].reScale(-scaledif*i, -scaledif*j);
	}
	
	public static void toRender(){
		for(int i = 0;i<dx;i++){
			Cursor.addClck(table[i]);
			for(int j = 0;j<dy;j++){
				table[i][j].toRender();
				
			}
		}
	}
	
	public static void cycle(){
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				if(table[i][j].cycle())
					spread.add(new Point(i,j));
		
		if(!spread.isEmpty()){
			for(Point p : spread){
				int i = p.getX();
				int j = p.getY();

				table[i][j].spread();
				
				if(i != 0)
					table[i-1][j].spread();
				if(i != dx-1)
					table[i+1][j].spread();
				if(j != 0)
					table[i][j-1].spread();
				if(j != dy-1)
					table[i][j+1].spread();
			}
			spread.clear();
		}
	}
	
	public static int getX(){
		return dx;
	}
	public static int getY(){
		return dy;
	}
}
