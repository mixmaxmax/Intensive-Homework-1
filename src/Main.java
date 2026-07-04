/**
 * Необходимо написать собственную реализацию HashMap.
 * Обязательные методы: get, put, remove.
 */

import java.util.HashMap;

public class Main {
    public static void main (String[] args) {
        CustomHashMap<String, Integer> numbersOfPlayers = new CustomHashMap<>();
        IO.println("Вывод пустой мапы:");
        IO.println(String.format("Количество корзин в мапе = %d, количество ключ-значений в мапе = %d", numbersOfPlayers.getCapacity(), numbersOfPlayers.getSize()));
        IO.println(numbersOfPlayers);

        numbersOfPlayers.put("Cristiano", 7);
        numbersOfPlayers.put("Messi", 10);
        numbersOfPlayers.put("Mbappe", 10);
        numbersOfPlayers.put("Zidane", 5);
        numbersOfPlayers.put("Kerzhakov", 11);
        numbersOfPlayers.put("Neymar", 11);
        numbersOfPlayers.put("Kroos", 8);
        numbersOfPlayers.put("Safonov", 39);
        numbersOfPlayers.put("Raya", 1);
        numbersOfPlayers.put("Ramos", 4);
        numbersOfPlayers.put("Marcelo", 12);

        IO.println("\nВывод всех ключ-значений в мапе после добавления ключ-значений: ");
        IO.println(String.format("Количество корзин в мапе = %d, количество ключ-значений в мапе = %d", numbersOfPlayers.getCapacity(), numbersOfPlayers.getSize()));
        IO.println(numbersOfPlayers);

        IO.println("\nУдалим из мапы объекты с ключами 'Messi', 'Kroos', 'Kerzhakov':");
        numbersOfPlayers.remove("Messi");
        numbersOfPlayers.remove("Kroos");
        numbersOfPlayers.remove("Kerzhakov");
        IO.println(String.format("Количество корзин в мапе = %d, количество ключ-значений в мапе = %d", numbersOfPlayers.getCapacity(), numbersOfPlayers.getSize()));
        IO.println(numbersOfPlayers);

        IO.println("\nПолучим значения по следующим ключам: 'Cristiano', 'Mbappe', 'Safonov':");
        IO.println(numbersOfPlayers.get("Cristiano"));
        IO.println(numbersOfPlayers.get("Mbappe"));
        IO.println(numbersOfPlayers.get("Safonov"));

        IO.println("\nПопытаемся получить значение по несуществующему ключу:");
        IO.println(numbersOfPlayers.get("QWERTY"));

        IO.println("\nВизуализация структуры итоговой мапы:");
        numbersOfPlayers.printStructure();
    }
}