public class Game {
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        Card demo;

        for (int i = 0; i < 52; i++) {
            myDeck.shuffle();
            demo = myDeck.dealCard();
            System.out.println(demo.getName() + " of " + demo.getSuit());
        }
//        for (Card c : myDeck.getMyDeck())
//        {
//            System.out.println(c.getName() + " of " + c.getSuit());
//        }

    }
}
