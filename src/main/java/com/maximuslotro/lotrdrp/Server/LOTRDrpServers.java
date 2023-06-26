package com.maximuslotro.lotrdrp.Server;

import java.util.HashMap;
import java.util.Map;

public class LOTRDrpServers {
	public static ISupportedServer TOS = new ISupportedServer() {
		public String name() {
			return "LOTR Mod: Official Server";
		}

		public String ip() {
			return "lotr.g.akliz.net";
		}

		public String iconName() {
			return "tos";
		}
	};

	public static ISupportedServer LOTRSurvival = new ISupportedServer() {
		public String name() {
			return "LOTR Survival";
		}

		public String ip() {
			return "lotrmc.net";
		}

		public String iconName() {
			return "serv";
		}
	};

	public static ISupportedServer TheRingOfPower = new ISupportedServer() {
		public String name() {
			return "The Ring of Power";
		}

		public String ip() {
			return "trop.g.akliz.net";
		}

		public String iconName() {
			return "trop";
		}
	};

	public static ISupportedServer OtisSiegeServer = new ISupportedServer() {
		public String name() {
			return "The LOTR Siege Server";
		}

		public String ip() {
			return "144.217.39.165:25586";
		}

		public String iconName() {
			return "pvpServer";
		}
	};

	public static ISupportedServer SecondServerThatWillNotRuleAnything = new ISupportedServer() {
		public String name() {
			return "The Second Server That Wont Rule Anything";
		}

		public String ip() {
			return "t2nds.net";
		}

		public String iconName() {
			return "second";
		}
	};

	public static ISupportedServer DOME = new ISupportedServer() {
		public String name() {
			return "Dominion of Middle-Earth";
		}

		public String ip() {
			return "dominion.g.akliz.net";
		}

		public String iconName() {
			return "dome";
		}
	};

	public static ISupportedServer Ascension = new ISupportedServer() {
		public String name() {
			return "Ascension d'Arda";
		}

		public String ip() {
			return "149.202.121.115:27220";
		}

		public String iconName() {
			return "asc";
		}
	};

	public static ISupportedServer Edgorovs = new ISupportedServer() {
		public String name() {
			return "LOTR: Edgorovs Realm";
		}

		public String ip() {
			return "edgorovsrealm.apexmc.co";
		}

		public String iconName() {
			return "engov";
		}
	};

	public static ISupportedServer Champ = new ISupportedServer() {
		public String name() {
			return "The Champions Game";
		}

		public String ip() {
			return "tcg.apexmc.co";
		}

		public String iconName() {
			return "cg";
		}
	};

	public static ISupportedServer Reforged = new ISupportedServer() {
		public String name() {
			return "Middle Earth Reforged";
		}

		public String ip() {
			return "41.216.190.100:25565";
		}

		public String iconName() {
			return "minecraft-lord-of-the-rings-mod";
		}
	};

	public static ISupportedServer Quenta = new ISupportedServer() {
		public String name() {
			return "Ilu Ambar - Quenta Arda";
		}

		public String ip() {
			return "51.81.53.218:25566";
		}

		public String iconName() {
			return "quenta";
		}
	};

	private static final Map<String, ISupportedServer> servers = new HashMap<>();

	public static void initServers() {
		addServer(TOS);
		addServer(LOTRSurvival);
		addServer(TheRingOfPower);
		addServer(OtisSiegeServer);
		addServer(SecondServerThatWillNotRuleAnything);
		addServer(DOME);
		addServer(Ascension);
		addServer(Edgorovs);
		addServer(Champ);
		addServer(Reforged);
		addServer(Quenta);
	}

	public static void addServer(ISupportedServer server) {
		getServers().put(server.ip(), server);
	}

	public static Map<String, ISupportedServer> getServers() {
		return servers;
	}
}
