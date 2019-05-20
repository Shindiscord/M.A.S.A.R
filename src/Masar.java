/* Main program here test*/

import Objects.MasarSystem;
import UI.MenuButton;
import backend.*;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;
import org.newdawn.slick.tests.xml.GameData;

//Classe/Structure de donées échangées entre les différents composants principaux du jeu


class MasarGame implements Game {

    private MasarData gameData;

    Image BackgroundMenu;
    Image BackgroundGame;

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
           this.BackgroundMenu = new Image("./res/img/Background/bgm.png");
           this.BackgroundGame = new Image("./res/img/Background/bgg.png");
       } catch (SlickException e) {
           e.printStackTrace();
       }

       gameData.loadImages();
       gameData.loadCoordinates();
       /*for (int i=0 ; i<24 ; i++)
           System.out.println("x : " + gameData.getCoordinates()[i][0] + " et y : " + gameData.getCoordinates()[i][1] );
        */

        this.gameData.getSystemList().add(new MasarSystem(1,1000000,3, this.gameData));
        this.gameData.getSystemList().add(new MasarSystem(0,100000,1, this.gameData));
        this.gameData.getSystemList().add(new MasarSystem(0,100000,1, this.gameData));
        this.gameData.getSystemList().add(new MasarSystem(2,1000000,3, this.gameData));

        for (int i=4 ; i<24 ; i++)
            this.gameData.getSystemList().add(new MasarSystem(0,100000,1, this.gameData));


        for (int i = 0; i< MasarData.NB_SYSTEMS ; i++)
            this.gameData.getSystemList().get(i).setPos(gameData.getCoordinates()[i][0], gameData.getCoordinates()[i][1]);



        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.MENUROOM, this.gameData));
        this.gameData.getRoomList().get(0).addButton(new MenuButton(640f, 300f, this.gameData, "./res/img/Buttons/b_chapters.png", "Chapters"));
        this.gameData.getRoomList().get(0).addButton(new MenuButton(640f, 450f, this.gameData, "./res/img/Buttons/b_settings.png", "Settings"));
        this.gameData.getRoomList().get(0).addButton(new MenuButton(640f, 600f, this.gameData, "./res/img/Buttons/b_quit.png", "Quit"));
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.GAMEROOM, this.gameData));
        this.gameData.setRoom(0);

    }

    public void render(GameContainer gc, Graphics graphics){
        if(this.gameData.getCurrentRoom().getRoomType() == 1) {
            BackgroundMenu.draw(0, 0, gc.getWidth(), gc.getHeight());
        } else {
            BackgroundGame.draw(0, 0, gc.getWidth(), gc.getHeight());
        }

        this.gameData.getCurrentRoom().render(gc, graphics);
    }

    public void update(GameContainer gc, int delta){
        this.gameData.getCurrentRoom().update(gc, delta);
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