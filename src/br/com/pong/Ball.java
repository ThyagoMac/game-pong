package br.com.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	private double x, y;
	private int width;
	private int height;
	private static int enemyScore = 0;
	private static int playerScore = 0;

	// define a direcao
	private double dx, dy;
	private double speed;

	public Ball(int x, int y, int width, int height, double speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;

		int angle = new Random().nextInt(120-45) + 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
//		gera numeros aleatorios que serao adicionado ao eixo x/y
//		dx = new Random().nextGaussian();
//		dy = new Random().nextGaussian();
	}

	public void tick() {
		if (x + (dx * speed) + width >= Game.getWIDTH() || x + (dx * speed) < 0) {
			dx *= -1;
		}

		if (y >= Game.getHEIGHT()) {
			System.out.println("Enemy Score: " + ++enemyScore);
			System.out.println("Player Score: " + playerScore);
			new Game();
			return;
		} else if (y < 0) {
			System.out.println("Enemy Score: " + enemyScore);
			System.out.println("Player Score: " + ++playerScore);
			new Game();
			return;
		}

		// Rectangle permite testes de colisoes
		Rectangle bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), width, height);

		Rectangle boundsPlayer = new Rectangle(Game.player.getX(), Game.player.getY(), Game.player.getWidth(),
				Game.player.getHeigth());
		Rectangle boundsEnemy = new Rectangle((int) Game.enemy.getX(), (int) Game.enemy.getY(), Game.enemy.getWidth(),
				Game.enemy.getHeigth());

		if (bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120-45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if (dy > 0) {
				dy *= -1;
			}
			
		} else if (bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120-45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			
			Random r = new Random();
			double rangeMin=0.001;
			double rangeMax =0.5;
			double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			
			Game.enemy.setDifficulty(randomValue);
			System.out.println(randomValue);
			if (dy < 0) {
				dy *= -1;
			}
		}

		x += (dx * speed);
		y += (dy * speed);

	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, width, height);

	}

	public double getX() {
		return x;
	}

}
