import java.util.ArrayList;
import java.util.Scanner;

public class ClientRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Data loaded.");
        System.out.println("Welcome to the LVM system.");
        String input = "";
        ArrayList<HardDrive> sdList = new ArrayList<>();

        while (!input.equals("exit"))
        {
            System.out.print("cmd#: ");
            input = s.nextLine();
            String[] inputList = input.split(" ");

            if (inputList[0].equals("install-drive"))
            {
                if (sdList.size() > 0) {
                    for (HardDrive drive : sdList) {
                        if (drive.getName().equals(inputList[1])) {
                            System.out.println(inputList[1] + " creation failed");
                        } else {
                            HardDrive sd = new HardDrive(inputList[1], inputList[2]);
                            sdList.add(sd);
                            System.out.println("Drive " + sd.getName() + " installed");
                            System.out.println(sd.toString());
                        }
                    }
                }
            }
            if (inputList[0].equals("list-drives"))
            {
                for (HardDrive drive : sdList)
                {
                    System.out.println(drive.toString());
                }
            }
        }
        System.out.println("Data saved.");
    }
}


