public class Blackjack {

    public static void main(String[] args) {
        Blackjack myBlackjack = new Blackjack();
        myBlackjack.initialiseRunGame();
    }

    public void initialiseRunGame() {
        Deck myDeck = new Deck();
        Player myPlayer = new Player("Player 1");
        Player myDealer = new Player("Dealer");
        myDeck.shuffle();

        int startingHandSize = 2;
        for (int i = 0; i < startingHandSize; i++)
            myPlayer.hit(myDeck.dealCard());
        for (int i = 0; i < startingHandSize; i++)
            myDealer.hit(myDeck.dealCard());

        Game myGame = new Game(myPlayer, myDealer, myDeck);
        myGame.runGame();
    }
}
