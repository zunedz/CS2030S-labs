import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Roster roster = new Roster("AY");
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            String modId = sc.next();
            String assId = sc.next();
            String grade = sc.next();
            roster.update(name, modId, assId, grade);
        }
        while (sc.hasNext()) {
            String name = sc.next();
            String modId = sc.next();
            String assId = sc.next();
            String output = roster.getGrade(name, modId, assId);
            System.out.println(output);
        }
    }
}
