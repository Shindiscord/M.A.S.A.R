package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import UI.WindowSystem;
import backend.Clickable;
import backend.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tests.xml.GameData;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class MasarRoom implements Renderable{
    private LinkedList<Renderable> renderables;
    private MasarData gameData;
    private ClickManager clickManager;

    private int deltaSum;
    private int deltaBot;

    private int roomType;

    public static final int  MENUROOM = 1;
    public static final int GAMEROOM = 2;
    public static final int PAUSEROOM = 3;

    public ClickManager getClickManager(){return this.clickManager;}

    public MasarRoom(int roomType, MasarData gameData){
        this.renderables = new LinkedList<>();
        this.gameData = gameData;
        this.clickManager = new ClickManager(this.gameData);
        this.roomType = roomType;
        this.deltaSum = 0;
        this.deltaBot = 0;
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

            this.gameData.getImageList().get(0).draw(300, 5);
            this.gameData.getImageList().get(1).draw(800, 5);
            this.gameData.getButtonsImages().get("Enemy").drawNextSubimage(600, 16);
            this.gameData.getButtonsImages().get("Allied").drawNextSubimage(500, 16);

            TrueTypeFont font = this.gameData.getFont("UITopFont");
            font.drawString(536, 0, "VS" , Color.green);

            int popa = this.gameData.getPopA();
            int pope = this.gameData.getPopE();
            int resa = this.gameData.getResA();
            int rese = this.gameData.getResE();

            font.drawString(620, 0, ""+pope , Color.red);
            font.drawString(830, 0, ""+rese , Color.red);

            if ( popa > 1000000 ) font.drawString(480 - font.getWidth(""+popa/1000000 + "M"), 0, ""+popa/1000000 + "M" , Color.cyan);
            else if ( popa > 1000 ) font.drawString(480 - font.getWidth(""+popa/1000 + "K"), 0, ""+popa/1000 + "K" , Color.cyan);
            else  font.drawString(480 - font.getWidth(""+popa), 0, ""+popa , Color.cyan);
            font.drawString(295 - font.getWidth(""+resa), 0, ""+resa , Color.cyan);

            for(WindowSystem w: this.gameData.getWindowList()){
                if(w.getMs().isShowWindow())
                    w.render(gc, g);
            }
            //this.getClickManager().renderHitboxes(gc, g);

        }

        //this.getClickManager().renderHitboxes(gc, g);
    }

    public void update(GameContainer gc, int delta){
        if(gameData.getPopA() == 0){
            //TODO Charge la room Game Over
        }
        else if(gameData.getPopE() == 0){
            //TODO Charge la room WINNER, WWINNER, CHICKEN DINNER
        }

        deltaSum += delta;
        deltaBot += delta;
        if(this.gameData.getCurrentRoom().getRoomType() == MasarRoom.GAMEROOM) {
            if (deltaSum >= 250) {
                try {
                    for (MasarSystem s : this.gameData.getSystemList())
                        s.update(gc);
                    for (SystemLink l : this.gameData.getLinkList())
                        l.update();
                }catch (ConcurrentModificationException e){}
                deltaSum = 0;
            }

            if(deltaBot >= 5000) {
                //this.gameData.getBot().update();
                deltaBot = 0;
            }
        }
    }


    public void setAsRoom(MasarData gameData){
    gameData.getGameContainer().getInput().removeAllListeners();

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