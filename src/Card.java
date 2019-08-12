public class Card {
    private int value;
    private Suit suit;
    private String name;

    public Card(Suit aSuit, int aValue)
    {
        suit = aSuit;
        value = aValue;
        this.name = String.valueOf(value);
    }

    //overloaded constructor for Picture cards
    public Card(Suit aSuit, int aValue, String aName)
    {
        suit = aSuit;
        value = aValue;
        this.name = aName;
    }

    public String readCard(Card aCard)
    {
        return (aCard.getValue() + " of " + aCard.getSuit());
    }


    public int getValue() {
        return value;
    }

    public Suit getSuit() { return suit; }

    public void setValue(int value) { this.value = value; }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
