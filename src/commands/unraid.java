package commands;

import core.settings;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;

public class unraid extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"unraid");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg) && Checks.isAdmin(msg);
	}

	@Override
	public void action(Message msg) {
		Guild guild = msg.getGuild();

		msg.getChannel().sendMessage("Disabling Raid Mode.").queue();

		for(GuildChannel temp: guild.getChannels()) {
			if (temp.getType().equals(ChannelType.TEXT)) {
				temp.getManager().setSlowmode(0).queue();
			}
		}


	}

	@Override
	public String helpText() {
		return "Disables Raid Mode.";
	}

	@Override
	public String name() {
		return settings.prefix+"unraid";
	}

}
