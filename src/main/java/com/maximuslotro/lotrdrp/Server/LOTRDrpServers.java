package com.maximuslotro.lotrdrp.Server;

import java.util.HashMap;
import java.util.Map;

public class LOTRDrpServers {

	private static final Map<String, ISupportedServer> servers = new HashMap<>();

	public static void initServers() {
		addServer("LOTR Mod: Official Server", "lotr.g.akliz.net");
		addServer("Abyss of Middle Earth", "51.81.43.65:27110");
		addServer("Adventures of Middle-Earth", "66.59.210.181:25564");
		addServer("Aeons of Ainulindalë", "ainulindale.apexmc.co");
		addServer("Age of Sauron", "5.135.80.105:25615");
		addServer("AncarCraft: The Third Age", "mc.ancarnetwork.online");
		addServer("Anduins Wrath", "anduinswrath.com");
		addServer("Arda's Legends", "178.33.208.218:25650");
		addServer("Ascension d'Arda", "91.121.65.169:25567");
		addServer("Blood on Mithril", "bloodonmithril.com");
		addServer("Darkness upon Middle-Earth", "dumelotr.dedimc.io");
		addServer("Doom of Mandos", "dom.apexmc.co");
		addServer("History of Middle-earth", "game05.koopjeserver.nl:28000");
		addServer("Isildur's Bane LOTR Server", "isildursbane.mcph.co");
		addServer("It's an even hARDA world", "172.107.244.82:25599");
		addServer("Lord of Embers: Third Age", "thelordofembers.com");
		addServer("Lords of Middle-earth", "51.222.245.105 ");
		addServer("LOTR Survival", "lotrmc.net");
		addServer("LOTR Survival Old", "lotrmc.net:25566");
		addServer("LOTR: A Story in Middle-earth", "51.195.61.72:25580");
		addServer("LoTRCircles", "lotr.circlecraft.info");
		addServer("Realms of Arda", "realmsofarda.apexmc.co");
		addServer("Rekindled Legacy", "ganymede.cheesehosting.net:57206");
		addServer("Tales of Middle Earth", "renewedtome.g.akliz.net");
		addServer("The One Ring", "theoneringofficial.com");
		addServer("The Rise of Middle Earth", "23.139.82.27:2356");
		addServer("The Rise of Morgoth", "5.62.71.7:21987");
		addServer("The Second Server That Wont Rule Anything", "158.69.55.2");
		addServer("Third age: Rise of War", "minecraft1259.omgserv.com:10095");
		addServer("Vintage Arda", "vintagearda.g.akliz.net");
		addServer("War of the Ring", "wotrmc-world1.com");
	}

	public static void addServer(String name, String ip) {//, String iconName) {
		ISupportedServer server = new ISupportedServer() {
			public String name() {
				return name;
			}

			public String ip() {
				return ip;
			}

			public String iconName() {
				//return iconName;
				return "";
			}
		};
		getServers().put(ip, server);
	}

	public static Map<String, ISupportedServer> getServers() {
		return servers;
	}
}
