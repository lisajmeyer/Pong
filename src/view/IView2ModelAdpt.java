package view;

import java.awt.*;

public interface IView2ModelAdpt {
  /**
   * Method so the model can paint to the view canvas
   * @param g the Graphics
   */
  void paint(Graphics g);

  /**
   * Adds a new game ball to the screen (only one at a time)
   * @param canvas the component on which the ball is to be drawn
   */
  void makeBall(Component canvas);

  /**
   * Adds the paddles to the screen.
   * @param canvas the component on which the paddles are to be drawn
   */
  void makePaddles(Component canvas);

  /**
   * Clears the game ball and resets the paddles.
   */
  void resetGame();
}
