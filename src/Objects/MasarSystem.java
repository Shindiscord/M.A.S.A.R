package Objects;

import backend.Clickable;
import backend.Renderable;
import backend.SystemClickable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.awt.*;

public class MasarSystem implements Renderable{

    private int clan;
    private int maxPop;
    private int pop;
    private int popSec;

    private float x_pos;
    private float y_pos;

    public float getX(){return this.x_pos;}
    public float getY(){return this.y_pos;}

    public void setPos(float x, float y){
        this.x_pos = x;
        this.y_pos = y;
;    }

    public float getDistance(MasarSystem s2){
        float dx = s2.getX() - this.getX();
        float dy = s2.getY() - this.getY();

        return (float)Math.sqrt(dx*dx + dy*dy);
    }

    public MasarSystem(int clan, int maxPop, int pop, int popSec) {
        this.clan = clan;
        this.maxPop = maxPop;
        this.pop = pop;
        this.popSec = popSec;
    }

    public void render(GameContainer gc, Graphics g){

    }
}
