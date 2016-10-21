package dev.liketysplit.MyFirstGame;

import dev.liketysplit.MyFirstGame.Display.Display;

public class Launcher {
	
	public static void main(String[] args){
		Game game = new Game("MyFirstGame", 600, 600);
		game.start();
	}
	
}
