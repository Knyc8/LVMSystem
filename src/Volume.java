import java.util.UUID;

public class Volume {
    private UUID uuid;
    private String name;

    public Volume(String n)
    {
        uuid = UUID.randomUUID();
        name = n;
    }

    public UUID getUuid() {
        return uuid;
    }
    public String getName() {
        return name;
    }
}
