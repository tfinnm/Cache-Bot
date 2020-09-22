package commands;

import java.util.List;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class clearAdvisory extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"clearadvisory");
	}

	@Override
	public void action(Message msg) {

		if (msg.getMentionedMembers().size() > 0) {
			List<User> mentionedUsers = msg.getMentionedUsers();
			for (User tempbanuser : mentionedUsers) {
				if(Data.advisories.get(tempbanuser.getId())!=null) {
					Data.advisories.remove(tempbanuser.getId());
					msg.getChannel().sendMessage("Cleared "+tempbanuser.getAsMention()).queue();
				}
			}
		} else {
			String tempbanuser = msg.getContentRaw().split(" ")[1];
			if(Data.advisories.get(tempbanuser)!=null) {
				Data.advisories.remove(tempbanuser);
				msg.getChannel().sendMessage("Cleared User ID \""+tempbanuser+"\"").queue();
			}
		}
	}

	@Override
	public String helpText() {
		return "Removes an Advisory from a user.";
	}

	@Override
	public String name() {
		return settings.prefix+"clearAdvisory <@user>";
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg) && Checks.isTfinnm(msg);
	}


}