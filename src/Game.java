import java.util.Scanner;

public class Game {
    static Deck myDeck = new Deck();
    static Player myPlayer = new Player();
    static Player myDealer = new Player();
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        init();
    }
    public static void init()
    {
        myDeck.shuffle();

        for (int i = 0; i < 2; i++)
            myPlayer.hit(myDeck.dealCard());
        for (int i = 0; i < 2; i++)
            myDealer.hit(myDeck.dealCard());
        playerTurn();
        if (myPlayer.isAlive())
            dealerTurn();
        else {
            System.out.println("Dealer wins!");
            System.exit(0);
        }
        if (myDealer.isAlive())
        {
            if ( Integer.parseInt(myDealer.readHandTotal()) >= Integer.parseInt(myPlayer.readHandTotal()) )
                System.out.println("Dealer wins!");
            else
                System.out.println("You beat the dealer!");
        }
        else
            System.out.println("You beat the dealer!");
    }

    public static void playerTurn()
    {
        while (! (myPlayer.hasSat()) )
        {
            if (myPlayer.checkBlackjack())
                break;
            int playerInput;
            System.out.println("You are currently at " + myPlayer.readHandTotal());
            System.out.println("with the hand [" + myPlayer.readCardsInHand() + "]\n");

            if (myPlayer.readHandTotal().equals("Bust!") ) {
                myPlayer.setIsAlive(false);
                break;
            }

            System.out.print("Hit or Stay? (Hit = 1, Stay = 0): ");
            playerInput = scn.nextInt();

            switch (playerInput) {
                case (1): //hit
                    myPlayer.hit(myDeck.dealCard());
                    System.out.println("You draw " + myPlayer.readLastCard() + "\n");
                    break;
                case (0): //stay
                    System.out.println("\n");
                    myPlayer.stay();
                    break;
            }
        }
    }

    public static void dealerTurn() {
        //check blackjack
        while (! (myDealer.hasSat()) ) {
            System.out.println("Dealer is at " + myDealer.readHandTotal());
            System.out.println("with the hand " + myDealer.readCardsInHand() + "\n");
            try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}
            if (Integer.parseInt(myDealer.readHandTotal()) < 17)
            {
                myDealer.hit(myDeck.dealCard());
                System.out.println("Dealer draws: " + myDealer.readLastCard() + "\n");
                try { Thread.sleep(1800); } catch (InterruptedException e) { Thread.currentThread().interrupt();}


                if (myDealer.readHandTotal().equals("Bust!")) {
                    myDealer.setIsAlive(false);
                    break;
                }
            }
            else
                myDealer.stay();
        }
    }


//    public void shuffleTest()
//    {
//        Card demo;
//        for (int i = 0; i < 52; i++) {
//            myDeck.shuffle();
//            demo = myDeck.dealCard();
//            System.out.println(demo.getName() + " of " + demo.getSuit());
//        }
//    }
}
