package team6.porker.model;

public class Deck {
  int deck[];

  public Deck() {
    for (int i = 0; i < 52; i++) {
      deck[i] = i + 1;
    }
  }

  public int[] getDeck() {
    return deck;
  }

  public void setDeck(int[] deck) {
    this.deck = deck;
  }

}
