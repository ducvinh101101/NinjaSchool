package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton{
    private BufferedImage[] urmImgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;
    public UrmButton(int x, int y, int width, int height,int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadUrmButton();
    }

    private void loadUrmButton() {
        BufferedImage img = LoadSave.getSpriteAlas(LoadSave.URM_BUTTON);
        urmImgs = new BufferedImage[3];
        for (int i = 0; i < urmImgs.length; i++)
            urmImgs[i] = img.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
    }

    public void update(){
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;

    }
    public void draw(Graphics g){
        g.drawImage(urmImgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}