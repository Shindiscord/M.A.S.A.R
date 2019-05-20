package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.tests.xml.GameData;

public class SystemClickable implements Clickable{

    private MasarSystem attachedSystem;
    private MouseOverArea mouseOverArea;
    private MasarData gameData;

    public MasarSystem getAttachedSystem(){return this.attachedSystem;}

    public SystemClickable(MasarSystem attachedSystem, MasarData gameData){
        Image img;
        try {
            img = new Image("res/img/Hitboxes/System_Hitbox.png");
        }catch (SlickException e){
            System.out.println("Can't open System Hitbox image");
            return;
        }
        this.gameData = gameData;
        this.attachedSystem = attachedSystem;
        this.mouseOverArea = new MouseOverArea(this.gameData.getGameContainer(), img, 0,0);
        this.updateLocation();
    }

    public boolean isMouseOver(){
        return this.mouseOverArea.isMouseOver();
    }
    public void onMousePressed(int button){
        System.out.println("Pop : " + this.attachedSystem.getPop() + "/" + this.attachedSystem.getMaxPop());
    }

    public void onMouseReleased(int button, Clickable clickablePressed){
        if(clickablePressed == this){
            //afficher les donées du systeme
        }else if(clickablePressed instanceof SystemClickable){
            //CreateLink
            SystemLink newLink = new SystemLink(((SystemClickable) clickablePressed).getAttachedSystem(), this.getAttachedSystem(), this.gameData);
            for(SystemLink link: this.gameData.getLinkList()){
                if(link.equals(newLink)) {
                    System.out.println("ce lien existe deja");
                    return;
                }
            }
            if(this.attachedSystem.getDistance(((SystemClickable) clickablePressed).getAttachedSystem()) < MasarData.DIST_SYST + 5f) {
                this.gameData.getLinkList().add(newLink);
                this.gameData.getCurrentRoom().addClickable(new LinkClickable(newLink, this.gameData));
            }else{
                System.out.println("Those systems are too far");
            }
        }
    }

    public void renderHitbox(GUIContext gc, Graphics g){
        this.mouseOverArea.render(gc, g);
    }

    public void updateLocation(){
        float x = attachedSystem.getX() - (float)this.mouseOverArea.getWidth()/2;
        float y =  attachedSystem.getY() - (float)this.mouseOverArea.getHeight()/2;

        mouseOverArea.setLocation(x, y);
    }
}