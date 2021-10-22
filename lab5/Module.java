import java.util.HashMap;
import java.util.Map;

class Module extends KeyableMap<Assessment> {
    Module(String key) {
        super(key);
    }

    Module(String key, Map<String, Assessment> assessment) {
        super(key, assessment);
    }

    Module put(Assessment as) {
        KeyableMap<Assessment> keyMap = super.put(as);
        return new Module(keyMap.getKey(), keyMap.getMap());
    }
}
