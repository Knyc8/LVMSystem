public class PhysicalVolume extends Volume
{
    private int size;
    private String name;
    private HardDrive associatedDrive;

    public PhysicalVolume(String n, HardDrive ad)
    {
        super();
        size = ad.getSize();
        name = n;
        associatedDrive = ad;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public HardDrive getAssociatedDrive() { return associatedDrive; }

    public String toString()
    {
        return name + ": [" + size + associatedDrive.getUnit() + "] [" + getUuid().toString() + "]";
    }

    public String toStringGroup(String vgName)
    {
        return name + ": [" + size + associatedDrive.getUnit() + "] [" + vgName + "] [" + super.getUuid().toString() + "]";
    }
}
