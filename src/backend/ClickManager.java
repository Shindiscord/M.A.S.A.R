package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import backend.Clickable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.gui.GUIContext;

import java.util.LinkedList;
import java.util.Set;

public class ClickManager implements MouseListener {


    private LinkedList<Clickable> registeredClickables;
    private MasarData gameData;

    private boolean mousePressed;
    private Clickable pressedClickable;
    private GUIContext gc;

    public ClickManager(GUIContext gc, MasarData gameData){
        this.gameData = gameData;
        this.mousePressed = false;
        this.registeredClickables = new LinkedList<Clickable>();
        this.pressedClickable = null;
        this.gc = gc;
        System.out.println("ClickManagerCreated");
    }

    public void init(){
        System.out.println("Creating SystemClickables");
        int i = 0;
        for(MasarSystem s: this.gameData.getSystemList()){
            this.registeredClickables.add(new SystemClickable(s, gc));
            i++;
        }
        System.out.println("ClickManager started with " + i + " Systems.");

        for(MenuButton mb: this.gameData.getButtonList()) {
            this.registeredClickables.add(new MenuButtonClickable(mb, gc));
        }

    }

    public void render(GUIContext gc, Graphics graphics){
        for(Clickable c:registeredClickables) {
            if(c instanceof  MenuButtonClickable) {
                if (this.gameData.getDisplayMode() == 0 && ((MenuButtonClickable) c).getButtonType() == 0) {
                    c.render(gc, graphics);
                }
                if(this.gameData.getDisplayMode() == 1 && ((MenuButtonClickable) c).getButtonType() == 1) {
                    c.render(gc, graphics);
                }
            }

            if (this.gameData.getDisplayMode() == 3 && c instanceof SystemClickable) {
                c.render(gc, graphics);
            } else if (this.gameData.getDisplayMode() == 3 && c instanceof  LinkClickable) {
                c.render(gc, graphics);
            }

        }

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

    private void onAddLink(SystemClickable s1, SystemClickable s2){
        if(s1.getAttachedSystem().getDistance(s2.getAttachedSystem()) > 300f)
            return;
        SystemLink link = new SystemLink(s1.getAttachedSystem(),s2.getAttachedSystem());
        for(SystemLink l:this.gameData.getLinkList()) {
            if (l.equals(link)) return;
        }
        System.out.println("Lien créé");
        this.gameData.getLinkList().add(link);
        this.registeredClickables.add(new LinkClickable(link, this.gc));
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
                if(pressedClickable instanceof MenuButtonClickable) {
                    if(((MenuButtonClickable) pressedClickable).getButtonName().equals("Chapters") && this.gameData.getDisplayMode() == 0) {
                        this.gameData.setDisplayMode(1);
                    }
                    if (((MenuButtonClickable) pressedClickable).getButtonName().equals("Quit") && this.gameData.getDisplayMode() == 0) {
                        System.exit(0);
                    }
                    if(((MenuButtonClickable) pressedClickable).getButtonName().equals("Chap1") && this.gameData.getDisplayMode() == 1) {
                        this.gameData.setDisplayMode(3);
                    }
                }
            }else{
                int i = 0;
                for(Clickable c: selected) {
                    pressedClickable = selected.get(i);
                    if(pressedClickable instanceof MenuButtonClickable && this.gameData.getDisplayMode() != 3) {
                        if(((MenuButtonClickable) pressedClickable).getButtonType() != this.gameData.getDisplayMode()) {
                            selected.remove(i);
                            continue;
                        }
                    }
                    i++;
                }
                pressedClickable = selected.element();
                if(((MenuButtonClickable) pressedClickable).getButtonName().equals("Chapters") && this.gameData.getDisplayMode() == 0) {
                    this.gameData.setDisplayMode(1);
                    return;
                }
                if (((MenuButtonClickable) pressedClickable).getButtonName().equals("Quit") && this.gameData.getDisplayMode() == 0) {
                    System.exit(0);
                }
                if(((MenuButtonClickable) pressedClickable).getButtonName().equals("Chap1") && this.gameData.getDisplayMode() == 1) {
                    this.gameData.setDisplayMode(3);
                }
                //System.out.println("Several object clicked, manage conflicts");
            }
        }
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseReleased(int button, int mx, int my) {
        LinkedList<Clickable> selected;
        if(button == Input.MOUSE_LEFT_BUTTON){
            mousePressed = false;
            selected = selectClickedObjects();
            if(selected.isEmpty()) {
                System.out.println("Mouse released on empty space");
            }else if(selected.size() == 1){
                if(selected.element() == pressedClickable) {
                    System.out.println("1 object clicked");
                }else{
                    if(selected.element() instanceof SystemClickable && pressedClickable instanceof SystemClickable && this.gameData.getDisplayMode() == 3)
                        //2 Systems selected, create link.
                        onAddLink((SystemClickable) this.pressedClickable, (SystemClickable) selected.element());
                }
            }else{
                System.out.println("Several object released, manage conflicts");
            }
            pressedClickable = null;
        }else if(button == Input.MOUSE_RIGHT_BUTTON){
            selected = selectClickedObjects();
            for(Clickable c:selected) {
                if(c instanceof LinkClickable){
                    this.gameData.getLinkList().remove(((LinkClickable) c).getAttachedLink());
                    this.registeredClickables.remove(c);
                }
            }
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