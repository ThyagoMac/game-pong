package br.com.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

//Estende Canvas () e usa Runnable como interface para vincular metodos run()
public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private static int WIDTH = 140;
	private static int HEIGHT = 120;
	private static int SCALE = 3;
	private boolean isRunning;

	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

	protected static Player player;
	protected static Enemy enemy;
	protected static Ball ball;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		// propria classe adiciona o evento de escutar teclado
		this.addKeyListener(this);

		player = new Player((WIDTH/2)-15, HEIGHT - 5, 40, 5);
		enemy = new Enemy((WIDTH/2)-15, 0, 40, 5, 1.0);
		ball = new Ball((WIDTH/2)-1, (HEIGHT / 2) - 1, 4, 4, 2.2);

	}

	public static void main(String[] args) {

		Game game = new Game();
		JFrame frame = new JFrame("Game-Pong by Thyago Mac v0.2.4");
		// Impede o redimencionamento da tela
		frame.setResizable(false);
		// Fecha a tela no X
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// adiciona um componente ao jframe
		frame.add(game);
		frame.pack();
		// Location devine o local da janela (null) -> centraliza
		frame.setLocationRelativeTo(null);
		// visibilidade on (caso contrario nao aparece)
		frame.setVisible(true);

		new Thread(game).start();
	}

	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}

	public void render() {
		// renderizando
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		player.render(g);
		enemy.render(g);
		ball.render(g);

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		// mostra a bagaca toda
		bs.show();
	}

	@Override
	public void run() {
		isRunning = true;
		while (isRunning) {
			tick();
			render();
			// 60 frames
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// ver o cod do botao que está sendo pressionado
		// System.out.println(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}
	
}
