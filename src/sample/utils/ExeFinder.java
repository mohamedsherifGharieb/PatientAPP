package sample.utils;//import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;


//import com.profesorfalken.jpowershell.PowerShell;
public class ExeFinder {


    //Working! generates a txt file with all .lnk files in start menu and desktop + exe files in Program Files
    public static void main(String[] args) throws IOException {

        //String command = "powershell.exe  your command";
        //Getting the version
        @SuppressWarnings("unused")
        File startMenu = new File("startMenuApps.txt");
        Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("startMenuApps.txt"), "utf-8"));
        String line1 = "";
        int nolines1 = 0;

        //StartMenu Full Path
        BufferedWriter fullPathS = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("startMenuAppsFullPath.txt"), "utf-8"));
        String commandS = "powershell.exe Get-ChildItem " +
                "'C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs' " +
                "-Recurse -Include *.lnk ` " +
                "| Get-ItemProperty | Select FullName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcessS = Runtime.getRuntime().exec(commandS);
        powerShellProcessS.getOutputStream().close();
        String lineS = "";
        BufferedReader stdoutS = new BufferedReader(new InputStreamReader(powerShellProcessS.getInputStream()));
        while ((lineS = stdoutS.readLine()) != null) {
            if (!lineS.equals("")) // don't write out blank lines
            {
                String[] tempS = lineS.split("FullName : ");
                System.out.println(tempS[1]);
                fullPathS.write(tempS[1]);
                fullPathS.write("\r\n");
            }
        }
        stdoutS.close();
        fullPathS.close();

        //1 Start Menu
        String command1 = "powershell.exe  Get-ChildItem -path " +
                "'C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs' " +
                "-Recurse -Include *.lnk ` | Get-ItemProperty | Select BaseName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcess1 = Runtime.getRuntime().exec(command1);
        powerShellProcess1.getOutputStream().close();
        //Process powerShellProcess = runtime.exec("powershell.exe testPS.ps1");

        System.out.println("Standard Output:");
        BufferedReader stdout1 = new BufferedReader(new InputStreamReader(powerShellProcess1.getInputStream()));

        while ((line1 = stdout1.readLine()) != null) {
            if (!line1.equals("")) // don't write out blank lines
            {
                writer1.write(line1, 0, line1.length());
                writer1.write("\r\n");
            }

        }
        stdout1.close();
        writer1.close();

        try (BufferedReader b1 = new BufferedReader(new FileReader("startMenuApps.txt"))) {
//condition was != at first
            while (b1.readLine() != null) {
                nolines1++;
            }

        }
        FileWriter fw1 = new FileWriter("output1.txt");
        BufferedWriter bw1 = new BufferedWriter(fw1);
        try (BufferedReader br1 = new BufferedReader(new FileReader("startMenuApps.txt"))) {
            bw1.write("{\"Start Menu Programs\":[\n");
            bw1.newLine();
            int count1 = 1;

            int lines1 = 0;
            String put1 = "";

            while ((line1 = br1.readLine()) != null) {
                lines1++;
                String split1 = line1.trim();
                String[] dir1 = split1.split(":");
                int i = 0;

                while (i <= dir1.length - 1) {

                    if (lines1 < nolines1) {
                        put1 = dir1[i].trim();
                        bw1.write("{\"" + put1 + "\":");
                        i++;
                        put1 = dir1[i].trim();
                        bw1.write("\"" + put1 + "\"},\n");
                        bw1.newLine();
                        i++;
                        count1 = 1;
                    } else {
                        put1 = dir1[i].trim();
                        bw1.write("\"" + put1 + "\":");
                        i++;
                        put1 = dir1[i].trim();
                        bw1.write("\"" + put1 + "\"}\n");
                        bw1.newLine();
                        i++;
                        count1 = 1;
                    }
                }
            }
            bw1.write("]}");
        }
        bw1.close();

        //Desktop Full Path
        BufferedWriter fullPathD = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("desktopAppsFullPath.txt"), "utf-8"));
        String commandD = "powershell.exe Get-ChildItem " +
                "'C:\\Users\\" + System.getProperty("user.name") + "\\Desktop' " +
                "-Recurse -Include *.lnk ` " +
                "| Get-ItemProperty | Select FullName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcessD = Runtime.getRuntime().exec(commandD);
        powerShellProcessD.getOutputStream().close();
        String lineD = "";
        BufferedReader stdoutD = new BufferedReader(new InputStreamReader(powerShellProcessD.getInputStream()));
        while ((lineD = stdoutD.readLine()) != null) {
            if (!lineD.equals("")) // don't write out blank lines
            {
                String[] tempD = lineD.split("FullName : ");
                System.out.println(tempD[1]);
                fullPathD.write(tempD[1]);
                fullPathD.write("\r\n");
            }
        }
        stdoutD.close();
        fullPathD.close();

        //Desktop Name
        BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("desktopApps.txt"), "utf-8"));
        String command2 = "powershell.exe Get-ChildItem " +
                "'C:\\Users\\" + System.getProperty("user.name") + "\\Desktop' " +
                "-Recurse -Include *.lnk ` " +
                "| Get-ItemProperty | Select BaseName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcess2 = Runtime.getRuntime().exec(command2);
        powerShellProcess2.getOutputStream().close();
        String line2 = "";
        int nolines2 = 0;
        BufferedReader stdout2 = new BufferedReader(new InputStreamReader(powerShellProcess2.getInputStream()));
        while ((line2 = stdout2.readLine()) != null) {
            if (!line2.equals("")) // don't write out blank lines
            {
                System.out.println(line2);
                writer2.write(line2, 0, line2.length());
                writer2.write("\r\n");
            }
        }
        stdout2.close();
        writer2.close();
        try (BufferedReader b2 = new BufferedReader(new FileReader("desktopApps.txt"))) {

            while (b2.readLine() != null) {

                nolines2++;
            }

        }
        FileWriter fw2 = new FileWriter("output2.txt");
        BufferedWriter bw2 = new BufferedWriter(fw2);
        try (BufferedReader br2 = new BufferedReader(new FileReader("desktopApps.txt"))) {
            bw2.write("{\"Desktop Programs\":[\n");
            bw2.newLine();
            int count2 = 1;

            int lines2 = 0;
            String put2 = "";

            while ((line2 = br2.readLine()) != null) {
                lines2++;
                String split2 = line2.trim();
                String[] dir2 = split2.split(":");
                int i = 0;

                while (i <= dir2.length - 1) {
                    if (lines2 < nolines2) {
                        put2 = dir2[i].trim();
                        bw2.write("{\"" + put2 + "\":");
                        i++;
                        put2 = dir2[i].trim();
                        bw2.write("\"" + put2 + "\"},\n");
                        bw2.newLine();
                        i++;
                        count2 = 1;
                    } else {
                        put2 = dir2[i].trim();
                        bw2.write("\"" + put2 + "\":");
                        i++;
                        put2 = dir2[i].trim();
                        bw2.write("\"" + put2 + "\"}\n");
                        bw2.newLine();
                        i++;
                        count2 = 1;
                    }
                }
            }
            bw2.write("]}");
        }
        bw2.close();

        //ProgramFiles86 Full Path
        BufferedWriter fullPathP = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ProgramFiles86AppsFullPath.txt"), "utf-8"));
        String commandP = "powershell.exe Get-ChildItem " +
                "'C:\\Program Files (x86)' " +
                "-Recurse -Include *.exe ` " +
                "| Get-ItemProperty | Select FullName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcessP = Runtime.getRuntime().exec(commandP);
        powerShellProcessP.getOutputStream().close();
        String lineP = "";
        BufferedReader stdoutP = new BufferedReader(new InputStreamReader(powerShellProcessP.getInputStream()));
        while ((lineP = stdoutP.readLine()) != null) {
            if (!lineP.equals("")) // don't write out blank lines
            {
                String[] tempP = lineP.split("FullName : ");
                System.out.println(tempP[1]);
                fullPathP.write(tempP[1]);
                fullPathP.write("\r\n");
            }
        }
        stdoutP.close();
        fullPathP.close();

        //ProgramFiles86
        BufferedWriter writer3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("programFiles86Apps.txt"), "utf-8"));
        String command3 = "powershell.exe Get-ChildItem " +
                "'C:\\Program Files (x86)' " +
                "-Recurse -Include *.exe ` | Get-ItemProperty | Select BaseName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcess3 = Runtime.getRuntime().exec(command3);
        powerShellProcess3.getOutputStream().close();
        String line3 = "";
        int nolines3 = 0;
        BufferedReader stdout3 = new BufferedReader(new InputStreamReader(powerShellProcess3.getInputStream()));
        while ((line3 = stdout3.readLine()) != null) {
            if (!line3.equals("")) // don't write out blank lines
            {
                writer3.write(line3, 0, line3.length());
                writer3.write("\r\n");
            }
        }
        stdout3.close();
        writer3.close();

        try (BufferedReader b3 = new BufferedReader(new FileReader("programFiles86Apps.txt"))) {

            while (b3.readLine() != null) {

                nolines3++;
            }

        }
        FileWriter fw3 = new FileWriter("output3.txt");
        BufferedWriter bw3 = new BufferedWriter(fw3);
        try (BufferedReader br3 = new BufferedReader(new FileReader("programFiles86Apps.txt"))) {
            bw3.write("{\"Program Files (86) Programs\":[\n");
            bw3.newLine();
            int count3 = 1;

            int lines3 = 0;
            String put3 = "";

            while ((line3 = br3.readLine()) != null) {
                lines3++;
                String split3 = line3.trim();
                String[] dir3 = split3.split(":");
                int i = 0;

                while (i <= dir3.length - 1) {
                    if (lines3 < nolines3) {
                        put3 = dir3[i].trim();
                        bw3.write("{\"" + put3 + "\":");
                        i++;
                        put3 = dir3[i].trim();
                        bw3.write("\"" + put3 + "\"},\n");
                        bw3.newLine();
                        i++;
                        count3 = 1;
                    } else {
                        put3 = dir3[i].trim();
                        bw3.write("\"" + put3 + "\":");
                        i++;
                        put3 = dir3[i].trim();
                        bw3.write("\"" + put3 + "\"}\n");
                        bw3.newLine();
                        i++;
                        count3 = 1;
                    }
                }
            }
            bw3.write("]}");
        }
        bw3.close();

        //Program Files Full Path
        BufferedWriter fullPathP2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ProgramFilesAppsFullPath.txt"), "utf-8"));
        String commandP2 = "powershell.exe Get-ChildItem " +
                "'C:\\Program Files' " +
                "-Recurse -Include *.exe ` " +
                "| Get-ItemProperty | Select FullName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcessP2 = Runtime.getRuntime().exec(commandP2);
        powerShellProcessP2.getOutputStream().close();
        String lineP2 = "";
        BufferedReader stdoutP2 = new BufferedReader(new InputStreamReader(powerShellProcessP2.getInputStream()));
        while ((lineP2 = stdoutP2.readLine()) != null) {
            if (!lineP2.equals("")) // don't write out blank lines
            {
                String[] tempP2 = lineP2.split("FullName : ");
                System.out.println(tempP2[1]);
                fullPathP2.write(tempP2[1]);
                fullPathP2.write("\r\n");
            }
        }
        stdoutP2.close();
        fullPathP2.close();

        //Program Files Name
        BufferedWriter writer4 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("programFilesApps.txt"), "utf-8"));
        String command4 = "powershell.exe Get-ChildItem -path " +
                "'C:\\Program Files' " +
                "-Recurse -Include *.exe ` | Get-ItemProperty | Select BaseName | Format-List * | Format-Wide | out-string -Width 4096";
        Process powerShellProcess4 = Runtime.getRuntime().exec(command4);
        powerShellProcess4.getOutputStream().close();
        String line4 = "";
        int nolines4 = 0;
        BufferedReader stdout4 = new BufferedReader(new InputStreamReader(powerShellProcess4.getInputStream()));
        while ((line4 = stdout4.readLine()) != null) {
            if (!line4.equals("")) // don't write out blank lines
            {
                writer4.write(line4, 0, line4.length());
                writer4.write("\r\n");
            }
        }
        stdout4.close();
        writer4.close();

        try (BufferedReader b4 = new BufferedReader(new FileReader("programFilesApps.txt"))) {

            while (b4.readLine() != null) {


                nolines4++;
            }

        }
        FileWriter fw4 = new FileWriter("output4.txt");
        BufferedWriter bw4 = new BufferedWriter(fw4);
        try (BufferedReader br4 = new BufferedReader(new FileReader("programFilesApps.txt"))) {
            bw4.write("{\"Program Files Programs\":[\n");
            bw4.newLine();
            int count4 = 1;

            int lines4 = 0;
            String put4 = "";

            while ((line4 = br4.readLine()) != null) {
                lines4++;
                String split4 = line4.trim();
                String[] dir4 = split4.split(":");
                int i = 0;

                while (i <= dir4.length - 1) {
                    if (lines4 < nolines4) {
                        put4 = dir4[i].trim();
                        bw4.write("{\"" + put4 + "\":");
                        i++;
                        put4 = dir4[i].trim();
                        bw4.write("\"" + put4 + "\"},\n");
                        bw4.newLine();
                        i++;
                        count4 = 1;
                    } else {
                        put4 = dir4[i].trim();
                        bw4.write("\"" + put4 + "\":");
                        i++;
                        put4 = dir4[i].trim();
                        bw4.write("\"" + put4 + "\"}\n");
                        bw4.newLine();
                        i++;
                        count4 = 1;
                    }
                }
            }
            bw4.write("]}");
        }
        bw4.close();

        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess3.getErrorStream()));
        while ((line4 = stderr.readLine()) != null) {
            System.out.println(line4);
        }
        stderr.close();
        System.out.println("Done");


    }
}


