package commands;

import java.util.ArrayList;
import java.util.List;

import core.settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class serverstats extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"serverstats");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.guild(msg);
	}

	@Override
	public void action(Message msg) {
		List<Field> cmds = new ArrayList<Field>();
		Guild curserver = msg.getGuild();
		cmds.add(new Field("Name", curserver.getName(), true));
		cmds.add(new Field("Owner", curserver.getOwner().getEffectiveName(), true));
		cmds.add(new Field("Created", String.valueOf(curserver.getTimeCreated()), true));
		cmds.add(new Field("Boosts", String.valueOf(curserver.getBoostCount()), true));
		cmds.add(new Field("Users", String.valueOf(curserver.getMemberCount()), true));
		cmds.add(new Field("Roles", String.valueOf(curserver.getRoles().size()), true));
		cmds.add(new Field("Channels", String.valueOf(curserver.getChannels().size()), true));
		cmds.add(new Field("Region", curserver.getRegion().getName(),true));
		MessageEmbed embed = new MessageEmbed(null, "Server Statistics", "", null, null, 0, new Thumbnail(msg.getGuild().getIconUrl(), null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null,cmds);
		msg.getChannel().sendMessage(embed).queue();
	}

	@Override
	public String helpText() {
		return "Usefull stats about this server.";
	}

	@Override
	public String name() {
		return settings.prefix+"serverstats";
	}

}
