public class Game {
    public static void main(String[] args) {
        Deck myDeck = new Deck();
    //myDeck.shuffle();
    //Card demo = myDeck.dealCard();

//    for (int i = 0; i < 20; i++)
//        myDeck.dealCard();

        //System.out.println(demo.getValue() + " of " + demo.getSuit());
        for (Card c : myDeck.getMyDeck())
        {
            System.out.println(c.getValue() + " of " + c.getSuit());
        }

    }
}
