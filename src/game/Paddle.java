package game;

import com.sun.deploy.security.SelectableSecurityManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle implements KeyListener {

  /**
   * The height of the paddle
   */
  public static int HEIGHT = 70;
  /**
   * The width of the paddle
   */
  public static int WIDTH = 10;
  /**
   * The distance from the center of the paddle to the wall
   */
  private int DISTANCE = 30;
  /**
   * The maximum speed of the paddle
   */
  private int SPEED = 7;
  /**
   * The position of the paddle relative to its center
   */
  private Point position;
  /**
   * Speed of the paddle (in the vertical direction)
   */
  private int speed;
  /**
   * Whether the paddle is the left paddle
   */
  private boolean isLeft;
  /**
   * The component on which the paddle is drawn
   */
  private Component canvas;

  /**
   * Constructor for the paddle
   * @param canvas the component on which the paddle is drawn
   * @param isLeft whether the paddle is the left paddle
   */
  public Paddle(Component canvas, boolean isLeft) {
    this.canvas = canvas;
    canvas.addKeyListener(this);
    this.isLeft = isLeft;
    int x;
    if(isLeft)
      x = DISTANCE;
    else
      x = canvas.getWidth() - DISTANCE;
    position = new Point(x, canvas.getHeight() / 2);
    speed = 0;
  }

  /**
   * Updates the position of the paddle according to its speed.
   */
  public void update() {
    position.translate(0, speed);
    if(!isLeft)
      position.move(canvas.getWidth() - DISTANCE, position.y);
    if(position.y < (HEIGHT / 2))
      position.move(position.x, HEIGHT / 2);
    if(position.y + (HEIGHT / 2) > canvas.getHeight())
      position.move(position.x, canvas.getHeight() - (HEIGHT / 2));
  }

  /**
   * Paints the paddle to the screen
   * @param g the graphics object
   */
  public void paint(Graphics g) {
    g.setColor(Color.GREEN); // Set the color to use when drawing
    g.fillRect(position.x - (WIDTH / 2), position.y - (HEIGHT / 2), WIDTH, HEIGHT);
  }

  /**
   * Resets the position of the paddle to the vertical center of the screen
   */
  public void resetPosition() {
    position.move(position.x, canvas.getHeight() / 2);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_UP && !isLeft) {
      speed = -SPEED;
    }
    else if(e.getKeyCode() == KeyEvent.VK_DOWN && !isLeft) {
      speed = SPEED;
    }
    else if(e.getKeyCode() == KeyEvent.VK_W && isLeft) {
      speed = -SPEED;
    }
    else if(e.getKeyCode() == KeyEvent.VK_S && isLeft) {
      speed = SPEED;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_UP && !isLeft) {
      speed = 0;
    }
    else if(e.getKeyCode() == KeyEvent.VK_DOWN && !isLeft) {
      speed = 0;
    }
    else if(e.getKeyCode() == KeyEvent.VK_W && isLeft) {
      speed = 0;
    }
    else if(e.getKeyCode() == KeyEvent.VK_S && isLeft) {
      speed = 0;
    }
  }

  /**
   * @return the current position of the paddle
   */
  public Point getPosition() {
    return position;
  }
}
