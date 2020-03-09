import java.util.Collections;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Graph{
	int vertices;
    LinkedList<Edge>[] adjacencylist;
	int[] terminalVertices;
	
	List<Integer> weights;
	
	@SuppressWarnings("unchecked")
    public Graph(int vertices, int[] tVertices) {
        this.vertices = vertices;
		terminalVertices = new int[vertices];
		terminalVertices = tVertices;
		weights = new ArrayList<Integer>();
		
        adjacencylist = new LinkedList[vertices + 1];
        //initialize adjacency lists for all the vert<Edge>ices
		for (int i = 0; i < vertices + 1; i++) {
			adjacencylist[i] = new LinkedList<Edge>();
		}
    }

    public void addEgde(int v1, int v2, int weight) {
        Edge edge1 = new Edge(v1, v2, weight);
		Edge edge2 = new Edge(v2, v1, weight);
		
		LinkedList<Edge> list = adjacencylist[v1];
		boolean isValid = true;
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).v1 == v1 && list.get(i).v2 == v2){
				isValid = false;
				break;
			}
		}
		
		if(isValid){
			if(!weights.contains(weight)){
				weights.add(weight);
				Collections.sort(weights, Collections.reverseOrder());
			}
			adjacencylist[v1].add(edge1); 
			Collections.sort(adjacencylist[v1]);
			adjacencylist[v2].add(edge2);
			Collections.sort(adjacencylist[v2]);
		}else{
			//Does nothing is same edge is inputted
		}
   
    }
	
	// prints BFS traversal from a given source s 
    public boolean BFS(int vertex){ 
		int s = vertex;
        // Mark all the vertices as not visited(By default 
        // set as false) 
        Boolean visited[] = new Boolean[vertices + 1]; 
  
        // Create a queue for BFS 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
  
        // Mark the current node as visited and enqueue it 
        visited[s]=true; 
        queue.add(s); 
		
        while (queue.size() != 0){ 
            // Dequeue a vertex from queue and print it 
            s  = queue.poll(); 
  
            // Get all adjacent vertices of the dequeued vertex s 
            // If a adjacent has not been visited, then mark it 
            // visited and enqueue it 
            Iterator<Edge> i = adjacencylist[s].listIterator(); 
            while (i.hasNext()){ 
                int n = i.next().v2; 
                if (visited[n] == null) 
                { 
                    visited[n] = true; 
                    queue.add(n); 
                } 
            } 
        } 

		for(int i = 0; i < terminalVertices.length; i++){
			if(visited[terminalVertices[i]] == null){

				return false;
			}
		}

		return true;
    }
	
	/* Under Construction */
	public void removeEdge(){
		for(int h = 0; h < weights.size(); h++){
			for (int i = 0; i < vertices + 1; i++) {
				LinkedList<Edge> list1 = adjacencylist[i];

				for (int j = 0; j <list1.size() ; j++) {

					int tempV1 = list1.get(j).v1;
					int tempV2 = list1.get(j).v2;
					int weight = list1.get(j).weight;
					

					if(weights.get(h) == weight){
						
						list1.remove(j);
					
						LinkedList<Edge> list2 = adjacencylist[tempV2];
						
						for(int k = 0; k < list2.size(); k++){
							if(list2.get(k).v2 == tempV1){
								list2.remove(k);		
								break;
							}
						}

						if(! BFS(terminalVertices[0])){
							addEgde(tempV1, tempV2, weight);
						}else{
							j--;
						}
					}
				
				}
				
			}
		}
	}

    public void printGraph(){
		for (int i = 0; i < vertices ; i++) {
            LinkedList<Edge> list = adjacencylist[i];
            for (int j = 0; j <list.size() ; j++) {
                System.out.println("vertex-" + list.get(j).v1 + " is connected to " +
                    list.get(j).v2 + " with weight " +  list.get(j).weight);
            }
        }
		
    }
	
	public int treeCost(){
		int cost = 0;
		for (int i = 0; i < vertices ; i++) {
		
			LinkedList<Edge> list1 = adjacencylist[i];

			for (int j = 0; j <list1.size() ; ) {

				int tempV1 = list1.get(j).v1;
				int tempV2 = list1.get(j).v2;
				int weight = list1.get(j).weight;

				list1.remove(j);
				
				LinkedList<Edge> list2 = adjacencylist[tempV2];
					
				for(int k = 0; k < list2.size(); k++){
					if(list2.get(k).v2 == tempV1){
						list2.remove(k);		
						break;
					}
				}
					
				cost += weight;
			}
				
		}
		return cost;
	}
}