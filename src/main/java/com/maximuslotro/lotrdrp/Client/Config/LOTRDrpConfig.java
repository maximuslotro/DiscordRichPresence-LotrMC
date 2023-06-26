package com.maximuslotro.lotrdrp.Client.Config;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

import com.maximuslotro.lotrdrp.LOTRDrpMain;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class LOTRDrpConfig {
	public static String[] allThemesId = new String[] { 
			"minecraft-lord-of-the-rings-mod", "mithril", "pvp", "quest", "book", "smokeship", "siege", "coin", "horn", "truesilver", 
			"kingsfoil", "compass", "bounty", "gondolin", "flesh", "evenstar" };
		  
	private static String[] allThemes = new String[] { 
			"default", "mithril", "pvp", "quest", "book", "smoke-ship", "pipe", "coin", "horn", "true-silver", 
			"kings-foil", "compass", "random", "bounty", "gondolin", "flesh", "evenstar" };
		  
	public static Configuration config;
		  
	public static String cfgVersion;
		  
	public static boolean cfgEnableServers;
		  
	public static boolean cfgEnableDrp;
		  
	public static boolean enableVersionCheck;
		  
	public static boolean incognitoMode;
		  
	public static String theme;
		  
	public static Integer themeNo;
		  
	public static String getAllThemeNames() {
		return Arrays.toString((Object[])allThemes) + "random";
	}
		  
	public static void init(String cfgDir) {
		if (config == null) {
			File path = new File(cfgDir + "/LOTRDrp/" + "lotrdrp" + ".cfg");
			config = new Configuration(path);
			loadConfig();
			LOTRDrpMain.LOG.info("Config Inited");
		}
	}

	private static void loadConfig() {
		cfgEnableServers = config.getBoolean("Enable Server Icons", "general", true,
				"If this is enabled LOTRDrp will give custom images and text for supported servers.");
		enableVersionCheck = config.getBoolean("Enable Version Check", "general", true,
				"If this is enabled LOTRDrp will check for updates.");
		cfgEnableDrp = config.getBoolean("Enable Presence", "general", true,
				"If this is enabled your LOTRDrp pressence will not apear.");
		theme = config.getString("DRP Theme", "general", "random", "Themes " + getAllThemeNames());
		incognitoMode = config.getBoolean("Incognito Mode", "general", false,
				"If this is enabled your server and world name will be hiden and the supported server system will be disabled.");
		if (theme != allThemes[0] || theme != allThemes[1] || theme != allThemes[2] || theme != allThemes[3]
				|| theme != allThemes[4] || theme != allThemes[5] || theme != allThemes[6] || theme != allThemes[7]
				|| theme != allThemes[8] || theme != allThemes[9] || theme != allThemes[10] || theme != allThemes[11]
				|| theme != allThemes[13] || theme != allThemes[14] || theme != allThemes[15] || theme != allThemes[16]
				|| theme != allThemes[17]) {
			Random rand = new Random();
			themeNo = Integer.valueOf(rand.nextInt(allThemesId.length));
			theme= "random";
		}
		LOTRDrpMain.theme = "minecraft-lord-of-the-rings-mod";
		LOTRDrpMain.LOG.error("Invalid Theme " + theme);
		if (theme.equalsIgnoreCase("default")) {
			LOTRDrpMain.theme = "minecraft-lord-of-the-rings-mod";
			LOTRDrpMain.LOG.info("theme set to default");
		} else if (theme.equalsIgnoreCase("mithril")) {
			LOTRDrpMain.theme = "mithril";
			LOTRDrpMain.LOG.info("theme set to mithril");
		} else if (theme.equalsIgnoreCase("pvp")) {
			LOTRDrpMain.theme = "pvp";
			LOTRDrpMain.LOG.info("theme set to pvp");
		} else if (theme.equalsIgnoreCase("quest")) {
			LOTRDrpMain.theme = "quest";
			LOTRDrpMain.LOG.info("theme set to quest");
		} else if (theme.equalsIgnoreCase("kings-foil")) {
			LOTRDrpMain.theme = "kingsfoil";
			LOTRDrpMain.LOG.info("theme set to kings-foil");
		} else if (theme.equalsIgnoreCase("book")) {
			LOTRDrpMain.theme = "book";
			LOTRDrpMain.LOG.info("theme set to book");
		} else if (theme.equalsIgnoreCase("smoke-ship")) {
			LOTRDrpMain.theme = "smokeship";
			LOTRDrpMain.LOG.info("theme set to smoke-ship");
		}
		if (theme.equalsIgnoreCase("coin")) {
			LOTRDrpMain.theme = "coin";
			LOTRDrpMain.LOG.info("theme set to coin");
		} else if (theme.equalsIgnoreCase("pipe")) {
			LOTRDrpMain.theme = "siege";
			LOTRDrpMain.LOG.info("theme set to pipe");
		} else if (theme.equalsIgnoreCase("horn")) {
			LOTRDrpMain.theme = "horn";
			LOTRDrpMain.LOG.info("theme set to horn");
		} else if (theme.equalsIgnoreCase("compass")) {
			LOTRDrpMain.theme = "compass";
			LOTRDrpMain.LOG.info("theme set to compass");
		} else if (theme.equalsIgnoreCase("true-silver")) {
			LOTRDrpMain.theme = "truesilver";
			LOTRDrpMain.LOG.info("theme set to true-silver");
		} else if (theme.equalsIgnoreCase("random")) {
			LOTRDrpMain.theme = allThemesId[themeNo.intValue()];
			LOTRDrpMain.LOG.info("theme set to random");
		} else if (theme.equalsIgnoreCase("bounty")) {
			LOTRDrpMain.theme = "bounty";
			LOTRDrpMain.LOG.info("theme set to bounty");
		} else if (theme.equalsIgnoreCase("flesh")) {
			LOTRDrpMain.theme = "flesh";
			LOTRDrpMain.LOG.info("theme set to flesh");
		} else if (theme.equalsIgnoreCase("gondolin")) {
			LOTRDrpMain.theme = "gondolin";
			LOTRDrpMain.LOG.info("theme set to gondolin");
		} else if (theme.equalsIgnoreCase("evenstar")) {
			LOTRDrpMain.theme = "evenstar";
			LOTRDrpMain.LOG.info("theme set to evenstar");
		}
		if (config.hasChanged()) {
			config.save();
			LOTRDrpMain.LOG.info("Config Saved");
		}
	}

	@SubscribeEvent
	public void onCfgChnageEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals("lotrdrp")) {
			loadConfig();
			LOTRDrpMain.LOG.info("Config Has Been Changed");
		}
	}

	public static Configuration getConfig() {
		return config;
	}
}
