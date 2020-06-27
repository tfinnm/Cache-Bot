package commands;

import core.settings;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;

public class purge extends command {

	@Override
	public boolean trigger(Message msg) {
		return msg.getContentRaw().toLowerCase().startsWith(settings.prefix+"purge");
	}

	@Override
	public boolean checkPerms(Message msg) {
		return Checks.canDelete(msg);
	}

	@Override
	public void action(Message msg) {
		int num = Integer.parseInt(msg.getContentRaw().split(" ")[1]);
		num++;
		int times = num/100;
		int remain = num%100;
		new MessageHistory(msg.getChannel()).retrievePast(remain).queue((msgs) -> {
			msg.getChannel().purgeMessages(msgs);
		});

		for (int i = 0; i < times; i++) {
			new MessageHistory(msg.getChannel()).retrievePast(100).queue((msgs2) -> {
				msg.getChannel().purgeMessages(msgs2);
			});
		}
	}

	@Override
	public String helpText() {
		return "Removes messages in bulk.";
	}

	@Override
	public String name() {
		return settings.prefix+"purge <#>";
	}

}
