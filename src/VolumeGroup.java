import java.util.ArrayList;

public class VolumeGroup extends Volume{
    private ArrayList<PhysicalVolume> pvList;
    private String name;

    public VolumeGroup(String n)
    {
        super();
        name = n;
        pvList = new ArrayList<>();
    }

    public ArrayList<PhysicalVolume> getPvList() {
        return pvList;
    }

    public String getName() {
        return name;
    }
}
