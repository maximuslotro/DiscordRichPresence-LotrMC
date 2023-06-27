package com.maximuslotro.lotrdrp.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.maximuslotro.lotrdrp.LOTRDrpMain;

public class UtilGetSeason {
	public static void getSeason() {
		if (!checkedseason) {
			Thread checkThread = new Thread("Checker") {
				public void run() {
					try {
						boolean seasonCheck=false;
						LOTRDrpMain.LOG.info("DRP Checking Season");
						URL url = new URL(UtilGetSeason.seasonUrl);
						BufferedReader seasonReader = new BufferedReader(new InputStreamReader(url.openStream()));
						String season = "";
						String check = "";
						String line;
						while ((line = seasonReader.readLine()) != null&& !seasonCheck) {
							check = check.concat(line);
							if (check.contains("true")) {
								UtilGetSeason.isSeason=true;
								season =season.concat(seasonReader.readLine());
								UtilGetSeason.seasonId = season;
								seasonCheck=true;
								LOTRDrpMain.LOG.info("DRP Found Season");
							}
							else if(check.contains("false")){
								UtilGetSeason.isSeason=false;
								seasonCheck=true;
								LOTRDrpMain.LOG.info("DRP No Season Found");
							}
							else {
								UtilGetSeason.isSeason=false;
								seasonCheck=true;
								LOTRDrpMain.LOG.error("DRP Couldnt find season");
							}
						}
						seasonReader.close();
						season = season.trim();
					} catch (Exception e) {
						LOTRDrpMain.LOG.error("DRP Season Check Failed");
					}
				}
			};
			checkThread.setDaemon(checkedseason = true);
			checkThread.start();
		}
	}

	private static String seasonUrl = "https://raw.githubusercontent.com/maximuslotro/DiscordRichPresence-LotrMC/main/Info/SEASON_DRP.txt";

	protected static boolean checkedseason = false;

	public static boolean isSeason;

	public static String seasonId;
}
