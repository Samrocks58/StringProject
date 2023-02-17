import java.util.Scanner;

public class HangmanClient {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter word: ");
        String w = reader.nextLine();
        HangmanGame h1 = new HangmanGame(w);
        int numGuesses = h1.getNumGuesses();
        for (int i = 0; i<10 ; i++) {
            System.out.println("\n");
        }

        while (!h1.isGuessed()) {
            h1.display();
            System.out.print("Enter guess: ");
            String guess = reader.nextLine();
            h1.checkWord(guess);
            numGuesses = h1.getNumGuesses();
            System.out.println("\n");

            // Prints the man plus the correct answer.
            if(numGuesses == 6){
                System.out.println(h1.getHangingMan());
                System.out.println("The word was " + h1.getWord());
                break;
            }
        }

        // Prints that the player is correct and the original input for the word.
        if(numGuesses < 6){
            System.out.println("Correct!");
            System.out.println(w);
        }
    }

}