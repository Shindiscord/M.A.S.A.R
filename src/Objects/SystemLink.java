package Objects;

import UI.MasarSprite;
import backend.MasarData;
import backend.Renderable;
import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tests.xml.GameData;

import java.awt.*;

public class SystemLink implements Renderable{

    private MasarSystem sys1, sys2;
    private MasarData gameData;
    private LinkConflict conflict;

    private float centerX;
    private float centerY;

    private float angleInDegrees;

    private void initValues(){
        //Compute angle between two systems
        float delX = this.getSys2().getX() - this.getSys1().getX();
        float delY = this.getSys2().getY() - this.getSys1().getY();

        double angle = Math.atan(delY/delX);

        if(delX > 0 || (delX == 0 && delY > 0)){
            angle = angle + Math.PI;
        }
        this.angleInDegrees = (float)Math.toDegrees(angle);

        //compute sprite origin
        this.centerX = this.sys1.getX() + delX/2;
        this.centerY = this.sys1.getY() + delY/2;
    }

    public SystemLink(MasarSystem sys1, MasarSystem sys2, MasarData gameData) {
        this.sys1 = sys1;
        this.sys2 = sys2;
        this.gameData = gameData;
        this.initValues();
        System.out.println("Lien créé entre clan " + this.getSys1().getClan() + " et " + this.getSys2().getClan());
    }

    //verifier si le lien est le meme (un lien/couple de syst max)
    public boolean equals(SystemLink l2){
        return((sys1 == l2.getSys1() && sys2 == l2.getSys2()) || (sys1 == l2.getSys2() && sys2 == l2.getSys1()));
    }

    public MasarSystem getSys1() {
        return sys1;
    }

    public MasarSystem getSys2() {
        return sys2;
    }

    public void update(){
        if(this.getSys1().getClan() == this.getSys2().getClan() && this.conflict != null)
            this.conflict = null;
        if(this.getSys1().getClan() != this.getSys2().getClan()){
            if(this.getSys2().getClan() == MasarSystem.NEUTRAL){
                // -- CONQUEST
                int nb_links_system = 0;
                for(SystemLink l : this.gameData.getLinkList())
                    if ( l.getSys1() == this.getSys1() )
                        nb_links_system++;
                int RPS_sent = (this.getSys1().getRPS() + this.getSys1().getRPS_BOOST())/nb_links_system;
                if(this.getSys1().getClan() == 1){
                    this.getSys2().getConquest().addAlliedProgress(RPS_sent);
                }
                else if (this.getSys1().getClan() == 2){
                    this.getSys2().getConquest().addEnnemyProgress(RPS_sent);
                }
            }else if(this.conflict == null && this.getSys1().getClan() != MasarSystem.NEUTRAL){
                System.out.println("new conflict !");
                this.conflict = new LinkConflict(this);
            }
        }
        if(this.conflict != null){
            this.conflict.update();
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        MasarSprite sprite;
        if(this.getSys1().getClan() == MasarSystem.ALLIED && this.conflict == null) {
            sprite = this.gameData.getLinkImages().get("player_link_sprite");
            sprite.drawNextSubimageRotated(this.centerX, this.centerY, this.angleInDegrees);
        }
        else if (this.getSys1().getClan() == MasarSystem.ENNEMY && this.conflict == null) {
            sprite = this.gameData.getLinkImages().get("enemy_link_sprite");
            sprite.drawNextSubimageRotated(this.centerX, this.centerY, this.angleInDegrees);
        }
        else if(this.getSys1().getClan() == MasarSystem.ALLIED && this.conflict != null) {
            sprite = this.gameData.getLinkImages().get("conflict_link_sprite");
            sprite.drawNextSubimageRotated(this.centerX, this.centerY, this.angleInDegrees - 180);
        }
        else if(this.getSys1().getClan() == MasarSystem.ENNEMY && this.conflict != null) {
            sprite = this.gameData.getLinkImages().get("conflict_link_sprite");
            sprite.drawNextSubimageRotated(this.centerX, this.centerY, this.angleInDegrees);
        }
    }
}
