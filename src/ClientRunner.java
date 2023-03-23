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
                boolean dupDrive = false;
                if (sdList.size() > 0) {
                    for (int i = 0;  i < sdList.size(); i++) {
                        if (sdList.get(i).getName().equals(inputList[1])) {
                            dupDrive = true;
                            System.out.println(inputList[1] + " creation failed.");
                        }
                    }
                }
                if (dupDrive == false)
                {
                    HardDrive sd = new HardDrive(inputList[1], inputList[2]);
                    sdList.add(sd);System.out.println("Drive " + sd.getName() + " installed.");
                }
            }
            if (inputList[0].equals("list-drives"))
            {
                if (sdList.size() == 0) {
                    System.out.println("No drives installed currently.");
                }
                else {
                    for (HardDrive drive : sdList) {
                        System.out.println(drive.toString());
                    }
                }
            }
        }
        System.out.println("Data saved.");
    }
}


