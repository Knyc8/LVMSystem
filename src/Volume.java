import java.util.UUID;

public class Volume {
    private UUID uuid;

    public Volume()
    {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
