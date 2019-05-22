/* Main program here test*/

import UI.MenuButton;
import backend.*;
import org.newdawn.slick.*;

//Classe/Structure de donées échangées entre les différents composants principaux du jeu


class MasarGame implements Game {

    private MasarData gameData;

    Image BackgroundTitle;
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

    public void init(GameContainer gc) throws SlickException{


        this.gameData = new MasarData(gc);

        this.gameData.getBackground().loop();
        this.gameData.getBackground().setVolume(0.2f);

       try {
           this.BackgroundTitle = new Image("img/Background/bgmt.png");
           this.BackgroundMenu = new Image("img/Background/bgm.png");
           this.BackgroundGame = new Image("img/Background/bgg.png");
       } catch (SlickException e) {
           e.printStackTrace();
       }


       gameData.loadImages();
       gameData.loadCoordinates();
       /*for (int i=0 ; i<24 ; i++)
           System.out.println("x : " + gameData.getCoordinates()[i][0] + " et y : " + gameData.getCoordinates()[i][1] );
        */



        /*for (int i=4 ; i<24 ; i++)
            this.gameData.getSystemList().add(new MasarSystem(0,0,1, this.gameData));*/


        //for (int i=0 ; i<4 ; i++)
           // this.gameData.getSystemList().get(i).setPos(gameData.getCoordinates()[i][0], gameData.getCoordinates()[i][1]);



        MenuButton p = new MenuButton(1207f, 16f, this.gameData, "img/Buttons/b_playpause.png", "PlayPause");
        MenuButton b = new MenuButton(1257f, 16f, this.gameData, "img/Buttons/b_back_lte.png", "BackLte");
        MenuButton e = new MenuButton(640f, 687.5f, this.gameData, "img/Buttons/b_back.png", "BackEnd");

        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.MENUROOM, this.gameData));
        this.gameData.getRoomList().get(0).addButton(new MenuButton(640f, 300f, this.gameData, "img/Buttons/b_chapters.png", "Chapters"));
        this.gameData.getRoomList().get(0).addButton(new MenuButton(640f, 450f, this.gameData, "img/Buttons/b_settings.png", "Settings"));
        this.gameData.getRoomList().get(0).addButton(new MenuButton(640f, 600f, this.gameData, "img/Buttons/b_quit.png", "Quit"));
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.MENUROOM, this.gameData));
        this.gameData.getRoomList().get(1).addButton(new MenuButton(640f, 58f, this.gameData, "img/Buttons/b_chapter1.png", "Chapter1"));
        this.gameData.getRoomList().get(1).addButton(new MenuButton(640f, 188f, this.gameData, "img/Buttons/b_chapter2.png", "Chapter2"));
        this.gameData.getRoomList().get(1).addButton(new MenuButton(640f, 318f, this.gameData, "img/Buttons/b_chapter3.png", "Chapter3"));
        this.gameData.getRoomList().get(1).addButton(new MenuButton(640f, 448f, this.gameData, "img/Buttons/b_chapter4.png", "Chapter4"));
        this.gameData.getRoomList().get(1).addButton(new MenuButton(640f, 578f, this.gameData, "img/Buttons/b_chapter5.png", "Chapter5"));
        this.gameData.getRoomList().get(1).addButton(new MenuButton(640f, 687.5f, this.gameData, "img/Buttons/b_back.png", "Back"));
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.MENUROOM, this.gameData));
        this.gameData.getRoomList().get(2).addButton(new MenuButton(640f, 335f, this.gameData, "img/Buttons/b_music.png", "Music"));
        this.gameData.getRoomList().get(2).addButton(new MenuButton(640f, 687.5f, this.gameData, "img/Buttons/b_done.png", "Done"));
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.GAMEROOM, this.gameData));
        this.gameData.getRoomList().get(3).addButton(b);
        this.gameData.getRoomList().get(3).addButton(p);
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.PAUSEROOM, this.gameData));
        this.gameData.getRoomList().get(4).addButton(p);
        this.gameData.getRoomList().get(4).addButton(b);
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.VICTORYROOM, this.gameData));
        this.gameData.getRoomList().get(5).addButton(e);
        this.gameData.getRoomList().add(new MasarRoom(MasarRoom.DEFEATROOM, this.gameData));
        this.gameData.getRoomList().get(6).addButton(e);
        this.gameData.setRoom(0);

    }

    public void render(GameContainer gc, Graphics graphics){
        if(this.gameData.getCurrentRoom().getRoomType() == 1 && this.gameData.getCurrentRoom().getRoomNumber() == 1) {
            BackgroundTitle.draw(0, 0, gc.getWidth(), gc.getHeight());
        } else if (this.gameData.getCurrentRoom().getRoomType() == 1 && this.gameData.getCurrentRoom().getRoomNumber() != 1) {
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