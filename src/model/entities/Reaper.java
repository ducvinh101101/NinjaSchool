package model.entities;

import model.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static model.utilz.Constants.EnemyConstants.*;

public class Reaper extends Enemy {
    private Rectangle2D.Float attackBox;

    private int attackBoxOffsetX;
    private int attackBoxOffsetY;

    public Reaper(float x, float y) {
        super(x, y, (int) (MONSTER_WIDTH*2 * Game.SCALE), (int) (MONSTER_HEIGHT*2* Game.SCALE), REAPER);
        initHitBox( (int) (68 * Game.SCALE), (int) (68 * Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (140 * Game.SCALE), (int) (80 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
        attackBoxOffsetY = (int) (Game.SCALE * 0);
    }

    public void update(int[][] lvData, Player player) {
        updateBehavior(lvData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitBox.x - attackBoxOffsetX;
        attackBox.y = hitBox.y - attackBoxOffsetY;
    }

    public void drawAttackBox(Graphics g, int xlvOffset, int yLevelOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xlvOffset), (int) (attackBox.y-yLevelOffset), (int) attackBox.width, (int) attackBox.height);
    }

    private void updateBehavior(int[][] lvData, Player player) {
//        if (firstUpdate) firstUpdateCheck(lvData);
        if (inAir) updateInAir(lvData);
        else {
            switch (enemyState) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvData, player)) {
                        //turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player)) newState(ATTACK);
                    }
                    //move(lvData);
                    move2(4);
                    break;
                case ATTACK:
                    if (animationIndex == 0) attackChecked = false;
                    if (animationIndex == 1 && !attackChecked) checkPlayerHit(attackBox, player);
                    break;
                case DEAD:
                    alive = false;
                    break;
            }
        }
    }
    public void drawHP(Graphics g,  int xLvOffset, int yLevelOffset){
        double oneScale =  (MONSTER_WIDTH*2* Game.SCALE)/maxHealth;
        double hpBarValue = oneScale * currentHealth;

        // thanh máu nền
        g.setColor(new Color(35, 35, 35));
        g.fillRect((int) hitBox.x - xLvOffset, (int) hitBox.y - yLevelOffset - 14, (int) (MONSTER_WIDTH*2* Game.SCALE) , 6);

        // thanh máu hiện tại
        g.setColor(new Color(255, 0, 30));
        g.fillRect((int) hitBox.x - xLvOffset, (int) hitBox.y - yLevelOffset - 14, (int)hpBarValue, 6);
    }

}