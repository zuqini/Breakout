import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.net.URL;

class Breakout {

	View view;
	LoseView loseView;
	WinView winView;
	SplashView splashView;
	Model model;
	JFrame frame;
	int frameRate;
	int ballSpeed;
	int levelCounter = 0;
	int score = 0;
	Timer mainTimer = new Timer();

	Integer[][] level = {{0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1},
						{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
						{0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
						{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
						{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
						{0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1}};

	Integer[][] level1 = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

	Integer[][] level2 = {{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}};

	Integer[][] level3 = {{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
						{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},};

	Integer[][] level4 = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}};

	void vibrate() {
		int x = frame.getLocationOnScreen().x; 
		int y = frame.getLocationOnScreen().y;
		int interval = 80; 
		int strength = 5;
		mainTimer.schedule(new TimerTask() {
		  @Override
		  public void run() {
		    frame.setLocation(x, y + strength); 
		  }
		}, interval);
		mainTimer.schedule(new TimerTask() {
		  @Override
		  public void run() {
		    frame.setLocation(x, y - strength);
		  }
		}, interval*2);
		mainTimer.schedule(new TimerTask() {
		  @Override
		  public void run() {
		    frame.setLocation(x + strength, y); 
		  }
		}, interval*3);
		mainTimer.schedule(new TimerTask() {
		  @Override
		  public void run() {
		    frame.setLocation(x - strength, y);
		  }
		}, interval*4);
		mainTimer.schedule(new TimerTask() {
		  @Override
		  public void run() {
		    frame.setLocation(x, y); 
		  }
		}, interval*5);
	}

	Breakout(int frameRate, int ballSpeed) {
		this.view = new View();
		this.loseView = new LoseView();
		this.winView = new WinView();
		this.splashView = new SplashView();
		this.frameRate = frameRate;
		this.ballSpeed = ballSpeed;

		frame = new JFrame("BreakOut");
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(800, 600);
		frame.setContentPane(splashView);
		frame.setVisible(true);
		splashView.requestFocus();
	}

	class Vector {
		int x, y;
		Vector(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Vector(double deg, int length) {
			if(deg > 4.5 && deg < 4.9) {
				deg = 4.5;
			}
			this.x = (int)(Math.cos(deg) * length);
			this.y = (int)(Math.sin(deg) * length);
		}

		Vector changeDirV() {
			this.y *= -1;
			return this;
		}

		Vector changeDirH() {
			this.x *= -1;
			return this;
		}

		Vector add(Vector a) {
			this.x += a.x;
			this.y += a.y;
			return this;
		}

		boolean isZero() {
			return this.x == 0 && this.y == 0;
		}
	}

	class Block {
		Vector coords;
		int width;
		int height;
		boolean active;
		Block(Vector coords, int width, int height) {
			this.coords = coords;
			this.width = width;
			this.height = height;
			this.active = true;
		}
	}
	class Ball {
		Vector coords;
		Vector speed;
		int radius;
		int defaultSpeed;
		Ball(int defaultSpeed, Vector coords) {
			this.defaultSpeed = defaultSpeed;
			this.coords = coords;
			this.radius = 7;
			speed = new Vector(0,0);
		}

		void updPos() {
			if(speed.x == 0 && speed.y == 0) {
				coords.x = model.paddle.coords.x+model.paddle.width/2 - 7;
				coords.y = model.paddle.coords.y - 14;
			}
			coords.add(speed);
		}
	}
	class Paddle { 
		Vector coords;
		float friction;
		int width;
		int height;
		float s = 0;
		float defaultMaxSpeed;
		Paddle(Vector coords,float defaultMaxSpeed, float friction) {
			this.coords = coords;
			this.width = 125;
			this.height = 10;
			this.defaultMaxSpeed = defaultMaxSpeed;
			this.friction = friction;
		}
		void accelerateRight() {
			s=defaultMaxSpeed;
		}

		void accelerateLeft() {
			s=-defaultMaxSpeed;
		}

		void updPos() {
			this.coords.x += s;
			if(this.coords.x > model.width - this.width) {
				this.coords.x = model.width - this.width;
				s = 0;
			} else if (this.coords.x < 0){
				this.coords.x = 0;
				s = 0;
			}
		}

		void applyFriction() {
			if(s > 0) {
				s-=friction;
			} else if (s < 0) {
				s+=friction;
			} else {
				return;
			}
		}
	}

	class Model {
		boolean left = false;
		boolean right = false;
		List<Block> blocks;
		Ball ball;
		Paddle paddle;
		Timer timer;
		private Boolean isRunning;
		final int block_width = 42;
		final int block_height = 30;
		final int width = 800;
		final int height = 600;
		final int padding = 5;

		boolean ballBlockCollide() {
			boolean allInactive = true;
			for(int i=0; i<blocks.size(); i++) {
				if(blocks.get(i).active) {

					float width = 0.5f * (ball.radius*2 + block_width);
					float height = 0.5f * (ball.radius*2 + block_height);
					float changeX = ball.coords.x + ball.speed.x+ ball.radius - blocks.get(i).coords.x - block_width/2;
					float changeY = ball.coords.y + ball.speed.y + ball.radius - blocks.get(i).coords.y - block_height/2;

					if (Math.abs(changeX) <= width && Math.abs(changeY) <= height){
						float widthY = width * changeY;
						float heightX = height * changeX;

						if (widthY > heightX) {
							if (widthY > -heightX) {
								System.out.println("top");
								ball.speed.changeDirV();
							} else {
								System.out.println("left");
								ball.speed.changeDirH();
							}
						} else {
							if (widthY > -heightX) {
								System.out.println("right");
								ball.speed.changeDirH();
							} else {
								System.out.println("bottom");
								ball.speed.changeDirV();
							}
						}
						blocks.get(i).active = false;
						score++;
						vibrate();
					}
					if(blocks.get(i).active) {
						allInactive = false;
					}
				}
			}
			return allInactive;
		}

		void ballPaddleCollide() {
			if(ball.coords.y + 2*ball.radius + ball.speed.y > paddle.coords.y && ball.coords.y + 2*ball.radius <= paddle.coords.y && 
				ball.coords.x < paddle.coords.x + paddle.width &&
				ball.coords.x + 2*ball.radius > paddle.coords.x) {
				ball.speed.changeDirV();
				score++;
			}
		}

		boolean ballBottomCollide() {
			if(ball.coords.y + ball.speed.y >= 610) {
				return true;
			}
			return false;
		}

		void ballEnvCollide() {
			if(ball.coords.x + 2*ball.radius + ball.speed.x > 800 ||
				ball.coords.x + ball.speed.x < 0) {
				ball.speed.changeDirH();
				score++;
			}
			if(ball.coords.y + ball.speed.y < 0) {
				ball.speed.changeDirV();
				score++;
			}
		}

		Model(int frameRate, int ballSpeed, Integer[][] level) {
			this.blocks = new ArrayList<Block>();

			int startX = 70;
			int startY = 100;
			for (int i=0; i<16; i++) {
				for (int j=0; j<6; j++) {
					if(level[j][i] == 1) {
						blocks.add(new Block(new Vector(startX + i * (block_width), startY + j * block_height), block_width, block_height));
					}
				}
			}
			this.paddle = new Paddle(new Vector(350, 540), ballSpeed * 60 / frameRate, ballSpeed * 3f/frameRate);
			this.ball = new Ball((int)(ballSpeed * 60 / frameRate), new Vector(paddle.coords.x+paddle.width/2 - 7, paddle.coords.y - 14));
			this.isRunning = true;
			this.timer = new Timer();
		    this.timer.schedule(new GameLoop(), 0, 1000 / frameRate);
		}

		void releaseBall() {
			if(ball.speed.isZero())
				ball.speed = new Vector(3.8f + Math.random() * 1.8f, ball.defaultSpeed);
		}

		private void update() {
			if (ballBlockCollide()) {
				levelCounter++;
				frame.setContentPane(winView);
				winView.requestFocus();
				frame.revalidate();
				isRunning = false;
				return;
			}
			ballPaddleCollide();
			if (ballBottomCollide()) {
				score = 0;
				levelCounter = 0;
				frame.setContentPane(loseView);
				loseView.requestFocus();
				frame.revalidate();
				isRunning = false;
				return;
			}
			ballEnvCollide();
			ball.updPos();
			if(model.left) {
				paddle.accelerateLeft();
			} else if(model.right) {
				paddle.accelerateRight();
			} else {
				paddle.applyFriction();
			}
			paddle.updPos();
		}

		private class GameLoop extends TimerTask {
		    public void run() {
		    	model.update();
		    	view.revalidate();
		    	view.repaint();

				if (!isRunning) {
					timer.cancel();
				}
		    }
		}
	}

	class LoseView extends JPanel {
		class LoseKeyAdapter extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent ke) {
	        }

			@Override
	        public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
					model = new Model(frameRate, ballSpeed, level);
					frame.setContentPane(view);
					view.requestFocus();
					frame.revalidate();
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
	        }
		}
		LoseView() {
			setBackground(Color.BLACK);
			addKeyListener(new LoseKeyAdapter());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GREEN);
			g.setFont(new Font("Calibri", Font.PLAIN, getWidth() / 20));
			g.drawString("Game Over, press space to restart.", 30, 200);
			Toolkit.getDefaultToolkit().sync();
		}
	}

	class SplashView extends JPanel {
		class SplashKeyAdapter extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent ke) {
	        }

			@Override
	        public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
					model = new Model(frameRate, ballSpeed, level);
					frame.setContentPane(view);
					view.requestFocus();
					frame.revalidate();
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
	        }
		}
		SplashView() {
			setBackground(Color.BLACK);
			addKeyListener(new SplashKeyAdapter());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);	
			g.setColor(Color.WHITE);
			g.setFont(new Font("Calibri", Font.PLAIN, getWidth() / 20));
			g.drawString("Name: Zuqi Li", 30, getHeight() / 10);
			g.drawString("Student #: 20512622", 30, getHeight() / 5);
			g.drawString("UserID: zq6li", 30, 3*getHeight() / 10);
			g.setFont(new Font("Calibri", Font.PLAIN, getWidth() / 30));
			g.setColor(Color.GREEN);
			g.drawString("Use Arrow Keys / Mouse to move paddle", 30, 15*getHeight() / 30);
			g.drawString("Press Space / Left Click to release ball", 30, 18*getHeight() / 30);
			g.drawString("Press Escape to quit anytime", 30, 24*getHeight() / 30);
			g.drawString("Destroy the blocks and do not let the ball reach the bottom", 30, 21*getHeight() / 30);
			g.drawString("Press Space to play", 30, 27*getHeight() / 30);
			g.setFont(new Font("Calibri", Font.PLAIN, getWidth() / 50));
			g.drawString("Press K to cheat", 30, 49*getHeight() / 50);
			Toolkit.getDefaultToolkit().sync();
		}
	}

	class WinView extends JPanel {
		class WinKeyAdapter extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent ke) {
	        }

			@Override
	        public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
					if(levelCounter % 5 == 0) {
						model = new Model(frameRate, ballSpeed, level);
					} else if(levelCounter % 5 == 1) {
						model = new Model(frameRate, ballSpeed, level1);
					} else if(levelCounter % 5 == 2) {
						model = new Model(frameRate, ballSpeed, level2);
					} else if(levelCounter % 5 == 3) {
						model = new Model(frameRate, ballSpeed, level3);
					} else if(levelCounter % 5 == 4) {
						model = new Model(frameRate, ballSpeed, level4);
					}
					frame.setContentPane(view);
					view.requestFocus();
					frame.revalidate();
					return;
				} 
				if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
	        }
		}
		WinView() {
			setBackground(Color.BLACK);
			addKeyListener(new WinKeyAdapter());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);	
			g.setColor(Color.GREEN);
			g.setFont(new Font("Calibri", Font.PLAIN, getWidth() / 20));
			g.drawString("You've advanced to Level " + (levelCounter+1)  + "!", 30, getHeight() / 3);
			g.drawString("Press space to continue.", 30, 2*getHeight() / 3);
			Toolkit.getDefaultToolkit().sync();
		}
	}

	class View extends JPanel{

		class BreakOutKeyAdapter extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					model.left = true;
					model.right = false;
				} 
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					model.right = true;
					model.left = false;
				}
	        }

			@Override
	        public void keyReleased(KeyEvent ke) {
				if (ke.getKeyChar() == 'k') {
					levelCounter++;
					frame.setContentPane(winView);
					winView.requestFocus();
					frame.revalidate();
					model.isRunning = false;
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
					model.releaseBall();
				}
				if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					model.left = false;
				}
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					model.right = false;
				}
				if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
	        }
		}

		class BreakOutMouseListener implements MouseListener {
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				model.releaseBall();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		}

		class BreakOutMouseMotionListener implements MouseMotionListener {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(e.getX() > model.paddle.coords.x + model.paddle.width) {
					model.right = true;
					model.left = false;
				} else if(e.getX() < model.paddle.coords.x) {
					model.left = true;
					model.right = false;
				} else {
					model.right = false;
					model.left = false;
				}
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {
		    }
		}

		View() {
			setBackground(Color.BLACK);
			setFocusable(true);
			requestFocusInWindow();
			addKeyListener(new BreakOutKeyAdapter());
			addMouseListener(new BreakOutMouseListener());
			addMouseMotionListener(new BreakOutMouseMotionListener());
		}

		int normalizeX(int i) {
			return i*getWidth()/model.width;
		}

		int normalizeY(int i) {
			return i*getHeight()/model.height;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			final Random random=new Random();
			for (int i=0;i<model.blocks.size();i++) {
				g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
				if(model.blocks.get(i).active)
					g.drawRect(normalizeX(model.blocks.get(i).coords.x), normalizeY(model.blocks.get(i).coords.y), normalizeX(model.blocks.get(i).width), normalizeY(model.blocks.get(i).height));
			}
			g.setColor(Color.CYAN);
			g.drawRoundRect(normalizeX(model.paddle.coords.x), normalizeY(model.paddle.coords.y), normalizeX(model.paddle.width), normalizeY(model.paddle.height), 10, 10);

			g.setColor(Color.CYAN);
			g.drawOval(normalizeX(model.ball.coords.x), normalizeY(model.ball.coords.y), 2*normalizeX(model.ball.radius), 2*normalizeY(model.ball.radius));

			g.setColor(Color.WHITE);
			g.setFont(new Font("Calibri", Font.PLAIN, getWidth() / 50));
			g.drawString("FPS: " + (frameRate), 0, getWidth()/50);
			g.drawString("Score: " + (score), 0, 2 * getWidth()/50);
			g.drawString("Level: " + (levelCounter+1), 0, 3 * getWidth()/50);
			Toolkit.getDefaultToolkit().sync();
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				int frameRate = 60;
				int speed = 10;
				if (args.length > 0) {
					frameRate = Math.abs(Integer.parseInt(args[0]));
				}
				if (args.length > 1) {
					speed = Math.abs(Integer.parseInt(args[1]));
				}
				Breakout game = new Breakout(frameRate, speed);
			}
		});
	}
}
