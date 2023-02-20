package com.example.concurrency.example.syncContainer;

import com.example.concurrency.annoations.NotThreadSafe;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author xurui
 * @description TODO
 * @date 2023/2/9 22:30
 */
@Slf4j
@NotThreadSafe
public class VectorExample3 {
    private static Vector<Integer> vector = new Vector<>();

    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> vector) { //foreach
        for (Integer i : vector) {
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> vector) { //iterator
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).equals(3)) {
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }

}
