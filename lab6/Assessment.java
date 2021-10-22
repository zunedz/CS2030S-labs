import java.util.HashMap;

class Assessment implements Keyable {
    private final HashMap<String, String> map;

    Assessment(String key, String grade) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(key, grade);
        this.map = map;
    }

    @Override
    public String getKey() {
        Object[] keySet = map.keySet().toArray();
        return (String) keySet[0];
    }

    public String getGrade() {
        String key = this.getKey();
        return map.get(key);
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", this.getKey(), this.getGrade());
    }
}
