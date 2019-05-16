package backend;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

public interface Clickable{
    public boolean isMouseOver();
    public void render(GUIContext guiContext, Graphics g);
}