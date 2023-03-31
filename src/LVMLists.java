import java.util.ArrayList;

public class LVMLists {
    private static ArrayList<HardDrive> sdList = new ArrayList<>();
    private static ArrayList<PhysicalVolume> pvList = new ArrayList<>();
    private static ArrayList<VolumeGroup> vgList = new ArrayList<>();
    private static ArrayList<LogicalVolume> lvList = new ArrayList<>();

    public static ArrayList<HardDrive> getSdList() {
        return sdList;
    }

    public static ArrayList<PhysicalVolume> getPvList() {
        return pvList;
    }

    public static ArrayList<VolumeGroup> getVgList() {
        return vgList;
    }
    public static ArrayList<LogicalVolume> getLvList() {
        return lvList;
    }

    public static void addToSDL(HardDrive sd)
    {
        sdList.add(sd);
    }

    public static void addToPVL(PhysicalVolume pv)
    {
        pvList.add(pv);
    }

    public static void addToVGL(VolumeGroup vg)
    {
        vgList.add(vg);
    }
    public static void addToLVL(LogicalVolume lv)
    {
        lvList.add(lv);
    }

    public static Boolean sdExists(String n){
        for (int i = 0; i < sdList.size(); i++) {
            if (sdList.get(i).getName().equals(n)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean pvExists(String n1, String n2){
        for (PhysicalVolume pv : pvList)
        {
            if (pv.getName().equals(n1) || pv.getAssociatedDrive().getName().equals(n2))
            {
                return true;
            }
        }
        return false;
    }
    public static Boolean vgExists(String n){
        for (VolumeGroup vg : LVMLists.getVgList())
        {
            if (vg.getName().equals(n))
            {
                return true;
            }
        }
        return false;
    }
    public static Boolean lvExists(String n){
        for (LogicalVolume lv : LVMLists.getLvList())
        {
            if (lv.getName().equals(n))
            {
                return true;
            }
        }
        return false;
    }
}
