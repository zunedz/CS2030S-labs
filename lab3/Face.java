class Face implements Cloneable {
    private final int[][] grid;
    static final int SIDE_LENGTH = 3;

    Face(int[][] grid) {
        this.grid = grid;
    }

    Face right() {
        int[][] tempFace = new int[Face.SIDE_LENGTH][Face.SIDE_LENGTH];
        for (int i = 0; i < Face.SIDE_LENGTH; i++) {
            for (int j = 0; j < Face.SIDE_LENGTH; j++) {
                int tempElement = this.grid[i][j];
                tempFace[j][Face.SIDE_LENGTH - i - 1] = tempElement;
            }
        }
        return new Face(tempFace);
    }

    Face left() {
        return this.right().right().right();
    }

    Face half() {
        return this.right().right();
    }

    int[][] toIntArray() {
        return this.grid;
    }

    @Override
    public String toString() {
        String tempString = "\n";
        for (int i = 0; i < Face.SIDE_LENGTH; i++) {
            tempString = tempString + String.format("%02d%02d%02d\n", this.grid[i][0], 
                    this.grid[i][1], this.grid[i][2]);
        }
        return tempString;
    }

    @Override
    public Face clone() {
        return this.half().half();
    }
}
