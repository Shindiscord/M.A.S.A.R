package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import UI.WindowSystem;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;

/* Classe du système de salles du jeu */
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
    public static final int VICTORYROOM = 4;
    public static final int DEFEATROOM = 5;
    private static int NBROOM = 0;

    private int RoomNumber;

    ClickManager getClickManager(){return this.clickManager;}

    /* Constructeur */
    public MasarRoom(int roomType, MasarData gameData){
        this.renderables = new LinkedList<>();
        this.gameData = gameData;
        this.clickManager = new ClickManager(this.gameData);
        this.roomType = roomType;
        this.deltaSum = 0;
        this.deltaBot = 0;
        MasarRoom.NBROOM++;
        this.RoomNumber = MasarRoom.NBROOM;
    }

    /* Ajout d'un clickable au click manager */
    public void addClickable(Clickable c){
        if(!this.clickManager.getRegisteredClickables().contains(c)){
            this.clickManager.addClickable(c);
        }
    }

    /* Ajoute le bouton passé en paramètre à la liste des renderables et des clickables */
    public void addButton(MenuButton button){
        this.renderables.add(button);
        this.addClickable(button);
    }

    /* Fonction de dessin propre aux salles */
    public void render(GameContainer gc, Graphics g){

        for(Renderable r:renderables){
            r.render(gc, g);
        }

        if(this.gameData.getCurrentRoom().getRoomType() == MasarRoom.VICTORYROOM) {
            this.gameData.getImageList().get(2).draw(226, 284);
        }

        if(this.gameData.getCurrentRoom().getRoomType() == MasarRoom.DEFEATROOM) {
            this.gameData.getImageList().get(3).draw(120, 288);
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

    /* */
    public void update(GameContainer gc, int delta){
        //si la population alliée totale arrive a 0 -> défaite
        if(gameData.getPopA() == 0 && this.gameData.getCurrentRoom().getRoomType() == MasarRoom.GAMEROOM){
            this.gameData.setRoom(6);
        }
        //si la population ennemie totale arrive a 0 -> victoire
        else if(gameData.getPopE() == 0 && this.gameData.getCurrentRoom().getRoomType() == MasarRoom.GAMEROOM){
            this.gameData.setRoom(5);
        }

        deltaSum += delta; //permet de definir le temps de chaque cycle d'update des objets
        deltaBot += delta; //permet de definir le temps de chaque cycle d'update du bot

        if(this.gameData.getCurrentRoom().getRoomType() == MasarRoom.GAMEROOM) {
            if (deltaSum >= 250) {
                try {
                    for (MasarSystem s : this.gameData.getSystemList())
                        s.update(gc);
                    for (SystemLink l : this.gameData.getLinkList())
                        l.update();
                }catch (ConcurrentModificationException e){
                    System.out.println("ConcurrentModificationException");
                }
                deltaSum = 0;
            }

            if(deltaBot >= 5000) {
                this.gameData.getBot().update();
                deltaBot = 0;
            }
        }
    }

    /* Change la salle active */
    void setAsRoom(MasarData gameData){
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

    public int getRoomNumber() {
        return this.RoomNumber;
    }
}