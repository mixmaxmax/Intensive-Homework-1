/**
 * Необходимо написать собственную реализацию HashMap.
 * Обязательные методы: get, put, remove.
 */

import java.util.HashMap;

public class Main {
    public static void main (String[] args) {
        CustomHashMap<String, Integer> numbersOfPlayers = new CustomHashMap<>();
        numbersOfPlayers.put("Cristiano", 7);
        numbersOfPlayers.put("Messi", 10);
        numbersOfPlayers.put("Mbappe", 10);
        numbersOfPlayers.put("Zidane", 5);
        IO.println(numbersOfPlayers);
    }
}
