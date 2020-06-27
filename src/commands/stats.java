package commands;

import java.util.ArrayList;
import java.util.List;

import core.Data;
import core.settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class stats extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"stats");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}

	@Override
	public void action(Message msg) {
		List<Field> cmds = new ArrayList<Field>();
		cmds.add(new Field("Servers", String.valueOf(Data.jda.getGuilds().size()), false));
		int totalMembers = 0;
		int totalChannels = 0;
		int totalBoosts = 0;
		for (Guild temp: Data.jda.getGuilds()) {
			totalMembers += temp.getMemberCount();
			totalChannels += (temp.getTextChannels().size()+temp.getVoiceChannels().size());
			totalBoosts += temp.getBoostCount();
		}
		cmds.add(new Field("Members", String.valueOf(totalMembers), true));
		cmds.add(new Field("Channels", String.valueOf(totalChannels), true));
		cmds.add(new Field("Boosts", String.valueOf(totalBoosts), true));
		cmds.add(new Field("Synced Bans", String.valueOf(Data.BL.size()), true));
		cmds.add(new Field("Active Advisories", String.valueOf(Data.AL.size()), true));
		cmds.add(new Field("Ping", String.valueOf(Data.jda.getGatewayPing()), true));
		cmds.add(new Field("Shard", Data.jda.getShardInfo().getShardString(), true));
		cmds.add(new Field("Uptime Events", String.valueOf(Data.jda.getResponseTotal()),true));
		MessageEmbed embed = new MessageEmbed(null, "Bot Statistics", "", null, null, 0, new Thumbnail(settings.logo, null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer("DSC Bot | Powered By Tfinnm Development", settings.logo, null), null,cmds);
		msg.getChannel().sendMessage(embed).queue();
	}

	@Override
	public String helpText() {
		return "Interesting statistics about this bot.";
	}

	@Override
	public String name() {
		return settings.prefix+"stats";
	}

}
