package battleship.model;

class Ship {

    private final int length;
    private final String name;
    private int pv;
    private boolean isAlive = true;
    private String parts = "";

    private final int[] rowIndex;
    private final int[] colIndex;

    Ship(int length, String name, String parts, int[] rowIndex, int[] colIndex) {
        this.length = length;
        this.name = name;
        this.parts = parts;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.pv = length;
    }

    int getLength() {
        return length;
    }

    String getParts() {
        return parts;
    }

    int[] getRowIndex() {
        return rowIndex;
    }

    int[] getColIndex() {
        return colIndex;
    }

    int getPv() {
        return pv;
    }

    void setPv() {
        this.pv--;
    }

    boolean getIsAlive() {
        return isAlive;
    }

    void setIsAlive() {
        this.isAlive = false;
    }
}
