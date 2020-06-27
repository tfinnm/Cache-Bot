package commands;

import core.settings;
import net.dv8tion.jda.api.entities.Message;

public class privacy extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"privacy");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}

	@Override
	public void action(Message msg) {
		msg.getAuthor().openPrivateChannel().queue((pc) -> {
			pc.sendMessage("This bot currently does not store any information, however this may change in the future, but don't worry, we will make it very clear to you if we ever do.").queue();
		});
	}

	@Override
	public String helpText() {
		return "General privacy information.";
	}

	@Override
	public String name() {
		return settings.prefix+"privacy";
	}

}
