/*
	Problem: Creating a Steiner Tree
	
	Sources: 
		1. Creating a Weighted Graph
		https://ide.tutorialhorizon.com/?gistUrl=https%3A%2F%2Fgist.githubusercontent.com%2Fthmain%2F10f6f48145c768620da2945670c9dfe4%2Fraw
		2. BFS Traversal
		https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
		3. Sort Linkedlist using Collections.sort in Java
		https://www.programcreek.com/2013/01/sort-linkedlist-of-user-defined-objects-in-java/
		
		
	Special thanks to:
	1. Jatico family
	2. Philip Espia
	3. Danijel Carlos
	4. God
*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class SteinerTree{
	public static void main(String[] args)throws Exception{
		long startTime = System.currentTimeMillis();
		
		File file = new File("Test1.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		//Graph Representation
		int vertices = Integer.parseInt(br.readLine());
		String[] terminalVerticesStr = br.readLine().split(" ");
		int[] terminalVertices = new int[terminalVerticesStr.length];
		for(int i = 0; i < terminalVertices.length; i++){
			terminalVertices[i] = Integer.parseInt(terminalVerticesStr[i]);
		}
		
		Graph graph = new Graph(vertices, terminalVertices);
		
		String st; 
		int ctr = 0;
		while ((st = br.readLine()) != null){ 
			String[] edge = st.split(" ");
			graph.addEgde(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]), Integer.parseInt(edge[2]));
		} 
		//Print initial graph
		System.out.println("Input: ");
		graph.printGraph();
		//Pruning of edges
		graph.removeEdge();	
		//Print the pruned graph
		System.out.println("\nOutput: ");
		graph.printGraph();
		//Cost of Steiner Tree
		System.out.println("Steiner Tree Cost: " + graph.treeCost());
		
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
 
        System.out.println("Execution time in seconds: " + timeElapsed / 1000.0);
	}
}