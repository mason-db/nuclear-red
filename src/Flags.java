import java.util.HashMap;

public class Flags {
    private static HashMap<String, Boolean> map = new HashMap<>();

    public static boolean getFlag(String name) {
        try {
            return map.get(name);
        } catch (NullPointerException e) {
            return false;
        }
    }
    public static void setFlag(String name, boolean value){
        map.put(name, value);
    }
}