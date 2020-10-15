import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DictReader {
	static HashMap<String, String> get_dict(String path) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = null;
		char spliter = '-';
		String[] tmp;
		while((line = br.readLine()) != null) {
			int spliter_idx = line.indexOf(spliter);
			if(spliter_idx != -1) {
				tmp = line.split(Character.toString(spliter));
				map.put(tmp[0], tmp[1]);
			}
		}
		br.close();
		return map;
	}
	/*
	public static void main(String[] args) throws IOException {
		String path = "D:/Programs/Java/ja2/W5/HW2/Dictionary.txt";
		HashMap<String, String> dict = get_dict(path);
		
		//System.out.println(dict.size());
		//System.out.println(dict.get("aspire"));
	}
	*/
}
