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