package backend;

import UI.MenuButton;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class MenuButtonClickable implements Clickable {

    private MenuButton attachedMenuButton;
    private MouseOverArea mouseOverArea;

    public MenuButtonClickable(MenuButton attachedMenuButton, GUIContext container, String ImageFile) {
        Image img;
        try{
            img = new Image(ImageFile);
        } catch (SlickException e) {
            e.printStackTrace();
            return;
        }
        this.attachedMenuButton = attachedMenuButton;
        this.mouseOverArea = new MouseOverArea(container, img, 0,0);
        this.updateLocation();
    }

    public void updateLocation(){
        int x = attachedMenuButton.getPosx();
        int y =  attachedMenuButton.getPosy();

        mouseOverArea.setLocation(x, y);
    }

    @Override
    public boolean isMouseOver() {
        return this.mouseOverArea.isMouseOver();
    }

    @Override
    public void render(GUIContext guiContext, Graphics g) {
        this.mouseOverArea.render(guiContext, g);
    }
}
