public class LogicalVolume extends Volume
{
    private String name;
    private int size;
    public LogicalVolume(String n, int s)
    {
        super();
        name = n;
        size = s;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
