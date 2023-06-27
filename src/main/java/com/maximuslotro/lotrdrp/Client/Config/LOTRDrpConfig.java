package com.maximuslotro.lotrdrp.Client.Config;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.maximuslotro.lotrdrp.LOTRDrpMain;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class LOTRDrpConfig {

	public static List<String> allThemes = Arrays.asList(new String[] { 
			"default", "book", "chill", "coin", "book", "elfstar", "floppa", "gear", "goldring", "gondolin", 
			"hobbitring", "horn", "mithpick", "mithril", "ring", "scroll", "seal", "termite", "trophy", "wargskinice", "random"});

	public static Configuration config;

	public static boolean cfgEnableServers;

	public static boolean cfgEnableDrp;

	public static boolean enableVersionCheck;

	public static boolean incognitoMode;

	public static String theme;

	public static Integer themeNo;

	public static String getAllThemeNames() {
		return String.join(",", allThemes);
	}

	public static void init(String cfgDir) {
		if (config == null) {
			File path = new File(cfgDir + "/LOTRDrp/" + "lotrdrp" + ".cfg");
			config = new Configuration(path);
			loadConfig();
			LOTRDrpMain.LOG.info("DRP Config Inited");
		}
	}

	private static void loadConfig() {
		cfgEnableServers = config.getBoolean("Enable Server Icons", "general", true, "If this is enabled LOTRDrp will give custom images and text for supported servers.");
		enableVersionCheck = config.getBoolean("Enable Version Check", "general", true, "If this is enabled LOTRDrp will check for updates.");
		cfgEnableDrp = config.getBoolean("Enable Presence", "general", true, "If this is enabled your LOTRDrp pressence will not apear.");
		theme = config.getString("DRP Theme", "general", "random", "Themes " + getAllThemeNames());
		incognitoMode = config.getBoolean("Incognito Mode", "general", false, "If this is enabled your server and world name will be hiden and the supported server system will be disabled.");
		if (!allThemes.contains(theme)) {
			LOTRDrpMain.LOG.error("DRP Invalid Theme " + theme);
			theme= "random";
		}
		changeTheme();
		if (theme.equalsIgnoreCase("default")) {
			LOTRDrpMain.theme = "ring";
			LOTRDrpMain.LOG.info("DRP theme set to ring");
		}
		else if (theme.equalsIgnoreCase("random")) {
			LOTRDrpMain.theme = allThemes.get(themeNo);
			LOTRDrpMain.LOG.info("DRP set random theme");
		}
		else {
			LOTRDrpMain.theme = theme;
			LOTRDrpMain.LOG.info("DRP theme set to "+theme);
		}
		
		if (config.hasChanged()) {
			config.save();
			LOTRDrpMain.LOG.info("DRP Config Saved");
		}
	}
	public static void changeTheme() {
		Random rand = new Random();
		themeNo = Integer.valueOf(rand.nextInt(allThemes.size()));
		if(theme.equalsIgnoreCase("random")) {
			LOTRDrpMain.theme = allThemes.get(themeNo);
		}
	}
	@SubscribeEvent
	public void onCfgChnageEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals("lotrdrp")) {
			loadConfig();
			LOTRDrpMain.LOG.info("DRP Config Has Been Changed");
		}
	}

	public static Configuration getConfig() {
		return config;
	}
}
