package io.gocklkatz.m12s.tsp;

import java.util.ArrayList;
import java.util.List;

// https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
public class Permutation {
    private List<String> tours = new ArrayList<>();

    public List<String> getTours() {
        return tours;
    }

    public void permute(String str, int l, int r)
    {
        if (l == r) {
            tours.add(str);
        }
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }

    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
