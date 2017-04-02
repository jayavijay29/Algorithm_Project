import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException {
		int number = 0;
		LocalInstance li = new LocalInstance();
		File folder = new File("input");
		File[] listOfFiles = folder.listFiles();
		for (int n = 0; n < listOfFiles.length; n++) {
			if (listOfFiles[n].isFile()) {
				BufferedReader br = new BufferedReader(new FileReader("input/" + listOfFiles[n].getName()));
				try {
					number = Integer.parseInt(br.readLine());
					List x = new ArrayList();
					List y = new ArrayList();
					for (int i = 0; i < number; i++) {
						String s[] = br.readLine().split(" ");
						x.add(Integer.parseInt(s[0]));
						y.add(Integer.parseInt(s[1]));
					}

					List value = li.line(x, y, number);

					// printing the returned values in a file
					PrintWriter writer = new PrintWriter(
							"output_greedy/greedy_solution" + String.format("%02d", n + 1) + ".txt", "UTF-8");
					writer.println(value.size());
					String temp;
					for (int i = 0; i < value.size(); i++) {
						temp = (String) value.get(i);
						if (temp.contains("v")) {
							temp = temp.replace("v", "");
							temp = "v " + temp;
						} else {
							temp = temp.replace("h", "");
							temp = "h " + temp;
						}
						writer.println(temp);
					}
					writer.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
