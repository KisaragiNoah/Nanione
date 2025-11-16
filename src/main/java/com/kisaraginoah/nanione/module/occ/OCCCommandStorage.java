package com.kisaraginoah.nanione.module.occ;

public class OCCCommandStorage {

    private static String storedCommand = "";

    public static void setStoredCommand(String cmd) {
        storedCommand = cmd;
    }

    public static String getStoredCommand() {
        return storedCommand;
    }

    public static boolean hasCommand() {
        return storedCommand != null && !storedCommand.isEmpty();
    }
}
