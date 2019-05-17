/* Main program here test*/

import Objects.MasarSystem;
import UI.MenuButton;
import backend.ClickManager;
import backend.Clickable;
import backend.MenuButtonClickable;
import backend.SystemClickable;
import org.lwjgl.input.Cursor;
import backend.MasarData;
import org.newdawn.slick.*;

//Classe/Structure de donées échangées entre les différents composants principaux du jeu


class MasarGame implements Game {

    private MasarData gameData;


    /*0 = main menu, 1 = chapters menu, 2 = settings menu, 3 = in-game */

    int DisplayMode;

    Image Background;

    public MasarGame(){
        this.DisplayMode = 0;
        this.gameData = new MasarData();
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

        try {
            this.Background = new Image("./res/img/Background/bg.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.gameData.setClickManager(new ClickManager(gc, this.gameData));
        gc.getInput().addMouseListener(this.gameData.getClickManager());

        this.gameData.getSystemList().add(new MasarSystem(0,0,0,0));
        this.gameData.getSystemList().add(new MasarSystem(0,0,0,0));
        this.gameData.getSystemList().add(new MasarSystem(0,0,0,0));
        this.gameData.getSystemList().add(new MasarSystem(0,0,0,0));

        this.gameData.getSystemList().get(0).setPos(100.0f, 100.0f);
        this.gameData.getSystemList().get(1).setPos(300.0f, 120.0f);
        this.gameData.getSystemList().get(2).setPos(100.0f, 300f);
        this.gameData.getSystemList().get(3).setPos(500f, 120.0f);

        this.gameData.getClickManager().init();
    }

    public void render(GameContainer gc, Graphics graphics){
        this.gameData.getClickManager().render(gc, graphics);
        Background.draw(0, 0, gc.getWidth(), gc.getHeight());

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