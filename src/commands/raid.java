package commands;

import java.time.OffsetDateTime;

import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class raid extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"raid");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg) && Checks.canKick(msg);
	}

	@Override
	public void action(Message msg) {
		Guild guild = msg.getGuild();

		msg.getChannel().sendMessage("Activating Raid Mode.").queue();

		for(GuildChannel temp: guild.getChannels()) {
			if (temp.getType().equals(ChannelType.TEXT)) {
				temp.getManager().setSlowmode(21600).queue();
			}
		}

		SystemChannels.blacklist.sendMessage("@everyone").queue();
		MessageEmbed embed = new MessageEmbed(null, "RAID IN PROGRESS!", "Server: "+guild.getName(), null, OffsetDateTime.now(), 0xF40C0C, new Thumbnail(guild.getIconUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
		SystemChannels.blacklist.sendMessage(embed).queue();


	}

	@Override
	public String helpText() {
		return "Activates Raid Mode and alerts other servers.";
	}

	@Override
	public String name() {
		return settings.prefix+"raid";
	}

}
