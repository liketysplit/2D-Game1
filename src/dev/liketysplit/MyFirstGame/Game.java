package dev.liketysplit.MyFirstGame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.liketysplit.MyFirstGame.Display.Display;
import dev.liketysplit.MyFirstGame.gfx.Assets;
import dev.liketysplit.MyFirstGame.gfx.ImageLoader;
import dev.liketysplit.MyFirstGame.gfx.SpriteSheet;
import dev.liketysplit.MyFirstGame.input.KeyManager;
import dev.liketysplit.MyFirstGame.states.GameState;
import dev.liketysplit.MyFirstGame.states.State;

public class Game implements Runnable{
	
	private Display display;
	public int width, height;
	public String title;
	private boolean running= false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	
	
	//States
	private State gameState;
	private State menu;
	private State settings;
	
	//Input
	private KeyManager keyManager;
	
	public Game(String title, int width, int height){
		this.width=width;
		this.height=height;
		this.title=title;
		keyManager = new KeyManager();
		
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		menu = new GameState(this);
		settings = new GameState(this);
		State.setState(gameState);
	}
	
	private void tick(){
		
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Start Drawing
		if(State.getState() != null)
			State.getState().render(g);
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while (running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now-lastTime;
			lastTime = now;
			if (delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if (timer >=1000000000){ //Used to check FPS via console 
				System.out.println("Tick and Frames: " + ticks);
				ticks = 0;
				timer = 0;
				
			} 
		}
	}
	public KeyManager getKeyManager(){
		return keyManager;
	}
	public synchronized void start(){
		if (running)
			return;
		running =true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
