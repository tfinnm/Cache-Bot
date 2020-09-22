package commands;

import java.io.File;
import java.util.Arrays;

import core.settings;
import net.dv8tion.jda.api.entities.Message;

public class FAHStats extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"fahstats");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return true;
	}

	@Override
	public void action(Message msg) {
		msg.getChannel().sendMessage("Join at https://foldingathome.org/ with Team ID: "+settings.FAHTeamID).queue();
		String[] args = msg.getContentRaw().split(" ");
		if (Arrays.stream(args).anyMatch("points"::equals)) {
			msg.getChannel().sendMessage(" ").addFile(new File("https://apps.foldingathome.org/awards?team="+settings.FAHTeamID)).queue();
		}
		if (Arrays.stream(args).anyMatch("wus"::equals)) {
			msg.getChannel().sendMessage(" ").addFile(new File("https://apps.foldingathome.org/awards?team="+settings.FAHTeamID+"&type=wus")).queue();
		}
	}

	@Override
	public String helpText() {
		return "Get statistics about "+settings.netname+"'s Folding@Home Team";
	}

	@Override
	public String name() {
		return settings.prefix+"FAHStats [points/wus]";
	}

}
