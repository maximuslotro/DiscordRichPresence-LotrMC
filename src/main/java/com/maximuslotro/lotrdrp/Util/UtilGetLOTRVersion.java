package com.maximuslotro.lotrdrp.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.maximuslotro.lotrdrp.LOTRDrpConnector;
import com.maximuslotro.lotrdrp.LOTRDrpMain;

public class UtilGetLOTRVersion {
	public static void getLotrV() {
		if (!checkedVersion) {
			Thread checkThread = new Thread("Checker") {
				public void run() {
					try {
						URL url = new URL(UtilGetLOTRVersion.versionURL);
						BufferedReader versionReader = new BufferedReader(new InputStreamReader(url.openStream()));
						String lotrVersion = "";
						String line;
						while ((line = versionReader.readLine()) != null)
							lotrVersion = lotrVersion.concat(line);
						versionReader.close();
						lotrVersion = lotrVersion.trim();
						LOTRDrpMain.LOG.info("DRP LOTR Version Check Suscuess");
						LOTRDrpConnector.version = lotrVersion;
					} catch (Exception e) {
						LOTRDrpMain.LOG.error("DRP LOTR Version Check Failed");
					}
				}
			};
			checkThread.setDaemon(checkedVersion = true);
			checkThread.start();
		}
	}

	private static String versionURL = "https://raw.githubusercontent.com/maximuslotro/DiscordRichPresence-LotrMC/main/Info/VERSION_LOTR.txt";

	protected static boolean checkedVersion = false;
}
