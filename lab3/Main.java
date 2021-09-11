import java.util.Scanner;

class Main {
    static final int NFACES = 6;
    static final int NROWS = 3;
    static final int NCOLS = 3;

    static Rubik oneMove(Rubik rubik, String move) {
        char face = move.charAt(0);
        
        if (face == 'R') {
            rubik = new RubikRight(rubik);
        } else if (face == 'L') {
            rubik = new RubikLeft(rubik);
        } else if (face == 'U') {
            rubik = new RubikUp(rubik);
        } else if (face == 'D') {
            rubik = new RubikDown(rubik);
        } else if (face == 'B') {
            rubik = new RubikBack(rubik);
        }

        if (move.length() == 1) {                
            rubik = rubik.right();
        } else {
            if (move.charAt(1) == '\'') {
                rubik = rubik.left();
            } else {
                rubik = rubik.half();
            }
        }
       
        return new RubikFront(rubik);
    }


    public static void main(String[] args) {
        int[][][] grid = new int[NFACES][NROWS][NCOLS];

        Scanner sc = new Scanner(System.in);
        for (int k = 0; k < NFACES; k++) {
            for (int i = 0; i < NROWS; i++) {
                for (int j = 0; j < NCOLS; j++) {
                    grid[k][i][j] = sc.nextInt();
                }
            }
        }
        Rubik rubik = new RubikFront(grid);

        while (sc.hasNext()) {
            rubik = oneMove(rubik, sc.next());
        }
        System.out.println(rubik);
    }
}
