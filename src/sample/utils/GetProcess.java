package sample.utils;

/**
 * Created by Johnny Bishara on 23/03/2016.
 */
import java.io.*;
import java.util.*;
public class GetProcess {
    public static List listRunningProcesses() {
        List<String> processes = new ArrayList<String>();
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe /nh");
            BufferedReader input = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("")) {
// keep only the process name
                    processes.add(line.substring(0, line.indexOf("  ")));
                }
            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        return processes;
    }
    public static void main(String[] args){
        try {
            String line;
            Process p = Runtime.getRuntime()
                    .exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fi \"STATUS eq RUNNING\"");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    public static void msgBox(String msg) {
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
                        null, msg, "WindowsUtils",
                javax.swing.JOptionPane.DEFAULT_OPTION);
    }
}