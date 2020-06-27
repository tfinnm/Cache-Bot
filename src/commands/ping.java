package commands;

import core.settings;
import net.dv8tion.jda.api.entities.Message;

public class ping extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"ping");
	}

	@Override
	public void action(Message msg) {
		msg.getChannel().sendMessage("Bot Online. :)").queue();
	}

	@Override
	public String helpText() {
		return "Checks if the bot is working.";
	}

	@Override
	public String name() {
		return settings.prefix+"ping";
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}

}
