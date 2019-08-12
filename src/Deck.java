import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> myDeck = new ArrayList<Card>();

    public Deck()
    {
        for(Suit s: Suit.values())
        {
            for ( int i = 2; i <11; i++)
                myDeck.add(new Card(s, i));
        }

        for(Picture p: Picture.values())
        {
            for(Suit s: Suit.values())
                myDeck.add(new Card(s, p.getValue()));
        }

    }

    public Card dealCard()
    {
        Card result = myDeck.get(myDeck.size()-1);
        myDeck.remove(result);
        return result;
    }

    public void shuffle()
    {
        for (int i = 0; i < myDeck.size(); i++)
        {
            Card current = myDeck.get(i);
            myDeck.set((int)Math.random(), current);
        }
    }

    public ArrayList<Card> getMyDeck() {
        return myDeck;
    }
}
