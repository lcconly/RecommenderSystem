package StatisDeal;

public class QuieckSortMedium {
    private boolean bOdd;//is odd?  
    private int kv;//k value
    private double medium;  
    public int partition(Object a[], int low, int high) {  
        Double tmp = (Double)a[low];  
        int i = low, j = high;  
        while (i < j) {  
            while (i < j && ((Double)a[j]).doubleValue() >= tmp){  
                j--;  
            }  
            while (i < j && ((Double)a[i]).doubleValue() <= tmp){  
                i++;  
            }  
            swap(a, i, j);  
        }  
        a[low] = a[i];  
        a[i] = tmp;  
        return i;  
    }  
  
    public void swap(Object a[], int i, int j) {  
        if(i == j){  
            return;  
        }  
        Object tmp = a[i];  
        a[i] = a[j];  
        a[j] = tmp;  
    }  
    public void findMedium(Object a[]){  
        bOdd = a.length % 2 == 0;  
        kv = a.length / 2 + 1;  
        medium = 0;  
        findK(a, 0, a.length - 1, kv, -1);  
    }  
      
    /** 
     * find k miniest  
     * @param a 
     * @param low 
     * @param high 
     * @param k 
     * @param prePart  
     */  
    public void findK(Object a[], int low, int high, int k, int prePart){  
        if(low > high){  
            return;  
        }  
        int pos = partition(a, low, high);  
        int left = pos - low + 1;//the count in the left  
        if(k > left){//median is in the right, use the value as the next prePart  
            findK(a, pos + 1, high, k - left, pos);  
        }  
        else if(k < left){//median is in the left, this prePart will be next prePart  
            findK(a, low, pos - 1, k, prePart);  
        }  
        else{  
            if(bOdd){//if it is odd  
                double v1 = ((Double)a[pos]).doubleValue();  
                double v2 = 0;  
                if(low >= pos){  
                    v2 = ((Double)a[prePart]).doubleValue(); //when there is no number in the left, take prePart  
                }else{  
                    v2 = findMax(a, low, pos - 1);//when there is value in the left, take the max
                }  
                medium = (v1 + v2) / 2;  
            }else{  
                medium = ((Double)a[pos]).doubleValue();  
            }  
        }  
    }  
      
    public double findMax(Object a[], int low, int high){  
        double max = ((Double)a[low]).doubleValue();  
        for(int i = low + 1; i <= high; i ++){  
            if(((Double)a[i]) > max){  
                max = ((Double)a[i]).doubleValue();  
            }  
        }  
        return max;  
    }  
      
    public double getMedium(){  
        return medium;  
    }  
}
