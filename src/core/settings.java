package core;
/**
 *  Copyright 2020 Toby McDonald
 */

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class settings {
	public static final String version = "1.2.0b";
	public static final String buildDate = "7/15/2020";
	public static final String prefix = "&";
	public static final String netname = "MetaCompute";
	public static final String FAHTeamID = "266815";
	
	public static final String logo = "https://cdn.discordapp.com/avatars/634302091233591296/2f2c9613f025a540980b13a390a90818.png";
	public static final OnlineStatus status = OnlineStatus.ONLINE;
	public static final Activity activity = Activity.watching(netname+" Member Servers");
}
