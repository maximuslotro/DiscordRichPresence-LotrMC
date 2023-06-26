package com.maximuslotro.lotrdrp.Client.Config;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class LOTRDrpGuiConfig extends GuiConfig{
	public LOTRDrpGuiConfig(GuiScreen guiScreen) {
		super(guiScreen, (new ConfigElement<Object>(LOTRDrpConfig.getConfig().getCategory("general"))).getChildElements(),
				"lotrdrp", false, false, GuiConfig.getAbridgedConfigPath(LOTRDrpConfig.getConfig().toString()));
	}
}
