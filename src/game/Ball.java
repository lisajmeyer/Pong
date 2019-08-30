package game;

import java.awt.*;
import java.util.Random;

/**
 * The ball used in the game
 */
public class Ball extends ABall {
  /**
   * Current position of the ball
   */
  private Point position;
  /**
   * Current velocity of the ball
   */
  private Point velocity;
  /**
   * The radius of the ball
   */
  private int RADIUS = 10;
  /**
   * The component on which the ball is drawn
   */
  private Component canvas;

  /**
   * Random number generator
   */
  private Random r = new Random();

  /**
   * Constructor for a game ball.
   * The ball will appear in the center moving
   * @param canvas the component on which the ball is being drawn
   */
  public Ball(Component canvas) {
    this.canvas = canvas;
    position = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
    int xspeed = r.nextInt(4) + 6;
    int yspeed = r.nextInt(4) + 6;
    if(r.nextDouble() < 0.5)
      xspeed *= -1;
    if(r.nextDouble() < 0.5)
      yspeed *= -1;
    velocity = new Point(xspeed, yspeed);
  }

  /**
   * Updates the painting of the ball after a timetick by checking its movement and bouncing,
   * then painting the new ball
   * @param leftPaddle the position of the left paddle
   * @param rightPaddle the position of the right paddle
   * @return 0 if no one scored, 1 if player 1 scored, and 2 if player 2 scored
   */
  public int update(Point leftPaddle, Point rightPaddle) {
    move();
    bounceWall();
    collide(leftPaddle, rightPaddle);
    return scoreWall();
  }

  /**
   * Moves the ball according to its velocity and position
   */
  private void move() {
    position.translate(velocity.x, velocity.y);
  }

  /**
   * Checks if the ball goes off the left or right side of the panel
   * @return the player number that scored, or 0 if nobody scored
   */
  private int scoreWall() {
    int left = 0;
    int right = canvas.getWidth();

    // left wall
    if (position.x - RADIUS <= left) {
      return 2;
    }
    // right wall
    if (position.x + RADIUS >= right) {
      return 1;
    }
    return 0;
  }

  /**
   * Checks if the ball bounced off the ceiling or floor and updates velocity/pos accordingly.
   */
  private void bounceWall() {
    int ceiling = 0;
    int floor = canvas.getHeight();

    // ceiling
    if (position.y - RADIUS <= ceiling) {
      velocity.move(velocity.x, -velocity.y);
      position.move(position.x, 2 * RADIUS - position.y);
    }
    // floor
    if (position.y + RADIUS >= floor) {
      velocity.move(velocity.x, -velocity.y);
      position.move(position.x, 2 * floor - (2 * RADIUS) - position.y);
    }
  }

  /**
   * Checks for a collision between the ball and paddles, and moves the ball appropriately
   * @param left the position of the left paddle
   * @param right the position of the right paddle
   */
  private void collide(Point left, Point right) {
    int x = position.x;
    int y = position.y;
    if(x - RADIUS <= left.x + (Paddle.WIDTH / 2)) {
      if(y <= left.y + (Paddle.HEIGHT / 2) && y >= left.y - (Paddle.HEIGHT / 2)) {
        System.out.println("Left");
        velocity.move(-velocity.x, velocity.y);
        position.move(2 * (left.x + (Paddle.WIDTH / 2) - x + RADIUS) + x, position.y);
      }
    }
    if(x + RADIUS >= right.x - (Paddle.WIDTH / 2)) {
      if(y <= right.y + (Paddle.HEIGHT / 2) && y >= right.y - (Paddle.HEIGHT / 2)) {
        System.out.println("Right");
        velocity.move(-velocity.x, velocity.y);
        position.move(2 * (right.x - (Paddle.WIDTH / 2)) - (2 * RADIUS) - position.x, position.y);
      }
    }
  }

  /**
   * Paints the ball at the appropriate location.
   * @param g the graphic object
   */
  public void paint(Graphics g) {
    int x = position.x;
    int y = position.y;
    g.setColor(Color.RED); // Set the color to use when drawing
    g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
  }
}
