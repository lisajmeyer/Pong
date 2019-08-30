package game;

import java.awt.*;

/**
 * NullBall singleton that does nothing and appears as nothing.
 */
public class NullBall extends ABall{
  private static NullBall INSTANCE;

  private NullBall() {
  }

  public static NullBall make() {
    if(INSTANCE == null) {
      INSTANCE = new NullBall();
    }

    return INSTANCE;
  }

  @Override
  public int update(Point leftPaddle, Point rightPaddle) {
    return 0;
  }

  @Override
  public void paint(Graphics g) {
    //no-op
  }

}
