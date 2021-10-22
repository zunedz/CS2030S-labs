import java.util.HashMap;
import java.util.Map;

class Student extends KeyableMap<Module> {
    Student(String key) {
        super(key);
    }

    Student(String key, Map<String, Module> map) {
        super(key, map);
    }

    Student(KeyableMap<Module> keyMap) {
        super(keyMap);
    }
    
    @Override
    Student put(Module mod) {
        KeyableMap<Module> keyMap = super.put(mod);
        return new Student(keyMap);
    }
}
