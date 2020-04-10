package ru.gb.jtwo.lesson_3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Jan", 1);
        map.put("Feb", 2);
        map.put("Mar", 3);
        map.put("Apr", 4);
        map.put("May", 5);
        map.put("Jun", 6);

        System.out.println(map);


        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        Set<Map.Entry<String, Integer>> mset = map.entrySet();
        for (Map.Entry<String, Integer> entry : mset) {
            System.out.println(entry.getKey() + "==" + entry.getValue());
        }

        Set<String> kset = map.keySet();
        for (String k : kset) {
            System.out.println(k + "=" + map.get(k));
        }

    }

    private static void treeExample() {
        Box b0 = new Box(1,1);
        Box b1 = new Box(2,2);
        Box b2 = new Box(3,3);
        Box b3 = new Box(4,4);
        Set<Box> set = new TreeSet<>(Arrays.asList(b0, b2, b3, b1));

        System.out.println(set);
    }

    private static void setExample() {
        Set<String> set = new HashSet<>();
        set.add("jan");
        set.add("feb");
        set.add("mar");
        set.add("apr");
        set.add("may");
        set.add("jan");

        System.out.println(set);

//        System.out.println(set.contains("feb"));
    }

    private static void alBoxLLExample() {
        Box b0 = new Box(1,1);
        Box b1 = new Box(2,2);
        Box b2 = new Box(3,3);
        Box b3 = new Box(4,4);
        LinkedList<Box> list = new LinkedList<>();
        list.add(b0);
        list.add(b1);
        list.add(b2);
        list.add(b3);
        // other project

        Box b = new Box(1,1);
        System.out.println(list);

        System.out.println(Integer.toHexString(b0.hashCode()));
        System.out.println(Integer.toHexString(b.hashCode()));

        System.out.println(list.contains(b));
    }

    private static void alSimpleExample() {
        int i0 = 10;
        Integer i1 = new Integer(10);
        i1 = 11;

        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Java");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String s : list) {
            System.out.println(s);
        }

        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            System.out.println(s);
        }

        System.out.println(list);
    }
}
