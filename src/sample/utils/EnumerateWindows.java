package sample.utils;

import static sample.utils.EnumerateWindows.Kernel32.*;
import static sample.utils.EnumerateWindows.Psapi.*;
import static sample.utils.EnumerateWindows.User32DLL.*;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.PointerByReference;


import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.unix.X11;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EnumerateWindows {
    private static final int MAX_TITLE_LENGTH = 1024;

    public static void main(String[] args) throws Exception {
        //char[] buffer = new char[MAX_TITLE_LENGTH * 2];
//        GetWindowTextW(GetForegroundWindow(), buffer, MAX_TITLE_LENGTH);
//        System.out.println("Active window title: " + Native.toString(buffer));

        if (Platform.isWindows()) {
            final int PROCESS_VM_READ = 0x0010;
            final int PROCESS_QUERY_INFORMATION = 0x0400;
            final User32 user32 = User32.INSTANCE;
            ;
            WinDef.HWND windowHandle = user32.GetForegroundWindow();
            IntByReference pid = new IntByReference();
            user32.GetWindowThreadProcessId(windowHandle, pid);
            Pointer processHandle = Kernel32.OpenProcess(PROCESS_VM_READ | PROCESS_QUERY_INFORMATION, true, pid.getPointer());

            char[] filename = new char[512];
            Psapi.GetModuleBaseNameW(processHandle, Pointer.NULL, filename, filename.length);
            String name = new String(filename);
            System.out.println(name);
            if (name.endsWith("wwahost.exe")) { // Metro App
                // There is no stable API to get the current Metro app
                // But you can guestimate the name form the current directory of the process
                // To query this, see:
                // http://stackoverflow.com/questions/16110936/read-other-process-current-directory-in-c-sharp
            }
        }
    }

    static class Psapi {
        static { Native.register("psapi"); }
        public static native int GetModuleBaseNameW(Pointer hProcess, Pointer hmodule, char[] lpBaseName, int size);
    }

    static class Kernel32 {
        static { Native.register("kernel32"); }
        public static int PROCESS_QUERY_INFORMATION = 0x0400;
        public static int PROCESS_VM_READ = 0x0010;
        public static native int GetLastError();
        public static native Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, Pointer pointer);
    }

    static class User32DLL {
        static { Native.register("user32"); }
        public static native int GetWindowThreadProcessId(HWND hWnd, PointerByReference pref);
        public static native HWND GetForegroundWindow();
        public static native int GetWindowTextW(HWND hWnd, char[] lpString, int nMaxCount);
    }
}