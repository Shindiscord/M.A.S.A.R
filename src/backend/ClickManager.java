package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import backend.Clickable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.tests.xml.GameData;

import java.util.LinkedList;
import java.util.Set;

public class ClickManager implements MouseListener {

    private LinkedList<Clickable> registeredClickables;
    private MasarData gameData;

    private boolean mousePressed;
    private Clickable pressedClickable;

    public LinkedList<Clickable> getRegisteredClickables(){return this.registeredClickables;}

    public ClickManager(MasarData gameData){
        this.gameData = gameData;
        this.mousePressed = false;
        this.registeredClickables = new LinkedList<>();
        this.pressedClickable = null;
    }

    public void addClickable(Clickable c){
        if(!registeredClickables.contains(c)){
            registeredClickables.add(c);
        }
    }

    private LinkedList<Clickable> selectClickedObjects(){
        LinkedList<Clickable> selected = new LinkedList<>();
        for(Clickable c:this.registeredClickables) {
            if(c.isMouseOver()){
                selected.add(c);
            }
        }
        return selected;
    }

    @Override
    public void mousePressed(int button, int mx, int my) {
        LinkedList<Clickable> selected;
        selected = selectClickedObjects();
        if(selected.size() == 0){
            System.out.println("Mousse pressed on empty space");
        }else if(selected.size() == 1){
            selected.element().onMousePressed(button);
            this.pressedClickable = selected.element();
        }

        if(button == Input.MOUSE_LEFT_BUTTON)
            mousePressed = true;
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseReleased(int button, int mx, int my) {
        LinkedList<Clickable> selected;
        selected = selectClickedObjects();

        if(selected.size() == 0){
            System.out.println("Mouse released on empty space");
        }else if(selected.size() == 1){
            selected.element().onMouseReleased(button, this.pressedClickable);
        }

        if(button == Input.MOUSE_LEFT_BUTTON)
            mousePressed = false;
    }

    public void renderHitboxes(GameContainer gc, Graphics g){
        for(Clickable c:this.getRegisteredClickables()){
            c.renderHitbox(gc, g);
        }
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void setInput(Input input) {
        input.addMouseListener(this);
    }

    @Override
    public boolean isAcceptingInput(){return true;}

    @Override
    public void inputEnded(){}

    @Override
    public void inputStarted(){}

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {}
}