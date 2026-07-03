/**Кастомная хэш-мапа*/
public class CustomHashMap<K, V> {

    private Node<K, V>[] buckets; //массив корзин
    private int size; //текущий счетчик элементов
    private int capacity; //максимальная вместимость мапы
    public CustomHashMap() {
        capacity = 16; //фиксированный размер массива
        buckets = new Node[capacity];
        size = 0;
    }

    /**Вычисление ячейки массива по остатку от деления*/
    private int calcIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**Добавление в мапу*/
    public void put(K key, V value) {
        try {
            if (key == null) {
                throw new IllegalArgumentException("Передаваемый ключ не может быть null");
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

        } catch (Exception e) {
            throw new RuntimeException(e);
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
                    sb.append("{" + current.key + " = " + current.value + "} ");
                    current = current.next;
                }
            }
        }
        return sb.toString();
     }


     /**Вложенный класс. Узел связанного списка*/
    private static class Node<K,V> {
        private K key; //ключ
        private V value; //значение
        private Node<K, V> next; //ссылка на следующий узел

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
