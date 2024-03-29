import java.util.ArrayList;

public class VolumeGroup extends Volume{
    private ArrayList<PhysicalVolume> pvGroup;
    private ArrayList<LogicalVolume> lvGroup;

    public VolumeGroup(String n, PhysicalVolume pv)
    {
        super(n);
        pvGroup = new ArrayList<>();
        pvGroup.add(pv);
        lvGroup = new ArrayList<>();
    }

    public ArrayList<PhysicalVolume> getPvList() {
        return pvGroup;
    }
    public ArrayList<LogicalVolume> getLvList() {
        return lvGroup;
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
        return super.getName() + ": Total:[" + calcTotalSpace() + "G] Available:[" + calcRemainSpace() + "G] " + pvNameList().toString() + " [" + super.getUuid().toString() + "]";
    }
}
