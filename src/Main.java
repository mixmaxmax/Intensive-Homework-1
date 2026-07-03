import java.util.HashMap;

public class Main {
    public static void main (String[] args) {
        HashMap<String, Integer> empIds = new HashMap<>();
        empIds.put("Cristiano", 7);
        empIds.put("Modric", 10);
        empIds.put("Mbappe", 10);
        empIds.put("Zidane", 5);
        IO.println(empIds.containsKey("Zidanea"));
    }
}
