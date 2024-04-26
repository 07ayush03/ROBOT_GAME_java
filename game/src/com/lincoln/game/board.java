package com.lincoln.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.lincoln.game.animation.ninjastar;
import com.lincoln.game.animation.Player;

public class board extends JPanel {
	Timer timer;
	BufferedImage backgroundimage;
	Player player;
	ninjastar nin[] = new ninjastar[3];

	public board() {
		setSize(1000, 820);
		load();
		player = new Player();
		loadEnimies();
		gl();
		bindEvents();
		setFocusable(true);
	}

	private void gameOver(Graphics pen) {
		if (player.outOfScreen()) {
			pen.setFont(new Font("times", Font.BOLD, 30));
			pen.setColor(Color.GREEN);
			pen.drawString("Game Win", 1500 / 2 - 80, 800 / 2 - 50);
			timer.stop();
		}
		for (ninjastar nins : nin) {
			if (isCollide(nins)) {
				pen.setFont(new Font("times", Font.BOLD, 30));
				pen.setColor(Color.RED);
				pen.drawString("Game Over", 1500 / 2 - 80, 800 / 2 - 50);
				timer.stop();
			}
		}
	}

	private boolean isCollide(ninjastar nin) {
		int xDis = Math.abs(player.x - nin.x);
		int yDis = Math.abs(player.y - nin.y);
		int maxH = Math.abs(player.h - nin.h);
		int maxW = Math.abs(player.w - nin.w);

		return xDis <= maxW && yDis <= maxH;
	}

	private void bindEvents() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.speed = 10;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.speed = -10;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player.speed = 0;
			}

		});
	}

	private void loadEnimies() {
		int x = 400;
		int gap = 400;
		int speed = 5;
		for (int i = 0; i < nin.length; i++) {
			nin[i] = new ninjastar(x, speed);
			x += gap;
			speed += 5;
		}
	}

	private void gl() {
		timer = new Timer(50, (e) -> repaint());
		timer.start();
	}

	private void load() {
		try {
			backgroundimage = ImageIO.read(board.class.getResource("bakcground.jpg_large"));
		} catch (IOException e) {
			System.out.print("NOT FOUND IMAGE");
			System.exit(1);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawEnimies(Graphics pen) {
		for (ninjastar nins : nin) {
			nins.draw(pen);
			nins.move();
		}
	}

	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		pen.drawImage(backgroundimage, 0, 0, 1500, 800, null);
		player.draw(pen);
		player.move();
		drawEnimies(pen);
		gameOver(pen);
	}
}
