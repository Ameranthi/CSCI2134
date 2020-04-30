import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpressoTest {

    @Test
    void getGrowth() {
        Spresso p = new Spresso(15, 4, "Spresso", true,  true);
        assertEquals(4, p.getGrowth(),
                "getElem() did not return correct value");

    }

    @Test
    void getEnergy() {
        Spresso p = new Spresso(15, 0, "Spresso", true,  true);
        assertEquals(15, p.getEnergy(),
                "getElem() did not return correct value");
    }

    @Test
    void getPlantCount() {

        Spresso p = new Spresso(15, 0, "Spresso", true,  true);
        assertEquals(0, p.getPlantCount(),
                "getElem() did not return correct value");

    }
}
