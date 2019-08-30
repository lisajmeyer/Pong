package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
  /**
   * Adapter to let the view access the model
   */
  private IView2ModelAdpt v2mAdpt;

  private JPanel appPnl;
  private JPanel canvasPnl;
  private JLabel score1Lbl;
  private JLabel score2Lbl;
  private JLabel countDownLbl;
  private JButton startBtn;
  private JButton endBtn;

  private int score1 = 0;
  private int score2 = 0;
  private int COUNTDOWN = 3;
  private int count = 0;
  private Timer t;

  /**
   * Initializes the view
   * @param v2mAdpt an adapter to let the view access the model
   */
  public View(IView2ModelAdpt v2mAdpt) {
    this.v2mAdpt = v2mAdpt;
    initGUI();
  }

  /**
   * Starts the view by making it visible
   */
  public void start() {
    setVisible(true);
    v2mAdpt.makePaddles(canvasPnl);
  }

  /**
   * Initializes the view frame and components
   */
  private void initGUI() {
    t =  new Timer(800, (e) -> countdown());
    setTitle("Pong");
    setContentPane(appPnl);
    canvasPnl.setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100,100, 1000,800);
    startBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startBtn.setEnabled(false);
        endBtn.setEnabled(true);
        count = COUNTDOWN;
        countDownLbl.setText(Integer.toString(count));
        t.start();
      }
    });
    endBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        v2mAdpt.resetGame();
        t.stop();
        countDownLbl.setText("");
        score1 = 0;
        score2 = 0;
        score1Lbl.setText(Integer.toString(score1));
        score2Lbl.setText(Integer.toString(score2));
        startBtn.setEnabled(true);
        endBtn.setEnabled(false);
      }
    });
  }

  public void update() {
    repaint();
  }
  public void score(int player) {
    if(player == 1) {
      score1++;
      score1Lbl.setText(Integer.toString(score1));
    } else {
      score2 = score2 + 1;
      score2Lbl.setText(Integer.toString(score2));
    }
    count = COUNTDOWN;
    countDownLbl.setText(Integer.toString(count));
    t.start();
  }

  private void countdown() {
    if(count == 1) {
      t.stop();
      countDownLbl.setText("");
      v2mAdpt.makeBall(canvasPnl);
    } else {
      count--;
      countDownLbl.setText(Integer.toString(count));
    }
  }

  private void createUIComponents() {
    canvasPnl = new JPanel() {
      /**
       * Overridden paintComponent method to paint a shape in the panel.
       * @param g The Graphics object to paint on.
       **/
      public void paintComponent(Graphics g) {
        super.paintComponent(g); // Do everything normally done first, e.g. clear the screen.
        v2mAdpt.paint(g);
      }
    };
  }
}
