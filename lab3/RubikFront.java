class RubikFront extends Rubik {
    static final int SIDE_LENGTH = 3;
    static final int NUM_FACES = 6;
    static final int TOP_FACE_INDEX = 0;
    static final int LEFT_FACE_INDEX = 1;
    static final int FRONT_FACE_INDEX = 2;
    static final int RIGHT_FACE_INDEX = 3;
    static final int BOTTOM_FACE_INDEX = 4;
    static final int BACK_FACE_INDEX = 5;
    private final Face[] faces = new Face[6];


    RubikFront(int[][][] grids) {
        Face[] tempFaces = new Face[6];

        for (int i = 0; i < 6; i++) {
            int[][] grid = grids[i];
            Face tempFace = new Face(grid);
            faces[i] = tempFace;
        }
    }

    RubikFront(Rubik rubik) {
        this(rubik.clone().toIntArray());
    }

    @Override 
    RubikFront right() {
        int[][][] newGrids = toIntArray();
        int[][][] grid = toIntArray();

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            newGrids[RubikFront.RIGHT_FACE_INDEX][i][0] =  
                grid[RubikFront.TOP_FACE_INDEX][2][i]; 
        }

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            newGrids[RubikFront.BOTTOM_FACE_INDEX][0][RubikFront.SIDE_LENGTH - i - 1] =  
                grid[RubikFront.RIGHT_FACE_INDEX][i][0]; 
        }

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            newGrids[RubikFront.LEFT_FACE_INDEX][i][2] =  
                grid[RubikFront.BOTTOM_FACE_INDEX][0][i]; 
        }

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            newGrids[RubikFront.TOP_FACE_INDEX][2][RubikFront.SIDE_LENGTH - i - 1] =  
                grid[RubikFront.LEFT_FACE_INDEX][i][2]; 
        }

        newGrids[RubikFront.FRONT_FACE_INDEX] = 
            faces[RubikFront.FRONT_FACE_INDEX].right().toIntArray();

        return new RubikFront(newGrids);
    }

    @Override
    RubikFront left() {
        return this.right().right().right();
    }

    @Override 
    RubikFront half() {
        return this.right().right();
    }

    @Override
    public RubikFront clone() {
        return this.half().half();
    }
    
    @Override
    int[][][] toIntArray() {
        int[][][] newGrids =
            new int[RubikFront.NUM_FACES][RubikFront.SIDE_LENGTH][RubikFront.SIDE_LENGTH];
        for (int i = 0; i < faces.length; i++) {
            newGrids[i] = faces[i].clone().toIntArray();
        }
        return newGrids;
    }

    @Override
    RubikFront upView() {
        int[][][] newGrids = toIntArray();
        int[][][] tempGrids = toIntArray();
        newGrids[RubikFront.FRONT_FACE_INDEX] = tempGrids[RubikFront.TOP_FACE_INDEX];
        newGrids[RubikFront.BOTTOM_FACE_INDEX] = tempGrids[RubikFront.FRONT_FACE_INDEX];
        newGrids[RubikFront.BACK_FACE_INDEX] = tempGrids[RubikFront.BOTTOM_FACE_INDEX];
        newGrids[RubikFront.TOP_FACE_INDEX] = tempGrids[RubikFront.BACK_FACE_INDEX];
        newGrids[RubikFront.LEFT_FACE_INDEX] = faces[LEFT_FACE_INDEX].right().toIntArray();
        newGrids[RubikFront.RIGHT_FACE_INDEX] = faces[RIGHT_FACE_INDEX].left().toIntArray();

        return new RubikFront(newGrids);
    }

    @Override
    RubikFront downView() {
        return this.upView().upView().upView();
    }

    @Override
    RubikFront frontView() {
        return this.clone();
    }

    @Override
    RubikFront rightView() {
        int[][][] newGrids = toIntArray();
        int[][][] tempGrids = toIntArray();
        newGrids[RubikFront.FRONT_FACE_INDEX] = tempGrids[RubikFront.RIGHT_FACE_INDEX];
        newGrids[RubikFront.LEFT_FACE_INDEX] = tempGrids[RubikFront.FRONT_FACE_INDEX];
        newGrids[RubikFront.BACK_FACE_INDEX] = 
            faces[RubikFront.LEFT_FACE_INDEX].half().toIntArray();
        newGrids[RubikFront.RIGHT_FACE_INDEX] = 
            faces[RubikFront.BACK_FACE_INDEX].half().toIntArray();
        newGrids[RubikFront.TOP_FACE_INDEX] = faces[TOP_FACE_INDEX].right().toIntArray();
        newGrids[RubikFront.BOTTOM_FACE_INDEX] = faces[BOTTOM_FACE_INDEX].left().toIntArray();

        return new RubikFront(newGrids);
    }

    @Override
    RubikFront leftView() {
        return this.rightView().rightView().rightView();
    }

    @Override
    RubikFront backView() {
        return this.rightView().rightView();
    }

    @Override
    public String toString() {
        String tempString = "\n";
        String emptyLine = "......";
        int[][][] grids = this.toIntArray();

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            tempString = tempString + emptyLine + formatLine(grids[RubikFront.TOP_FACE_INDEX][i])
                + emptyLine + "\n";
        }

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            tempString = tempString + formatLine(grids[RubikFront.LEFT_FACE_INDEX][i])
                + formatLine(grids[RubikFront.FRONT_FACE_INDEX][i])
                + formatLine(grids[RIGHT_FACE_INDEX][i]) + "\n";
        }      

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            tempString = tempString + emptyLine + formatLine(grids[RubikFront.BOTTOM_FACE_INDEX][i])
                + emptyLine + "\n";
        }

        for (int i = 0; i < RubikFront.SIDE_LENGTH; i++) {
            tempString = tempString + emptyLine + formatLine(grids[RubikFront.BACK_FACE_INDEX][i])
                + emptyLine + "\n";
        }

        return tempString;
    }

    private String formatLine(int[] array) {
        return String.format("%02d%02d%02d", array[0], array[1], array[2]);
    }

}
