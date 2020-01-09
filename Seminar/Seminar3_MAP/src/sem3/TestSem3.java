package sem3;

import java.util.*;

public class TestSem3 {
    public static void main(String[] args){
        Student s1=new Student("Dan",4.5f);
        Student s2=new Student("Ana",8.5f);
        Student s3=new Student("Dan",4.5f);
        Student s4=new Student("Andrei",1.1f);
        Student s5=new Student("Barbu",10.0f);

        //s1.setId((long)12);
        HashSet<Student> h=new HashSet<>();
        //HashSet h=new HashSet<Student>(); //sunt echivalente?

        h.add(s1);
        h.add(s2);
        h.add(s3);
        System.out.println(h);

        TreeSet<Student> tree=new TreeSet<>();
        tree.addAll(Arrays.asList(s1,s2,s3,s4,s5));
        System.out.println(tree);

        TreeSet<Student> tree2=new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        tree2.addAll(Arrays.asList(s1,s2,s3,s4,s5));
        System.out.println(tree2);

        Map<String, Student> map1=new HashMap<>();
        map1.put(s1.getName(),s1);
        map1.put(s2.getName(),s2);
        map1.put(s3.getName(),s3);
        map1.put(s4.getName(),s4);
        map1.put(s5.getName(),s5);

        System.out.println(map1.entrySet());
        for (Map.Entry<String, Student> pereche : map1.entrySet()) {
            System.out.println("key="+pereche.getKey()+ " value="+pereche.getValue());
        }

        Map<String, Student> map=new TreeMap<>();
        map.put(s1.getName(),s1);
        map.put(s2.getName(),s2);
        map.put(s3.getName(),s3);
        map.put(s4.getName(),s4);
        map.put(s5.getName(),s5);

        System.out.println(map.entrySet());
  }
}
