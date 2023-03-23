import java.util.ArrayList;
import java.util.Scanner;

public class ClientRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Data loaded.");
        System.out.println("Welcome to the LVM system.");
        String input = "";
        ArrayList<HardDrive> sdList = new ArrayList<>();
        ArrayList<PhysicalVolume> pvList = new ArrayList<>();
        ArrayList<VolumeGroup> vgList = new ArrayList<>();

        while (!input.equals("exit")) {
            System.out.print("cmd#: ");
            input = s.nextLine();
            String[] inputList = input.split(" ");
            if (inputList[0].equals("install-drive") && inputList.length == 3) {
                boolean dupDrive = false;
                if (sdList.size() > 0) {
                    for (int i = 0; i < sdList.size(); i++) {
                        if (sdList.get(i).getName().equals(inputList[1])) {
                            dupDrive = true;
                            System.out.println(inputList[1] + " creation failed.");
                        }
                    }
                }
                if (dupDrive == false) {
                    HardDrive sd = new HardDrive(inputList[1], inputList[2]);
                    sdList.add(sd);
                    System.out.println("Drive " + sd.getName() + " installed.");
                }
            }
            if (inputList[0].equals("list-drives")) {
                if (sdList.size() == 0) {
                    System.out.println("No drives installed currently.");
                } else {
                    for (HardDrive drive : sdList) {
                        System.out.println(drive.toString());
                    }
                }
            }
            if (inputList[0].equals("pvcreate") && inputList.length == 3) {
                if (sdList.size() == pvList.size()) {
                    System.out.println(inputList[1] + " creation failed.");
                } else {
                    HardDrive associated = null;
                    for (HardDrive sd : sdList) {
                        if (inputList[2].equals(sd.getName())) {
                            associated = sd;
                        }
                    }
                    if (associated != null) {
                        PhysicalVolume pv = new PhysicalVolume(inputList[1], associated);
                        pvList.add(pv);
                        System.out.println(inputList[1] + " created.");
                    } else {
                        System.out.println(inputList[1] + " creation failed.");
                    }
                }
            }
            if (inputList[0].equals("pvlist")) {
                if (pvList.size() == 0) {
                    System.out.println("No physical volumes created currently.");
                } else {
                    for (PhysicalVolume pv : pvList) {
                        if (vgList.size() > 0) {
                            for (VolumeGroup vg : vgList) {
                                for (PhysicalVolume pv1 : vg.getPvList()) {
                                    if (pv.getName().equals(pv1.getName())) {
                                        System.out.println(pv.toStringGroup(vg.getName()));
                                    }
                                }
                            }
                        } else {
                            System.out.println(pv.toString());
                        }
                    }
                }
                if (inputList[0].equals("vgcreate") && inputList.length == 3) {
                    if (pvList.size() == 0) {
                        System.out.println(inputList[1] + " creation failed.");
                    } else {
                        PhysicalVolume assignedPv = null;
                        for (PhysicalVolume pv : pvList) {
                            if (pv.getName().equals(inputList[2])) {
                                assignedPv = pv;
                            }
                        }
                        if (assignedPv != null) {
                            VolumeGroup vg = new VolumeGroup(inputList[1], assignedPv);
                            vgList.add(vg);
                            System.out.println(inputList[1] + " created.");
                        }
                    }
                }
            }
        }
        System.out.println("Data saved.");
    }
}


