import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> arrivalTimes = new ArrayList<Double>();
        List<Double> servingTimes = new ArrayList<Double>();
        LinkedList<Double> restTimes = new LinkedList<Double>();
        int numOfServers = sc.nextInt();
        int maxQueLength = sc.nextInt();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            double time = sc.nextDouble();
            double serveTime = sc.nextDouble();
            arrivalTimes.add(time);
            servingTimes.add(serveTime);
        }
        for (int i = 0; i < N; i++) {
            double restTime = sc.nextDouble();
            restTimes.add(restTime);
        }
        Simulator s = new Simulator(numOfServers, arrivalTimes, servingTimes, maxQueLength, restTimes);
        s.simulate();
    }
}
