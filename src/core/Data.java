package core;
/**
 *  Copyright 2020 Toby McDonald
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import data.KeyValuePair;
import net.dv8tion.jda.api.hooks.*;
public class Data extends ListenerAdapter {
	public static KeyValuePair blacklist = new KeyValuePair(new File("BL.DSC"));
	public static KeyValuePair advisories = new KeyValuePair(new File("AL.DSC"));
	public static KeyValuePair memberCount = new KeyValuePair(new File("MC.DSC"),true);
	public static KeyValuePair logChannel = new KeyValuePair(new File("LC.DSC"),true);
	public static KeyValuePair joinChannel = new KeyValuePair(new File("JC.DSC"),true);
	
	//TODO: bring back meme mode.
	//public static ArrayList<String[]> bruhCount = new ArrayList<String[]>(); 
	//public static boolean memeMode = false;

	static ArrayList<String[]> badges;

	public static ArrayList<String[]> getBadges() throws IOException {
		File file = new File("Badges.DSC"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 
		badges = new ArrayList<String[]>();
		String st; 
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			String[] t = st.split("\\|");
			String[] arr = {t[0],t[1]};
			badges.add(arr);
		}
		br.close();
		return badges;
	}


}
