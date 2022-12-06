package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms<T extends Comparable<T>>{


    public static <T extends Comparable<T>> void sort(T [] array) {

        try{

            for(int i = 0; i < array.length; i++){
                for(int k = 1; k < (array.length-i); k++){             
                    int j = k - 1;

                    if(array[j].compareTo(array[k]) > 0){
                        swap(array, k, j);
                    }
                }
            }
            
        }catch(NullPointerException e){
            System.out.println("NullPointerExeption Caught!");
        }
    }


    public static <T> void reverse(T [] array) {

        try{

            int i = 0;

            while (i < array.length/2) {
                int k = array.length-i-1;
                    swap(array, i, k); 
                i++;
            }   

        }catch(NullPointerException e){
            System.out.println("NullPointerExeption Caught!");
        }
    }

    public static <T> void swap(T [] array, int first, int second){

        T temp = array[first];
        array[first] = array[second];
        array[second] = temp; 
    }


    public static class ModeSearchResult<T> {
        public T theMode;
        public int count = 0;
    }
  
    public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array) {
        ModeSearchResult<T> result = new ModeSearchResult<>();
        result.theMode = null;
        result.count = -1;

        int topF = 1;
        int tempF = 1;
        int i = 1;

        if(array == null || array.length < 2){
            return result;
        }

        sort(array);

        result.theMode = array[0];
        result.count = -1;

        while(i < array.length){
            if(array[i].compareTo(array[i-1]) <= 0){
            tempF++;
            } else {
                if(tempF > topF){
                    result.count = tempF;
                    result.theMode = array[i-1];
                    topF = tempF;
                }
                tempF = 1;
            }
            i++;
        }
        if(tempF > topF){
            result.count = tempF;
            result.theMode = array[i-1];
            topF = tempF;
        }
        return result;
    }


    public static <T> int partitionByRule(T [] array, int count, Predicate<T> rule){

        if(array == null){
            return -1;
        }

        int index = 0;
        
        for( ; index < count; index++){
            if(rule.test(array[index])){
                break;    
            }
        }
        if(index >= count){
            return count;
        }

        int nextIndex = index + 1;

        while(nextIndex < count){
            if(!(rule.test(array[nextIndex]))){
                swap(array, index, nextIndex);
                index++;
            }
            nextIndex++;
        }
        return index;
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {

        if (fromArray == null || fromArray.length == 0 || fromIndex < 0 || toIndex >= fromArray.length || fromIndex > toIndex) {
            return -1;
          }
        
          int low = fromIndex;
          int high = toIndex;
        
          while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (aValue.compareTo(fromArray[mid]) < 0) {
              high = mid - 1;
            } else if (aValue.compareTo(fromArray[mid]) > 0) {
              low = mid + 1;
            } else {
              return mid;
            }
          }

        return -1;
    }


    public static <E extends Comparable<E>> void fastSort(E[] array) {
        if (array == null || array.length == 0) {
            return;
        }
    
        fastSort(array, 0, array.length - 1);
    }
    
    private static <E extends Comparable<E>> void fastSort(E[] array, int low, int high) {
        if (low >= high) {
            return;
        }
    
        int pivotIndex = partition(array, low, high);
        fastSort(array, low, pivotIndex - 1);
        fastSort(array, pivotIndex + 1, high);
    }
    
    private static <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        int pivotIndex = low + ((high - low) / 2);
        swap(array, pivotIndex, high);
    
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(array[high]) <= 0) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, high);
        return i;
    }
}

