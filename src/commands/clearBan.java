package commands;

import java.util.List;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class clearBan extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"clearban");
	}

	@Override
	public void action(Message msg) {
		//TODO: there is duplicate code here that probably doesn't need to be duplicated
		if (msg.getMentionedMembers().size() > 0) {
			List<User> mentionedUsers = msg.getMentionedUsers();
			for (User tempbanuser : mentionedUsers) {
				if(Data.blacklist.get(tempbanuser.getId())!=null) {
					Data.blacklist.remove(tempbanuser.getId());
					msg.getChannel().sendMessage("Cleared "+tempbanuser.getAsMention()).queue();
				}
			}
		} else {
			String tempbanuser = msg.getContentRaw().split(" ")[1];
			if(Data.blacklist.get(tempbanuser)!=null) {
				Data.blacklist.remove(tempbanuser);
				msg.getChannel().sendMessage("Cleared User ID \""+tempbanuser+"\"").queue();
			}
		}
	}

	@Override
	public String helpText() {
		return "Removes a ban from a user.";
	}

	@Override
	public String name() {
		return settings.prefix+"clearBan <@user>";
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg) && Checks.isTfinnm(msg);
	}


}