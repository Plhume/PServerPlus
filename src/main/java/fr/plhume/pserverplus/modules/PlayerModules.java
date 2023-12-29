package fr.plhume.pserverplus.modules;

import fr.plhume.pserverplus.PServerPlus;

public class PlayerModules {

    private static final PServerPlus plugin = PServerPlus.getInstance();

    public static boolean flyModule() {
        return plugin.getConfig().getBoolean("modules.fly");
    }

    public static boolean joinLeaveModule() {
        return plugin.getConfig().getBoolean("modules.join-leave");
    }
}
