package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class FullSet {

        Car[] cars;
        BstSet<Car> bstCarsSet;
        TreeSet<Car> treeCarsSet;

        @Setup(Level.Iteration)
        public void generateElements(BenchmarkParams params) {
            cars = Benchmark.generateElements(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarSet(BenchmarkParams params) {
            bstCarsSet = new BstSet<>();
            treeCarsSet= new TreeSet<>();
            for (Car car : cars) {
                bstCarsSet.add(car);
                treeCarsSet.add(car);
            }
        }
    }

    @Param({"10000", "20000", "40000", "80000", "100000"})
    public int elementCount;

    Car[] cars;

    @Setup(Level.Iteration)
    public void generateElements() {

        cars = generateElements(elementCount);
    }

    static Car[] generateElements(int count) {

        return new CarsGenerator().generateShuffle(count, 1.0);
    }

    // testing Class BstSet: remove() and Class java.util.TreeSet<E>: remove()
    @org.openjdk.jmh.annotations.Benchmark
    public BstSet<Car> removeBstSet() {
        BstSet<Car> carSet = new BstSet<>(Car.byPrice);
        for (Car car : cars) {
            carSet.remove(car);
        }

        return carSet;
    }

    @org.openjdk.jmh.annotations.Benchmark
    public TreeSet<Car> removeTreeSet() {
        TreeSet<Car> carSet = new TreeSet<>(Car.byPrice);
        for (Car car : cars) {
            carSet.remove(car);
        }

        return carSet;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
