public class HardDrive extends Volume{
    private String name;
    private int size;

    public HardDrive(String n, int s)
    {
        super();
        name = n;
        size = s;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
