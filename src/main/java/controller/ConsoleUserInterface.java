/*
 * The MIT License
 *
 * Copyright 2022 Szymon Wilk.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Application reads a text from the console and return array. 
 * 
 * @author Szymon Wilk
 * @since 1.0
 */

public class ConsoleUserInterface {
    /**
     * @since 1.0 defines first integer value to have a size of hand
     */
    private final int HANDSIZE = 5;
    
    /**
     * Gets a string from standard input using the scanner objet.
     * 
     * @param args command line parameters
     * @return array of cards
     * @since 1.0
     */
    public List<String> scanner(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a 5 cards \n");
        List<String> array0fCards = new ArrayList<>();
        
        for (int i = 0; i < HANDSIZE; i++) { 
            System.out.print("Enter a card " + (i + 1) + " : "); 
            array0fCards.add(scanner.nextLine());
        }
        
        return array0fCards;
    } 
}
