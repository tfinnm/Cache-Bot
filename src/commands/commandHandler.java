package commands;

import net.dv8tion.jda.api.entities.Message;

public class commandHandler {

	private commandHandler() {}
	
	public static void loadCommands() {
		new advise();
		new checkbans();
		new clearAdvisory();
		new clearBan();
		new help();
		new ping();
		new privacy();
		new purge();
		new raid();
		new unraid();
		new reload();
		new runuser();
		new serverstats();
		new servers();
		new stats();
		new sync();
	}
	
	public static void handleMessage(Message msg) {
		for (command t: command.Commands) {
			if (t.trigger(msg)) {
				if (t.checkPerms(msg)) t.action(msg);
			}
		}
	}
	
}
