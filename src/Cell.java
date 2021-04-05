//package pacman;

import java.lang.System;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Cell {
    final int CELL = 20;
    protected char type;
    protected int  x, y;
    
    public Cell(int x, int y, char type) {
        this.type = type;
        this.x    = x;
        this.y    = y;
    }

    public char getType() {
        return this.type;
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public void setType(char type) {
    	this.type = type;
    }
}
