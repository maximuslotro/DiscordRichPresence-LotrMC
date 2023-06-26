package com.maximuslotro.lotrdrp;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.maximuslotro.lotrdrp.Client.Config.LOTRDrpConfig;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import net.minecraft.client.Minecraft;

public class LOTRDrpConnector {
	private static final Long APP_ID = 432578629461999636L;

	public static String v = "";

	private Activity activityMain;

	private Core coreMain;

	public LOTRDrpConnector() throws IOException {
		if (LOTRDrpConfig.cfgEnableDrp) {
			File discordLibrary = downloadDiscordLibrary();
			if(discordLibrary == null)
			{
				System.err.println("Error downloading Discord SDK.");
				System.exit(-1);
			}
			// Initialize the Core
			Core.init(discordLibrary);
			
			try(CreateParams params = new CreateParams())
			{
				params.setClientID(APP_ID);
				params.setFlags(CreateParams.getDefaultFlags());
				// Create the Core
				try (Core core = new Core(params)) {
					coreMain = core;
					// Create the Activity
					try(Activity activity = new Activity())
					{
						activity.setDetails("LOTR Mod " + v);
						activity.setState("In Menu IGN:" + Minecraft.getMinecraft().getSession().getUsername());

						// Setting a start time causes an "elapsed" field to appear
						activity.timestamps().setStart(Instant.now());

						// Make a "cool" image show up
						//activity.assets().setLargeImage("simple");
						//activity.assets().setLargeText("Snazzy Presence Brought To You By LOTR Drp!");
						
						// Setting a join secret and a party ID causes an "Ask to Join" button to appear
						activity.party().size().setMaxSize(0);

						activityMain=activity;
						// Finally, update the current activity to our activity
						coreMain.activityManager().updateActivity(activityMain);
					}
				}
			}
		}
	}

	public void run() {
		(new Thread("RPC-Callback-Handler") {
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					LOTRDrpConnector.this.coreMain.activityManager().updateActivity(activityMain);
					LOTRDrpConnector.this.coreMain.runCallbacks();
					try {
						Thread.sleep(2000L);
					} catch (InterruptedException interruptedException) {
					}
				}
			}
		}).start();
	}

	public void updateTimestamp() {
		this.activityMain.timestamps().setStart(Instant.now());
	}

	public void updateState(String state, String details) {
		this.activityMain.setState(state);
		this.activityMain.setDetails(details);
	}

	public void updateText(String small, String large) {
		//this.activityMain.assets().setLargeImage(large);
		//this.activityMain.assets().setSmallImage(small);
	}

	public void updateImages(String small, String large) {
		//this.activityMain.assets().setLargeText(large);
		//this.activityMain.assets().setSmallText(small);
	}

	public static File downloadDiscordLibrary() throws IOException
	{
		// Find out which name Discord's library has (.dll for Windows, .so for Linux)
		String name = "discord_game_sdk";
		String suffix;

		String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		String arch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);

		if(osName.contains("windows"))
		{
			suffix = ".dll";
		}
		else if(osName.contains("linux"))
		{
			suffix = ".so";
		}
		else if(osName.contains("mac os"))
		{
			suffix = ".dylib";
		}
		else
		{
			throw new RuntimeException("cannot determine OS type: "+osName);
		}

		/*
		Some systems report "amd64" (e.g. Windows and Linux), some "x86_64" (e.g. Mac OS).
		At this point we need the "x86_64" version, as this one is used in the ZIP.
		 */
		if(arch.equals("amd64"))
			arch = "x86_64";

		// Path of Discord's library inside the ZIP
		String zipPath = "lib/"+arch+"/"+name+suffix;

		// Open the URL as a ZipInputStream
		URL downloadUrl = new URL("https://dl-game-sdk.discordapp.net/2.5.6/discord_game_sdk.zip");
		HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
		connection.setRequestProperty("User-Agent", "discord-game-sdk4j (https://github.com/JnCrMx/discord-game-sdk4j)");
		ZipInputStream zin = new ZipInputStream(connection.getInputStream());

		// Search for the right file inside the ZIP
		ZipEntry entry;
		while((entry = zin.getNextEntry())!=null)
		{
			if(entry.getName().equals(zipPath))
			{
				// Create a new temporary directory
				// We need to do this, because we may not change the filename on Windows
				File tempDir = new File(System.getProperty("java.io.tmpdir"), "java-"+name+System.nanoTime());
				if(!tempDir.mkdir())
					throw new IOException("Cannot create temporary directory");
				tempDir.deleteOnExit();

				// Create a temporary file inside our directory (with a "normal" name)
				File temp = new File(tempDir, name+suffix);
				temp.deleteOnExit();

				// Copy the file in the ZIP to our temporary file
				Files.copy(zin, temp.toPath());

				// We are done, so close the input stream
				zin.close();

				// Return our temporary file
				return temp;
			}
			// next entry
			zin.closeEntry();
		}
		zin.close();
		// We couldn't find the library inside the ZIP
		return null;
	}
}
