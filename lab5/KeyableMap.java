import java.util.HashMap;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final Map<String, V> map;

    KeyableMap(String key) {
        this.key = key;
        this.map = new HashMap<String, V>();
    }

    KeyableMap(String key, Map<String, V> map) {
        this.key = key;
        this.map = map;
    } 

    KeyableMap<V> put(V newAs) {
        Map<String, V> newMap = map;
        newMap.put(newAs.getKey(), newAs);
        return new KeyableMap<V>(this.key, newMap);
    }
    
    boolean contains(String id) {
        return map.containsKey(id);
    }

    V get(String key) {
        return this.map.get(key);
    }

    Map<String, V> getMap() {
        return this.map;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        String output = key + ": {";
        for (int i = 0; i < map.keySet().toArray().length; i++) {
            if (i == 0) {
                String key = (String) map.keySet().toArray()[i];
                output += this.get(key);
            } else {
                String key = (String) map.keySet().toArray()[i];
                output += ", "
                    + this.get(key);
            }
        }
        return output + "}";
    }
}   
