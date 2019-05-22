package backend;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

public interface Clickable{
    boolean isMouseOver();
    void onMousePressed(int button);
    void onMouseReleased(int button, Clickable clickablePressed);
    void renderHitbox(GUIContext gc, Graphics g);
}