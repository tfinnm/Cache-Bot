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
		if (!e.getUser().isBot() && e.getReactionEmote().isEmoji() && e.getReactionEmote().getEmoji().equals("☑️")) {
			String[][] servers = {{"MSG ID HERE","ROLE ID HERE"},{"726469641966452817","726469840201580575"}};
			
			for (String[] t: servers) {
				if (t[0].equals(e.getMessageId())) {
					e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(t[1])).queue();
				}
			}
		}
	}
}