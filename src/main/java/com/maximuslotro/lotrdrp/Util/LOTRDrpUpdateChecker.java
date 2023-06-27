package com.maximuslotro.lotrdrp.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.maximuslotro.lotrdrp.LOTRDrpMain;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class LOTRDrpUpdateChecker {
	public static void reloadUpdateCheck() {
		checkedUpdate = false;
	}

	public static void checkForUpdates() {
		if (!checkedUpdate) {
			Thread checkThread = new Thread("LOTR Drp Update Checker") {
				public void run() {
					try {
						URL url = new URL(LOTRDrpUpdateChecker.versionURL);
						BufferedReader updateReader = new BufferedReader(new InputStreamReader(url.openStream()));
						String updateVersion = "";
						String line;
						while ((line = updateReader.readLine()) != null)
							updateVersion = updateVersion.concat(line);
						updateReader.close();
						updateVersion = updateVersion.trim();
						if (!updateVersion.equals(LOTRDrpMain.VERSION)) {
							ChatComponentText chatComponentText = new ChatComponentText(
									"[LOTR Drp Update Checker] Update Available! " + updateVersion
											+ " Click Here To Download The Latest Update");
							chatComponentText.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
							chatComponentText.getChatStyle().setChatHoverEvent(
									new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent) new ChatComponentText(
											"Check Out Our Website And Download The Latest Version Here!")));
							chatComponentText.getChatStyle().setChatClickEvent(
									new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/maximuslotro/DiscordRichPresence-LotrMC/releases"));
							EntityClientPlayerMP entityClientPlayerMP = (Minecraft.getMinecraft()).thePlayer;
							if (entityClientPlayerMP != null)
								entityClientPlayerMP.addChatComponentMessage((IChatComponent) chatComponentText);
						} else {
							ChatComponentText chatComponentText = new ChatComponentText(
									"[LOTR Drp Update Checker] LOTR Drp Is Up To Date!");
							chatComponentText.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
							EntityClientPlayerMP entityClientPlayerMP = (Minecraft.getMinecraft()).thePlayer;
							if (entityClientPlayerMP != null)
								entityClientPlayerMP.addChatComponentMessage((IChatComponent) chatComponentText);
						}
					} catch (Exception e) {
						LOTRDrpMain.LOG.error("DRP Update Check Failed");
					}
				}
			};
			checkThread.setDaemon(checkedUpdate = true);
			checkThread.start();
		}
	}

	private static String versionURL = "https://github.com/maximuslotro/DiscordRichPresence-LotrMC/blob/main/Info/VERSION_DRP.txt";

	protected static boolean checkedUpdate = false;
}
