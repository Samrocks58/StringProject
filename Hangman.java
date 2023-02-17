// Sam Pearce & Ryan Greenwood
// January 26th, 2023
// Hanging men

import java.util.Scanner;

public class Hangman{

    public static void main(String[] arg){

        //   +---+
        //   |   |
        //   O   |
        //  /|\  |
        //  / \  |
        //       |
        // =========

       String[] man = {"  +---+ " + "\n  |   | " + "\n      | " + "\n      | " + "\n      | " + "\n      | " + "\n=========",
                        "  +---+ " + "\n  |   | " + "\n  O   | " + "\n      | " + "\n      | " + "\n      | " + "\n=========",
                        "  +---+ " + "\n  |   | " + "\n  O   | " + "\n  |   | " + "\n      | " + "\n      | " + "\n=========",
                        "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|   | " + "\n      | " + "\n      | " + "\n=========",
                        "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|\\  | " + "\n      | " + "\n      | " + "\n=========",
                        "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|\\  | " + "\n /    | " + "\n      | " + "\n=========",
                        "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|\\  | " + "\n / \\  | " + "\n      | " + "\n========="};

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter word: ");
        String w = reader.nextLine();
        String word = w.toLowerCase().replace(".", "").replace(",", "").replace("?", "").replace("!", "").replace("|", "").trim();
        String wordFill = "";

        // Makes the blank space to let the player know how long the word is
        // and fills in as they get characters correct.
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i) == ' ')
                wordFill += " ";
            else
                wordFill += "_";
        }

        String used = "";
        String g = "";
        int incorrect = 0;

        for(int i=0; i<15; i++)
            System.out.println("\n");

        while(!wordFill.equals(word)){

            System.out.println(man[incorrect]);
            System.out.println(wordFill);
            System.out.println("Guesses: "+used);

            Scanner r = new Scanner(System.in);
            System.out.print("Enter guess: ");
            g = r.nextLine();

            // Adds guess to a list of previous guesses to help the player
            // remember what they already guessed.
            if(!used.contains(g)){
                    if(used.length() == 0)
                        used += g;
                    else
                        used += ", " + g;
                }

            // Ends the loop, quitting the game.
            if(g.equals("***"))
                break;
            // Checks full word/phrase guesses.
            if(g.length() > 1){
                if(g.equals(word))
                    wordFill = g;
                else {
                    String used2 = " " + used + ",";
                    System.out.println(used2);
                    if (!used2.contains(" "+g+",")) {
                        incorrect++;
                    }
                }

            // Checks single character guesses.
            } else {
                if(word.contains(g)){
                    int count = 0;
                    int a = 0;
                    while (a != -1) {
                        a = word.indexOf(g, a);
                        if (a != -1) {
                            count++;
                            a += g.length();
                        }
                    }

                    // Modifies wordFill string to contain character if guess is correct
                    int index = word.indexOf(g);
                    for(int i=0; i<count; i++){
                        wordFill = wordFill.substring(0, index) + g + wordFill.substring(index+1);
                        index = word.indexOf(g, index+1);
                    }

                } else {
                    String used2 = " " + used + ",";
                    System.out.println(used2);
                    if (!used2.contains(" "+g+","))
                        {incorrect++;}
                }
            }

            System.out.println("\n");

            // Prints the man plus the correct answer.
            if(incorrect == 6){
                System.out.println(man[6]);
                System.out.println("The word was " + w);
                break;
            }


        }

        // Prints that the player is correct and the original input for the word.
        if(incorrect < 6){
            System.out.println("Correct!");
            System.out.println(w);
        }


    }

}