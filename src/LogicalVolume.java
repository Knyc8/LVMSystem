public class LogicalVolume extends Volume
{
    private int size;
    private String groupName;
    public LogicalVolume(String n, String s, String gn)
    {
        super(n);
        size = Integer.parseInt(s.substring(0, s.length()-1));
        groupName = gn;
    }

    public int getSize() {
        return size;
    }
    public String getGroupName() {return groupName;}

    public String toString()
    {
        return super.getName() + ": [" + size  + "G] [" + groupName + "] [" + super.getUuid().toString() + "]";
    }
}
