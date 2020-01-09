package map.seminar6;

import map.seminar6.domain.Nota;
import map.seminar6.domain.NotaDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("ok");
        List<String> list = Arrays.asList("bcd", "asd", "mno", "clf", "map");
        list.stream()
                .sorted((x, y) -> {
                    System.out.println("sorted: " + x + " " + y);
                    return x.compareTo(y);
                })
                .map(x -> {
                    System.out.println("map: " + x);
                    return x.toUpperCase();
                })
                .filter(x -> {
                    System.out.println("filter: " + x);
                    return x.startsWith("M");
                })
                .forEach(s -> System.out.println("forEach: " + s));


    }
}
