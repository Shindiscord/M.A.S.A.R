package backend;

import IA.Bot;
import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import UI.WindowSystem;
import backend.ClickManager;
import org.newdawn.slick.GameContainer;
import UI.MasarSprite;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Predicate;

public class MasarData{
    private LinkedList<MasarSystem> systemList;
    private LinkedList<SystemLink> linkList;
    private LinkedList<MasarRoom> roomList;
    private LinkedList<Image> imageList;
    private LinkedList<WindowSystem> windowList;
    private Map<String, MasarSprite> systemsImages;
    private Map<String, MasarSprite> buttonsImages;
    private Map<String, MasarSprite> linkImages;
    private Map<String, MasarSprite> planetImage;
    private Map<String, TrueTypeFont> fontMap;

    private float[][] Coordinates;

    private MasarRoom currentRoom;
    private GameContainer gc;

    private Bot bot;

    public final static int NB_SYSTEMS = 24;
    public final static float DIST_SYST = 180;
    public final static float DIST_JOINING_CONFLICT = 154;

    public LinkedList<MasarSystem> getSystemList(){return this.systemList;}
    public LinkedList<SystemLink> getLinkList(){return this.linkList;}
    public LinkedList<MasarRoom> getRoomList(){return this.roomList;}
    public LinkedList<Image> getImageList() {return this.imageList;}
    public LinkedList<WindowSystem> getWindowList() {return windowList;}
    public GameContainer getGameContainer(){return this.gc;}
    public MasarRoom getCurrentRoom(){return this.currentRoom;}
    public void setCurrentRoom(MasarRoom room){this.currentRoom = room;}
    public Map<String, MasarSprite> getSystemsImages() {return this.systemsImages;}
    public Map<String, MasarSprite> getButtonsImages() {return this.buttonsImages;}
    public Map<String, MasarSprite> getLinkImages(){return this.linkImages;}
    public float[][] getCoordinates(){ return this.Coordinates;}
    public TrueTypeFont getFont(String s){return this.fontMap.get(s);}
    public MasarSprite getPlanetImage(String s){return this.planetImage.get(s);}
    public Bot getBot() {return this.bot;}

    public void setRoom(int index){
        if(index < roomList.size() && roomList.get(index) != null){
            this.roomList.get(index).setAsRoom(this);
            System.out.println("room is now " +  index);
        }else{
            System.out.println("Error, Room number "+ index+ " Can't be found");
        }
    }

    //remove link and linkclickable (hitbox)
    public void removeLink(SystemLink link){
        this.getCurrentRoom().getClickManager().getRegisteredClickables().removeIf(lc -> (lc instanceof LinkClickable &&((LinkClickable) lc).getAttachedLink().equals(link)));
        this.getLinkList().removeIf(Predicate.isEqual(link));
    }

    //at the end of a system conquest, remove all links from the loser
    public void removeAllLinksOfDefeated(MasarSystem system_conquered){
        for(SystemLink l : this.getLinkList()){
            if( l.getSys2() == system_conquered && l.getSys1().getClan() != system_conquered.getClan()  )
                this.removeLink(l);
        }
    }

    public MasarData(GameContainer gc){
        this.gc = gc;
        this.systemList = new LinkedList<>();
        this.linkList = new LinkedList<>();
        this.roomList = new LinkedList<>();
        this.imageList = new LinkedList<>();
        this.windowList = new LinkedList<>();
        this.systemsImages = new HashMap<>();
        this.buttonsImages = new HashMap<>();
        this.linkImages = new HashMap<>();
        this.Coordinates = new float[NB_SYSTEMS][2];
        this.currentRoom = null;
        this.planetImage = new HashMap<>();
        this.fontMap = new HashMap<>();
        this.bot = new Bot(this);
    }

    public void loadCoordinates(){
        int itab = 0; //nombres de systemes sur une ligne = 6
        int ytab = 0; //nombres de lignes = 5
        for( int i=0 ; i<NB_SYSTEMS ; i++ ){
            if ( ytab%2 == 0 )
                this.Coordinates[i][0] = 100 + itab*DIST_SYST;
            else
                this.Coordinates[i][0] = 100 + 90+ itab*DIST_SYST;

            this.Coordinates[i][1] = 32 + 123 + ytab*DIST_JOINING_CONFLICT;
            itab = (itab + 1)%6;
            if ( itab == 0 ) ytab++;
        }
    }

    public void loadImages(){
        this.systemsImages.put("ne_1planet_var1", new MasarSprite("res/img/System/Neutral/ne_starsys1_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("ne_2planet_var1", new MasarSprite("res/img/System/Neutral/ne_starsys1_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("ne_3planet_var1", new MasarSprite("res/img/System/Neutral/ne_starsys1_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("ne_1planet_var2", new MasarSprite("res/img/System/Neutral/ne_starsys2_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("ne_2planet_var2", new MasarSprite("res/img/System/Neutral/ne_starsys2_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("ne_3planet_var2", new MasarSprite("res/img/System/Neutral/ne_starsys2_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("ne_1planet_var3", new MasarSprite("res/img/System/Neutral/ne_starsys3_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("ne_2planet_var3", new MasarSprite("res/img/System/Neutral/ne_starsys3_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("ne_3planet_var3", new MasarSprite("res/img/System/Neutral/ne_starsys3_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("allied_1planet_var1", new MasarSprite("res/img/System/Allied/allied_starsys1_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("allied_2planet_var1", new MasarSprite("res/img/System/Allied/allied_starsys1_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("allied_3planet_var1", new MasarSprite("res/img/System/Allied/allied_starsys1_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("allied_1planet_var2", new MasarSprite("res/img/System/Allied/allied_starsys2_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("allied_2planet_var2", new MasarSprite("res/img/System/Allied/allied_starsys2_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("allied_3planet_var2", new MasarSprite("res/img/System/Allied/allied_starsys2_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("allied_1planet_var3", new MasarSprite("res/img/System/Allied/allied_starsys3_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("allied_2planet_var3", new MasarSprite("res/img/System/Allied/allied_starsys3_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("allied_3planet_var3", new MasarSprite("res/img/System/Allied/allied_starsys3_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("en_1planet_var1", new MasarSprite("res/img/System/Enemy/en_starsys1_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("en_2planet_var1", new MasarSprite("res/img/System/Enemy/en_starsys1_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("en_3planet_var1", new MasarSprite("res/img/System/Enemy/en_starsys1_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("en_1planet_var2", new MasarSprite("res/img/System/Enemy/en_starsys2_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("en_2planet_var2", new MasarSprite("res/img/System/Enemy/en_starsys2_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("en_3planet_var2", new MasarSprite("res/img/System/Enemy/en_starsys2_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("en_1planet_var3", new MasarSprite("res/img/System/Enemy/en_starsys3_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("en_2planet_var3", new MasarSprite("res/img/System/Enemy/en_starsys3_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("en_3planet_var3", new MasarSprite("res/img/System/Enemy/en_starsys3_130_105px_3pl.png", 130,105,5));

        this.buttonsImages.put("Chapters", new MasarSprite("./res/img/Buttons/sheet_b_chapters.png", 312, 104, 2));
        this.buttonsImages.put("Settings", new MasarSprite("./res/img/Buttons/sheet_b_settings.png", 320, 104, 2));
        this.buttonsImages.put("Quit", new MasarSprite("./res/img/Buttons/sheet_b_quit.png", 200, 104, 2));
        this.buttonsImages.put("BackLte", new MasarSprite("./res/img/Buttons/sheet_b_back_lte.png", 46, 26, 2));
        this.buttonsImages.put("Back", new MasarSprite("./res/img/Buttons/sheet_b_back.png", 115, 65, 2));
        this.buttonsImages.put("Chapter1", new MasarSprite("./res/img/Buttons/sheet_b_chapter1.png", 344, 104, 2));
        this.buttonsImages.put("Done", new MasarSprite("./res/img/Buttons/sheet_b_done.png", 115, 65, 2));
        this.buttonsImages.put("PlayPause", new MasarSprite("./res/img/Buttons/sheet_b_playpause.png", 54, 26, 2));

        this.systemsImages.put("en_3planet_var3", new MasarSprite("res/img/System/Enemy/en_starsys3_130_105px_3pl.png", 130,105,5));

        this.linkImages.put("player_link_sprite", new MasarSprite("res/img/Link/ray_pl_loop_180px_sheet.png", 170, 11, 19));
        this.linkImages.put("enemy_link_sprite", new MasarSprite("res/img/Link/ray_en_loop_180px_sheet.png", 170, 11, 19));

        Image i1 = null;
        Image i2 = null;
        try {
            i1 = new Image("./res/img/UI/res_allied.png");
            i2 = new Image("./res/img/UI/res_enemy.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.imageList.add(i1);
        this.imageList.add(i2);
        this.buttonsImages.put("Enemy", new MasarSprite("./res/img/UI/human_enemy.png", 28, 27, 9));
        this.buttonsImages.put("Allied", new MasarSprite("./res/img/UI/human_allied.png", 28, 27, 9));

        this.linkImages.put("conflict_link_sprite", new MasarSprite("res/img/Link/ray_conflict_180px_sheet.png", 180, 19, 20));


        this.planetImage.put("planet1", new MasarSprite("res/img/Planets/planet1.png", 26, 26, 25));
        this.planetImage.put("planet2", new MasarSprite("res/img/Planets/planet1.png", 26, 26, 25));
        this.planetImage.put("planet3", new MasarSprite("res/img/Planets/planet1.png", 26, 26, 25));

        this.fontMap.put("default", new TrueTypeFont(new Font("Monospaced", Font.PLAIN, 12), false));
        this.fontMap.put("default2", new TrueTypeFont(new Font("Monospaced", Font.PLAIN, 9), false));
        this.fontMap.put("UITopFont", new TrueTypeFont(new Font("Monospaced", java.awt.Font.PLAIN, 25),  false));
        this.fontMap.put("OnMapText", new TrueTypeFont(new Font("Monospaced", Font.BOLD, 12), false));

        this.planetImage.put("InfoWindow", new MasarSprite("res/img/InfoWindow/window.png", 240, 137, 1));
    }
}