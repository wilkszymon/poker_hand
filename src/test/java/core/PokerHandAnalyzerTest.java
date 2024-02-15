package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit tests for the core (program model)
 *
 * @author Szymon Wilk
 * @version 2.0
 * @since 2.0
 */
public class PokerHandAnalyzerTest {

    /**
     * Unit test object pokerHand, used for all tests
     *
     * @version 2.0
     * @since 2.0
     */
    PokerHandAnalyzer pokerHand;

    /**
     * Setup for the testRecognizePokerHand, later changes are being done in the
     * test unit method
     *
     * @version 2.0
     * @since 2.0
     */
    @BeforeEach
    public void setUp() {
        pokerHand = new PokerHandAnalyzer();
    }

    /**
     * Test of recognizePokerHand(), checks everyone poker hand and exceptions
     *
     * @since 2.0
     * @version 2.0
     */
    @Test
    public void testRecognizePokerHand() {
        String result;
        List<String> array0fCards = new ArrayList<>();

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "7dd", "2h", "qd", "ks", "2s");
            pokerHand.recognizePokerHand(array0fCards);
            fail("An exception should be thrown when the card too many characters");
        } catch (PokerHandException e) {
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "7d", "2h", "qd", "ks", "2s");
            pokerHand.recognizePokerHand(array0fCards);
            fail("An exception should be thrown when the card is lowercase");
        } catch (PokerHandException e) {
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "7D", "KS", "QD");
            pokerHand.recognizePokerHand(array0fCards);
            fail("An exception should be thrown when cards too few");
        } catch (PokerHandException e) {
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "2H", "2H", "7D", "KS", "QD");
            pokerHand.recognizePokerHand(array0fCards);
            fail("An exception should be thrown when the two cards is the same");
        } catch (PokerHandException e) {
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "2H", "2S", "2D", "KS", "QD");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("three of a kind", result, "Added 2H 2S 7D 8S 9D shoud give a high card.");
        } catch (PokerHandException e) {
            fail("Check poker hand with 2H 2S 7D 8S 9D - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "2H", "5H", "7D", "8S", "9D");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("high card", result, "Added 2H 5H 7D 8S 9D shoud give a high card.");
        } catch (PokerHandException e) {
            fail("Check poker hand with 2H 5H 7D 8S 9D - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "AH", "2D", "3S", "4S", "5S");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("straight", result, "Added AH 2D 3S 4S 5S shoud give a straight.");
        } catch (PokerHandException e) {
            fail("Check poker hand with AH 2D 3S 4S 5S - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "2H", "3H", "2D", "3S", "3D");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("full house", result, "Added 2H 3H 2D 3S 3D shoud give a full house.");
        } catch (PokerHandException e) {
            fail("Check poker hand with AH 2H 3H 2D 3S 3D - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "2H", "7H", "2D", "3S", "3D");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("two pair", result, "Added 2H 7H 2D 3S 3D shoud give a two pair.");
        } catch (PokerHandException e) {
            fail("Check poker hand with 2H 7H 2D 3S 3D - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "2H", "7H", "7D", "7S", "7C");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("four of a kind", result, "Added 2H 7H 7D 7S 7C shoud give a four of a kind.");
        } catch (PokerHandException e) {
            fail("Check poker hand with 2H 7H 7D 7S 7C - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "TH", "JH", "QH", "KH", "AH");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("straight-flush", result, "Added TH JH QD KH AH shoud give a straight flush.");
        } catch (PokerHandException e) {
            fail("Check poker hand with TH JH QD KH AH - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "4H", "4C", "KC", "5D", "TC");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("one pair", result, "Added 4H 4C KC 5D TC shoud give a one pair.");
        } catch (PokerHandException e) {
            fail("Check poker hand with 4H 4C KC 5D TC - The exception should not be thrown.");
        }

        try {
            array0fCards.clear();
            Collections.addAll(array0fCards, "QC", "TC", "7C", "6C", "4C");
            result = pokerHand.recognizePokerHand(array0fCards);
            assertEquals("flush", result, "Added QC TC 7C 6D 4C shoud give a flush.");
        } catch (PokerHandException e) {
            fail("Check poker hand with QC TC 7C 6D 4C - The exception should not be thrown.");
        }

    }

    /**
     * Tests for the nthPrimeNumber(), checks nth prime number all options
     * included
     *
     * @version 2.0
     * @since 2.0
     * @param hand
     */
    @ParameterizedTest
    @ValueSource(strings = {"YC TC 7C 6C 4C"})
    public void testNonExistingFace(String hand) {
        List<String> array0fCards = new ArrayList<>(Arrays.asList(hand.split(" ")));
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: non-existing face", exception.getMessage());
    }

    /**
     * Tests for the nthPrimeNumber(), checks nth prime number all options
     * included
     *
     * @version 2.0
     * @since 2.0
     * @param hand
     */
    @ParameterizedTest
    @ValueSource(strings = {"QQ 3K 7C 6C 4C"})
    public void testNonExistingSuit(String hand) {
        List<String> array0fCards = new ArrayList<>(Arrays.asList(hand.split(" ")));
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: non-existing suit", exception.getMessage());
    }

    /**
     * Tests of exception the card is null
     *
     * @version 2.0
     * @since 2.0
     */
    @Test
    public void testNullArrays() {
        List<String> array0fCards = null;
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: hand is null", exception.getMessage());
    }

    /**
     * Tests of exception the card is too few
     *
     * @version 3.0
     * @since 2.0
     * @param hand
     */
    @ParameterizedTest
    @ValueSource(strings = {"QS 7C 6C 4C", "KS 2D"})
    public void testTooFewArguments(String hand) {
        List<String> array0fCards = new ArrayList<>(Arrays.asList(hand.split(" ")));
        Collections.addAll(array0fCards, "QS", "7C", "6C", "4C");
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: too few arguments", exception.getMessage());
    }

    /**
     * Tests of exception the card is duplicates
     *
     * @version 3.0
     * @since 2.0
     * @param hand
     */
    @ParameterizedTest
    @ValueSource(strings = {"2H 2H 2S 6C 4C", "QS KS QS 3D 2H"})
    public void testDuplicates(String hand) {
        List<String> array0fCards = new ArrayList<>(Arrays.asList(hand.split(" ")));
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: duplicates", exception.getMessage());
    }

    /**
     * Tests of exception the card is lowercase
     *
     * @version 3.0
     * @since 2.0
     * @param hand
     */
    @ParameterizedTest
    @ValueSource(strings = {"2H 2h 2s 6C 4C", "qs kS QS 3D 2H"})
    public void testCardIsLowercase(String hand) {
        List<String> array0fCards = new ArrayList<>(Arrays.asList(hand.split(" ")));
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: you gave a cards is lowercase", exception.getMessage());
    }

    /**
     * Tests of exception the card has to many characters
     *
     * @version 3.0
     * @since 3.0
     * @param hand
     */
    @ParameterizedTest
    @ValueSource(strings = {"2HH 2h 2s 6CC 4C"})
    public void testCardHasToManyCharacters(String hand) {
        List<String> array0fCards = new ArrayList<>(Arrays.asList(hand.split(" ")));
        PokerHandException exception = assertThrows(
                PokerHandException.class,
                () -> pokerHand.recognizePokerHand(array0fCards),
                "Expected recognizePokerHand(array0fCards) to throw, but it didn't");
        assertEquals("invalid hand: too many characters in card: 2HH you can gave only two characters", exception.getMessage());
    }
}
