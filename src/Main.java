import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Character> guesses = new ArrayList<>();
        // I got the wordlist from here https://github.com/Tom25/Hangman/blob/master/wordlist.txt
        WordList w = new WordList();
        String word=w.getRandomWord();
        String name = get_name();
        int miss_count = 0;
        while(true){
            printHangman(miss_count);
            System.out.println(render_game(word,guesses));
            display_guesses(guesses);
            char guess = get_guess(guesses);
            guesses.add(guess);
            if(!process_guess(guess,word))
                miss_count++;
            System.out.println("You have "+(7-miss_count)+" guesses left.");
            //If statements Check if the game is over.
            if(miss_count==7){
                printHangman(miss_count);
                System.out.println("You Lost. The word is "+ word);
                if (high_score(Path.of("records.txt"), miss_count)) {
                    System.out.println("High Score");
                } else {
                    System.out.println("Not High Score");
                }
                record_game(name,word,miss_count,guesses);

                break;
            }
            if(!render_game(word,guesses).contains("_")){
                System.out.println("You Won.The word is " + word);
                if (high_score(Path.of("records.txt"), miss_count)) {
                    System.out.println("High Score");
                } else {
                    System.out.println("Not High Score");
                }
                record_game(name,word,miss_count,guesses);
                break;
            }
        }
    }
    public static String get_name(){
        Scanner s=new Scanner(System.in);
        System.out.println("Welcome to hangman!");
        System.out.println("Please Enter Your Name. ");
        return s.nextLine();
    }
    public static char get_guess(ArrayList<Character> guesses){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter a letter lowercase.");
        char result = s.next().charAt(0);
        if(result>='a'&&result<='z'){
            if(guesses.contains(result)){
                System.out.println("You Entered "+result);
                System.out.println("You already made this guess. Try again");
                return get_guess(guesses);
            }
        System.out.println("You Entered "+result);
        return result;}
        else{
            System.out.println("Enter a valid input");
            return get_guess(guesses);
        }
    }
    public static void display_guesses(ArrayList<Character> guesses){
        System.out.println("Guesses Made:");
        System.out.println(guesses);
    }
    //Checks if the users guess is in the word or not.
    public static boolean process_guess(char guess,String word){
           if(word.indexOf(guess)!=-1){
               System.out.println("Your Guess is Correct!");
                return true;
            }else{
               System.out.println("Your Guess is Wrong.");
                return false;}
        }
        //Reads a file according to the miss count and prints  the hangman properly on the screen
    public static void printHangman(int miss_count) throws IOException {
        Files.readAllLines(Path.of("hangman"+miss_count+".txt")).stream().forEach(System.out::println);
}
    public static String render_game(String word,ArrayList<Character> guesses){

        return Arrays.stream(word.split("")).map(e->guesses.contains(e.charAt(0))?e:"_").reduce("",(String a, String b)->a+b);

    }
    //records the game in the following format
    //name,word,miss_count,guesses
public static void record_game(String name,String word,int miss_count,ArrayList<Character> guesses) throws IOException {
        File my_file = new File("records.txt");
        Writer output = new BufferedWriter(new FileWriter(my_file, true));
        output.append(name+"|"+miss_count+"|"+word+"|"+guesses+"\n");
        output.close();
}
//Checks if the player got the best score
public static boolean high_score(Path path,int miss_count) throws IOException {
        Scanner s = new Scanner(new File(String.valueOf(path)));
        if(!s.hasNextLine())
            return true;
        s.close();
  ArrayList<String> a = Files.readAllLines(path).stream().map(e -> e.split("\\|")[1]).sorted().collect(Collectors.toCollection(ArrayList::new));
    return !(Integer.parseInt(a.get(0))<miss_count);
}}