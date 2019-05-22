package backend;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

import java.util.LinkedList;

public class ClickManager implements MouseListener {

    private LinkedList<Clickable> registeredClickables;

    private Clickable pressedClickable;

    LinkedList<Clickable> getRegisteredClickables(){return this.registeredClickables;}

    ClickManager(MasarData masarData){
        this.registeredClickables = new LinkedList<>();
        this.pressedClickable = null;
        if(masarData == null){
            System.out.println("WARNING : NO MASARDATA");
        }
    }

    void addClickable(Clickable c){
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