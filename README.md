# Домашнее задание 1. 
### Необходимо написать собственную реализацию HashMap. Обязательные методы: get, put, remove.
- - -
### Код программы: 
### `Main.java:`
```
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
``` 
### `CustomHashMap.java:`
```
/**Кастомная хэш-мапа*/
public class CustomHashMap<K, V> {

    private Node<K, V>[] buckets; //массив корзин
    private int size; //текущий счетчик элементов
    private int capacity; //максимальная вместимость мапы
    public CustomHashMap() {
        capacity = 12; //фиксированный размер массива
        buckets = new Node[capacity];
        size = 0;
    }

    /**Получение кол-ва корзин в мапе*/
    public int getCapacity() {
        return capacity;
    }
    /**Получение кол-ва элементов в мапе*/
    public int getSize() {
        return size;
    }

    /**Вычисление ячейки массива по остатку от деления*/
    private int calcIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**Добавление в мапу ключ-значения*/
    public void put(K key, V value) {
            if (key == null) {
                throw new IllegalArgumentException("Передаваемый ключ не может быть null!!");
            }
            if (value == null) {
                throw new IllegalArgumentException("Передаваемое значение не может быть null!!!");
            }

            int index = calcIndex(key); //вычисление индекса

            Node<K, V> current = buckets[index];
            while (current!=null) { //поиск узла
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                } else {
                    current = current.next;
                }
            }

            Node<K, V> newNode = new Node<>(key, value, buckets[index]);
            buckets[index] = newNode;
            size++;
    }
    /**Получение из мапы значения по ключу*/
    public V get(K key) {
            if (key == null) {
                throw new IllegalArgumentException("Передаваемый ключ не может быть null!!!");
            }
            int index = calcIndex(key);

            Node<K, V> current = buckets[index];
            if (current == null) { //ключа нет - возвращаем null
                return null;
            }

            while (current!=null) {
                if (current.key.equals(key) && (current.value!=null)) {
                    return current.value; //возвращаем значение, если ключи равны, и, если значение не null
                } else {
                    current = current.next; //иначе идем в следующий узел
                }
            }

            return null; //возвращаем null если ничего не нашли
    }

    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Передаваемый ключ не может быть null!!!");
        }
        int index = calcIndex(key);
        Node<K, V> current = buckets[index]; //найденный узел по индексу
        Node<K, V> previous = null; //ссылка на предыдущий узел
        while (current!=null) {
            if (current.key.equals(key)) {
                if (previous == null) { //если true, то находимся в голове списка
                    buckets[index] = current.next; //меняем ссылку с текущего на следующий узел
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

     @Override
     public String toString() {
        StringBuilder sb = new StringBuilder("");
        if (size == 0) {
            return "CustomHashMap is empty: {}";
        }
        else {
            for (int i = 0; i < capacity; i++) {
                Node<K, V> current = buckets[i];
                while (current!=null) {
                    sb.append("{").append(current.key).append(" = ").append(current.value).append("} ");
                    current = current.next;
                }
            }
        }
        return sb.toString();
     }

    public void printStructure() {
        IO.println(String.format("CustomHashMap structure. Capacity = %d, size = %d", getCapacity(), getSize()));
        for (int i = 0; i < capacity; i++) {
            Node<K, V> current = buckets[i]; //головной узел в корзине
            if (current == null) {
                IO.println("Bucket["+i+"]: empty");
            } else {
                IO.print("Bucket["+i+"]: ");
                while (current!=null) {
                    IO.print("{"+current.key + "=" + current.value+"}");
                    if (current.next != null) {
                        IO.print(" ---> ");
                    }
                    current = current.next;
                }
                IO.println();
            }
        }
    }


     /**Вложенный класс. Узел связанного списка*/
    private static class Node<K,V> {
        private final K key; //ключ
        private V value; //значение
        private Node<K, V> next; //ссылка на следующий узел

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
``` 
### Описание класса CustomHashMap.java: 
- "Бакеты" реализованы через массив фиксированной длины. Нет автоматического расширения;
- Применяется односвязный список. "Ноды" имеют ссылку на следующую "ноду". Это позволяет решать коллизии;
- Ключи и значения не могут быть null. 
#### Основные методы: 
- `private int calcIndex(K key)` - вычисляет индекс корзины по остатку от деления;
- `public void put(K key, V value)` - добавляет в мапу ключ-значение;
- `public V get(K key)` - возвращает значение по ключу;
- `public void remove(K key)` - удаляет (или переписывает ссылки) ключ-значений по ключу. 
#### Дополнительные методы: 
- гетеры;
- `public String toString()` - выводит все ключ-значения мапы в консоль;
- `public void printStructure()`- визуализирует мапу, выводит все бакеты, их содержимое, ссылки на следующие ключ-значения (есл есть). 
- - - 
### Вывод консоли: 
```
Вывод пустой мапы:
Количество корзин в мапе = 12, количество ключ-значений в мапе = 0
CustomHashMap is empty: {}

Вывод всех ключ-значений в мапе после добавления ключ-значений: 
Количество корзин в мапе = 12, количество ключ-значений в мапе = 11
{Zidane = 5} {Kroos = 8} {Safonov = 39} {Raya = 1} {Mbappe = 10} {Neymar = 11} {Cristiano = 7} {Marcelo = 12} {Kerzhakov = 11} {Messi = 10} {Ramos = 4} 

Удалим из мапы объекты с ключами 'Messi', 'Kroos', 'Kerzhakov':
Количество корзин в мапе = 12, количество ключ-значений в мапе = 8
{Zidane = 5} {Safonov = 39} {Raya = 1} {Mbappe = 10} {Neymar = 11} {Cristiano = 7} {Marcelo = 12} {Ramos = 4} 

Получим значения по следующим ключам: 'Cristiano', 'Mbappe', 'Safonov':
7
10
39

Попытаемся получить значение по несуществующему ключу:
null

Визуализация структуры итоговой мапы:
CustomHashMap structure. Capacity = 12, size = 8
Bucket[0]: empty
Bucket[1]: {Zidane=5}
Bucket[2]: empty
Bucket[3]: empty
Bucket[4]: empty
Bucket[5]: empty
Bucket[6]: {Safonov=39}
Bucket[7]: {Raya=1} ---> {Mbappe=10}
Bucket[8]: {Neymar=11} ---> {Cristiano=7}
Bucket[9]: {Marcelo=12}
Bucket[10]: {Ramos=4}
Bucket[11]: empty

Process finished with exit code 0
```
