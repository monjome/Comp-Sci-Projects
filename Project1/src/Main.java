import java.util.Scanner;

public class Main {

    public int balance = 0;

    public Scanner sc;

    public winLog gameLog;

    public Main() {
        sc = new Scanner(System.in);
        gameLog = new winLog();
    }

    public static Card Hit(Deck deck) {
        return deck.draw();
    }


    public void Blackjack() {
        int decision;
        int bet;
        int repeat;

        if (balance <= 0){
            System.out.println("Invalid balance! Returning to lobby...");
            return;
        }
        else {
            repeat = 1;
        }

        while (repeat == 1) {
            Deck deck = new Deck();
            deck.shuffle();
            int dealerTotal = 0;
            int playerTotal = 0;
            Card dealer, player;
            boolean gameOver = false;

            do {
                System.out.println("Current Balance: " + balance);
                System.out.println("How much would you like to bet? ");
                bet = sc.nextInt();
                if (bet < balance || bet <= 0){
                    System.out.println("Invalid Bet");
                }
            } while (bet < balance);

            for (int i = 0; i < 2; i++) {
                dealer = deck.draw();
                System.out.println("Dealer drew: " + dealer.toString());
                player = deck.draw();
                System.out.println("You drew: " + player.toString());
                dealerTotal += dealer.value;
                playerTotal += player.value;

            }
            System.out.println("Dealer Total: " + dealerTotal + " Player Total: " + playerTotal);

            if (playerTotal == 21) {
                gameOver = true;
                balance = balance + bet;
                System.out.println("Player wins!");
                gameLog.add(bet, "Player 1");
                break;
            }

            while (playerTotal < 21 && dealerTotal < 21 && !gameOver) {
                do {
                    System.out.println("Hit (0) or Stand(1)? ");
                    decision = sc.nextInt();
                } while (decision != 1 && decision != 0);

                if (decision == 1) {
                    while (dealerTotal < 21) {
                        if (dealerTotal == 17 && playerTotal == 17) {
                            System.out.println("Dealer is a tie!");
                            gameOver = true;
                            gameLog.add(0, "Player 1");
                            break;
                        }
                        dealer = Hit(deck);
                        System.out.println("Dealer drew: " + dealer.toString());
                        dealerTotal += dealer.value;
                        System.out.println("Dealer Total: " + dealerTotal + " Player: " + playerTotal);
                        if (dealerTotal > 21) {
                            System.out.println("Dealer busts!");
                            gameOver = true;
                            balance = balance + bet;
                            gameLog.add(bet, "Player 1");
                            break;
                        } else if (dealerTotal == 21){
                            System.out.println("Dealer wins!");
                            gameOver = true;
                            balance = balance - bet;
                            gameLog.add(bet * -1, "Player 1");
                            break;
                        } else if (dealerTotal > playerTotal && dealerTotal < 21) {
                            System.out.println("Dealer wins!");
                            gameOver = true;
                            balance = balance - bet;
                            gameLog.add(bet * -1, "Player 1");
                            break;
                        }
                    }
                } else {
                    player = Hit(deck);
                    System.out.println("You drew: " + player.toString());
                    playerTotal += player.value;
                    System.out.println("Dealer Total: " + dealerTotal + " Player: " + playerTotal);
                    if (playerTotal > 21) {
                        System.out.println("Player busts!");
                        gameOver = true;
                        balance = balance - bet;
                        gameLog.add(bet * -1, "Player 1");
                        break;
                    }
                    if (playerTotal == 21) {
                        gameOver = true;
                        System.out.println("Player wins!");
                        balance = balance + bet;
                        gameLog.add(bet, "Player 1");
                    }
                }
            }

            System.out.println("Continue (1) or Leave? ");
            repeat = sc.nextInt();
        }
    }

    public void ATM() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Your current balance is: " + balance);
        System.out.println("How much would you like to add to your balance?");
        balance = balance + sc.nextInt();
    }

    public void printLog(){
        gameLog.printLog();
    }

    public void Menu() {
        System.out.println("Welcome to the Menu!");
        System.out.println("1. Play Blackjack");
        System.out.println("2. Go to ATM");
        System.out.println("3. Print WinLog");
        System.out.println("4. Exit ");
        System.out.println("Choose an option: ");
    }

    public static void main(String[] args) {
        Main game = new Main();

        int choice;
        do {
            game.Menu();
            choice = game.sc.nextInt();

            switch (choice) {
                case 1:
                    game.Blackjack();
                    break;
                case 2:
                    game.ATM();
                    break;
                case 3:
                    game.printLog();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while (choice != 4);
    }
}
