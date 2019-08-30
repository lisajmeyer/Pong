package model;

import game.*;

import javax.swing.*;
import java.awt.*;

/**
 * The model of the system. This controls the functionality of
 * the application.
 */
public class Model {
  /**
   * Adapter to let the model access the view
   */
  private IModel2ViewAdpt m2vAdpt;
  /**
   * Update every 50 milliseconds
   */
  private int timeSlice = 30;
  /**
   * Create a timer
   */
  private Timer timer = new Timer(timeSlice, (e) -> m2vAdpt.update());
  /**
   * The game ball
   */
  private ABall ball = NullBall.make();
  /**
   * The left paddle used in the game.
   */
  private Paddle leftPaddle;

  /**
   * The right paddle used in the game.
   */
  private Paddle rightPaddle;

  /**
   * Initializes the model with the adapter
   * @param m2vAdpt an adapter to allow the model access to the view
   */
  public Model(IModel2ViewAdpt m2vAdpt) {
    this.m2vAdpt = m2vAdpt;
  }

  /**
   * Starts the model
   */
  public void start() {
    timer.start();
  }

  /**
   * Creates a new game ball
   * @param canvas the canvas on which a ball is drawn
   */
  public void makeBall(Component canvas) {
    ball = new Ball(canvas);
  }

  /**
   * Creates the paddles
   * @param canvas the canvas on which the paddles are drawn
   */
  public void makePaddles(Component canvas) {
    leftPaddle = new Paddle(canvas, true);
    rightPaddle = new Paddle(canvas, false);
  }

  /**
   * Updates the ball and paddle positions
   * @param g graphics object
   */
  public void update(Graphics g) {
    leftPaddle.update();
    rightPaddle.update();
    int player = ball.update(leftPaddle.getPosition(), rightPaddle.getPosition());
    leftPaddle.paint(g);
    rightPaddle.paint(g);
    if(player > 0)
      score(player);
    else
      ball.paint(g);
  }

  /**
   * Clears the ball from the screen and adds to the score when someone scores.
   * @param player the player who scored
   */
  private void score(int player) {
    ball = NullBall.make();
    m2vAdpt.score(player);
  }

  /**
   * Resets the game state.
   * Removes the ball, resets the paddles.
   */
  public void resetGame() {
    ball = NullBall.make();
    leftPaddle.resetPosition();
    rightPaddle.resetPosition();
  }
}
