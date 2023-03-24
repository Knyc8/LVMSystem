import java.util.ArrayList;

public class LVMLists {
    private static ArrayList<HardDrive> sdList = new ArrayList<>();
    private static ArrayList<PhysicalVolume> pvList = new ArrayList<>();
    private static ArrayList<VolumeGroup> vgList = new ArrayList<>();

    public static ArrayList<HardDrive> getSdList() {
        return sdList;
    }

    public static ArrayList<PhysicalVolume> getPvList() {
        return pvList;
    }

    public static ArrayList<VolumeGroup> getVgList() {
        return vgList;
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
}
