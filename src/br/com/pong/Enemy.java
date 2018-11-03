package br.com.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	// varia a velocidade da ai com double (int = vel fixa)
	private double x, y;
	private int width;
	private int heigth;
	private double difficulty = 0.4;

	public Enemy(int x, int y, int width, int heigth, double difficulty) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.difficulty = difficulty;
	}

	public void tick() {
		
		x += (Game.ball.getX() - x) * difficulty;
		if (x + width > Game.getWIDTH()) {
			x = Game.getWIDTH() - width;
		} else if (x < 0) {
			x = 0;
		}
		

		//Rectangle rect1 = new Rectangle((int) x, (int) y, width, heigth);
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, heigth);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	
}
