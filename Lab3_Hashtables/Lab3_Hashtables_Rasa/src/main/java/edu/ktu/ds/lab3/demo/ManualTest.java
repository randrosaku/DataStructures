package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.*;

import java.util.Locale;

import static edu.ktu.ds.lab3.utils.HashMap.DEFAULT_INITIAL_CAPACITY;
import static edu.ktu.ds.lab3.utils.HashMap.DEFAULT_LOAD_FACTOR;

public class ManualTest {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // we standardize number formats
        executeTest();
    }

    public static void executeTest() {
        Car car1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car car2 = new Car("Renault", "Megane", 2001, 20000, 3500);
        Car car3 = new Car("Toyota", "Corolla", 2001, 20000, 8500.8);
        Car car4 = new Car("Renault Laguna 2001 115900 7500");
        Car car5 = new Car.Builder().buildRandom();
        Car car6 = new Car("Honda   Civic  2007  36400 8500.3");
        Car car7 = new Car("Renault Laguna 2001 115900 7500");
        Car car8 = new Car("Tuscan TVR 2005 111800 17500");
        Car car9 = new Car("Honda Laguna 2001 5900 8500");
        Car car10 = new Car("Audi A20 2001 1900 7200");

        // view key array
        String[] carsIds = {"TA156", "TA102", "TA178", "TA126", "TA101", "TA099", "TA107", "TA155", "TA177", "TA179"};
        // An array of view values
        Car[] cars = {car1, car2, car3, car4, car5, car6, car7, car8, car9, car10};

        executeCarMapTests(carsIds, cars);
        //executeCarMapOaTests(carsIds, cars);

    }

    private static void executeCarMapTests(String[] carsIds, Car[] cars) {
        ParsableHashMap<String, Car> carsMap
                = new ParsableHashMap<>(String::new, Car::new, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, HashManager.HashType.DIVISION);

        for (int id = 0; id < cars.length; id++) {
            carsMap.put(carsIds[id], cars[id]);
        }

        Ks.oun("Arrangement of pairs in the view by keys:");
        carsMap.println("");

        Ks.oun(carsMap.averageChainSize());
        /*
        Ks.oun("Is there a pair in the picture?");
        Ks.oun(carsMap.contains(carsIds[6]));
        Ks.oun(carsMap.contains(carsIds[7]));
        Ks.oun("Arrangement of pairs in the view by keys. Only keys are shown:");
        carsMap.println("=");

        Ks.oun("\n" +
                "We are searching for pairs in the view:");
        Ks.oun(carsMap.get(carsIds[2]));
        Ks.oun(carsMap.get(carsIds[7]));
        Ks.oun("We print the view pairs in String:");
        Ks.oun("Does the given car exist? (Toyota,Corolla, 2001, 20000, 8500.8) (returns true)");
        Ks.oun(carsMap.containsValue(cars[2]));

        Car nonExistentCar = new Car("Maserati Ghibli 2021 1100 60000");

        Ks.oun("Does the given car exist? (Maserati Ghibli 2021 1100 60000) (returns false)");
        Ks.oun(carsMap.containsValue(nonExistentCar));

        Ks.oun("Replaces TA178 key value (Toyota,Corolla, 2001, 20000, 8500.8) with new value (Maserati Ghibli 2021 1100 60000), and returns true");
        Ks.oun(carsMap.replace(carsIds[2], cars[2], nonExistentCar));
        Ks.ounn(carsMap);

        Ks.oun("Returns false when the given key (TA178) is not true");
        Ks.oun(carsMap.replace(carsIds[2], cars[5], nonExistentCar));

        Ks.oun("Returns false when the given key (TA178) value does not exist");
        Ks.oun(carsMap.replace("TA999", cars[5], nonExistentCar));

        Ks.oun("Returns null if the car does not exist (by the key)");
        Ks.oun(carsMap.remove("TA999"));

        Ks.oun("Returns the car it deletes (Honda   Civic  2007  36400 8500.3)");
        Ks.oun(carsMap.remove(carsIds[5]));
        Ks.ounn(carsMap);*/
    }

    private static void executeCarMapOaTests(String[] carsIds, Car[] cars) {
        ParsableMap<String, Car> carsMapOa
                = new ParsableHashMapOa<>(String::new, Car::new, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.LINEAR);

        for (int id = 0; id < cars.length; id++) {
            carsMapOa.put(carsIds[id], cars[id]);
        }

        Ks.oun("Location of pairs in the open address view by keys:");
        carsMapOa.println("");
        /*Ks.oun("Is there a pair in the open address view?");
        Ks.oun(carsMapOa.contains(carsIds[6]));
        Ks.oun(carsMapOa.contains(carsIds[7]));
        Ks.oun("Arrangement of pairs in an open addressing view by keys. Only keys are shown:");
        carsMapOa.println("=");

        Ks.oun("We are searching for pairs in the open address view:");
        Ks.oun(carsMapOa.get(carsIds[2]));
        Ks.oun(carsMapOa.get(carsIds[7]));
        Ks.oun("We print the pairs of the open address view in the String line:");*/

        /*Ks.oun("Does the given car exist? (Toyota,Corolla, 2001, 20000, 8500.8) (returns true)");
        Ks.oun(carsMapOa.containsValue(cars[2]));

        Car nonExistingCar = new Car("Maserati Ghibli 2021 1100 60000");

        Ks.oun("Does the given car exist? (Maserati Ghibli 2021 1100 60000) (returns false)");
        Ks.oun(carsMapOa.containsValue(nonExistingCar));

        Ks.oun("Replaces TA178 key value (Toyota,Corolla, 2001, 20000, 8500.8) with new value (Maserati Ghibli 2021 1100 60000), and returns true");
        Ks.oun(carsMapOa.replace(carsIds[2], cars[2], nonExistingCar));
        Ks.ounn(carsMapOa);

        Ks.oun("Returns false when the given key (TA178) is not true");
        Ks.oun(carsMapOa.replace(carsIds[2], cars[5], nonExistingCar));

        Ks.oun("Returns false when the given key (TA178) value does not exist");
        Ks.oun(carsMapOa.replace("TA999", cars[5], nonExistingCar));

        Ks.oun("Returns null if the car does not exist (by the key)");
        Ks.oun(carsMapOa.remove("TA999"));

        Ks.oun("Returns the car it deletes (Honda   Civic  2007  36400 8500.3)");
        Ks.oun(carsMapOa.remove(carsIds[5]));
        Ks.ounn(carsMapOa);*/
    }
}
