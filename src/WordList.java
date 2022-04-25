import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordList {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    /**
     *
     * @author mgillman
     */
        // Attribute to hold a list of words to use
        private ArrayList<String> dictionary;

        // Constructor
        // Since we are not dealing with try/catches yet we need to
        // incidate when a function can have certain exceptions
        public WordList() throws FileNotFoundException {
            // Constructor

            // Create memory for our list
            dictionary = new ArrayList<String>();

            Scanner sc = new Scanner(new File("words.txt"));

            int maxlength = 0;
            int count = 0;
            // Scan in the words from our file until we reach the end
            while (sc.hasNextLine()) {
                dictionary.add(sc.nextLine());
//
//
//
//            if (dictionary.get(count).length() > maxlength) {
//                maxlength = dictionary.get(count).length();
//                System.out.println("Word: " + dictionary.get(count));
//            }
//            count++;
            }

            //     System.out.println(maxlength);
            sc.close();

        }

        // Use this method to get a random word to use for our game
        public String getRandomWord() {

            Random r = new Random();
            int index = r.nextInt(dictionary.size());

            return dictionary.get(index);
        }
    }

