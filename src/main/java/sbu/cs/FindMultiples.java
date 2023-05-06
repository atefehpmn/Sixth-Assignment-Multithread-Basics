package sbu.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FindMultiples
{
    public class Multiple implements Runnable{
        private int n;
        private int div;

        public Multiple(int n, int div) {
            this.n = n;
            this.div = div;
        }
        private ArrayList<Integer> muls = new ArrayList<>();

        public ArrayList<Integer> getMuls() {
            return muls;
        }

        @Override
        public void run() {
            for (int i = 1; i <= n / div; i++){
                muls.add(i * div);
            }
        }
    }

    public int getSum(int n) {
        int sum = 0;

        ExecutorService TP = Executors.newFixedThreadPool(3);
        Multiple muls3 = new Multiple(n, 3);
        TP.execute(muls3);
        Multiple muls5 = new Multiple(n, 5);
        TP.execute(muls5);
        Multiple muls7 = new Multiple(n, 7);
        TP.execute(muls7);
        TP.shutdown();
        try {
            TP.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> allMuls = new ArrayList<>();
        allMuls.addAll(muls3.getMuls());
        allMuls.addAll(muls5.getMuls());
        allMuls.addAll(muls7.getMuls());

        Integer[] arr = new Integer[allMuls.size()];  //convert arraylist to array and use built-in methods to calculate sum of unique elements
        arr = allMuls.toArray(arr);
        sum = Arrays.stream(arr).distinct().mapToInt(Integer::intValue).sum();

        return sum;
    }

    public static void main(String[] args) {
    }
}
