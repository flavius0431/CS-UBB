package grades;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GradeRunner {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        Student s1 = new Student(212,"Ana");
        s1.setId(1L);
        Student s2 = new Student(212,"Dan");
        s2.setId(2L);
        Student s3 = new Student(214,"Paula");
        s3.setId(3L);
        Student s4 = new Student(215,"Mircea");
        s4.setId(4L);
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);

        List<Tema> temaList = new ArrayList<Tema>();
        Tema t1= new Tema("La MAP","1");
        Tema t2= new Tema("La SO","2");
        Tema t3= new Tema("La ASC","3");
        Tema t4= new Tema("La MAP","4");

        temaList.addAll(Arrays.asList(t1,t2,t3,t4));

        List<Nota> notaList=new ArrayList<Nota>();
        Nota n1 = new Nota("Popescu",s1,t1,9d);
        Nota n2 = new Nota("Popescu",s2,t1,10d);
        Nota n3 = new Nota("Vancea",s3,t3,7d);
        Nota n4 = new Nota("Ionescu",s4,t4,8d);
        notaList.addAll(Arrays.asList(n1,n2,n3,n4));
        report1(notaList);
    }

    public static void report1(List<Nota> notaList){
        Predicate<Nota> byGrupa = x->x.getStudent().getGroup()==212;
        Predicate<Nota> byProfesor = x->x.getProfesor().equals("Popescu");
        Predicate<Nota> filter= byGrupa.and(byProfesor);
        notaList.stream()
                .filter(filter)
                .map(x->
                    new NotaDTO(x.getStudent().getName(), x.getProfesor(), x.getTema().getId(),x.getValue())
                )
                .forEach(x-> System.out.println(x));
    }

    public static void report2(List<Nota> notaList){
        Map<Student,List<Nota>> note = notaList.stream()
                .collect(Collectors.groupingBy(x->x.getStudent()));
        note.entrySet()
                .forEach(x->{
                    System.out.println(x.getKey().getName());
                    int count = x.getValue().size();
                    double sum =x.getValue().stream()
                            .map(y->y.getValue())
                            .reduce(0d,(a,b)->a+b);
                    System.out.println(sum/count);
                });
    }
}
