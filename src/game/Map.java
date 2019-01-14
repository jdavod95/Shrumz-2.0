package game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Point;

import elements.Cursor;
import game.plants.Plant;
import render2d.shape.RectTex;
import render2d.shape.Shape;

public class Map {

	static final int mx = 5;
	static final int my = 95;
	
	static int dx,dy;
	static Tile[][] table;

	static List<Point> spread = new ArrayList<>();
	
	public static int getX(){
		return dx;
	}
	
	public static int getY(){
		return dy;
	}
	
	public static Tile[][] getTable() {
		return table;
	}
	
	public static void setTable(int x, int y){
		table = new Tile[x][y];
		dx = x;
		dy = y;
		
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j] = new Tile(
						mx + (8-i-1)*(Tile.getScale()) + j*Tile.getScale(),
						my + (i+1)*Tile.getScale()/2 + j*Tile.getScale()/2,
						null
					);
	}
	
	public static void load(int x, int y, int scale){
		Tile.setScale(scale);
		setTable(x,y);
	}
	
	public static void toRender(){
		for(int i = 0;i<dx;i++){
			Cursor.addClck(table[i]);
			for(int j = 0;j<dy;j++)
				table[i][j].toRender();
		}
	}
	
	public static void reScale(int scale){
		int scaledif = Tile.getScale() - scale;
		Tile.setScale(scale);
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j].reScale(-scaledif*j+scaledif*i, -scaledif*j/2-scaledif/2*i);
	}

	public static void cycle(){
		
		// growing
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++){
				setNeigh(i, j, table[i][j]);
				if(table[i][j].cycle())
					spread.add(new Point(i,j));			
			}
		
		// spreading
		if(!spread.isEmpty()){
			for(Point p : spread){
				
				Plant[] t = table[p.getX()][p.getY()].spread();
				for(Plant pl : t){
					RectTex skin = pl.getSkin();
					try{
						int i = skin.getX();
						int j = skin.getY(); 
						Shape soil = table[i][j].getShape();
						if(table[i][j].getPlant() == null){
							table[i][j].setPlant(pl);
							skin.setX(soil.getX()
							+soil.getW()/4);
							skin.setY(soil.getY()
							-soil.getH()/2);
						}
					} catch (Exception e){} 
				}
			}
			spread.clear();
		}
		
		// clean up
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++)
				table[i][j].chkDead();
		
	}

	public static void setNeigh(int x, int y, Tile t){
		Point[][] p = new Point[3][3];

		for(int i = -1;i < 2;i++)
			for(int j = -1;j < 2;j++)
				p[i+1][j+1] = new Point(x+i, y+j);

		t.setNeigh(p);
	}
}
