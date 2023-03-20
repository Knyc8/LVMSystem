public class PhysicalVolume extends Volume
{
    private int size;
    private String name;

    public PhysicalVolume(HardDrive sd, String n)
    {
        super();
        size = sd.getSize();
        name = n;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
