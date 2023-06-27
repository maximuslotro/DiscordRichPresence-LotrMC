package com.maximuslotro.lotrdrp;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.maximuslotro.lotrdrp.Client.LOTRDrpClientProxy;
import com.maximuslotro.lotrdrp.Client.Config.LOTRDrpConfig;
import com.maximuslotro.lotrdrp.Server.LOTRDrpServers;
import com.maximuslotro.lotrdrp.Util.UtilGetLOTRVersion;
import com.maximuslotro.lotrdrp.Util.UtilGetSeason;
import com.maximuslotro.lotrdrp.Util.UtilRemoveHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.jcm.discordgamesdk.GameSDKException;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = LOTRDrpMain.MODID, version = LOTRDrpMain.VERSION, name = "LOTR Drp", acceptedMinecraftVersions = "1.7.10", guiFactory = "com.maximuslotro.lotrdrp.Client.Config.LOTRDrpGui")
public class LOTRDrpMain
{
	@SidedProxy(clientSide = "com.maximuslotro.lotrdrp.Client.LOTRDrpClientProxy")
	public static LOTRDrpClientProxy proxy;
	
	public static File configDir;
	
	public static final String MODID = "lotrdrp";
	
    public static final String VERSION = "1.1.0";

    public static final String NAME = "LOTR Drp";

    @Instance
    public static LOTRDrpMain drp = new LOTRDrpMain();
    
    public LOTRDrpConnector discord;
    
    private boolean playingOnSupportedServer = false;
    
    public static final Logger LOG = LogManager.getLogger("LOTR Drp");
    
    public static String theme = "";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	LOTRDrpServers.initServers();
    	UtilGetSeason.getSeason();
    	UtilGetLOTRVersion.getLotrV();
    	LOTRDrpEventHandler handler = new LOTRDrpEventHandler();
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    	LOG.info("DRP Thanks For Using LOTR Drp V"+VERSION);
    	proxy.preload();
    	String cfgDir = event.getModConfigurationDirectory().toString();
    	LOTRDrpConfig.init(cfgDir);
    	FMLCommonHandler.instance().bus().register(new LOTRDrpConfig());
    	UtilRemoveHandler.removeOldConfig(event);
    	configDir = event.getModConfigurationDirectory();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	try {
			this.discord = new LOTRDrpConnector();
		} catch (IOException|GameSDKException e) {
	    	LOG.info("DRP Failed to create LOTRDrpConnector");
	    	e.printStackTrace();
	    	discord=null;
		}
        //this.discord.run();
        proxy.postload();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	proxy.load();
    	if (UtilGetSeason.isSeason)
    		theme = UtilGetSeason.seasonId;
    }
    
    public void setPlayingOnSupportedServer(boolean b) {
    	this.playingOnSupportedServer = b;
    }
    
    public boolean isPlayingOnSupportedServer() {
    	return this.playingOnSupportedServer;
    }
}
