public class Card {
    private int value;
    private Suit suit;
    private String name;

    public Card(Suit aSuit, int aValue)
    {
        suit = aSuit;
        value = aValue;
        name = String.valueOf(value);
    }

    //overloaded constructor for Picture cards
    public Card(Suit aSuit, int aValue, String aName)
    {
        suit = aSuit;
        value = aValue;
        name = aName;
    }


    public int getValue() {
        return value;
    }

    public Suit getSuit() { return suit; }

    public String getName() { return name; }
}
