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
}

