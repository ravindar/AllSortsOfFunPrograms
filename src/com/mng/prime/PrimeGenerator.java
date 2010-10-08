package com.mng.prime;

public class PrimeGenerator {
    private static boolean[] crossedOut;
    private static int[] result;
    
    public static int[] generatePrimes(int maxValue){
        if(maxValue < 2)
            return new int[0];
        
        crossedOut = new boolean[maxValue +1];
		for (int i = 2; i < crossedOut.length; i++) {
		    crossedOut[i] = false;
		}
		
        int limit = determineIterationLimit();
		for (int i = 2; i < limit; i++) {
		    if(notCrossed(i)){
		        for (int multiple = 2*i; multiple < crossedOut.length ; multiple += i){
				    crossedOut[multiple] = true;
				}
		    }
		}
		
        result = new int[numberOfUncrossedIntegers()];
		for(int j=0, i=2; i< crossedOut.length ; i++)
		    if(notCrossed(i))
		        result[j++] = i;
		return result;
        
    }

    private static int numberOfUncrossedIntegers() {
    	int count = 0;
    	for(int i =2; i< crossedOut.length ; i++){
    		if(notCrossed(i))
    			count++;
    	}
		return count;
	}

    private static boolean notCrossed(int i) {
        return crossedOut[i] == false;
    }

    private static int determineIterationLimit() {
        return (int) Math.sqrt(crossedOut.length)+1;
    }
}
