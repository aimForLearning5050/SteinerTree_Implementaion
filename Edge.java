public class Edge implements Comparable<Edge>{
	int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
	
	@Override
	public int compareTo(Edge e) {
		int comparedSize = e.weight;
		if (this.weight < comparedSize) {
			return 1;
		}else if (this.weight == comparedSize) {
			return 0;
		}else {
			return -1;
		}
	}
}