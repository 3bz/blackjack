public class Card {
    private int value;
    private Suit suit;

    public Card(Suit aSuit, int aValue)
    {
        suit = aSuit;
        value = aValue;
    }


    public int getValue() {
        return value;
    }

    public Suit getSuit() { return suit; }

    public void setValue(int value) { this.value = value; }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }


}
