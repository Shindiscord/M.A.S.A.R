package backend;

import Objects.SystemLink;
import backend.Clickable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.gui.GUIContext;

import java.util.LinkedList;
import java.util.Set;

public class ClickManager implements MouseListener {


    private LinkedList<Clickable> registeredClickables;

    private boolean mousePressed;
    private Clickable pressedClickable;
    private GUIContext gc;

    public ClickManager(GUIContext gc, LinkedList<Clickable> l){
        registeredClickables = l;
        mousePressed = false;
        pressedClickable = null;
        this.gc = gc;
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
        mousePressed = true;
        if(button == Input.MOUSE_LEFT_BUTTON){
            selected = selectClickedObjects();
            if(selected.isEmpty()) {
                pressedClickable = null;
                System.out.println("Mouse clicked on empty space");
            }else if(selected.size() == 1){
                System.out.println("Object Clicked");
                pressedClickable = selected.element();
            }else{
                System.out.println("Several object clicked, manage conflicts");
            }
        }
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseReleased(int button, int mx, int my) {
        LinkedList<Clickable> selected;
        mousePressed = false;
        if(button == Input.MOUSE_LEFT_BUTTON){
            selected = selectClickedObjects();
            if(selected.isEmpty()) {
                System.out.println("Mouse released on empty space");
            }else if(selected.size() == 1){
                if(selected.element() == pressedClickable) {
                    System.out.println("1 object clicked");
                }else{
                    if(selected.element() instanceof SystemClickable && pressedClickable instanceof SystemClickable)
                        registeredClickables.add(
                                new LinkClickable(
                                        new SystemLink(((SystemClickable) pressedClickable).getAttachedSystem(), ((SystemClickable) selected.element()).getAttachedSystem()),
                                        this.gc
                                )
                        );
                    System.out.println("Diff objects clicked, create link ?");
                }
            }else{
                System.out.println("Several object released, manage conflicts");
            }
        }
        pressedClickable = null;
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