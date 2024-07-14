package io.gocklkatz.m12s.tsp;

import java.util.ArrayList;
import java.util.List;

public class TspService {
    private static List<Tsp> tsps = new ArrayList<>();

    static {
        int[][] distanceMatrix =  {
                {0,1,2,3,1},
                {1,0,5,2,3},
                {2,5,0,1,3},
                {3,2,1,0,4},
                {1,3,3,4,0}
        };
        tsps.add(new Tsp(1, distanceMatrix));
    }

    public List<Tsp> findAll() {
        return tsps;
    }

    public Tsp findOne(int id) {
        for(Tsp tsp:tsps) {
            if(tsp.getId()==id) {
                return tsp;
            }
        }
        return null;
    }

    public Tsp save(Tsp tsp) {
        tsps.add(tsp);
        return tsp;
    }
}
