package events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class verifyReact extends event {

	@Override
	public boolean trigger(GenericEvent event) {
		return event instanceof GuildMessageReactionAddEvent;
	}
	
	@Override
	public void action(GenericEvent event) {
		GuildMessageReactionAddEvent e = (GuildMessageReactionAddEvent) event;
		if (!e.getUser().isBot() && e.getReactionEmote().isEmoji() && e.getReactionEmote().getEmoji().equals("â˜‘ï¸�")) {
			String[][] servers = {{"MSG ID HERE","ROLE ID HERE"},
					{"726469641966452817","726469840201580575"},
					{"726865898539057192","726858239945474069"},
					{"726866200964890681","726858137075712101"},
					{"726866390740631592","726858180868440105"}};
			
			for (String[] t: servers) {
				if (t[0].equals(e.getMessageId())) {
					e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(t[1])).queue();
				}
			}
		}
	}
}