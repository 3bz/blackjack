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

    public static Card WithNameValue(Suit aSuit, int aValue, String aName)
    {
        Card result = new Card(aSuit, aValue);
        result.setName(aName);
        return result;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() { return suit; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
}