import java.util.*;
import Map.MyMap;
import Domain.Student;
public class Main {
    public static void main(String[] args) {
        HashSet<Student> studenti = new HashSet<>();
        Student s1= new Student("Dan", 4.5f);
        Student s2= new Student("Ana", 8.5f);
        Student s3= new Student("Daniel", 4.5f);
        Student s4= new Student("Maria", 4.8f);
        Student s5= new Student("Bogdan", 5.6f);
        studenti.addAll(Arrays.asList(s1,s2,s3));
        System.out.println("HashSet");
        for(Student  student : studenti){
            System.out.println(student);
        }
        System.out.println("\nTreeSet");
        TreeSet<Student> treeStud = new TreeSet<>(new NameComparator() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getNume().compareTo(o2.getNume());
            }
        });
        treeStud.addAll(Arrays.asList(s1,s2,s3));
        for(Student  student : treeStud){
            System.out.println(student);
        }

        System.out.println("\nHashMap");
        HashMap<String,Student> hashmap=new HashMap<>();
        hashmap.put(s1.getNume(),s1);
        hashmap.put(s2.getNume(),s2);
        hashmap.put(s3.getNume(),s3);

        for (Map.Entry<String,Student>pair: hashmap.entrySet()){
            System.out.println(pair.getKey()+ " "+ pair.getValue());
        }

        System.out.println("\nTreeMap");
        TreeMap<String,Student> treemap=new TreeMap<>();
        treemap.put(s1.getNume(),s1);
        treemap.put(s2.getNume(),s2);
        treemap.put(s3.getNume(),s3);

        for (Map.Entry<String,Student>pair: treemap.entrySet()){
            System.out.println(pair.getKey()+ " "+ pair.getValue());
        }

        System.out.println("\nMyMap");
        MyMap myMap= new MyMap();
        myMap.add(s1);
        myMap.add(s2);
        myMap.add(s3);
        myMap.add(s4);
        myMap.add(s5);
        myMap.printAll();

        System.out.println("\nSortedStudents");
        Set<Map.Entry<Integer,List<Student>>> entries = myMap.getEntries();
        for(Map.Entry<Integer,List<Student>> entry : entries){
            List<Student> studentList=entry.getValue();
            studentList.sort(new NameComparator());
        }


    }
    static class NameComparator implements  Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getNume().compareTo(o2.getNume());
        }

    }

}