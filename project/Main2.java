import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> arrivalTimes = new ArrayList<Double>();
        List<Double> servingTimes = new ArrayList<Double>();
        int numOfServers = sc.nextInt();
        int maxQueLength = sc.nextInt();
        while (sc.hasNextDouble()) {
            double time = sc.nextDouble();
            double serveTime = sc.nextDouble();
            arrivalTimes.add(time);
            servingTimes.add(serveTime);
       
        }
        Simulator s = new Simulator(numOfServers, arrivalTimes, servingTimes, maxQueLength);
        s.simulate();
    }
}
