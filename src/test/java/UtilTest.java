import java.util.HashMap;

public class UtilTest {
    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("A", 2);
        map.put(null, 1);
        map.put("c", null);
        map.put("d", 5);
        map.compute("d",(k,v)->{

            return 3;} );


        System.out.println(map);
    }
}
