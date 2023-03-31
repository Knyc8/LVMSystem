public class LogicalVolume extends Volume
{
    private int size;
    private String unit;
    public LogicalVolume(String n, String s)
    {
        super(n);
        size = Integer.parseInt(s.substring(0, s.length()-1));
    }

    public int getSize() {
        return size;
    }
    public String getUnit() {return unit;}
}
