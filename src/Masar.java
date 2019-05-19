/* Main program here test*/

import Objects.MasarSystem;
import UI.MenuButton;
import backend.*;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;

//Classe/Structure de donées échangées entre les différents composants principaux du jeu


class MasarGame implements Game {

    private MasarData gameData;


    /*0 = main menu, 1 = chapters menu, 2 = settings menu, 3 = in-game */

    Image Background;

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

        this.gameData = new MasarData(gc);

       try {
           this.Background = new Image("./res/img/Background/bg.png");
       } catch (SlickException e) {
           e.printStackTrace();
       }

       gameData.loadImages();

        this.gameData.getSystemList().add(new MasarSystem(1,0,0,0,1, this.gameData));
        this.gameData.getSystemList().add(new MasarSystem(0,0,0,0,3, this.gameData));
        this.gameData.getSystemList().add(new MasarSystem(0,0,0,0,1, this.gameData));
        this.gameData.getSystemList().add(new MasarSystem(2,0,0,0,2, this.gameData));

        this.gameData.getSystemList().get(0).setPos(100.0f, 100.0f);
        this.gameData.getSystemList().get(1).setPos(300.0f, 120.0f);
        this.gameData.getSystemList().get(2).setPos(100.0f, 200.0f);
        this.gameData.getSystemList().get(3).setPos(500f, 120.0f);

        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.GAMEROOM, this.gameData));
        this.gameData.setRoom(0);

    }

    public void render(GameContainer gc, Graphics graphics){
        Background.draw(0, 0, gc.getWidth(), gc.getHeight());

        this.gameData.getCurrentRoom().render(gc, graphics);
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
            screen.setTargetFrameRate(10);
            screen.setShowFPS(false);
            System.out.println("Starting...");
            screen.start();//debut de init puis boucle update-render
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}