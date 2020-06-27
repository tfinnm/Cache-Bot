package core;
/**
 *  Copyright 2020 Toby McDonald
 */

//TODO: logging

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.hooks.*;
public class Data extends ListenerAdapter {
	public static String[] BlackList;
	public static ArrayList<String> BL;
	public static ArrayList<String> BLR;
	public static String[] AdvisoryList;
	public static ArrayList<String> AL;
	public static ArrayList<String> ALR;
	public static ArrayList<String[]> bruhCount = new ArrayList<String[]>(); 
	public static JDA jda;
	public static boolean memeMode = false;

	public static void getBans() throws IOException {
		File file = new File("BL.DSC"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 
		BL = new ArrayList<String>();
		BLR = new ArrayList<String>();

		String st; 
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			String st2 = "";
			if (st.split("\\|").length > 1) {
				st2 = st.split("\\|")[1];
			}
			st = st.split("\\|")[0];
			if (st2 == null) {
				st2 = "";
			}
			BL.add(st);
			BLR.add(st2);
			BlackList = new String[BL.size()];
			for (int i = 0; i < BL.size(); i++) {
				System.out.println(BL.get(i));
				BlackList[i] = BL.get(i);
				System.out.println(BlackList[i]);
			}
		}
		br.close();
	}

	public static void addBan(String ID,String reason) {
		try {//646545388576178178
			BufferedReader file = new BufferedReader(new FileReader("BL.DSC"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				if (!line.startsWith(ID)) {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			inputBuffer.append(ID+"|"+reason+" ");
			file.close();

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream("BL.DSC");
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Problem reading file.");
		}
	}

	public static void removeBan(String ID) {
		try {//646545388576178178
			BufferedReader file = new BufferedReader(new FileReader("BL.DSC"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				if (!line.startsWith(ID)) {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			file.close();

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream("BL.DSC");
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Problem reading file.");
		}
	}


	public static void getAdvisories() throws IOException {
		File file = new File("AL.DSC"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 
		AL = new ArrayList<String>();
		ALR = new ArrayList<String>();

		String st; 
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			String st2 = st.split("\\|")[1];
			st = st.split("\\|")[0];
			AL.add(st);
			ALR.add(st2);
			AdvisoryList = new String[AL.size()];
			for (int i = 0; i < AL.size(); i++) {
				System.out.println(AL.get(i));
				AdvisoryList[i] = AL.get(i);
				System.out.println(AdvisoryList[i]);
			}
		}
		br.close();
	}

	public static void addAdvisory(String ID,String reason) {
		try {//646545388576178178
			BufferedReader file = new BufferedReader(new FileReader("AL.DSC"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				if (!line.startsWith(ID)) {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			inputBuffer.append(ID+"|"+reason+" ");
			file.close();

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream("AL.DSC");
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Problem reading file.");
		}
	}

	public static void removeAdvisory(String ID) {
		try {//646545388576178178
			BufferedReader file = new BufferedReader(new FileReader("AL.DSC"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				if (!line.startsWith(ID)) {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			file.close();

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream("AL.DSC");
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Problem reading file.");
		}
	}


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
