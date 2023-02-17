public class HangmanGame {

    private String word;
    private int incorrect = 0;
    private String wordFill = "";
    private String used = "";

    private String man0 = "  +---+ " + "\n  |   | " + "\n      | " + "\n      | " + "\n      | " + "\n      | " + "\n=========";
    private String man1 = "  +---+ " + "\n  |   | " + "\n  O   | " + "\n      | " + "\n      | " + "\n      | " + "\n=========";
    private String man2 = "  +---+ " + "\n  |   | " + "\n  O   | " + "\n  |   | " + "\n      | " + "\n      | " + "\n=========";
    private String man3 = "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|   | " + "\n      | " + "\n      | " + "\n=========";
    private String man4 = "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|\\  | " + "\n      | " + "\n      | " + "\n=========";
    private String man5 = "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|\\  | " + "\n /    | " + "\n      | " + "\n=========";
    private String man6 = "  +---+ " + "\n  |   | " + "\n  O   | " + "\n /|\\  | " + "\n / \\  | " + "\n      | " + "\n=========";

    public HangmanGame(String s) {
        word = s.toLowerCase().replace(".", "").replace(",", "").replace("?", "").replace("!", "").trim();
        wordFill = createWordFill(word);
        incorrect = 0;
        used = "";
    }

    public String createWordFill(String s) {
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == ' ')
                wordFill += " ";
            else
                wordFill += "_";
        }
        return wordFill;
    }

    public int getNumGuesses() {
        return incorrect;
    }

    public String getHangingMan() {
        switch (incorrect) {
            case 0:
                return man0;
            case 1:
                return man1;
            case 2:
                return man2;
            case 3:
                return man3;
            case 4:
                return man4;
            case 5:
                return man5;
            case 6:
                return man6;
        }
        return null;
    }

    public boolean isGuessed() {
        return wordFill.equals(word);
    }

    public void display() {
        System.out.println(getHangingMan());
        System.out.println(wordFill);
        System.out.println("Guesses: "+used);
    }

    public void addGuess(String g) {
        if(!checkGuess(g)){
            if(used.length() == 0)
                used += g;
            else
                used += ", " + g;
        }
    }

    public void checkWord(String g) {
        boolean guessAdded = false;
        if(g.equals("***")) {
            wordFill = word;
            incorrect = 0;
        }else {
            int count = 0;
            int a = 0;
            // Checks full word/phrase guesses.
            if(g.length() > 1){
                if(g.equals(word)) {
                    wordFill = g;
                }else if (word.contains(g)) {
                    int firstIndex = word.indexOf(g);
                    if ((firstIndex == 0) || (word.charAt(firstIndex-1) == ' ')) {
                        int lastIndex = firstIndex + g.length();
                        if ((lastIndex == word.length()) || (word.charAt(lastIndex) == ' ')) {
                            if (!(lastIndex == word.length()))
                                wordFill = wordFill.substring(0, firstIndex) + g + wordFill.substring(lastIndex);
                            else
                                wordFill = wordFill.substring(0, firstIndex) + g;
                        }else {
                            if (!checkGuess(g)) {
                                incorrect++;
                            }
                            addGuess(g);
                            guessAdded=true;
                        }
                    }else {
                        if (!checkGuess(g)) {
                            incorrect++;
                        }
                        addGuess(g);
                        guessAdded=true;
                    }
                }else {
                    if (!checkGuess(g)) {
                        incorrect++;
                    }
                    addGuess(g);
                    guessAdded=true;
                }
            // Checks single character guesses.
            } else if(g.equals("")){
                incorrect++;
                guessAdded=true;

            }else{
                if(word.contains(g)){
                    count = 0;
                    a = 0;
                    while (a != -1) {
                        a = word.indexOf(g, a);
                        if (a != -1) {
                            count++;
                            a += g.length();
                        }
                    }
                }else {
                    if (!checkGuess(g)) {
                        incorrect++;
                    }
                    addGuess(g);
                    guessAdded=true;
                }
            }
            String oldWordFill = wordFill;
            modifyWordFill(g, count);
            if (g.length() == 1) {
                if (wordFill.equals(oldWordFill)) {
                    if (!checkGuess(g)) {
                        incorrect++;
                    }
                    addGuess(g);
                    guessAdded=true;
                }
            }
            if (!guessAdded) {
                addGuess(g);
            }
        }
    }

    public String getWord() {
        return word;
    }

    public boolean checkGuess(String g) {
        String usedGuesses = " "+used+",";
        String spacedGuess = " " + g + ",";
        return usedGuesses.contains(spacedGuess);
    }

    // Modifies wordFill string to contain character if guess is correct
    public void modifyWordFill(String g, int count) {
        int index = word.indexOf(g);
        for(int i=0; i<count; i++){
            wordFill = wordFill.substring(0, index) + g + wordFill.substring(index+1);
            index = word.indexOf(g, index+1);
        }
    }
}