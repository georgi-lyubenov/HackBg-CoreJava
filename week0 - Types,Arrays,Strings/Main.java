
public class Main {
    
    boolean isOdd(int n){
        return n % 2 != 0;
    }
    boolean isPrime(int N){
        for(int i=2;i<N;i++){
            if (N % i == 0)
                return false;
        }
        return true;
    }
    
    int min(int[] array){
        int minimum = array[0];
        for (int i=1; i< array.length;i++){
            if (array[i] <= minimum)
                minimum = array[i];
        }
        return minimum;
    }
    
    int kthMin(int k, int[] array){
        int minimum = array[0];
        int count = 1;
        int i = 0;
        while (count != k){
            if (array[i] < minimum){
                minimum = array[i];
            }
            count++;
        }
        return minimum;
    }
    
    double getAverage(int[] array){
        int sum = 0;
        for(int i=0;i<array.length;i++){
            sum += array[i];
        }
        return sum / (double)array.length;
    }
    
    int fact(int n){
        if (n <= 1) return 1;
        return n * fact(n-1);
    }
    
    long doubleFac(int n){
        return fact(fact(n));
    }
    
    
    long kthFac(int k, int n){
    	long res = 1;
        long limit = n;
        for (long i = 2; i <= limit; i++) {
            res *= i;
            if (i == limit && k > 0) {
                k--;
                if (k == 0){
                	return res;
                }
                limit = res;
            }
        }
        return res;
    }
    
    boolean div(int number, int[] arr){
        for (int i=0;i< arr.length;i++){
            if (number % arr[i] != 0)
                return false;
        }
    return true;
    }
    
    long getSmallestMultiple(int upperBound){
        int arr[] = new int[upperBound];
        for (int i=0; i < upperBound;i++)
            arr[i]= i + 1;
        int p = fact(upperBound);
        for (int i =1; i < p; i++){
            if (div(i, arr) == true)
                return i;
        }
        return p;
    }
    
    boolean palindrom(long number){
        long temp = number;
        int p = 0;
        while (temp > 0){
            p *= 10;
            p += (temp % 10);
            temp = temp / 10;
        }
        if (number == p)
            return true;
        else 
            return false;
        
    }
    
    long getLargestPalindrome(long N){
        for(long i= N-1;i>= 0;i--){
            if (palindrom(i) == true)
                return i;
        }
        return N;
    }
    
    int appearences(int number, short[][] matrix){
        int sum = 0;
        for (int i=0;i< matrix.length; i++)
            for (int j=0;j<matrix.length;j++){
                if (matrix[i][j] == number)
                    sum ++;
            }
        return sum;
    }
    int[] histogram(short[][] image){
        int result[] = new int[256];
        for (int i=0; i< 256; i++){
            result[i] = appearences(i, image);
        }
        return result;
    }
    
    long pow(int a, int b){
        if (b <0 )
            return 1/pow (a,-b);
        if (b == 0)
            return 1;
        return a * pow(a, b - 1);
    }
    
    boolean OddOccur(int number, int[] arr){
        int sum = 0;
        for (int i=0;i< arr.length;i++){
            if (arr[i] == number)
                sum ++;
        }
        if (sum % 2 != 0)
            return true;
        else
            return false;
    }
    int getOddOccurrence(int[] array){
        for (int i=0; i< array.length; i ++){
            if (OddOccur(array[i], array) == true)
                return array[i];
        }
        return -1;
    }
    
    int[] reverseArr(int[] array){
        for(int i = 0; i < array.length / 2; i++)
        {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
    long maximalScalarSum(int[] a, int[] b){
        int sum1 =0, sum2 = 0;
        for (int i=0;i<a.length;i++){
            sum1 += a[i]*b[i];
        }
        int[] b2 = reverseArr(b);
        for (int i=0;i<a.length;i++){
            sum2 += a[i]*b2[i];
        }
        if (sum1 > sum2) return sum1;
        else return sum2;
    }
    
    int span(int number, int[] arr){
        int len = arr.length - 1;
        int i=0;
        while (arr[i] != number){
            i++;
        }
        int startIndex = i - 1;
        int j = len;
        while(arr[j] != number){
            j--;
        }
        int endIndex = j;
        return endIndex - startIndex;
    }
    int maxSpan(int[] numbers){
        int maxSpan = 0;
        for (int i=0;i<numbers.length;i++){
            if (span(numbers[i], numbers) > maxSpan)
                    maxSpan = span(numbers[i], numbers);
        }
        return maxSpan;
    }
    
    boolean canBalance(int[] numbers){
        int sum = 0;
        int currentSum =0;
        for (int i=0;i<numbers.length;i++){
            sum += numbers[i];
        }
        for (int i=0;i<numbers.length;i++){
            if (currentSum + numbers[i] == sum - currentSum - numbers[i])
                return true;
            else{
                currentSum += numbers[i];
            }
        }
        return false;
    }
    
    // 16 ?
    
    String reverseMe(String argument){
        int len= argument.length();
        char[] tempCharArray = new char[len];
        char[] charArray = new char[len];
        //argument.getChars(0, len, tempCharArray, 0);
        for (int i = 0; i < len; i++) {
            tempCharArray[i] = 
                argument.charAt(i);
        }
        for (int j = 0; j < len; j++) {
            charArray[j] =
                tempCharArray[len - 1 - j];
        }
        String result = new String(charArray);
        return result;
    }
    
    
    String reverseEveryChar(String arg){
        int len = arg.length();
        StringBuilder arrayOfStrings = new StringBuilder();
        char[] inputString = arg.toCharArray();
        StringBuilder subStr = new StringBuilder();
        for (int i = 0; i < len; i++){
            if (inputString[i] == ' '){
                arrayOfStrings.append(subStr.reverse());
                arrayOfStrings.append(" ");
                subStr.delete(0, subStr.length());
            }
            else{
                subStr.append(inputString[i]);
            }
        }
        arrayOfStrings.append(subStr.reverse());
        return arrayOfStrings.toString();
    }
    
    
    boolean isPalindrome(String argument){
        return (argument.equals(reverseMe(argument)));
    }
    
    boolean isPalindrome(int argument){
        int p=0 ,temp = argument;
        while (temp > 0){
            p *= 10;
            p += (temp % 10);
            temp /= 10;
        }
        return (argument == p);
    }
    
    
    String copyEveryChar(String input, int k){
        int len= input.length();
        char[] tempCharArray = new char[len];
        StringBuilder charArray = new StringBuilder();
        input.getChars(0, len, tempCharArray, 0);
        for (int i = 0;i < len; i++){
            for (int j =0;j < k;j ++){
                charArray.append(tempCharArray[i]);
            }  
        }        
        return charArray.toString();
             
    }
    
    int getPalindromeLength(String input){
        int len= input.length();
        char[] inputString = input.toCharArray();
        int indexOfStar = 0;
        
        for (int i=0;i<len;i++){
            if (inputString[i] == '*')
                indexOfStar = i;
        }
        int i=1;
        int count = 0;
        while(inputString[indexOfStar - i] == inputString[indexOfStar + i]){
            i++;
            count++;
        }
        return count;

    }
    
    int countOcurrences(String needle, String haystack){
        int counter = haystack.split(needle, -1).length-1;
        return counter;
    }
    
    String decodeUrl(String input){
        return input.replaceAll("%20", " ").replaceAll("%3A", ":").replaceAll("%3D", "?").replaceAll("%2F", "/");
    }
    
    int sumOfNumbers(String input){
        input = input .replaceAll("[\\D]+"," ");
        String[] numbers = input.split(" ");
        int sum = 0;
        for(int i=0;i<numbers.length;i++){
            try{
                sum += Integer.parseInt(numbers[i]);
            }
            catch( Exception e ) {
              //Just in case, the element in the array is not parse-able into Integer, Ignore it
            }
        }
        return sum;
    }
    
    
    boolean member(char c, char[] arr){
        for (int i=0; i < arr.length; i++){
            if (arr[i] == c) return true;
        }
        return false;
    }
    boolean anagram(String A, String B){
        char[] firstArray = A.toCharArray();
        char[] secondArray = B.toCharArray();
        if (B.length() == A.length()){
            for (int i = 0; i < A.length();i++){
                if (member(firstArray[i], secondArray) == true) {}
                else return false;
            }
            return true;
        }
        return false;
    }
    
    boolean hasAnagramOf(String A, String B){
        for (int i = 0;i < B.length();i++){
            if (anagram(A, B.substring(i, i + A.length()))){
                return true;
            }
        }
        return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //int testArr[] = {5,3,9,2,7};
	    //int test12arr[] = {1,2,2,1,3,4,3,4,4,6,5,6,5};
	    //int a[] = {1,2,3};
	    //int b[] = {4,5,6};
	    //int spanArr[] = {1, 2, 4, 2, 4, 1, 4, 1};
	    //int spanArr2[] = {1, 2, 1, 1, 3, 7, 8, 9, 10, 11};//4
	    //int n = 12321;
	    //String s3 = "tldr";
	    //String s4 = "taz*zad";
	    //String s5 = "what is this";
	    //String s6 = "mary";
	    //String s7 = "army";
	    //String s8 = "you are in the army now";
	    Main object = new Main();
	    //System.out.print(object.sumOfNumbers("abc123dd34"));
	    //System.out.print(object.hasAnagramOf(s6, s8));
	    //System.out.print(object.anagram(s6, s7));
	    //System.out.print(object.getPalindromeLength(s4));
	    //System.out.print(object.countOcurrences("da", "daaadaadada"));
	    //System.out.print(object.copyEveryChar(s3, 3));
	    //System.out.print(object.isPalindrome(n));
	    //String s = "abcd";
	    //String s2 = "dot saw I was tod";
	    //System.out.print(object.isPalindrome(s2));
	    //System.out.print(object.reverseMe(s2));
	    //System.out.print(object.span(1, spanArr2));
	    //System.out.print(object.reverseEveryChar(s5));
	    //System.out.print(object.maxSpan(spanArr));
	    //System.out.println("");
	    //System.out.print(object.maxSpan(spanArr2));
	    //int balanceArr[] = {10,10};
	    //System.out.print(object.canBalance(balanceArr));
	    //System.out.print(object.maximalScalarSum(a, b));
	    //System.out.print(object.getOddOccurrence(test12arr));
	    //System.out.print(object.pow(2, 5));
	    //System.out.print(object.getLargestPalindrome(1234321));
	    //System.out.print(object.getSmallestMultiple(4));
	    //System.out.print(object.fact(5));
	    //System.out.print(object.doubleFac(3));
	    //System.out.print(object.kthFac(2, 3));
	    //System.out.print(object.getAverage(testArr));
	    //System.out.print(object.kthMin(3, testArr));
	    //System.out.print(object.min(testArr));
	    //System.out.println(object.isOdd(5));
	    //System.out.println(object.isPrime(7));
	}

}
