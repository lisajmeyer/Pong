package game;

import java.awt.*;

/**
 * Abstract ball class
 */
public abstract class ABall {
  /**
   * Updates the location of the ball
   * @param leftPaddle the position of the left paddle
   * @param rightPaddle the position of the right paddle
   * @return 0 if nobody scored, 1 if player 1 scored, 2 if player 2 scored
   */
  public abstract int update(Point leftPaddle, Point rightPaddle);

  /**
   * Paints the ball on the screen at its internal location
   * @param g the graphic object
   */
  public abstract void paint(Graphics g);
}
