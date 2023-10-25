package stream;

import java.util.*;

public class Stream {
    public static void main(String[] args){
        List<String> list = Arrays.asList("asf","bcd","asd","bed","bbb");
        String rez = list.stream()
                .filter(x-> {
                    return x.startsWith("b");
                })
                .map(x->{
                   return x.toUpperCase();
                })
                .reduce("",(x,y)->x+y);
        System.out.println("A:\n");
        System.out.println(rez);

        System.out.println("B:\n");
        list.stream()
                .filter(x->{
                    System.out.println(x);
                    return x.startsWith("b");
                })
                .map(x->{
                    System.out.println(x);
                    return x.toUpperCase();
                })
                .forEach(System.out::println);

        System.out.println("C:\n");
        Optional<String> rez2=list.stream()
                .filter(x->{
                    return x.startsWith("b");
                })
                .map(x->{
                    return x.toUpperCase();
                })
                .reduce((x,y)->x+y);
        if(!rez2.isEmpty())
            System.out.println(rez2.get());
        rez2.ifPresent(x-> System.out.println(x));
    }
}
