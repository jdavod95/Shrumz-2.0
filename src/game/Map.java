package game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Point;

import elements.Cursor;
import game.plants.Plant;
import game.plants.Shrum;
import render2d.RectTex;
import root.Shrumz;

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
						my+j*Tile.getScale(),
						null
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
		if(Shrumz.getTicks() % 5 != 0)
			return;
		for(int i = 0;i<dx;i++)
			for(int j = 0;j<dy;j++){
				setNeigh(i, j, table[i][j]);
				if(table[i][j].cycle())
					spread.add(new Point(i,j));			
			}
		/*
		if(!spread.isEmpty()){
			for(Point p : spread){

				System.out.println(p.getX() +" "+ p.getY());
				setNeigh(p.getX(), p.getX(), table[p.getX()][p.getY()]);
				Tile[] t = table[p.getX()][p.getY()].spread();
				for(Tile s : t){
					RectTex skin = s.getPlant().getSkin();
				// ha valami terjeszkedett már ide..
					if(table[skin.getX()][skin.getY()].getPlant() != null
							&& Math.random() > 0.5){
					//akkor 50% esély h felülírja
						table[skin.getX()][skin.getY()] = s;
					// vagy el is lehetne tárolni Tile ban a saját pozícióját a table[][] ben...	
						skin.setX(mx+skin.getX()*Tile.getScale());
						skin.setY(my+skin.getY()*Tile.getScale());
					// nem tetszik ez a módszer
					}
				}
			}
			spread.clear();
		}*/

		if(!spread.isEmpty()){
			for(Point p : spread){
				Plant[] t = table[p.getX()][p.getY()].spread();
				
				for(Plant pl : t){
					RectTex skin = pl.getSkin();
					
					try{
						if(table[skin.getX()][skin.getY()].getPlant() == null){
							table[skin.getX()][skin.getY()].setPlant(pl);
							skin.setX(mx+skin.getX()*Tile.getScale());
							skin.setY(my+skin.getY()*Tile.getScale());
						}
					} catch (Exception e){} 
					
				}
			}
			spread.clear();
		}
	
	}
	public static Tile[][] getTable() {
		return table;
	}
	public static int getX(){
		return dx;
	}
	
	public static int getY(){
		return dy;
	}
	public static void setNeigh(int x, int y, Tile t){
		Point[][] p = new Point[3][3];

		for(int i = -1;i < 2;i++)
			for(int j = -1;j < 2;j++)
				//try{
					p[i+1][j+1] = new Point(x+i, y+j);
				//} catch(Exception e){
				//	p[i+1][j+1] = null;
			//	}
		
		p[1][1] = null;
		t.setNeigh(p);
	}
}
