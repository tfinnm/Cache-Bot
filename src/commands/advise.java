package commands;

import java.time.OffsetDateTime;
import java.util.List;

import core.Data;
import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class advise extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"advise");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg) && Checks.canKick(msg);
	}

	@Override
	public void action(Message msg) {
		if (msg.getMentionedUsers().isEmpty())
		{
			msg.getChannel().sendMessage("You must mention a user to issue an advisory for!").queue();
		}
		else
		{
			Guild guild = msg.getGuild();

			List<User> mentionedUsers = msg.getMentionedUsers();
			for (User user : mentionedUsers)
			{
				Data.addAdvisory(user.getId(),msg.getContentRaw().substring(7+user.getId().length()+5));
				msg.getChannel().sendMessage("Issued Advisory.").queue();

				MessageEmbed embed = new MessageEmbed(null, "ADVISORY!", "User: "+user.getAsTag()+"\nID: "+user.getId()+"\nServer: "+guild.getName()+"\nFor Reason: "+msg.getContentRaw().substring(7+user.getId().length()+5), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(user.getEffectiveAvatarUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
				SystemChannels.blacklist.sendMessage(embed).queue();

			}
		}

	}

	@Override
	public String helpText() {
		return "Issues a warning across the entire network.";
	}

	@Override
	public String name() {
		return settings.prefix+"advise";
	}

}
