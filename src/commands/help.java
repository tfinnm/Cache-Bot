package commands;

import java.util.ArrayList;
import java.util.List;

import core.settings;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class help extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"help");
	}

	@Override
	public void action(Message msg) {
		List<Field> cmds = new ArrayList<Field>();
		for (command t: command.Commands) {
			cmds.add(new Field(t.name(),t.helpText(),false));
		}
		MessageEmbed embed = new MessageEmbed(null, settings.netname+" Bot Help!", "Need more help? Contact <@213319973756600322>", null, null, 0, new Thumbnail(settings.logo, null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot "+settings.version+" Build Date: "+settings.buildDate+" | Powered By Tfinnm Development", settings.logo, null), null,cmds);
		msg.getChannel().sendMessage(embed).queue();

	}

	@Override
	public String helpText() {
		return "Provides a list of bot commands";
	}

	@Override
	public String name() {
		return settings.prefix+"help";
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}
	
}
