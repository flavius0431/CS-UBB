package grades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
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
        Tema t1 = new Tema("1","la MAP");
        Tema t2 = new Tema("2","la SO");
        Tema t3 = new Tema("3","la ASC");
        Tema t4 = new Tema("4","la MAP");
        temaList.addAll(Arrays.asList(t1,t2,t3,t4));

        List<Nota> notaList = new ArrayList<Nota>();
        Nota n1 = new Nota("Popescu", s1,t1,9d);
        Nota n2 = new Nota("Popescu", s3,t1,10d);
        Nota n3 = new Nota("Vancea", s3,t3,7d);
        Nota n4 = new Nota("Ionescu", s4,t4,8d);
        notaList.addAll(Arrays.asList(n1,n2,n3,n4));


        System.out.println("1)");
        report1(notaList);
        System.out.println("2)");
        report2(notaList);
        System.out.println("3)");
        report3(notaList);
        System.out.println("4)");
        report4(notaList);
        System.out.println("5)");
        report5(notaList);
    }

    private static void report1(List<Nota> notaList){
        Predicate<Nota> byGrupa = x->x.getStudent().getGroup() == 212;
        Predicate<Nota> byProfesor = x->x.getProfesor().equals("Popescu");
        Predicate<Nota> filter = byGrupa.and(byProfesor);
        notaList.stream()
                .filter(filter)
                .map(x->
                    new NotaDTO(x.getStudent().getName(),x.getProfesor(),x.getTema().getId(),x.getValue())
                )
                .forEach(x-> System.out.println(x));
    }
    private static void report2(List<Nota> notaList){
        Map<Student,List<Nota>> note = notaList.stream()
                .collect(Collectors.groupingBy(x->x.getStudent()));
        note.entrySet()
                .forEach(x->{
                    System.out.println(x.getKey().getName());
                    int count = x.getValue().size();
                    double sum = x.getValue().stream()
                            .map(y->y.getValue())
                            .reduce(0d,(a,b)->a+b);
                    System.out.println(sum/count);
                });
    }

    //media notelor la o anumita tema
    private static void report3(List<Nota> notaList){
        Predicate<Nota> byTema = x->x.getTema().getId().equals("1");
        var count = notaList.stream()
                .filter(byTema);
        double sum =notaList.stream()
                .filter(byTema)
                .map(x->x.getValue())
                .reduce(0d,(a,b)->a+b);
        System.out.println(sum/ count.count());
    }

    //tema cu cea mai mare medie
    private static void report4(List<Nota> notaList){
        Map<Tema,List<Nota>> note = notaList.stream()
                .collect(Collectors.groupingBy(x->x.getTema()));
        final double[] media_max = {0};
        AtomicReference<String> id = new AtomicReference<>("");
        note.entrySet()
                .forEach(x->{
                    int count = x.getValue().size();
                    double sum = x.getValue().stream()
                            .map(y->y.getValue())
                            .reduce(0d,(a,b)->a+b);
                    if(sum/count > media_max[0]) {
                        media_max[0] = sum / count;
                        id.set(x.getKey().getId());
                    }
                });
        System.out.println(id);
    }

    //tema cea mai grea (cu cea mai mica medie)
    private static void report5(List<Nota> notaList){
        Map<Tema,List<Nota>> note = notaList.stream()
                .collect(Collectors.groupingBy(x->x.getTema()));
        final double[] media_min = {11};
        AtomicReference<String> id = new AtomicReference<>("");
        note.entrySet()
                .forEach(x->{
                    int count = x.getValue().size();
                    double sum = x.getValue().stream()
                            .map(y->y.getValue())
                            .reduce(0d,(a,b)->a+b);
                    if(sum/count < media_min[0]) {
                        media_min[0] = sum / count;
                        id.set(x.getKey().getId());
                    }
                });
        System.out.println(id);
    }


}
