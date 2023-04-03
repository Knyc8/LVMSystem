import java.io.*;

public class LVMLoad {
    public static void save(Object o) throws IOException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/Save_File.txt", true));
            output.writeObject(o + "\n");
            output.close();
        }
        catch (IOException ex)
        {
            System.out.println("Error saving to file.");
        }
    }

    public static Object load() throws IOException {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/Save_File.txt"));
            return input.readObject();
        }
        catch (IOException ex)
        {
            System.out.println("Error loading file.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
