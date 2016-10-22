package lab1;

public class ClosestPair {

    public final static double INF = java.lang.Double.POSITIVE_INFINITY;

    /** 
     * Given a collection of points, find the closest pair of point and the
     * distance between them in the form "(x1, y1) (x2, y2) distance"
     *
     * @param pointsByX points sorted in nondecreasing order by X coordinate
     * @param pointsByY points sorted in nondecreasing order by Y coordinate
     * @return Result object containing the points and distance
     */
   
	//lowest X first
    public double min(double a, double b) {
 	   if (a < b)
			return a;
 	   else
			return b;
     }
    
    static Result findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[]) {
       // YOUR CODE GOES HERE
		XComparator comparatorX = new XComparator();
		YComparator comparatorY = new YComparator();
    	if (pointsByX.length == 0) {  
    		return null;
		}
		
    	else if (pointsByX.length == 1) {          
			Result min = new Result(pointsByX[0],pointsByX[0],INF);
			return min;
		}
		
    	else if (pointsByX.length == 2) {
			Result min;
			Result temp = new Result(pointsByX[0],pointsByX[1],pointsByX[0].dist(pointsByX[1]));
			if (comparatorX.comp(pointsByX[0],pointsByX[1])) {
				min = temp;
			}
			
    		else {
				min = new Result (pointsByX[1],pointsByX[0],pointsByX[0].dist(pointsByX[1]));
			}		
			return min;
			
		}
		
		else if (pointsByX.length == 3) {
			Result min;
			Result temp = new Result(pointsByX[0],pointsByX[0],INF);
			for (int i = 0; i < pointsByX.length; i++) {
				for (int j = i+1; j < pointsByX.length; j++) {
					if ((pointsByX[i].dist(pointsByX[j])) < temp.dist) {
						temp.p1 = pointsByX[i];
						temp.p2 = pointsByX[j];
						temp.dist = pointsByX[i].dist(pointsByX[j]);
					}
				}
			}
			
			if (comparatorX.comp(temp.p1,temp.p2)) {
				min = temp;
			}
			
    		else {
				min = new Result (temp.p2,temp.p1,temp.dist);
			}
			return min;
		}
    	
    	else {
		
			int mid = pointsByX.length/2;
			XYPoint[] XL = new XYPoint[mid];
			XYPoint[] XR = new XYPoint[pointsByX.length - mid];
			XYPoint[] YL = new XYPoint[mid];
			XYPoint[] YR = new XYPoint[pointsByX.length - mid];
			
			
			
    	
			for (int i = 0; i < mid; i++){
				XL[i] = pointsByX[i];
			}
			
			for (int j = 0; j < pointsByX.length - mid; j++){
				XR[j] = pointsByX[j+mid];
			}
 
			int yleft = 0;
			int yright = 0;
			
			for (int k = 0; k < pointsByY.length; k++) {
				if (comparatorX.comp(pointsByY[k],pointsByX[mid])) {
					YL[yleft] = pointsByY[k];
					yleft++;
				}
				
				else {
					YR[yright] = pointsByY[k];
					yright++;
				}
			}
    	  	
			Result Lmin = findClosestPair(XL, YL);
			Result Rmin = findClosestPair(XR, YR);
			Result rMin = minR(Lmin,Rmin);
			
    	
			//create an array for midpoints
			XYPoint[] midPoints = new XYPoint[pointsByX.length];
			int midp = 0;
			for (int p = 0; p < pointsByX.length; p++){
				if ((abs(pointsByY[p].x - pointsByX[mid].x) < rMin.dist)) {
				midPoints[midp] = pointsByY[p];
				midp++;
				}
			}
			

			//sort the midpoints array
			Result MidResult = rMin;
			for (int midA = 0; midA < midp; midA++) {
			
				for (int midB = midA+1; midB < midp; midB++) {
				
					if (midPoints[midB].dist(midPoints[midA]) < MidResult.dist) {
						MidResult = new Result(midPoints[midA],midPoints[midB],midPoints[midB].dist(midPoints[midA]));
					}
				}			

			}
			Result temp = minR(rMin,MidResult);
			
			Result fResult;
			if (comparatorX.comp(temp.p1,temp.p2)) {
				fResult = temp;
			}
			else 
				fResult = new Result(temp.p2,temp.p1,temp.dist);

			return fResult;
			
		}
	
	}
    static Result minR(Result A, Result B)
    {
    	if (A.dist < B.dist) 
			return A;
		else return B;
    }
    
  
    static int abs(int x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }
}
