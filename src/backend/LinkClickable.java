package backend;

import Objects.SystemLink;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class LinkClickable implements Clickable{

    private SystemLink attachedLink;
    private MouseOverArea mouseOverArea;

    public SystemLink getAttachedLink(){return this.attachedLink;}

    public LinkClickable(SystemLink l, GUIContext container){
        attachedLink = l;
        if(l == null || l.getSys1() == null || l.getSys2() == null)
            return;

        Image img;
        try{
            img = new Image("res/img/Hitboxes/Link_Hitbox.png");
        }catch(SlickException e){
            System.out.println("Hitbox image file can't be found");
            return;
        }
        mouseOverArea = new MouseOverArea(container, img, 0, 0);

        float midDelX = (l.getSys2().getX() - l.getSys1().getX())/2;
        float midDelY = (l.getSys2().getY() - l.getSys1().getY())/2;
        mouseOverArea.setLocation(l.getSys1().getX() + 16f + midDelX, l.getSys1().getY() + 16f + midDelY);
    }

    @Override
    public boolean isMouseOver() {
        if(mouseOverArea.isMouseOver())
            return true;
        else
            return false;
    }

    @Override
    public void render(GUIContext guiContext, Graphics g) {
            mouseOverArea.render(guiContext, g);
    }
}