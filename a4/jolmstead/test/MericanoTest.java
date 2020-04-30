import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MericanoTest {

    @Test
    void getGrowth() {
        Mericano p = new Mericano(15, 4, "Mericano", true,  true);
        assertEquals(4, p.getGrowth(),
                "getElem() did not return correct value");

    }

    @Test
    void getEnergy() {
        Mericano p = new Mericano(15, 0, "Mericano", true,  true);
        assertEquals(15, p.getEnergy(),
                "getElem() did not return correct value");
    }

    @Test
    void getPlantCount() {

        Mericano p = new Mericano(15, 0, "Mericano", true,  true);
        assertEquals(0, p.getPlantCount(),
                "getElem() did not return correct value");

    }
}