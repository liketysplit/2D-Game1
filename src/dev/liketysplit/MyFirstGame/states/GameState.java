package dev.liketysplit.MyFirstGame.states;

import java.awt.Graphics;

import dev.liketysplit.MyFirstGame.Game;
import dev.liketysplit.MyFirstGame.entities.creatures.Player;
import dev.liketysplit.MyFirstGame.tiles.Tile;
import dev.liketysplit.MyFirstGame.worlds.World;

public class GameState extends State{

	private Player player;
	private World world;
	
	public GameState(Game game) {
		super(game);
		player = new Player (game, 100 , 100);
		world = new World("");
		
	}
	public void tick() {
		
		world.tick();
		player.tick();
		
	}


	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}
	

}
