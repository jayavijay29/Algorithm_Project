import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class LocalInstance {

	float vTemp = 0;
	float hTemp = 0;

	List line(List x, List y, int number) {
		LocalInstance li = new LocalInstance();
		List output = new ArrayList();
		String vertical[] = new String[number - 1];
		String horizontal[] = new String[number - 1];
		for (int i = 0; i < x.size() - 1; i++) {
			// Vertical Lines
			vTemp = (Integer) x.get(i + 1) - (Integer) x.get(i);
			vTemp = vTemp / 2;
			vertical[i] = String.valueOf(vTemp + (Integer) x.get(i));
			vertical[i] = vertical[i].concat("v");
		}

		// Horizontal Lines
		List yTemp = new ArrayList();
		yTemp = y;
		Object[] array = y.toArray(new Object[0]);
		Collections.sort(yTemp);
		y = new ArrayList(Arrays.asList(array));
		for (int i = 0; i < yTemp.size() - 1; i++) {
			hTemp = (Integer) yTemp.get(i + 1) - (Integer) yTemp.get(i);
			hTemp = hTemp / 2;
			horizontal[i] = String.valueOf(hTemp + (Integer) yTemp.get(i));
			horizontal[i] = horizontal[i].concat("h");
		}

		// Total Set - sepPoints
		List sepPoints1 = new ArrayList();
		List sepPoints2 = new ArrayList();
		for (int i = 0; i < x.size(); i++) {
			for (int j = 0; j < y.size(); j++) {
				if (i != j) {
					sepPoints1.add(x.get(i) + "x" + y.get(i) + "y");
					sepPoints2.add(x.get(j) + "x" + y.get(j) + "y");
				}
			}
		}

		// Vertical Set (V1,V2,....etc) - vPoint
		String vPoint[][] = new String[vertical.length][];
		List<String> vPointTemp = new ArrayList<String>();
		for (int j = 0; j < vertical.length; j++) {
			for (int i = 0; i < sepPoints1.size(); i++) {
				int val1 = (((String) sepPoints1.get(i)).lastIndexOf("x"));
				int val2 = (((String) sepPoints2.get(i)).lastIndexOf("x"));
				if ((Float.parseFloat(vertical[j].replace("v", "")) > Integer
						.parseInt(((String) sepPoints1.get(i)).substring(0, val1))
						&& (Float.parseFloat(vertical[j].replace("v", "")) < Integer
								.parseInt(((String) sepPoints2.get(i)).substring(0, val2))))) {
					vPointTemp.add(sepPoints1.get(i).toString() + sepPoints2.get(i).toString());
				} else if ((Float.parseFloat(vertical[j].replace("v", "")) < Integer
						.parseInt(((String) sepPoints1.get(i)).substring(0, val1))
						&& (Float.parseFloat(vertical[j].replace("v", "")) > Integer
								.parseInt(((String) sepPoints2.get(i)).substring(0, val2))))) {
					vPointTemp.add(sepPoints1.get(i).toString() + sepPoints2.get(i).toString());
				}
			}
			vPoint[j] = new String[vPointTemp.size()];
			for (int i = 0; i < vPointTemp.size(); i++) {
				vPoint[j][i] = vPointTemp.get(i).toString();
			}
			vPointTemp.clear();
		}

		// Horizontal Set (H1,H2,..etc) - hPoint
		String hPoint[][] = new String[horizontal.length][];
		List<String> hPointTemp = new ArrayList<String>();
		for (int j = 0; j < horizontal.length; j++) {
			for (int i = 0; i < sepPoints1.size(); i++) {
				int val1 = (((String) sepPoints1.get(i)).lastIndexOf("x"));
				int val2 = (((String) sepPoints2.get(i)).lastIndexOf("x"));
				int val3 = (((String) sepPoints1.get(i)).lastIndexOf("y"));
				int val4 = (((String) sepPoints2.get(i)).lastIndexOf("y"));
				if ((Float.parseFloat(horizontal[j].replace("h", "")) > Integer
						.parseInt(((String) sepPoints1.get(i)).substring(val1 + 1, val3))
						&& (Float.parseFloat(horizontal[j].replace("h", "")) < Integer
								.parseInt(((String) sepPoints2.get(i)).substring(val2 + 1, val4))))) {
					hPointTemp.add(sepPoints1.get(i).toString() + sepPoints2.get(i).toString());
				} else if ((Float.parseFloat(horizontal[j].replace("h", "")) < Integer
						.parseInt(((String) sepPoints1.get(i)).substring(val1 + 1, val3))
						&& (Float.parseFloat(horizontal[j].replace("h", "")) > Integer
								.parseInt(((String) sepPoints2.get(i)).substring(val2 + 1, val4))))) {
					hPointTemp.add(sepPoints1.get(i).toString() + sepPoints2.get(i).toString());
				}
			}
			hPoint[j] = new String[hPointTemp.size()];
			for (int i = 0; i < hPointTemp.size(); i++) {
				hPoint[j][i] = hPointTemp.get(i).toString();
				// System.out.println(hPoint[j][i]);
			}
			hPointTemp.clear();
		}

		int vLength[] = new int[vertical.length];
		int hLength[] = new int[horizontal.length];
		for (int i = 0; i < vPoint.length; i++) {
			vLength[i] = vPoint[i].length;
		}
		for (int i = 0; i < hPoint.length; i++) {
			hLength[i] = hPoint[i].length;
		}

		int maxIndex1 = 0;
		int maxIndex2 = 0;
		List sepPoints3 = new ArrayList();
		for (int i = 0; i < sepPoints1.size(); i++)
			sepPoints3.add((String) sepPoints1.get(i) + (String) sepPoints2.get(i));

		for (int i = 0; sepPoints3.size() != 0; i++) {
			maxIndex1 = li.findMax(vPoint, sepPoints3);
			maxIndex2 = li.findMax(hPoint, sepPoints3);
			int spSize1 = sepPoints3.size();
			int spSize2 = spSize1;
			if (vLength[maxIndex1] >= hLength[maxIndex2]) {
				vPointTemp.addAll(Arrays.asList(vPoint[maxIndex1]));
				sepPoints3.removeAll(vPointTemp);
				spSize1 = sepPoints3.size();
				vLength[maxIndex1] = 0;
				if (spSize1 < spSize2)
					output.add(vertical[maxIndex1]);

			} else {
				hPointTemp.addAll(Arrays.asList(hPoint[maxIndex2]));
				sepPoints3.removeAll(hPointTemp);
				spSize1 = sepPoints3.size();
				hLength[maxIndex2] = 0;
				if (spSize1 < spSize2)
					output.add(horizontal[maxIndex2]);
			}
		}
		return output;
	}

	// Finding the Max set index
	int findMax(String[][] point, List sepPoints3) {
		List pointTemp = new ArrayList();
		List common = new ArrayList();
		int maxRemoval = 0;
		int index = 0;
		for (int i = 0; i < point.length; i++) {
			pointTemp.addAll(Arrays.asList(point[i]));
			common = pointTemp;
			common.retainAll(sepPoints3);
			if (maxRemoval < common.size()) {
				maxRemoval = common.size();
				index = i;
			}
			pointTemp.clear();
		}
		return index;
	}
}
