import java.util.ArrayList;

public class VolumeGroup extends Volume{
    private ArrayList<PhysicalVolume> pvGroup;
    private ArrayList<LogicalVolume> lvGroup;
    private String name;

    public VolumeGroup(String n, PhysicalVolume pv)
    {
        super();
        name = n;
        pvGroup = new ArrayList<>();
        pvGroup.add(pv);
        lvGroup = new ArrayList<>();
    }

    public ArrayList<PhysicalVolume> getPvList() {
        return pvGroup;
    }

    public String getName() {
        return name;
    }

    public void addToPVGroup(PhysicalVolume pv)
    {
        pvGroup.add(pv);
    }

    public void addToLVGroup(LogicalVolume lv)
    {
        lvGroup.add(lv);
    }

    public ArrayList<String> pvNameList()
    {
        ArrayList<String> pvNames = new ArrayList<>();
        for (PhysicalVolume pv : pvGroup)
        {
            pvNames.add(pv.getName());
        }
        return pvNames;
    }

    public int calcTotalSpace()
    {
        int space = 0;
        for (PhysicalVolume pv : pvGroup)
        {
            space += pv.getSize();
        }
        return space;
    }

    public int calcRemainSpace()
    {
        int space = 0;
        for (LogicalVolume lv : lvGroup)
        {
            space += lv.getSize();
        }
        return calcTotalSpace()-space;
    }
    public String toString()
    {
        return name + ": Total:[" + calcTotalSpace() + "] Available:[" + calcRemainSpace() + "] " + pvNameList().toString() + " [" + super.getUuid().toString() + "]";
    }
}
