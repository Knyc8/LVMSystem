public class HardDrive extends Volume{
    private int size;

    public HardDrive(String n, String s)
    {
        super(n);
        size = Integer.parseInt(s.substring(0, s.length()-1));
    }

    public int getSize() {
        return size;
    }


    public String toString()
    {
        return super.getName() + " [" + size  + "G] [" + super.getUuid().toString() + "]";
    }
}
