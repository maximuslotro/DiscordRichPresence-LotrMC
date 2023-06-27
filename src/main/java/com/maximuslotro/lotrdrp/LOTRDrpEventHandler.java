package com.maximuslotro.lotrdrp;

import com.maximuslotro.lotrdrp.Client.Config.LOTRDrpConfig;
import com.maximuslotro.lotrdrp.Server.LOTRDrpSupportedServerHandler;
import com.maximuslotro.lotrdrp.Util.LOTRDrpUpdateChecker;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;

public class LOTRDrpEventHandler {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void connectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		LOTRDrpUpdateChecker.reloadUpdateCheck();
		LOTRDrpMain.drp.discord.updateTimestamp();
		if (LOTRDrpConfig.cfgEnableServers && !e.isLocal) {
			LOTRDrpSupportedServerHandler.handleServerJoin((Minecraft.getMinecraft().func_147104_D()).serverIP, e);
		} else if (e.isLocal) {
			LOTRDrpSupportedServerHandler.handleSPJoin();
		}
	}

	@SubscribeEvent
	public void disconnectedFromServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
		LOTRDrpConfig.changeTheme();
		if (LOTRDrpMain.drp.isPlayingOnSupportedServer())
			LOTRDrpMain.drp.setPlayingOnSupportedServer(false);
		LOTRDrpMain.drp.discord.updateTimestamp();
		LOTRDrpMain.drp.discord.updateState("In Menu IGN:" + Minecraft.getMinecraft().getSession().getUsername(),
				"LOTR Mod " + LOTRDrpConnector.version);
		LOTRDrpMain.drp.discord.updateText("", "Snazzy Presence Brought To You By LOTR Drp!");
		LOTRDrpMain.drp.discord.updateImages("", "ring");
	}
}
