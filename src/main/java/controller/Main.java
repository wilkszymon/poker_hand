/*
 * The MIT License
 *
 * Copyright 2024 Szymon Wilk.
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
import core.PokerHandException;
import core.PokerHandAnalyzer;
import java.util.List;
import view.View;


/**
 * Main class of application.
 * executing the application turns on a interactive mode
 * allowing to add five card to program
 * 
 * @author Szymon Wilk
 * @since 1.0
 */
public class Main {
    
    /**
     * Main method of application.
     * @param args the command line arguments
     * @throws PokerHandException throw exception in controller
     */
    public static void main(String[] args) throws PokerHandException {
        PokerHandAnalyzer pokerHand = new PokerHandAnalyzer();
        View view = new View();
        ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();
        List<String> cards = consoleUserInterface.scanner(args);
        String result = pokerHand.recognizePokerHand(cards);
        String joinCards = String.join(", ", cards);
        view.showUserMessage(result, joinCards);
    }
}
