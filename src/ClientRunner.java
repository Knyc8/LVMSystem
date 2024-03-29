import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientRunner {
    public static void main(String[] args) throws IOException {
        //load saved objects (Can write multiple objects to Save_File but can only load one at the moment)

        HardDrive loaded = (HardDrive) LVMLoad.load();
        LVMLists.addToSDL(loaded);
        System.out.println("Data loaded.");

        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to the LVM system.");
        String input = "";
        while (!input.equals("exit")) {
            System.out.print("cmd#: ");
            input = s.nextLine();
            String[] inputList = input.split(" ");
            if (inputList[0].equals("install-drive") && inputList.length == 3) {
                if (LVMLists.getSdList().size() > 0) {
                    if (LVMLists.sdExists(inputList[1]))
                    {
                        System.out.println(inputList[1] + " creation failed.");
                    }
                }
                if (!LVMLists.sdExists(inputList[1])) {
                    HardDrive sd = new HardDrive(inputList[1], inputList[2]);
                    LVMLists.addToSDL(sd);
                    LVMLoad.save(sd);
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
                if (LVMLists.pvExists(inputList[1], inputList[2]))
                {System.out.println(inputList[1] + " creation failed.");}
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
                        boolean inAGroup = false;
                        String groupName = "";
                        for (VolumeGroup vg : LVMLists.getVgList()) {
                            for (PhysicalVolume pv1 : vg.getPvList()) {
                                if (pv.getName().equals(pv1.getName())) {
                                    inAGroup = true;
                                    groupName = vg.getName();
                                }
                            }
                        }
                        if (inAGroup)
                        {
                            System.out.println(pv.toStringGroup(groupName));
                        }
                        else
                        {
                            System.out.println(pv);
                        }
                    }
                }
            }
            if (inputList[0].equals("vgcreate") && inputList.length == 3) {
                boolean inAGroup = false;
                for (VolumeGroup vg : LVMLists.getVgList()) {
                    for (PhysicalVolume pv1 : vg.getPvList()) {
                        if (inputList[2].equals(pv1.getName())) {
                            inAGroup = true;
                        }
                    }
                }
                if (inAGroup)
                {
                    System.out.println(inputList[2] + " already exists in another group.");
                }
                else if (LVMLists.getPvList().size() == 0 || LVMLists.vgExists(inputList[1])) {
                    System.out.println(inputList[1] + " creation failed.");
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
            if (inputList[0].equals("vgextend") && inputList.length == 3) {
                if (!LVMLists.vgExists(inputList[1]))
                {
                    System.out.println(inputList[1] + " has not been created");
                }
                else if (!LVMLists.pvExists(inputList[2], ""))
                {
                    System.out.println(inputList[2] + " has not been created");
                }
                else {
                    PhysicalVolume assignedPv = null;
                    for (PhysicalVolume pv : LVMLists.getPvList()) {
                        if (pv.getName().equals(inputList[2])) {
                            assignedPv = pv;
                        }
                    }
                    VolumeGroup assignedVg = null;
                    for (VolumeGroup vg : LVMLists.getVgList()) {
                        if (vg.getName().equals(inputList[1])) {
                            assignedVg = vg;
                        }
                    }
                    assignedVg.addToPVGroup(assignedPv);
                    System.out.println("Successfully appended " + inputList[2] + " to " + inputList[1]);
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
            if (inputList[0].equals("lvcreate") && inputList.length == 4) {
                if (LVMLists.getLvList().size() > 0) {
                    if (LVMLists.lvExists(inputList[1])) {
                        System.out.println(inputList[1] + " creation failed.");
                    }
                }
                if (!LVMLists.lvExists(inputList[1])) {
                    if (LVMLists.vgExists(inputList[3])) {
                        boolean space = true;
                        LogicalVolume lv = new LogicalVolume(inputList[1], inputList[2], inputList[3]);
                        for (VolumeGroup vg : LVMLists.getVgList()) {
                            if (vg.getName().equals(inputList[3])) {
                                if (vg.calcRemainSpace() < Integer.parseInt(inputList[2].substring(0, inputList[2].length()-1)))
                                {
                                    space = false;
                                }
                                else {
                                    vg.addToLVGroup(lv);
                                }
                            }
                        }
                        if (!space)
                        {
                            System.out.println(inputList[3] + " does not have enough space.");
                        }
                        else {
                            LVMLists.addToLVL(lv);
                            System.out.println((inputList[1] + inputList[2] + inputList[3]));
                            System.out.println(inputList[1] + " created.");
                        }
                    } else {
                        System.out.println(inputList[3] + " has not been created");
                    }
                }
            }
            if (inputList[0].equals("lvlist") && inputList.length == 1) {
                {
                    if (LVMLists.getLvList().size() == 0) {
                        System.out.println("No logical volumes created currently.");
                    } else {
                        for (LogicalVolume lv : LVMLists.getLvList()) {
                            System.out.println(lv);
                        }
                    }
                }
            }
        }
        System.out.println("Data saved.");
    }
}


