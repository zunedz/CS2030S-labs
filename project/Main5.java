import cs2030.simulator.Simulator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int seed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfAutoServers = sc.nextInt();
        int maxQueLength = sc.nextInt();
        int n = sc.nextInt();
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();
        double rho = sc.nextDouble();
        double rhoRest = sc.nextDouble();
        double rhoGreedy = sc.nextDouble();
        Simulator s = new Simulator(seed, numOfServers, numOfAutoServers, 
            maxQueLength, n, lambda, mu, rho, rhoRest, rhoGreedy);
        s.simulate();
    }
}
