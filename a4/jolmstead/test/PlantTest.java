import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

    @Test
    void getGrowth() {
        Plant p = new Plant(15, 4, "Mericano", true,  true);
        assertEquals(4, p.getGrowth(),
                "getElem() did not return correct value");

    }

    @Test
    void getEnergy() {
        Plant p = new Plant(15, 0, "Mericano", true,  true);
        assertEquals(15, p.getEnergy(),
                "getElem() did not return correct value");
    }

    @Test
    void getPlantCount() {

        Plant p = new Plant(15, 0, "Mericano", true,  true);
        assertEquals(0, p.getPlantCount(),
                "getElem() did not return correct value");

    }
}