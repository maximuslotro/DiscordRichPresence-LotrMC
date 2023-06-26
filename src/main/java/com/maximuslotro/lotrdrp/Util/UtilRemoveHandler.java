package com.maximuslotro.lotrdrp.Util;

import java.io.File;

import com.maximuslotro.lotrdrp.LOTRDrpMain;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class UtilRemoveHandler {
	public static void removeOldConfig(FMLPreInitializationEvent event) {
		File oldconfig = new File(event.getModConfigurationDirectory(), "lotrdrp.cfg");
		if (oldconfig.exists()) {
			oldconfig.deleteOnExit();
			LOTRDrpMain.LOG.info("Removed Old Config File");
		}
	}

	public static void resetConfig(FMLPreInitializationEvent event) {
		File config = new File(event.getModConfigurationDirectory(), "lotrdrp.cfg");
		if (config.exists()) {
			config.deleteOnExit();
			LOTRDrpMain.LOG.info("Reset Config File");
		}
	}
}
