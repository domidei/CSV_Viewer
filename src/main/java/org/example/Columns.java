package org.example;

public class Columns {
    public Columns(int column1Width, int column2Width, int column3Width) {
        this.column1Width = column1Width;
        this.column2Width = column2Width;
        this.column3Width = column3Width;
    }

    int column1Width;

    public int getColumn1Width() {
        return column1Width;
    }

    public void setColumn1Width(int column1Width) {
        this.column1Width = column1Width;
    }

    public int getColumn2Width() {
        return column2Width;
    }

    public void setColumn2Width(int column2Width) {
        this.column2Width = column2Width;
    }

    public int getColumn3Width() {
        return column3Width;
    }

    public void setColumn3Width(int column3Width) {
        this.column3Width = column3Width;
    }

    int column2Width;
    int column3Width;

}
