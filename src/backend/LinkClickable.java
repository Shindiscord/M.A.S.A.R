package backend;

import Objects.SystemLink;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class LinkClickable implements Clickable{

    private SystemLink attachedLink;
    private MouseOverArea[] mouseOverArea;
    private float length;
    private float angle;

    public SystemLink getAttachedLink(){return this.attachedLink;}

    public LinkClickable(SystemLink l, GUIContext container){
        attachedLink = l;
        if(l == null || l.getSys1() == null || l.getSys2() == null)
            return;
        this.computeAngle();
        this.computeLength();

        Image img;
        try{
            img = new Image("res/img/Hitboxes/Link_Hitbox.png");
            img.setCenterOfRotation(0,7);
            img.setRotation((float)Math.toDegrees(this.angle));
        }catch(SlickException e){
            System.out.println("Hitbox image file can't be found");
            return;
        }

        int numLink = (int)Math.floor(length/64 - 1);
        float addToOrigin = (length-64 - numLink*64f)/2;

        float linkOriginX = this.getAttachedLink().getSys1().getX() + (float)Math.cos(angle) * (32f + addToOrigin);
        float linkOriginY = this.getAttachedLink().getSys1().getY() + (float)Math.cos(angle) * (32f + addToOrigin);

        float delX = (float)Math.cos(this.angle) * 64f;
        float delY = (float)Math.sin(this.angle) * 64f;

        mouseOverArea = new MouseOverArea[numLink];
        for(int i = 0; i < mouseOverArea.length; i++){
            mouseOverArea[i] = new MouseOverArea(container, img, 0, 0);
            mouseOverArea[i].setLocation(linkOriginX + delX*i, linkOriginY + delY*i);
        }
    }

    public float getLength(){return this.length;}
    private void computeLength(){
        if(attachedLink == null || attachedLink.getSys1() == null || attachedLink.getSys2() == null){
            this.length = 0.0f;
            return;
        }

        float deltaX = attachedLink.getSys2().getX() - attachedLink.getSys1().getX();
        float deltaY = attachedLink.getSys2().getY() - attachedLink.getSys1().getY();

        this.length = (float)Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
    }

    public float getAngle(){return this.angle;}
    private void computeAngle(){
        if(attachedLink == null || attachedLink.getSys1() == null || attachedLink.getSys2() == null)
            return;
        float deltaX = attachedLink.getSys2().getX() - attachedLink.getSys1().getX();
        float deltaY = attachedLink.getSys2().getY() - attachedLink.getSys1().getY();
        this.angle = (float)Math.atan((double)(deltaY/deltaX));
    }

    @Override
    public boolean isMouseOver() {
        for(int i = 0; i < mouseOverArea.length; i++)
            if(mouseOverArea[i].isMouseOver())
                return true;
        return false;
    }

    @Override
    public void render(GUIContext guiContext, Graphics g) {
        for(int i = 0; i < mouseOverArea.length; i++)
            mouseOverArea[i].render(guiContext, g);
    }
}