public class LogicalVolume extends Volume
{
    private String name;
    private int size;
    public LogicalVolume(String n, String s)
    {
        super();
        name = n;
        size = Integer.parseInt(s.substring(0, s.length()-1));
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
