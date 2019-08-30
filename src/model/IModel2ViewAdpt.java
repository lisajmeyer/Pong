package model;

/**
 * Model to view adapter gives the model limited access to the view
 */
public interface IModel2ViewAdpt {
  /**
   * Tells the view to update
   */
  void update();

  /**
   * Increases the score on the view for the relevant player
   * @param player the player number who scored
   */
  void score(int player);
}
