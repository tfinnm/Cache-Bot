package commands;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Message;

public class setLog extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"setlog");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.isAdmin(msg);
	}

	@Override
	public void action(Message msg) {
		Data.joinChannel.put(msg.getGuild().getId(), msg.getChannel().getId());
		msg.getChannel().sendMessage("Set log message channel.").queue();
	}

	@Override
	public String helpText() {
		return "Sets the channel for mod logs.";
	}

	@Override
	public String name() {
		return settings.prefix+"setlog";
	}

}
