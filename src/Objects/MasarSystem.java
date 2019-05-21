package Objects;

import UI.MasarSprite;
import UI.WindowSystem;
import backend.Clickable;
import backend.MasarData;
import backend.Renderable;
import backend.SystemClickable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tests.xml.GameData;

import java.awt.*;
import java.util.ConcurrentModificationException;

public class MasarSystem implements Renderable{

    private int clan;
    private int maxPop;
    private int pop;
    private int RPS;
    private int RPS_BOOST;
    private int maxRPS;
    private int level;
    private int system_variant;
    private boolean showWindow;
    private WindowSystem windowSys;

    private MasarData gameData;

    private float x_pos;
    private float y_pos;

    private MasarSprite systemsheet;

    private ConquestSystem conquest;

    public static final int NEUTRAL = 0;
    public static final int ALLIED = 1;
    public static final int ENNEMY = 2;

    public int getRPS(){return this.RPS;}
    public int getRPS_BOOST(){return this.RPS_BOOST;}
    public int getMaxRPS(){return this.maxRPS;}
    public int getPop(){return this.pop;}
    public int getMaxPop(){return this.maxPop;}
    public int getLevel(){return this.level;}

    public float getX(){return this.x_pos;}
    public float getY(){return this.y_pos;}

    public ConquestSystem getConquest() {return conquest;}
    public int getClan(){return this.clan;}

    public float getDistance(MasarSystem s2){
        float dx = s2.getX() - this.getX();
        float dy = s2.getY() - this.getY();

        return (float)Math.sqrt(dx*dx + dy*dy);
    }
    public WindowSystem getWindowSys(){return this.windowSys;}
    public boolean isShowWindow(){return this.showWindow;}

    public void invertShowWindow(){this.showWindow = !this.showWindow;}

    public void setPos(float x, float y){
        this.x_pos = x;
        this.y_pos = y;
    }

    public void changeClan(int CLAN){
        this.clan = CLAN;
    }

    public void beHit(MasarSystem s2, int value){
        this.pop = this.pop - value*10;
        if(this.pop <= 0){
            this.clan = s2.getClan();
            this.pop = 1;
            try {
                for (SystemLink l : this.gameData.getLinkList()) {
                    if ((l.getSys1() == this && l.getSys2().getClan() != this.clan) || (l.getSys2() == this && l.getSys1().getClan() != this.clan))
                        this.gameData.removeLink(l);
                }
            }catch (ConcurrentModificationException e){
                System.out.println("oopsie");
            }
        }
    }

    public void render(GameContainer gc, Graphics g){
        if(this.systemsheet == null)
            System.out.println("hmmm");
        this.systemsheet.drawNextSubimage(getX(),getY());
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

        if(this.getConquest().isIn_conquest()){
            TrueTypeFont font = new TrueTypeFont(new Font("Monospaced", Font.BOLD, 20), false);
            if( this.getConquest().getAlliedProgression() > 0 && this.getConquest().isIn_conquest() ){
                font.drawString(this.getX(), this.getY(), this.getConquest().getPercentAllied() + "%" , Color.blue);
            }
            else if (this.getConquest().getEnnemyProgression() > 0 && this.getConquest().isIn_conquest())
                font.drawString(this.getX(), this.getY(), this.getConquest().getPercentEnnemy() + "%" , Color.red);

        }

        if(this.showWindow)
            this.windowSys.render(gc, g);
    }

    public void startGrowthPopulace(){ this.pop++; }

    public void update(GameContainer gc){

        // -- POPULATION GROWTH
        int link_out = 0;
        int fecondity_rate = 0;
        for(SystemLink l: this.gameData.getLinkList()){
            if( l.getSys1() == this  || (l.getSys2() == this && l.getSys1().getClan() != this.getClan()))
                link_out++;
        }
        if ( this.clan != NEUTRAL && this.pop != 0){
            if (link_out == 0 && this.pop != this.maxPop) {
                if (this.pop < 10) fecondity_rate = 1;
                else if (this.pop < 1000) fecondity_rate = 10;
                else if (this.pop < 10000) fecondity_rate = 20;
                else if (this.pop < 100000) fecondity_rate = 40;
                else if (this.pop < 1000000) fecondity_rate = 80;
                else if (this.pop < 10000000) fecondity_rate = 160;
                else fecondity_rate = 320;

                this.pop += this.pop / fecondity_rate;
                //System.out.println("Normies : " + this.pop);
            }
            if (this.pop > this.maxPop) this.pop = this.maxPop;

            // -- RPS / RPS_BOOST CALCULATION
            this.RPS = (int)((this.maxRPS/(float)this.maxPop) * this.pop);
            this.RPS_BOOST = 0; // reset si jamais un link est coupé puis recalcul
            for (SystemLink l : this.gameData.getLinkList()) {
                if (l.getSys2() == this)
                    this.RPS_BOOST += l.getSys1().getRPS() / 2;
            }
            //System.out.println("RPS : " + this.RPS + " RPS_BOOST : " + this.RPS_BOOST);
        }
    }

    public MasarSystem(int clan, int pop, int level, MasarData g) {

        this.clan = clan;
        this.level = level;
        this.showWindow = false;

        this.maxPop = level * 10000000;
        this.pop = pop;

        this.RPS = 0;
        this.RPS_BOOST = 0;
        if ( level == 1 ) this.maxRPS = 400;
        if ( level == 2 ) this.maxRPS = 700;
        if ( level == 3 ) this.maxRPS = 1000;

        this.conquest = new ConquestSystem(g, this);

        this.gameData = g;
        this.windowSys = new WindowSystem(this, this.gameData);
        this.gameData.getWindowList().add(this.getWindowSys());

        int system_variant = 1 + (int) (Math.random() * 3);
        this.system_variant = system_variant;
        //System.out.println(system_variant);
        //System.out.println("level : " + this.level + " max pop : " + this.maxPop + " maxRPS : " + this.maxRPS);

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
}
