package dev.liketysplit.MyFirstGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Static 
	
	public static Tile[] tiles = new Tile [256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile  dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	//Class
	
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILE_HEIGHT, TILE_WIDTH, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	public int getId(){
		return id;
	}
}
