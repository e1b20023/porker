package team6.porker.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
  int hand[];
  int score;

  public static Integer[] removeLastElement(Integer[] arr) {
    return Arrays.copyOf(arr, arr.length - 1);
  }

  public void Distribute(ArrayList<Deck> DECK) {
    Integer[] deck=DECK.toArray(new Integer[DECK.size()]);

    for (int i = 0; i < 5; i++) {
      this.hand[i] = deck[deck.length];
      deck=removeLastElement(deck);
    }
  }

  public int[] getHand() {
    return hand;
  }

  public void setHand(int[] hand) {
    this.hand = hand;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
