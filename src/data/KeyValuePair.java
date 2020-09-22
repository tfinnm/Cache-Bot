package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class KeyValuePair implements Iterable<String[]>{

	private File path;
	private ArrayList<String[]> data; 
	public boolean noRepeat = false;

	public KeyValuePair(File file) {
		path = file;
		initFromFile();
	}
	
	public KeyValuePair(File file, boolean noRepeat) {
		path = file;
		this.noRepeat = noRepeat;
		initFromFile();
	}

	public String[] get(String key) {
		for (String[] t: data) {
			if (t[0] == key)
				return t;
		}
		return null;
	}

	public void put(String key, String value) {
		String[] d = {value};
		put(key,d);
	}

	public void put(String key, String[] value) {
		String[] valuearr = new String[value.length+1];
		valuearr[0]=key;
		for(int i = 1; i < valuearr.length;i++) {
			valuearr[i] = value[i-1];
		}
		value = valuearr;
		if (noRepeat) {
			data.remove(get(key));
		}
		data.add(value);
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				if (!noRepeat || !line.startsWith(key)) {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			String values = "";
			for (String t: value) {
				values += "|"+t;
			}
			inputBuffer.append(key+values);
			file.close();

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream("BL.DSC");
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();
		} catch (IOException e) {

		}
	}
	
	public void remove(String key) {
		data.remove(get(key));
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			StringBuffer inputBuffer = new StringBuffer();
			String line;

			while ((line = file.readLine()) != null) {
				if (!line.startsWith(key)) {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			file.close();
			FileOutputStream fileOut = new FileOutputStream("BL.DSC");
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();
		} catch (Exception e) {
		}
	}

	private void initFromFile() {
		data = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path)); 

			String st; 
			while ((st = br.readLine()) != null) {
				String[] di = st.split("\\|");
				data.add(di);
			}
			br.close();
		} catch (IOException e) {

		}
	}

	@Override
	public Iterator<String[]> iterator() {
		return data.iterator();
	}
	
	public int size() {
		return data.size();
	}
}
