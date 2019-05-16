package Objects;

public class MasarSystem {

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

    public MasarSystem(int clan, int maxPop, int pop, int popSec) {
        this.clan = clan;
        this.maxPop = maxPop;
        this.pop = pop;
        this.popSec = popSec;
    }
}
