package datastructure.map;
import java.util.HashMap;
public class UtilsHashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
       // hashMap.put("11","112");
        String s = hashMap.putIfAbsent("11", "111");
        System.out.println(s);
   
        System.out.println(hashMap.get("11"));
    }
}
