package lab1;

public class Sort {
    
    //================================= sort =================================
    //
    // Input: array A of XYPoints refs 
    //        lessThan is the function used to compare points
    //
    // Output: Upon completion, array A is sorted in nondecreasing order
    //         by lessThan.
    //
    // DO NOT USE ARRAYS.SORT.  WE WILL CHECK FOR THIS.  YOU WILL GET A 0.
    // I HAVE GREP AND I AM NOT AFRAID TO USE IT.
    //=========================================================================
	public static void msort(XYPoint[] A, Comparator lessThan) 
		{
			XYPoint[] sorted = sort(A, lessThan);
			
			for (int i = 0; i < sorted.length; i++)
			{
				A[i] = sorted[i];
			}
			
		}
	
	public static XYPoint[] sort(XYPoint[] A, Comparator lessThan)
	{
	   //Implement your sort here.  (use mergeSort).
		
		XYPoint[] sortedA;
    	// two base cases
		if (A.length == 2)
    	{	
			sortedA = new XYPoint[2];
    		if (lessThan.comp(A[0], A[1]))
			{
				sortedA[0] = A[0];
				sortedA[1] = A[1];
			}
			
			else 
			{	
				sortedA[0] = A[1];
				sortedA[1] = A[0];
			}
    		return sortedA;
    		
    	}
		
    	if (A.length == 1)
    	{
			sortedA = new XYPoint[1];
    		sortedA[0] = A[0];
    		return sortedA;
    	}
    	
    	
		else{
		
			int mid = A.length /2;
			XYPoint[] Aleft = new XYPoint[mid];
			XYPoint[] Aright = new XYPoint[A.length - mid];
			
			for (int i = 0; i < mid; i++) {
				Aleft[i] = A[i];
			}    	
    	
			for (int j = 0; j < A.length - mid; j++) {
				Aright[j] = A[j+mid];
			}
			XYPoint[] left = sort(Aleft, lessThan);
			XYPoint[] right = sort(Aright, lessThan);
			sortedA = merge(left, right, lessThan);
			return sortedA;
			
		}
	}
    
    
    public static XYPoint[] merge(XYPoint[] Aleft, XYPoint[] Aright, Comparator lessThan ){
    //return result
    	
    	XYPoint[] Result = new XYPoint[Aleft.length + Aright.length];
    	int left = 0;
    	int right = 0;
    	
  
    	for (int k = 0; k < Result.length; k++)
    	{	
			if (left == Aleft.length) 
			{	
				Result[k] = Aright[right];
				right++;
			}		
			
			else if (right == Aright.length)
			{
				Result[k] = Aleft[left];
				left++;
			}	
			else 
			{
				if (lessThan.comp(Aleft[left], Aright[right]))
				{
					Result[k] = Aleft[left];
					left ++;
				}
				else
				{
					Result[k] = Aright[right];
					right ++;
				}
			}    	
    	}
    	return Result;	
    }  
}