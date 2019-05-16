package backend;

import Objects.MasarSystem;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class SystemClickable implements Clickable{

    private MasarSystem attachedSystem;
    private MouseOverArea mouseOverArea;

    public MasarSystem getAttachedSystem(){return this.attachedSystem;}

    public SystemClickable(MasarSystem attachedSystem, GUIContext container){
        Image img;
        try {
            img = new Image("res/img/Hitboxes/System_Hitbox.png");
        }catch (SlickException e){
            System.out.println("Can't open System Hitbox image");
            return;
        }
        this.attachedSystem = attachedSystem;
        this.mouseOverArea = new MouseOverArea(container, img, 0,0);
        this.updateLocation();
    }

    public void render(GUIContext guiContext, Graphics g){
        this.mouseOverArea.render(guiContext, g);
    }

    public boolean isMouseOver(){
        return this.mouseOverArea.isMouseOver();
    }

    public void updateLocation(){
        float x = attachedSystem.getX();
        float y =  attachedSystem.getY();

        mouseOverArea.setLocation(x, y);
    }
}