package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class LinkClickable implements Clickable{

    private SystemLink attachedLink;
    private MouseOverArea mouseOverArea;

    private MasarData gameData;

    public SystemLink getAttachedLink(){return this.attachedLink;}

    public LinkClickable(SystemLink l, MasarData gameData){
        this.attachedLink = l;
        this.gameData = gameData;
        if(l == null || l.getSys1() == null || l.getSys2() == null)
            return;

        Image img;
        try{
            img = new Image("img/Hitboxes/Link_Hitbox.png");
        }catch(SlickException e){
            System.out.println("Hitbox image file can't be found");
            return;
        }
        mouseOverArea = new MouseOverArea(this.gameData.getGameContainer(), img, 0, 0);

        float midDelX = (l.getSys2().getX() - l.getSys1().getX())/2;
        float midDelY = (l.getSys2().getY() - l.getSys1().getY())/2;
        mouseOverArea.setLocation(l.getSys1().getX() + midDelX - (float)mouseOverArea.getWidth()/2
                , l.getSys1().getY() + midDelY - (float)mouseOverArea.getHeight()/2);
    }


    public boolean isMouseOver() {
        if(mouseOverArea.isMouseOver())
            return true;
        else
            return false;
    }

    public void onMousePressed(int button){
        if(button == Input.MOUSE_RIGHT_BUTTON
                && this.attachedLink.getSys1().getClan() == MasarSystem.ALLIED
                && this.attachedLink.getSys2().getClan() != MasarSystem.ENNEMY
        ){
            this.gameData.removeLink(this.getAttachedLink());
            this.gameData.getCurrentRoom().getClickManager().getRegisteredClickables().remove(this);
        }
    }

    @Override
    public void onMouseReleased(int button, Clickable clickablePressed) {

    }

    public void renderHitbox(GUIContext gc, Graphics g) {
        this.mouseOverArea.render(gc, g);
    }
}