package events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class eventHandler implements EventListener {
	
	public static void loadEvents() {
		new messageReceive();
		new userJoin();
		new userLeave();
		new userBan();
		new userUnban();
		new verifyReact();
	}

	@Override
	public void onEvent(GenericEvent e) {
		for (event t: event.Events) {
			if (t.trigger(e)) {
				t.action(e);
			}
		}
	}
	
}
