package team6.porker.model;

import java.util.ArrayList;

public class Player {
  ArrayList<Integer> hand = new ArrayList<Integer>();
  int score;

  public Player() {
  }

  public void Distribute(ArrayList<Deck> deck) {

    for (int i = 0; i < 5; i++) {
      int tmp = deck.get(i).getDeckid();
      this.hand.add(tmp);
      // System.out.println(hand.get(i));
    }
  }

  public ArrayList<Integer> getHand() {
    return hand;
  }

  public void setHand(ArrayList<Integer> hand) {
    this.hand = hand;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
