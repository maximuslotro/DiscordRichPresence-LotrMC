package com.maximuslotro.lotrdrp.Client;

import com.maximuslotro.lotrdrp.LOTRDrpMain;
import com.maximuslotro.lotrdrp.Client.Config.LOTRDrpConfig;
import com.maximuslotro.lotrdrp.Util.LOTRDrpUpdateChecker;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

public class LOTRDrpClientTickHandler {
	LOTRDrpClientTickHandler() {
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onPreRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
		if (LOTRDrpConfig.enableVersionCheck)
			LOTRDrpUpdateChecker.checkForUpdates();
		if(LOTRDrpMain.drp.discord==null) {
			ChatComponentText chatComponentText = new ChatComponentText(
					"[LOTR Drp] FAILED TO START! Please report this if it is reproducible!");
			chatComponentText.getChatStyle().setColor(EnumChatFormatting.RED);
			EntityClientPlayerMP entityClientPlayerMP = (Minecraft.getMinecraft()).thePlayer;
			if (entityClientPlayerMP != null)
				entityClientPlayerMP.addChatComponentMessage((IChatComponent) chatComponentText);
		}
	}
}
