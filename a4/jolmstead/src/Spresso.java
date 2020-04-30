import java.lang.reflect.GenericArrayType;

public class Spresso extends Plant{


    public Spresso(int energy, int  growth, String name, boolean harvestable, boolean ripe){
        super(energy, growth, name, true, true);

    }

    public int getGrowth(){
        return growth;
    }
}
