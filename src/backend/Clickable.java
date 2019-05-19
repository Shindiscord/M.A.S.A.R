package backend;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

public interface Clickable{
    public boolean isMouseOver();
    public void onMousePressed(int button);
    public void onMouseReleased(int button, Clickable clickablePressed);
    public void renderHitbox(GUIContext gc, Graphics g);
}