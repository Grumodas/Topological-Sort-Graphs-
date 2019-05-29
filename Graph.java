import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;

public class Graph {
	LinkedList<Integer> graph[];
	ArrayList<String> info = new ArrayList<String>();
	String[] infos;
	int count = 0;

	public Graph(int howManyNodes) {
		infos = new String[howManyNodes];
		graph = new LinkedList[howManyNodes];

		for (int i = 0; i < howManyNodes; i++) {
			graph[i] = new LinkedList<Integer>();
		}
	}

	public void add(int src, int dest) {
		graph[src].add(0, dest);
	}

	public void add(String src, String dest) {
		int indexSrc, indexDest;
		
		if (Arrays.asList(infos).contains(src)) {
			indexSrc = Arrays.asList(infos).indexOf(src);
		} else {
			infos[count] = src;	
			indexSrc = count++;
		}

		if (Arrays.asList(infos).contains(dest)) {
			indexDest = Arrays.asList(infos).indexOf(dest);
		} else {
			infos[count] = dest;
			indexDest = count++;
		}
		
		graph[indexSrc].add(0, indexDest);
	}
	
	public void add(int u, int[] v) {
		for (int i = 0; i < v.length; i++) {
			graph[u].add(0, v[i]);
		}
	}
	
	public void print() {
		for (int i = 0; i < graph.length; i++) {
			System.out.println(i + " -> " + graph[i]);
		}
	}

	public void prints() {
		for (int i = 0; i < graph.length; i++) {
			System.out.print(infos[i] + " -> [");
			int m = 0;
			for (int j : graph[i]) {
				System.out.print(infos[j]);

				if (m < graph[i].size() - 1) {
					System.out.print(", ");
				}
				m++;
			}
			System.out.println("]");
		}
	}

	public String getNodeZeroIndegrees(ArrayList<String> removed) {
		int nodeZeroIndegreesCandidate = 0;
		while (removed.contains(infos[nodeZeroIndegreesCandidate])) {
			nodeZeroIndegreesCandidate++;
		}

		for (int i = 0; i < graph.length; i++) {
			// System.out.println("i is: " + i + " == " + infos[i] + " and looking for " + infos[nodeZeroIndegreesCandidate] + " inside " + graph[i]);
			if (removed.contains(infos[i])) {
				// System.out.println("working with " + i +" loop and " + infos[nodeZeroIndegreesCandidate]);
				continue;
			}

			if (graph[i].contains(nodeZeroIndegreesCandidate)) {
				int original = nodeZeroIndegreesCandidate;
				nodeZeroIndegreesCandidate++;
				while (removed.contains(infos[nodeZeroIndegreesCandidate])) {
					if (nodeZeroIndegreesCandidate > graph.length) {
						return infos[original];
					}
					nodeZeroIndegreesCandidate++;
				}

				i = -1;
				// System.out.println("working with " + i +" loop and " + infos[nodeZeroIndegreesCandidate]);
				// System.out.println("Candid: " + infos[nodeZeroIndegreesCandidate]);
				// for (int j : graph[i]) {
				// 	System.out.println(infos[j]);
				// }
				continue;
			}
		}

		return infos[nodeZeroIndegreesCandidate];
	}

}