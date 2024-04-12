package ui;

import gamestates.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.*;

public class MenuButton {
    private int posX, posY, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private boolean mouseOver, mousePress;
    private Gamestate state;
    private Rectangle bounds;
    BufferedImage[] imgs;

    public MenuButton(int posX, int posY, int rowIndex, Gamestate state) {
        this.posX = posX;
        this.posY = posY;
        this.rowIndex = rowIndex;
        this.state = state;
        loadsImg();
    }

    private void loadsImg() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAlas(LoadSave.MENU_BUTTON);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], posX - xOffsetCenter, posY, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePress) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePress() {
        return mousePress;
    }

    public void setMousePress(boolean mousePress) {
        this.mousePress = mousePress;
    }
}
