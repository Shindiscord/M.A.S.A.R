package Objects;

import UI.MasarSprite;
import backend.MasarData;
import backend.Renderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tests.xml.GameData;

import java.awt.*;

public class ConquestSystem {

    private int ressourcesNeeded;
    private MasarSystem systemConquered;
    private int alliedProgression;
    private int ennemyProgression;
    private boolean in_conquest;

    private MasarData gameData;

    public MasarSystem getSystemConquered() {
        return systemConquered;
    }

    public int getAlliedProgression() {
        return alliedProgression;
    }

    public int getEnnemyProgression() {
        return ennemyProgression;
    }

    public boolean isIn_conquest() {
        return in_conquest;
    }

    public double getPercentAllied(){
        return (double) this.alliedProgression  * 100 / (double)this.ressourcesNeeded;
    }

    public double getPercentEnnemy(){
        return (double) this.ennemyProgression  * 100 / (double)this.ressourcesNeeded;
    }


    public int getRessourcesNeeded() {return ressourcesNeeded;}

    public ConquestSystem(MasarData gd, MasarSystem s){
        this.systemConquered = s;
        this.gameData = gd;
        this.ressourcesNeeded = s.getMaxRPS() * 4;
        this.alliedProgression = 0;
        this.ennemyProgression = 0;
        this.in_conquest = false;
    }

    public void addAlliedProgress(int totalRPM){
        this.in_conquest = true;
        if ( this.ennemyProgression == 0 )
            this.alliedProgression += totalRPM;
        else {
            this.ennemyProgression -= totalRPM;
            if ( this.ennemyProgression < 0 ){
                this.alliedProgression -= this.ennemyProgression;
                this.ennemyProgression = 0;
            }
        }
        if ( this.alliedProgression >= this.ressourcesNeeded ){
            this.in_conquest = false;
            this.systemConquered.changeClan(1);
            this.systemConquered.startGrowthPopulace();
            this.gameData.removeAllLinksOfSystem(this.getSystemConquered());
        }
        //double test_percent = (double) this.alliedProgression  * 100 / (double)this.ressourcesNeeded;
        System.out.println("TotalRPM : " + totalRPM + " this.ressourcesNeeded : " + this.ressourcesNeeded);
        //System.out.println("Test percent ?? : " + test_percent);
        System.out.println("Progress conquest allied : " + this.alliedProgression);
    }

    public void addEnnemyProgress(int totalRPM){
        this.in_conquest = true;
        if ( this.alliedProgression == 0 )
            this.ennemyProgression += totalRPM;
        else {
            this.alliedProgression -= totalRPM;
            if ( this.alliedProgression < 0 ){
                this.ennemyProgression -= this.alliedProgression;
                this.alliedProgression = 0;
            }
        }
        if ( this.ennemyProgression >= this.ressourcesNeeded ){
            this.in_conquest = false;
            this.systemConquered.changeClan(2);
            this.systemConquered.startGrowthPopulace();
            this.gameData.removeAllLinksOfSystem(this.getSystemConquered());
        }
    }
}
