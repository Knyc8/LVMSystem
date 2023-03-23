import java.util.ArrayList;

public class VolumeGroup extends Volume{
    private ArrayList<PhysicalVolume> group;
    private String name;

    public VolumeGroup(String n, PhysicalVolume pv)
    {
        super();
        name = n;
        group = new ArrayList<>();
        group.add(pv);
    }

    public ArrayList<PhysicalVolume> getPvList() {
        return group;
    }

    public String getName() {
        return name;
    }
}
