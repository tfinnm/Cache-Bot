package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.Data;
import core.SystemChannels;
import core.settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class servers extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().equals(settings.prefix+"servers");
	}

	@Override
	public void action(Message msg) {
		List<Field> cmds = new ArrayList<Field>();
		for(Guild tempguild: Data.jda.getGuilds()) {
			if (!Arrays.asList(SystemChannels.GuildInviteBL).contains(tempguild.getId())) {
				cmds.add(new Field(tempguild.getName(), "Owner: "+tempguild.getOwner().getAsMention()+"\nInvite: "+tempguild.getDefaultChannel().createInvite().complete().getUrl(), false));
			}
		}
		MessageEmbed embed = new MessageEmbed(null, settings.netname+" Member Servers", "", null, null, 0, new Thumbnail(settings.logo, null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null,cmds);
		msg.getChannel().sendMessage(embed).queue();
	}

	@Override
	public String helpText() {
		return "Lists all "+settings.netname+" member servers";
	}

	@Override
	public String name() {
		return settings.prefix+"servers";
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}
	
}
