package commands;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class createMemberCount extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"createmembercount");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.isAdmin(msg);
	}

	@Override
	public void action(Message msg) {
		VoiceChannel c = msg.getGuild().createVoiceChannel("Members: "+msg.getGuild().getMemberCount()).complete();
		Data.memberCount.put(msg.getGuild().getId(), c.getId());
		msg.getChannel().sendMessage("Created member count channel.").queue();
	}

	@Override
	public String helpText() {
		return "Creates a voice channel that displays the member count.";
	}

	@Override
	public String name() {
		return settings.prefix+"createmembercount";
	}

}
