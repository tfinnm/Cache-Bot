package commands;

import java.io.IOException;
import core.settings;
import core.startup;
import net.dv8tion.jda.api.entities.Message;


public class reload extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"reload");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.isTfinnm(msg);
	}

	@Override
	public void action(Message msg) {
		try {
			startup.loadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		startup.runInitialTasks();
	}

	@Override
	public String helpText() {
		return "Reloads the bot.";
	}

	@Override
	public String name() {
		return settings.prefix+"reload";
	}

}
