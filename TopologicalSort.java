//Topological Sort
//Takes a graph and sorts it according to its hierarchy.
//In this example we have mokykla, darželis, uni, darbas, pensija graph and we need to sort it 
//to reflect this particular dynamics

import java.util.ArrayList;

public class TopologicalSort {
	public static void main(String[] args) {

		int graphSize = 5;
		Graph graph = new Graph(graphSize);
		graph.add("darbas", "pensija"); //to have "pensija" one first needs to complete "darbas"
		graph.add("darzelis", "uni"); //to get to "uni" one has to complete "darzelis"
		graph.add("darzelis", "mokykla"); //to get to "mokykla" one needs to complete "darzelis" etc 
		graph.add("uni", "darbas");
		graph.add("uni", "pensija");
		graph.add("mokykla", "uni");
		graph.add("darzelis", "darbas");
		graph.prints();

		ArrayList<String> visited = new ArrayList<String>();
		while (visited.size() < graphSize) {
			visited.add(graph.getNodeZeroIndegrees(visited));
		}
		System.out.println(visited);
		//output is 
		//["darzelis", "mokykla", "uni", "darbas", "pensija"]
	}
}	