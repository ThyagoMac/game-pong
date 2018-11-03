package br.com.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	protected boolean right, left;
	private int x, y;
	private int width;
	private int heigth;

	public Player(int x, int y, int width, int heigth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
	}

	// tick = ciclo (clock)
	public void tick() {

		if (right) {
			x++;
		}
		if (left) {
			x--;
		}

		if (x + width > Game.getWIDTH()) {
			x = Game.getWIDTH() - width;
		} else if (x < 0) {
			x = 0;
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		// define local e tamanho/largura
		g.fillRect(x, y, width, heigth);
	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}
	
	
}
