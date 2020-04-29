import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author for Tests: Julia Olmstead
 * @Author of src: Dr. Alex Brodsky
 *
 * These tests are to examine and see if there are any potential error in the original src code
 *
 */
class TimBotTest {
    //declare and instantiate test timbots
    final TimBot theFirstOfMany = new TimBot(1, 3);
    final TimBot theOneThatDies = new TimBot(2, 0);
    final TimBot theOneThatIsDead = new TimBot(3,-1);
    final TimBot theOneWithABrokenID = new TimBot(0,1);
    final TimBot fatboi = new TimBot(4, 99);

    /**
     * Because "It is recommended that this method store the information locally,
     *      in its own boolean variables or arrays to be used in later phases
     *      of the the round."
     *
     *      We will be creating local arrays...
     *
     *      2 - 5 element array
     *      spresso: a 5 element array of integers indicating when
     *                   the spresso plants will be ready for harvest in
     *                   corresponding districts.
     *                   the numbers decrement each round as the interger
     *                   is used gto express how many round it will take
     *                   till harvest time
     *
     *      bots   : a 5 element array of booleans indicating whether
     *                   TimBots are present in corresponding districts.
     *
     */


    int [] spresso = new int[]{3,3,3,3,3}; //three rounds till espresso ready to harvest
    Boolean[]bots = new Boolean[]{false,false,false,false,false}; // no bots in the Districts


    @Test
    void getID() throws NoSuchFieldException {
        //useful ish:
        //field : https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Field.html

        //given that the fields are accessible...
        final Field expectedField = theFirstOfMany.getClass().getDeclaredField("myId");
        expectedField.setAccessible(true);

        //when the expected result is the id of the timbot
        final int result = theFirstOfMany.getID();


        System.out.println("In method getID: ");
        //then...
        assertEquals(1, result, "first of many: The field was not retrieved properly " +
                "and returned the wrong value"); //supposed to print have 1 as the id
        //test for the lad that dies
        assertEquals(2, theOneThatDies.getID(), "lad that dies: The field was not retrieved " +
                "properly and returned the wrong value"); //supposed to print have 1 as the id

        //given that the id should never be lower than 1...then...

        //this bot is expected to always have a bad id
        assertTrue( theOneWithABrokenID.getID() > 0, "The ID for this bot does not fit the expected" +
                " boundaries");

        //this bot is expected to always have a good id
        assertTrue(0<theFirstOfMany.getID(), "the ID for this bot does not fit the expected " +
                "boundaries <=1");
    }

    @Test
    void startRound() {
        //given that the timbot uses one energy bolt at the start of the round and must have one energy to start the round

        assertFalse(theOneThatIsDead.isFunctional(), "This tim bot is dead the round should not begin");
        assertTrue(theFirstOfMany.isFunctional(), "This timbot is still very much alive and should be able " +
                "to start the round");

        //given that they must have one jolt of energy left to continue with the round then the one that dies should
        //prevent the round from beginning
        //changed useJolt from private to Protected in TimBot.java
        assertFalse(theOneThatDies.useJolt(), "This Tim bot should have died trying to start the round! " +
                "The round should not start");

    }

    @Test
    void senseDistricts() {
        //corresponding index with location
        // 0 currentD,
        // 1 NORTH,
        // 2 EAST,
        // 3 SOUTH,
        // 4 WEST

        //both arrays must be of the same size:
        assertTrue(spresso.length == bots.length, "The arrays are " +
                "not of the same size!" );

        //must be of size 5
        assertEquals(5, spresso.length, "not  the right size for the array");
        assertEquals(5, bots.length, "not  the right size for the array");


        //ints in spresso array cannot go lower than 0
        assertTrue(spresso[1]>=0, "the rounds till ripe went into the negatives");

        //assert that the arrays actually have values in them - not null
        assertNotNull(spresso, "There are no elements in this array");
        assertNotNull(spresso[0], "There are no elements in this index 0");
        assertNotNull(spresso[1], "There are no elements in this index 1");
        assertNotNull(spresso[2], "There are no elements in this index 2 ");
        assertNotNull(spresso[3], "There are no elements in this index 3");
        assertNotNull(spresso[4], "There are no elements in this index 4");

        assertNotNull(bots, "there is no elements in this array");
        assertNotNull(bots[0], "there is no elements in this index 0");
        assertNotNull(bots[1], "there is no elements in this index 1");
        assertNotNull(bots[2], "there is no elements in this index 2");
        assertNotNull(bots[3], "there is no elements in this index 3 ");
        assertNotNull(bots[4], "there is no elements in this index 4");



    }

    @Test
    void getNextMove() {
        //our bot should never move but should be in the current spot
        assertNotNull(District.CURRENT);

        //should be in current district
        assertEquals(0, District.CURRENT, "not in the current district");

    }

    @Test
    void isFunctional() {
        //given that we use 1 jolt of the lad/timbot that is dead he should not be functional

        //when
        final boolean result = theOneThatIsDead.isFunctional(); //should be false

        //then
        assertFalse(result, "returned that the bot is functional! The bot should be dead!");
        assertTrue(theFirstOfMany.isFunctional(), "This bot should still be functional");

    }

    @Test
    void useShield() {
        //when
        final boolean result = theOneThatIsDead.isFunctional(); //should be false

        //then
        assertFalse(result, "returned that the bot is functional! The bot should be dead!");
        assertTrue(theFirstOfMany.isFunctional(), "This bot should still be functional");

        //given that they must have one jolt of energy left to put up the shield then the one that dies should
        //just die n have no shield
        assertFalse(theOneThatDies.useJolt(), "This Tim bot should have died trying to put up their shield! " +
                "The round should not start");


    }

    @Test
    void harvestSpresso() {
        //bolts cannot exceed 99

        //given fatboi ate some spressos
         fatboi.harvestSpresso(9);

        int afterEating = fatboi.energyLevel;
        assertEquals(99, afterEating, "Did not cap how many jolts the bot can contain. She is gonna blow!!");

        //should it revive ones that are already dead?
        //given:
        theOneThatIsDead.harvestSpresso(10);
        //when
        int afterReviveTheDead = theOneThatIsDead.energyLevel;
        //then...
        assertEquals(9, afterReviveTheDead, "You did not revive a dead bot!");
        assertFalse(theOneThatIsDead.energyLevel > 0, "You revived a dead bot!"); // dead bots can eat?
        //or the dead bot was force fed by the gods and was revived

    }

    @Test
    void fireCannon() {
        //our cannon does not work so it never shoots. it must always return null
        assertNull(theFirstOfMany.fireCannon());

    }

    @Test
    void testToString() {
        //test if the to string output for a bot is correct
        String fs = theFirstOfMany.toString();
        String expected = "(N  1  3)"; //n because it was never changed to other personality type
        System.out.println("Inside testToString()");
        assertEquals(expected, fs, "Did not give back the expected String");

    }
}