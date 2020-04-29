import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BullyBotTest {

    BullyBot bigMeanie = new BullyBot(34,67);
    BullyBot deadMeanie = new BullyBot(778, -1);
    int [] spresso = new int [5];
    boolean [] bots = new boolean [5];
    boolean [] bots2 = new boolean [2];
    boolean [] bots3 = new boolean[0];

    @Test
    void fireCannon() {

        assertNotNull(bots.length, "oh darn we made an array size null");
        assertNotNull(bots3.length, " array size cannot be null!");

        //make sure that the array that is used as a size reference isn't the wrong size
        bigMeanie.senseDistricts(spresso, bots);

        assertEquals(5, bots.length, "Not the right size array");
        assertTrue( bots2.length == 5, "Not the right size array");
        //energy before shoot
        int energyBeforeShoot = bigMeanie.energyLevel;

        //energy after shoot
        bigMeanie.fireCannon();
        int energyAfterShoot = bigMeanie.energyLevel;

        //make sure ded things don't move when they are not supposed to
        assertEquals(District.CURRENT ,deadMeanie.getNextMove(), "The dead bot moved and it was scary");

        //given that we use 1 jolt of the timbot that is dead he should not be functional
        //when
        final boolean result = deadMeanie.isFunctional(); //should be false

        //then
        assertFalse(result, "returned that the bot is functional! The bot should be dead!");
        assertTrue(bigMeanie.isFunctional(), "This bot should still be functional");


        //array returned properly?


        //assertnull if it doesn't shoot
        assertNull(deadMeanie.fireCannon(), "dead meanie fired the canon! he should not be able to do" +
                " that! he is dead"); //shouldn't be able to shoot canon


    }

    @Test
    void getNextMoveEnergy(){
        System.out.println("Stay at current - getnextmove energy");

        //given first lady is not moving from her location
        bigMeanie.fireCannon();
        int energyAfterMove = bigMeanie.energyLevel;
        assertEquals(67, energyAfterMove, "energy was taken despite not moving from the current district");

    }
}