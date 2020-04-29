import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpressoBotTest {

    //instantialte and declare the spresso bot for testing
    SpressoBot firstlady = new SpressoBot(10, 10);
    SpressoBot theOneThatIsDead = new SpressoBot(11,-1);
    SpressoBot theFirstOfMany = new SpressoBot(12,12);
    int [] spresso = new int [5];
    boolean [] bots = new boolean [5];
    boolean [] bots2 = new boolean [2];
    boolean [] bots3 = new boolean[0];

    @Test
    void getNextMove() {

        assertNotNull(bots3.length, " array size cannot be null!");
        assertTrue( bots2.length == 5, "Not the right size array");


        //make sure that the array that is used as a size reference isn't the wrong size
        firstlady.senseDistricts(spresso, bots);

        assertNotNull(bots.length);

        assertEquals(5, bots.length, "Not the right size array");

        //returns not null
        assertNotNull(firstlady.getNextMove());

        //make sure ded things don't move when they are not supposed to
        assertEquals(District.CURRENT ,theOneThatIsDead.getNextMove(), "The dead bot moved and it was scary");

        //given that we use 1 jolt of the timbot that is dead he should not be functional
        //when
        final boolean result = theOneThatIsDead.isFunctional(); //should be false

        //then
        assertFalse(result, "returned that the bot is functional! The bot should be dead!");
        assertTrue(theFirstOfMany.isFunctional(), "This bot should still be functional");

    }

    @Test
    void getNextMoveEnergy(){
        System.out.println("Stay at current - getnextmove energy");

        //given first lady is not moving from her location
        firstlady.getNextMove();
        int energyAfterMove = firstlady.energyLevel;
        assertEquals(10, energyAfterMove, "energy was taken despite not moving from the current district");

    }
}