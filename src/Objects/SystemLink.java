package Objects;

import UI.MasarSprite;
import backend.MasarData;
import backend.Renderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class SystemLink implements Renderable{

    private MasarSystem sys1, sys2;
    private MasarData gameData;

    private float centerX;
    private float centerY;

    private float angleInDegrees;

    private void initValues(){
        //Compute angle between two systems
        float delX = this.getSys2().getX() - this.getSys1().getX();
        float delY = this.getSys2().getY() - this.getSys1().getY();

        float distance = this.getSys1().getDistance(this.sys2);

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
    }

    //verifier si le lien est le meme (un lien/couple de syst max)
    public boolean equals(SystemLink l2){
        if((sys1 == l2.getSys1() && sys2 == l2.getSys2()) || (sys1 == l2.getSys2() && sys2 == l2.getSys1()))
            return true;
        return false;
    }

    public MasarSystem getSys1() {
        return sys1;
    }

    public MasarSystem getSys2() {
        return sys2;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        MasarSprite sprite = this.gameData.getLinkImages().get("player_link_sprite");
        sprite.drawNextSubimageRotated(this.centerX, this.centerY, this.angleInDegrees);
    }
}
