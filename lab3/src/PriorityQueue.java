package lab3;

import java.util.ArrayList;


    /**
     * A priority queue class supporting operations needed for
     * Dijkstra's algorithm.
     */

class PriorityQueue<T> {
    	
    ArrayList<PQNode<T>> Q;
    	

     /**
     * Constructor
     */
     public PriorityQueue() {
    	 
     	Q = new ArrayList<PQNode<T>>();
     	
     }

     

     /**
     * @return true iff the queue is empty.
     */
     public boolean isEmpty() {
        	
        return Q.isEmpty();
        	
     }
        


    /**
     * Insert a (key, value) pair into the queue.
     *
     * @return a Handle to this pair so that we can find it later.
     */
        
        
    public Handle insert(int key, T value) {
        //why Q.size()??
       	Handle h = new Handle(Q.size(),true);
       	PQNode<T> node = new PQNode(h, key, value);
       	//add the node at the end of Q
       	Q.add(node);
       	HeapifyFromEnd (Q, Q.size()-1, Q.size());
       	return node.handle;
    }
        
        
        
    public void HeapifyFromEnd (ArrayList<PQNode<T>> Q, int i, int size) {
	
    	if ( i >= 0 && Q.get((i-1)/2).key > Q.get(i).key) {
        	swap(i, (i-1)/2);
        	i = (i-1)/2;
       		HeapifyFromEnd(Q, i, size);
       	}

    }

    

    
    /**
     * @return the min key in the queue.
     */
        
    public int min() {
        return Q.get(0).key;
     }

    
    
    /**
     * Find and remove the smallest (key, value) pair in the queue.
     *
     * @return the value associated with the smallest key
     */
    public T extractMin() {
    	if (Q.size() > 0 ) {
    		
    		T tempV = Q.get(0).value;
        	//move the last element to the root, then extract the last element
        	swap(0, Q.size()-1);
        	Q.get(Q.size()-1).handle.deleted = false;
        	Q.remove(Q.size()-1);
        	
        	if (Q.size() > 0) {
        		HeapifyFromTop(Q, 0);
        	}
          
        	return tempV;
    	}
    	
    	else return null;
    }
   
    
 
    //wrong
   public void HeapifyFromTop (ArrayList<PQNode<T>> Q, int i) {

	   	int Smallest = i;

   		//find the smallest child
   		if (2*i + 2 < Q.size()) {
   			if (Q.get(2*i+1).key < Q.get(2*i+2).key) {
   				Smallest = 2*i +1;
   			}
   			
   			else Smallest = 2*i +2;
   		}

   		else if (2*i + 2 == Q.size() ) {
   			
   			Smallest = 2*i+1;
   		} 
   		
   		//compare with i.
   		if (Q.get(Smallest).key < Q.get(i).key) {
   			swap(i, Smallest);
   			HeapifyFromTop(Q, Smallest);
       	
   		}

   }
   


    /**
     * Decrease the key to the newKey associated with the Handle.
     *
     * If the pair is no longer in the queue, or if its key <= newKey,
     * do nothing and return false.  Otherwise, replace the key with newkey,
     * fix the ordering of the queue, and return true.
     *
     * @return true if we decreased the key, false otherwise.
     */
   

   public boolean decreaseKey(Handle h, int newKey) {
   		
	   if (h.deleted == true && newKey < Q.get(h.position).key) {
   			int i=h.position;
			Q.get(h.position).key=newKey;
			while (i > 0 && Q.get((i-1)/2).key>newKey){
				swap(i , (i-1)/2);
				i = (i-1)/2;
			}
			return true;
		}
   		
   		else{
   			return false;
   		}
   
   }


    /**
     * @return the key associated with the Handle.
     */
    public int handleGetKey(Handle h) {
       return Q.get(h.position).key;
     }

    
    /**
     * @return the value associated with the Handle.
     */
    public T handleGetValue(Handle h) {
       return Q.get(h.position).value;
    }
    


    /**
     * Print every element of the queue in the order in which it appears
     * (i.e. the array representing the heap)
     */
    public String toString() {
    	
    	String result = "";
    	for (int i = 0; i < Q.size(); i++) {
    		result = result + " " + Q.get(i).toString();
    	}

       return result;
    }

    
    public void swap(int a, int b) {
    	Handle A = Q.get(a).handle;
    	Handle B = Q.get(b).handle;
		
		PQNode<T> temp = Q.get(a);
    	Q.set(a, Q.get(b));
    	Q.set(b, temp);
    	
    	//update the handles;
    	
    	int temp2 = A.position;
    	A.position = B.position;
    	B.position = temp2;
    	
    
    }


}
