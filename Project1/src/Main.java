//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Main {

    public static int Hit(){
        return (int) (Math.random() * 11) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int r = 1;
        int x;
        while (r == 1){
            int dealerTotal = 0;
            int playerTotal = 0;
            boolean gameOver = false;
            int dealer = (int) (Math.random() * 20) + 2;
            int player = (int) (Math.random() * 20) + 2;
            dealerTotal += dealer;
            playerTotal += player;
            System.out.println("Dealer Total: " + dealerTotal + " Player Total: " + playerTotal);
            if (playerTotal == 21){
                gameOver = true;
                System.out.println("Player win!");
                stack.push(1);
            }
            while (playerTotal < 21 && dealerTotal < 21 && !gameOver) {
                do {
                    System.out.println("Hit (0) or Stand(1)? ");
                    x = sc.nextInt();
                } while (x != 1 && x != 0);
                if (x == 1) {
                    while (dealerTotal < 21){
                        if (dealerTotal == playerTotal) {
                            System.out.println("Dealer is a tie!");
                            gameOver = true;
                            stack.push(2);
                            break;
                        }
                        dealer = Hit();
                        System.out.println("Dealer: " + dealer);
                        dealerTotal += dealer;
                        System.out.println("Dealer Total: " + dealerTotal + " Player: " + playerTotal);
                        if (dealerTotal > 21){
                            System.out.println("Dealer busts!");
                            gameOver = true;
                            stack.push(1);
                            break;
                        }
                        else if (dealerTotal > playerTotal && dealerTotal < 21) {
                            System.out.println("Dealer wins!");
                            gameOver = true;
                            stack.push(0);
                            break;
                        }

                    }
                }
                else{
                    player = Hit();
                    System.out.println("Player: " + player);
                    playerTotal += player;
                    System.out.println("Dealer Total: " + dealerTotal + " Player: " + playerTotal);
                    if (playerTotal > 21){
                        System.out.println("Player busts!");
                        gameOver = true;
                        stack.push(0);
                        break;
                    }
                }
            }

            System.out.println("Continue (1) or Leave? ");
            r = sc.nextInt();
        }
        System.out.println(stack);

    }
}