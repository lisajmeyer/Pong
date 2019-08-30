package controller;

import java.awt.*;
import view.*;
import model.*;

public class Controller {

  private View view;

  private Model model;

  /**
   * Instantiates the system
   */
  public Controller() {
    view = new View(new IView2ModelAdpt() {
      @Override
      public void paint(Graphics g) {
        model.update(g);
      }

      @Override
      public void makeBall(Component canvas) {
        model.makeBall(canvas);
      }

      @Override
      public void makePaddles(Component canvas) {
        model.makePaddles(canvas);
      }

      @Override
      public void resetGame() {
        model.resetGame();
      }
    });
    model = new Model(new IModel2ViewAdpt() {
      @Override
      public void update() {
        view.update();
      }

      @Override
      public void score(int player) {
        view.score(player);
      }
    });
  }

  /**
   * Start the system
   */
  public void start() {
    model.start();
    view.start();
  }

  /**
   * Launch the application.
   * @param args Arguments given by the system or command line.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
      public void run() {
        try {
          Controller controller = new Controller(); // instantiate the system
          controller.start(); // start the system
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
