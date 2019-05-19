package Objects;

import UI.MasarSprite;
import backend.Clickable;
import backend.MasarData;
import backend.Renderable;
import backend.SystemClickable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tests.xml.GameData;

import java.awt.*;

public class MasarSystem implements Renderable{

    private int clan;
    private int maxPop;
    private int pop;
    private int popSec;
    private int level;

    private MasarData gameData;

    private float x_pos;
    private float y_pos;

    private MasarSprite systemsheet;

    public static final int NEUTRAL = 0;
    public static final int ALLIED = 1;
    public static final int ENNEMY = 2;

    public float getX(){return this.x_pos;}
    public float getY(){return this.y_pos;}

    public void setPos(float x, float y){
        this.x_pos = x;
        this.y_pos = y;
    }

    public float getDistance(MasarSystem s2){
        float dx = s2.getX() - this.getX();
        float dy = s2.getY() - this.getY();

        return (float)Math.sqrt(dx*dx + dy*dy);
    }

    public MasarSystem(int clan, int maxPop, int pop, int popSec, int level, MasarData g) {
        this.clan = clan;
        this.maxPop = maxPop;
        this.pop = pop;
        this.popSec = popSec;
        this.level = level;
        this.gameData = g;
        int system_variant = 1 + (int) (Math.random() * 3);
        System.out.println(system_variant);

        if( clan == NEUTRAL ){
            if ( level == 1 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_1planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_1planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_1planet_var3");
            }
            else if ( level == 2 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_2planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_2planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_2planet_var3");
            }
            else if ( level == 3 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_3planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_3planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("ne_3planet_var3");
            }
        }
        if( clan == ALLIED ){
            if ( level == 1 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_1planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_1planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_1planet_var3");
            }
            else if ( level == 2 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_2planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_2planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_2planet_var3");
            }
            else if ( level == 3 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_3planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_3planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("allied_3planet_var3");
            }
        }
        if( clan == ENNEMY ){
            if ( level == 1 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("en_1planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("en_1planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("en_1planet_var3");
            }
            else if ( level == 2 ){
                if ( system_variant == 1 )
                    systemsheet = this.gameData.getSystemsImages().get("en_2planet_var1");
                if ( system_variant == 2 )
                    systemsheet = this.gameData.getSystemsImages().get("en_2planet_var2");
                if ( system_variant == 3 )
                    systemsheet = this.gameData.getSystemsImages().get("en_2planet_var3");
            }
            else if ( level == 3 ) {
                if (system_variant == 1)
                    systemsheet = this.gameData.getSystemsImages().get("en_3planet_var1");
                if (system_variant == 2)
                    systemsheet = this.gameData.getSystemsImages().get("en_3planet_var2");
                if (system_variant == 3)
                    systemsheet = this.gameData.getSystemsImages().get("en_3planet_var3");
            }
        }

    }

    public void render(GameContainer gc, Graphics g){
        if(this.systemsheet == null)
            System.out.println("hmmm");
        this.systemsheet.drawNextSubimage(getX(),getY());
    }
}
