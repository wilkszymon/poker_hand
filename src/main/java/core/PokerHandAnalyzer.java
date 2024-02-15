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
package core;

/**
 * A class providing main program features
 *
 * @author Szymon Wilk
 * @since 1.0
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class providing main program features
 *
 * @author Szymon
 * @since 1.0
 */
public class PokerHandAnalyzer {

    final String faces = "AKQJT98765432";
    final String suits = "HDSC";
    List<String> results = new ArrayList<>();

    /**
     * Method that handles poker hand recognition and returns a value of
     * category
     *
     * @param array0fCards array with cards
     * @throws core.PokerHandException when hand length is more than five, when
     * is duplicates, non-existing face and non-existing suits
     * @since 1.0
     * @return return a value for category
     */
    public String recognizePokerHand(List<String> array0fCards) throws PokerHandException {
        String value = analyzeHand(array0fCards).value;
        results.add(value);
        return value;
    }

    /**
     * The method of returning the results of a poker hand that is used in the
     * servlet
     *
     * @since 4.0
     * @return return a result of hand
     */
    public String results() {
        return results.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    /**
     * @param hand array of cards
     * @return Category
     * @throws core.PokerHandException when non-existing face and non-existing
     * suits
     * @since 1.0
     */
    private Category analyzeHand(final List<String> hand) throws PokerHandException {
        if (hand == null || hand.isEmpty()) {
            throw new PokerHandException("invalid hand: hand is null");
        }

        if (hand.size() != 5) {
            throw new PokerHandException("invalid hand: too few arguments");
        } else if (new HashSet<>(hand).size() != hand.size()) {
            throw new PokerHandException("invalid hand: duplicates");
        }

        for (String card : hand) {
            if (card.equals(card.toLowerCase())) {
                throw new PokerHandException("invalid hand: you gave a cards is lowercase");
            } else if (card.length() != 2) {
                throw new PokerHandException("invalid hand: too many characters in card: " + card + " you can gave only two characters");
            }
        }

        List<Integer> faceCount = new ArrayList<>(Arrays.asList(new Integer[faces.length()]));

        long straight = 0, flush = 0;
        for (String card : hand) {
            int face = faces.indexOf(card.charAt(0));
            if (face == -1) {
                throw new PokerHandException("invalid hand: non-existing face");
            }
            straight |= (1 << face);

            if (faceCount.get(face) == null) {
                faceCount.set(face, 1);
            } else {
                int value = faceCount.get(face);
                value += 1;
                faceCount.set(face, value);
            }

            if (suits.indexOf(card.charAt(1)) == -1) {
                throw new PokerHandException("invalid hand: non-existing suit");
            }
            flush |= (1 << card.charAt(1));
        }

        while (straight % 2 == 0) {
            straight >>= 1;
        }

        boolean hasStraight = straight == 0b11111 || straight == 0b1111000000001;
        boolean hasFlush = (flush & (flush - 1)) == 0;

        if (hasStraight && hasFlush) {
            /**
             * return value FOUR_OF_A_KIND
             */
            return Category.STRAIGHT_FLUSH;
        }

        int total = 0;

        for (Integer count : faceCount) {
            if (count == null) {
                continue;
            }
            if (count == 4) /**
             * return value FOUR_OF_A_KIND
             */
            {
                return Category.FOUR_OF_A_KIND;
            }
            if (count == 3) {
                total += 3;
            } else if (count == 2) {
                total += 2;
            }

        }

        if (total == 5) {
            /**
             * return value FULL_HOUSE
             */
            return Category.FULL_HOUSE;
        } else if (hasFlush) {
            /**
             * return value FLUSH
             */
            return Category.FLUSH;
        } else if (hasStraight) {
            /**
             * return value STRAIGHT
             */
            return Category.STRAIGHT;
        } else if (total == 3) {
            /**
             * return value THREE_OF_A_KIND
             */
            return Category.THREE_OF_A_KIND;
        } else if (total == 4) {
            /**
             * return value TWO_PAIR
             */
            return Category.TWO_PAIR;
        } else if (total == 2) {
            /**
             * return value ONE_PAIR
             */
            return Category.ONE_PAIR;
        } else {
            /**
             * return value HIGH_CARD
             */
            return Category.HIGH_CARD;
        }
    }

    /**
     * Categories that can be used
     *
     * @since 1.0
     */
    public enum Category {
        /**
         * high card
         */
        HIGH_CARD("high card"),
        /**
         * one pair
         */
        ONE_PAIR("one pair"),
        /**
         * two pair
         */
        TWO_PAIR("two pair"),
        /**
         * three of a kind
         */
        THREE_OF_A_KIND("three of a kind"),
        /**
         * straight
         */
        STRAIGHT("straight"),
        /**
         * flush
         */
        FLUSH("flush"),
        /**
         * full house
         */
        FULL_HOUSE("full house"),
        /**
         * four of a kind
         */
        FOUR_OF_A_KIND("four of a kind"),
        /**
         * straight flush
         */
        STRAIGHT_FLUSH("straight-flush");

        String value;

        Category(String result) {
            value = result;
        }
    }
}
