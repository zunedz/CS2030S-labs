import java.util.Map;

class Roster extends KeyableMap<Student> {
    Roster(String key) {
        super(key);
    }

    Roster(String key, Map<String, Student> map) {
        super(key, map);
    }

    Roster(KeyableMap<Student> keyMap) {
        super(keyMap);
    }

    Roster put(Student stu) {
        KeyableMap<Student> keyMap = super.put(stu);
        return new Roster(keyMap);
    }

    void update(String stuId, String modId, String assId, String grade) {
        if (!this.contains(stuId)) {
            this.put(new Student(stuId).put(new Module(modId).put(new Assessment(assId, grade))));
            return;
        } else if (!this.get(stuId).map(x -> x.contains(modId)).orElse(false)) {
            this.get(stuId).map(x -> x.put(new Module(modId).put(new Assessment(assId, grade))));
            return;
        } 
        this.get(stuId).flatMap(x -> x.get(modId)).map(x -> x.put(new Assessment(assId, grade)));
    }

    String getGrade(String stuId, String modId, String assId) {
        String notFound = String.format("No such record: %s %s %s", stuId, modId, assId);
        return this.get(stuId).flatMap(x -> x.get(modId)).flatMap(x -> x.get(assId))
        .map(x -> x.getGrade()).orElse(notFound);
    }
}
