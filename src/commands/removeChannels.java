package commands;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Message;

public class removeChannels extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"disableLog");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.isAdmin(msg);
	}

	@Override
	public void action(Message msg) {
		Data.joinChannel.remove(msg.getGuild().getId());
		Data.logChannel.remove(msg.getGuild().getId());
		msg.getChannel().sendMessage("Disabled logging channels.").queue();
	}

	@Override
	public String helpText() {
		return "Disables the mod log channel.";
	}

	@Override
	public String name() {
		return settings.prefix+"disablechannels";
	}

}
