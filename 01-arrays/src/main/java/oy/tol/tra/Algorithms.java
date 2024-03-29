package oy.tol.tra;

public class Algorithms<T extends Comparable<T>>{


    public static <T extends Comparable<T>> void sort(T [] array) {

        for(int i = 0; i < array.length; i++){
            for(int k = 1; k < (array.length-i); k++){             
                int j = k - 1;

                if(array[j].compareTo(array[k]) > 0){
                    swap(array, k, j);
                }
            }
        }      
    }


    public static <T> void reverse(T [] array) {

        int i = 0;

        while (i < array.length/2) {
            int k = array.length-i-1;
                swap(array, i, k); 
            i++;
        }   
    }

    public static <T> void swap(T [] array, int first, int second){

        T temp = array[first];
        array[first] = array[second];
        array[second] = temp; 
    }
}