public class PhysicalVolume extends Volume
{
    private int size;
    private String name;

    public PhysicalVolume(int s, String n)
    {
        super();
        size = s;
        name = n;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
