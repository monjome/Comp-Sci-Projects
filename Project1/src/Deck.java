import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;





public class Deck {
    private Queue<Card> deck;

    public Deck() {
        deck = new LinkedList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (String suit : suits) {
            for (int value : values) {
                deck.add(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        List<Card> temp = new LinkedList<>(deck);
        Collections.shuffle(temp);
        deck = new LinkedList<>(temp);
    }

    public Card draw() {
        return deck.poll();
    }

    public int size(){
        return deck.size();
    }
}
