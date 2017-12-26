package sp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(x -> {
            try {
                return Files.lines(Paths.get(x));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }).filter(x -> x.contains(sequence)).collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random rand = new Random();
        return Stream.generate(() -> {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            return Math.sqrt(Math.abs((x - 0.5)*(x - 0.5)) + Math.abs((y - 0.5)*(y - 0.5)));
        }).limit(10000)
                .filter(p -> p < 0.5)
                    .count()/10000.0;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream().collect(
                Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(
                                l -> l.getValue().stream().mapToInt(String::length).reduce(0, (a, b) -> a + b)
                        )
                )).entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue))
                .orElseThrow(IllegalArgumentException::new).getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(x -> x.entrySet().stream()).collect(
                Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(
                                Map.Entry::getValue
                        )
                )
        );
    }
}
