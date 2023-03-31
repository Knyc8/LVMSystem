public class PhysicalVolume extends Volume
{
    private int size;
    private HardDrive associatedDrive;

    public PhysicalVolume(String n, HardDrive ad)
    {
        super(n);
        size = ad.getSize();
        associatedDrive = ad;
    }

    public int getSize() {
        return size;
    }


    public HardDrive getAssociatedDrive() { return associatedDrive; }

    public String toString()
    {
        return super.getName() + ": [" + size + associatedDrive.getUnit() + "] [" + getUuid().toString() + "]";
    }

    public String toStringGroup(String vgName)
    {
        return super.getName() + ": [" + size + associatedDrive.getUnit() + "] [" + vgName + "] [" + super.getUuid().toString() + "]";
    }
}
