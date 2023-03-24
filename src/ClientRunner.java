import java.util.ArrayList;
import java.util.Scanner;

public class ClientRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Data loaded.");
        System.out.println("Welcome to the LVM system.");
        String input = "";

        while (!input.equals("exit")) {
            System.out.print("cmd#: ");
            input = s.nextLine();
            String[] inputList = input.split(" ");
            if (inputList[0].equals("install-drive") && inputList.length == 3) {
                boolean dupDrive = false;
                if (LVMLists.getSdList().size() > 0) {
                    for (int i = 0; i < LVMLists.getSdList().size(); i++) {
                        if (LVMLists.getSdList().get(i).getName().equals(inputList[1])) {
                            dupDrive = true;
                            System.out.println(inputList[1] + " creation failed.");
                        }
                    }
                }
                if (dupDrive == false) {
                    HardDrive sd = new HardDrive(inputList[1], inputList[2]);
                    LVMLists.addToSDL(sd);
                    System.out.println("Drive " + sd.getName() + " installed.");
                }
            }
            if (inputList[0].equals("list-drives")) {
                if (LVMLists.getSdList().size() == 0) {
                    System.out.println("No drives installed currently.");
                } else {
                    for (HardDrive drive : LVMLists.getSdList()) {
                        System.out.println(drive);
                    }
                }
            }
            if (inputList[0].equals("pvcreate") && inputList.length == 3) {
                boolean alrExist = false;
                for (PhysicalVolume pv : LVMLists.getPvList())
                {
                    if (pv.getName().equals(inputList[1]) || pv.getAssociatedDrive().getName().equals(inputList[2]))
                    {
                        alrExist = true;
                        break;
                    }
                }
                if (alrExist) {System.out.println(inputList[1] + " creation failed.");}
                else {
                    HardDrive associated = null;
                    for (HardDrive sd : LVMLists.getSdList()) {
                        if (inputList[2].equals(sd.getName())) {
                            associated = sd;
                        }
                    }
                    if (associated != null) {
                        PhysicalVolume pv = new PhysicalVolume(inputList[1], associated);
                        LVMLists.addToPVL(pv);
                        System.out.println(inputList[1] + " created.");
                    } else {
                        System.out.println(inputList[1] + " creation failed.");
                    }
                }
            }
            if (inputList[0].equals("pvlist") && inputList.length == 1) {
                if (LVMLists.getPvList().size() == 0) {
                    System.out.println("No physical volumes created currently.");
                } else {
                    for (PhysicalVolume pv : LVMLists.getPvList()) {
                        if (LVMLists.getVgList().size() > 0) {
                            for (VolumeGroup vg : LVMLists.getVgList()) {
                                for (PhysicalVolume pv1 : vg.getPvList()) {
                                    if (pv.getName().equals(pv1.getName())) {
                                        System.out.println(pv.toStringGroup(vg.getName()));
                                    }
                                    else {
                                        System.out.println(pv);
                                    }
                                }
                            }
                        } else {
                            System.out.println(pv);
                        }
                    }
                }
            }
            if (inputList[0].equals("vgcreate") && inputList.length == 3) {
                VolumeGroup volGroup = null;
                for (VolumeGroup vg : LVMLists.getVgList())
                {
                    if (vg.getName().equals(inputList[1]))
                    {
                        volGroup = vg;
                        break;
                    }
                }

                if (LVMLists.getPvList().size() == 0) {
                    System.out.println(inputList[1] + " creation failed.");
                }else if (volGroup != null){
                    PhysicalVolume assignedPv = null;
                    for (PhysicalVolume pv : LVMLists.getPvList()) {
                        if (pv.getName().equals(inputList[2])) {
                            assignedPv = pv;
                        }
                    }
                    if (assignedPv != null) {
                        volGroup.addToPVGroup(assignedPv);
                        System.out.println(inputList[2] + " added to " + inputList[1] + ".");
                    }
                } else {
                    PhysicalVolume assignedPv = null;
                    for (PhysicalVolume pv : LVMLists.getPvList()) {
                        if (pv.getName().equals(inputList[2])) {
                            assignedPv = pv;
                        }
                    }
                    if (assignedPv != null) {
                        VolumeGroup vg = new VolumeGroup(inputList[1], assignedPv);
                        LVMLists.addToVGL(vg);
                        System.out.println(inputList[1] + " created.");
                    }
                }
            }
            if (inputList[0].equals("vglist") && inputList.length == 1) {
                if (LVMLists.getVgList().size() == 0)
                {
                    System.out.println("No Volume Groups created currently.");
                }
                else {
                    for (VolumeGroup vg : LVMLists.getVgList())
                    {
                        System.out.println(vg);
                    }
                }
            }
        }
        System.out.println("Data saved.");
    }
}


