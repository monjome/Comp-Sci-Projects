public class Card {

    public String suit;
    public int value;


    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        if (value == 11){
            return "Ace of " + suit;
        }
        else{
            return value + " of " + suit;
        }
    }
}
