/**
 * Blackjack program simulates a one-round game
 * of the Casino game 'Blackjack', where it is
 * simply the player versus the dealer, with one
 * deck of cards.
 *
 * This class acts as the program initialiser and
 * main method.
 *
 * @author ryan.ebsworth
 *
*/

public class Blackjack {

    public static void main(String[] args) {
        Blackjack myBlackjack = new Blackjack();
        myBlackjack.initialiseRunGame();
    }

    public void initialiseRunGame() {
        Deck myDeck = new Deck();
        Player myPlayer = new Player("Player 1");
        Player myDealer = new Player("Dealer");
        Input myInput = new TerminalInput();

        Game myGame = new Game(myPlayer, myDealer, myDeck, myInput);
        myGame.start();
    }
}
