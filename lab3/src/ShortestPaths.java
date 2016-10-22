package lab3;

import java.util.ArrayList;



import lab3.Vertex.EdgeIterator;





/**
 * Compute shortest paths in a graph.
 *
 * Your constructor should compute the actual shortest paths and
 * maintain all the information needed to reconstruct them.  The
 * returnPath() function should use this information to return the
 * appropriate path of edge ID's from the start to the given end.
 *
 * Note that the start and end ID's should be mapped to vertices using
 * the graph's get() function.
 */
class ShortestPaths {
	
	PriorityQueue<Vertex> Q;
	int[] dist; 
	Edge[] edgeRecord;
	int tempDist;
	Handle[] handles;
	int startId;

    /**
     * Constructor
     */
	public ShortestPaths(Multigraph G, int startId) {
	     
		// YOUR CODE HERE
		this.startId = startId;
    	dist = new int[G.nVertices()];
    	Q = new PriorityQueue<Vertex>();
    	handles = new Handle[G.nVertices()];
    	edgeRecord = new Edge[G.nVertices()];
    
		
    	for (int i = 0; i < G.nVertices(); i++) {
    		dist[i] = Integer.MAX_VALUE;
    		//insert based on distance
    		handles[i] = Q.insert(dist[i], G.get(i));
    		edgeRecord[i] = null;
    	}
    	dist[startId] = 0;
	    Q.decreaseKey(handles[startId], 0);

	    while (Q.isEmpty() == false) {
	    	// remove vertex a from Q;
			Vertex a = Q.extractMin();
	   		EdgeIterator k = a.adj();
	   		
			if(dist[a.id()] == Integer.MAX_VALUE)
			{
				return;
			}
			
	   		while (k.hasNext()) {
	   			
	   			Edge e = k.next();
    			Vertex b = e.to();
	    		
    			
	    		tempDist = dist[a.id()] + e.weight();			
				//update dist[b.id()]
	    	
	   			if (tempDist < dist[b.id()]) {
    				dist[b.id()] = tempDist;
   					edgeRecord[b.id()] = e;
   					Q.decreaseKey(handles[b.id()], tempDist);	
   				}
	    			
	    		
	    	}	
	    }

	}
	    

	 
	    	
	 
    
    /**
     * Calculates the list of edge ID's forming a shortest path from the start
     * vertex to the specified end vertex.
     *
     * @return the array
     */
    
    
    
	 public int[] returnPath(int endId) { 
	        // YOUR CODE HERE
	     	int[] result;
	 		
	     	if (endId == startId) {
	 			return new int[0];
	 		}
			if (edgeRecord[endId] == null) {
				return new int[0];
			}
	 		
	 		else {
	 			
	 			ArrayList<Integer> tempR = new ArrayList<Integer>();
	 			tempR.add(0,edgeRecord[endId].id());
	     	
	 			while (edgeRecord[endId].from().id() != startId) {
	 				Vertex pv = edgeRecord[endId].from();
	 				endId = pv.id();
	 				tempR.add(0,edgeRecord[endId].id());
	 			}
	     	
	 			result = new int[tempR.size()];
	     	
	 			for (int i = 0; i < result.length; i++) {
	 				result[i] = tempR.get(i);
	 			}

	 		}
	     	
	     	return result;
	     	
	 }




   
}
