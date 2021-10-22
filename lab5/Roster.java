import java.util.Map;

class Roster extends KeyableMap<Student> {
    Roster(String key) {
        super(key);
    }

    Roster(String key, Map<String, Student> map) {
        super(key, map);
    }

    Roster put(Student stu) {
        KeyableMap<Student> keyMap = super.put(stu);
        return new Roster(keyMap.getKey(), keyMap.getMap());
    }

    void update(String stuId, String modId, String assId, String grade) {
        if (!this.contains(stuId)) {
            this.put(new Student(stuId).put(new Module(modId).put(new Assessment(assId, grade))));
            return;
        } else if (!this.get(stuId).contains(modId)) {
            this.get(stuId).put(new Module(modId).put(new Assessment(assId, grade)));
            return;
        } 
        this.get(stuId).get(modId).put(new Assessment(assId, grade));
    }

    String getGrade(String stuId, String modId, String assId) {
        String notFound = String.format("No such record: %s %s %s", stuId, modId, assId);
        if (this.get(stuId) instanceof Object && this.get(stuId).get(modId) instanceof Object &&
                this.get(stuId).get(modId).get(assId) instanceof Object) {
            return this.get(stuId).get(modId).get(assId).getGrade();
        }
        return notFound;
    }
}
