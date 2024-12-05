import java.util.Scanner;
import java.util.Stack;

public class Main {

    public int balance = 0;

    public Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static int Hit() {
        return (int) (Math.random() * 11) + 1;
    }

    public void Blackjack() {
        Stack<Integer> stack = new Stack<>();
        int r = 1;
        int x;
        int bet;

        while (r == 1) {
            int dealerTotal = 0;
            int playerTotal = 0;
            boolean gameOver = false;

            int dealer = (int) (Math.random() * 20) + 2;
            int player = (int) (Math.random() * 20) + 2;
            dealerTotal += dealer;
            playerTotal += player;

            System.out.println("How much would you like to bet? ");
            bet = sc.nextInt();
            System.out.println("Dealer Total: " + dealerTotal + " Player Total: " + playerTotal);

            if (playerTotal == 21) {
                gameOver = true;
                balance = balance + bet;
                System.out.println("Player wins!");
                stack.push(1);
            }

            while (playerTotal < 21 && dealerTotal < 21 && !gameOver) {
                do {
                    System.out.println("Hit (0) or Stand(1)? ");
                    x = sc.nextInt();
                } while (x != 1 && x != 0);

                if (x == 1) {
                    while (dealerTotal < 21) {
                        if (dealerTotal == 17 && playerTotal == 17) {
                            System.out.println("Dealer is a tie!");
                            gameOver = true;
                            stack.push(2);
                            break;
                        }
                        dealer = Hit();
                        System.out.println("Dealer: " + dealer);
                        dealerTotal += dealer;
                        System.out.println("Dealer Total: " + dealerTotal + " Player: " + playerTotal);
                        if (dealerTotal > 21) {
                            System.out.println("Dealer busts!");
                            gameOver = true;
                            balance = balance + bet;
                            stack.push(1);
                            break;
                        } else if (dealerTotal > playerTotal && dealerTotal < 21) {
                            System.out.println("Dealer wins!");
                            gameOver = true;
                            balance = balance - bet;
                            stack.push(0);
                            break;
                        }
                    }
                } else {
                    player = Hit();
                    System.out.println("Player: " + player);
                    playerTotal += player;
                    System.out.println("Dealer Total: " + dealerTotal + " Player: " + playerTotal);
                    if (playerTotal > 21) {
                        System.out.println("Player busts!");
                        gameOver = true;
                        balance = balance - bet;
                        stack.push(0);
                        break;
                    }
                    if (playerTotal == 21) {
                        gameOver = true;
                        System.out.println("Player wins!");
                        balance = balance + bet;
                        stack.push(1);
                    }
                }
            }

            System.out.println("Continue (1) or Leave? ");
            r = sc.nextInt();
            System.out.println(stack);
        }
    }

    public void ATM() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Your current balance is: " + balance);
        System.out.println("How much would you like to add to your balance?");
        balance = balance + sc.nextInt();
    }

    public void Menu() {
        System.out.println("Welcome to the Menu!");
        System.out.println("1. Play Blackjack");
        System.out.println("2. Go to ATM");
        System.out.println("3. Exit ");
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
                    System.out.println("Exiting...");
            }
        } while (choice != 3);
    }
}
