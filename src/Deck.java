import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> myDeck = new ArrayList<Card>();

    public Deck()
    {
        for(Suit suitsInDeck : Suit.values())
        {
            for ( int numericalInDeck = 2; numericalInDeck <11; numericalInDeck++)
                myDeck.add(new Card( suitsInDeck, numericalInDeck) );
        }

        for(Picture p: Picture.values())
        {
            for(Suit s: Suit.values())
                myDeck.add(new Card(s, p.getValue(), p.name()));
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
            Card selectedCard = myDeck.remove(i);
            myDeck.add((int) (Math.random() * myDeck.size()), selectedCard);
        }
    }

    public ArrayList<Card> getMyDeck() {
        return myDeck;
    }
}
