/* Main program here test*/

import Objects.MasarSystem;
import backend.ClickManager;
import backend.Clickable;
import backend.SystemClickable;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.ImageData;

import java.util.LinkedList;
import java.util.Set;

class MasarGame implements Game {
    LinkedList<MasarSystem> systemList;
    LinkedList<Clickable> clickables;

    ClickManager clickManager;

    public MasarGame(){

    }

    @Override
    public boolean closeRequested() {
        System.exit(0);
        return true;
    }

    public String getTitle(){
        return "MASAR";
    }

    public void init(GameContainer gc){
        this.clickManager = new ClickManager();
        gc.getInput().addMouseListener(clickManager);

        systemList = new LinkedList<>();
        clickables = new LinkedList<>();

        systemList.add(new MasarSystem(0,0,0,0));
        systemList.add(new MasarSystem(0,0,0,0));
        systemList.get(0).setPos(100.0f, 100.0f);
        systemList.get(1).setPos(200f, 200f);

        clickables.add(new SystemClickable(systemList.get(0), gc));
        clickables.add(new SystemClickable(systemList.get(1), gc));

        clickManager.addClickable(clickables.get(0));
        clickManager.addClickable(clickables.get(1));
    }

    public void render(GameContainer gc, Graphics graphics){
        for (Clickable c: clickables) {
            c.render(gc, graphics);
        }
    }

    public void update(GameContainer gc, int delta){

    }
}

public class Masar{
    public static void main(String[] args){
        AppGameContainer screen;
        try{
            screen = new AppGameContainer(new MasarGame());
            screen.setDisplayMode(1280, 720, false);
            System.out.println("Starting...");
            screen.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}