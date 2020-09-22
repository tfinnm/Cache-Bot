package core;
/**
 *  Copyright 2020 Toby McDonald
 */

import java.io.IOException;
import java.time.OffsetDateTime;

import javax.security.auth.login.LoginException;

import data.MessageCache;
import events.eventHandler;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Guild.Ban;
import net.dv8tion.jda.api.entities.MessageEmbed.AuthorInfo;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;

public class startup {

	public static void loadJDA() throws LoginException, InterruptedException {
		launcher.jda = new JDABuilder(Token.token).addEventListeners(new eventHandler()).setActivity(settings.activity).setStatus(settings.status).build();
		launcher.jda.awaitReady();
	}

	public static void loadData() throws IOException {
		MessageCache.cache = new MessageCache(launcher.jda,false);
	}

	public static void runInitialTasks() {
		for(Guild temp: launcher.jda.getGuilds()) {
			temp.retrieveBanList().queue((bans) -> {
				for(Ban temp2: bans) {
					String[] darr = {temp.getId(),temp2.getReason()};
					Data.blacklist.put(temp2.getUser().getId(), darr);
				}
			});
			SystemChannels.updateMemberCount(temp);
		}
		MessageEmbed embed = new MessageEmbed(null, "Service Online!", "Shard: "+launcher.jda.getShardInfo().getShardId()+"/"+launcher.jda.getShardInfo().getShardTotal()+"\nPing: "+launcher.jda.getGatewayPing(), null, OffsetDateTime.now(), 0x33cc33, new Thumbnail(settings.logo, null, 128, 128), null, new AuthorInfo("", null, "", null), null, new Footer(settings.netname+" Bot | Powered By Tfinnm Development", settings.logo, null), null, null);
		SystemChannels.botdev.sendMessage(embed).queue();
	}

	
}
