package commands;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Guild.Ban;
import net.dv8tion.jda.api.entities.Message;


public class sync extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"sync");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg);
	}

	@Override
	public void action(Message msg) {
		msg.getChannel().sendTyping().queue();
		msg.getGuild().retrieveBanList().queue((bans) -> {
			for(Ban temp: bans) {
				String[] darr = {msg.getGuild().getId(),temp.getReason()};
				Data.blacklist.put(temp.getUser().getId(), darr);
			}
		});
		msg.getChannel().sendMessage("Finished syncing ban list to the "+settings.netname+" Blacklist.").queue();
	}

	@Override
	public String helpText() {
		return "Syncs server bans to the database.";
	}

	@Override
	public String name() {
		return settings.prefix+"sync";
	}

}
