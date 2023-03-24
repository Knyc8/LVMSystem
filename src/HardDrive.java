public class HardDrive extends Volume{
    private String name;
    private int size;
    private String unit;

    public HardDrive(String n, String s)
    {
        super();
        name = n;
        size = Integer.parseInt(s.substring(0, s.length()-1));
        unit = s.substring(s.length()-1);
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {return unit;}

    public String toString()
    {
        return name + " [" + size + unit + "][" + super.getUuid().toString() + "]";
    }
}
