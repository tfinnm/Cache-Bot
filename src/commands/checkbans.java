package commands;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;


public class checkbans extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"checkbans");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg) && Checks.canBan(msg);
	}

	@Override
	public void action(Message msg) {
		msg.getChannel().sendTyping().queue();
		for(Member bantemp: msg.getGuild().getMembers()) {
			if(Data.BL.contains(bantemp.getId())) {
				msg.getChannel().sendMessage("Found: "+bantemp.getAsMention()+" For "+Data.BLR.get(Data.BL.indexOf(bantemp.getId()))).queue();
			}
		}
		msg.getChannel().sendMessage("DSC Blacklist Check Finished!").queue();
	}

	@Override
	public String helpText() {
		return "Checks if any banned members are present on the server.";
	}

	@Override
	public String name() {
		return settings.prefix+"checkbans";
	}

}
