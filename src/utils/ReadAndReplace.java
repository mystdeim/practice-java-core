package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadAndReplace {

	public static void main(String[] args) {

		String file = "C:/download/GeoIPASNum2.csv";
		String out_file = "C:/download/GeoIPASNum2_parsed.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(out_file))) {
			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				Pattern p = Pattern.compile("AS\\d+\\s+");
				Matcher m = p.matcher(line);
				if (m.find()) {
					String asn = m.group();
					bw.write(line.replace(asn, asn.trim() + "\",\""));
					bw.newLine();
				} else {
					p = Pattern.compile("AS\\d+");
					m = p.matcher(line);
					if (m.find()) {
						String asn = m.group();
						bw.write(line.replace(asn, "\"" + asn.trim() + "\",\"\""));
						bw.newLine();
					} else {
						System.out.println(line);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
