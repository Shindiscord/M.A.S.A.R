package backend;

import Objects.MasarSystem;
import UI.MenuButton;
import backend.Clickable;
import backend.Renderable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tests.xml.GameData;

import java.util.LinkedList;

public class MasarRoom implements Renderable{
    private LinkedList<Renderable> renderables;
    private MasarData gameData;
    private ClickManager clickManager;

    private int roomType;

    public static final int  MENUROOM = 1;
    public static final int GAMEROOM = 2;

    public ClickManager getClickManager(){return this.clickManager;}

    public MasarRoom(int roomType, MasarData gameData){
        this.renderables = new LinkedList<>();
        this.gameData = gameData;
        this.clickManager = new ClickManager(this.gameData);
        this.roomType = roomType;
    }

    public void addRenderable(Renderable renderable){
        if(!renderables.contains(renderable)){
            renderables.add(renderable);
        }
    }
    public void addClickable(Clickable c){
        if(!this.clickManager.getRegisteredClickables().contains(c)){
            this.clickManager.addClickable(c);
        }
    }

    public void addButton(MenuButton button){
        this.renderables.add(button);
        this.addClickable(button);
    }

    public void render(GameContainer gc, Graphics g){

        for(Renderable r:renderables){
            r.render(gc, g);
        }


        if(roomType == GAMEROOM){

            for(Renderable l: this.gameData.getLinkList()){
                l.render(gc, g);
            }
            for(Renderable s: this.gameData.getSystemList()){
                s.render(gc, g);
            }

            //this.getClickManager().renderHitboxes(gc, g);

        }

        //this.getClickManager().renderHitboxes(gc, g);
    }


    public void setAsRoom(MasarData gameData){
        gameData.getGameContainer().getInput().removeAllMouseListeners();

        if(this.roomType == GAMEROOM){
            for(MasarSystem system: this.gameData.getSystemList()){
                this.clickManager.addClickable(new SystemClickable(system, this.gameData));
            }
        }
        if(this.clickManager != null){
            gameData.getGameContainer().getInput().addMouseListener(clickManager);
        }

        this.gameData.setCurrentRoom(this);
    }

    public int getRoomType() {
        return this.roomType;
    }
}