package backend;

import IA.Bot;
import Objects.MasarSystem;
import Objects.SystemLink;
import UI.WindowSystem;
import org.newdawn.slick.*;
import UI.MasarSprite;
import org.newdawn.slick.Image;
import java.awt.Font;
import java.util.HashMap;
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

    private int chapitre;

    private Bot bot;

    private Music background;

    private final static int NB_SYSTEMS = 24;
    public final static float DIST_SYST = 180;
    private final static float DIST_JOINING_CONFLICT = 154;

    public LinkedList<MasarSystem> getSystemList(){return this.systemList;}
    public LinkedList<SystemLink> getLinkList(){return this.linkList;}
    public LinkedList<MasarRoom> getRoomList(){return this.roomList;}
    LinkedList<Image> getImageList() {return this.imageList;}
    public LinkedList<WindowSystem> getWindowList() {return windowList;}
    public GameContainer getGameContainer(){return this.gc;}
    public MasarRoom getCurrentRoom(){return this.currentRoom;}
    void setCurrentRoom(MasarRoom room){this.currentRoom = room;}
    public Map<String, MasarSprite> getSystemsImages() {return this.systemsImages;}
    public Map<String, MasarSprite> getButtonsImages() {return this.buttonsImages;}
    public Map<String, MasarSprite> getLinkImages(){return this.linkImages;}
    private float[][] getCoordinates(){ return this.Coordinates;}
    public TrueTypeFont getFont(String s){return this.fontMap.get(s);}
    public MasarSprite getPlanetImage(String s){return this.planetImage.get(s);}
    Bot getBot() {return this.bot;}
    public int getChapitre() {return this.chapitre;}
    public Music getBackground() {return background;}

    int getPopA(){
        int popa = 0;
        for (MasarSystem s:this.getSystemList()){
            if(s.getClan() == MasarSystem.ALLIED) {
                popa += s.getPop();
            }
        }

        return popa;
    }

    int getPopE(){
        int pope = 0;
        for (MasarSystem s:this.getSystemList()){
            if(s.getClan() == MasarSystem.ENNEMY) {
                pope += s.getPop();
            }
        }

        return pope;
    }

    int getResA(){
        int resa = 0;
        for (MasarSystem s:this.getSystemList()){
            if(s.getClan() == MasarSystem.ALLIED) {
                resa += s.getRPS();
            }
        }

        return resa;
    }

    public int getResE(){
        int rese = 0;
        for (MasarSystem s:this.getSystemList()){
            if(s.getClan() == MasarSystem.ENNEMY) {
                rese += s.getRPS();
            }
        }

        return rese;
    }

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

    public void removeAllLinksOfDefeated(MasarSystem system_conquered){
        for(SystemLink l : this.getLinkList()){
            if( l.getSys2() == system_conquered && l.getSys1().getClan() != system_conquered.getClan()  )
                this.removeLink(l);
        }
    }

    public MasarData(GameContainer gc) throws SlickException {
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
        this.background = new Music("music/Kevin_MacLeod-8bit_Dungeon_Boss.ogg");
        this.chapitre = 0;
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

    public void setChapitre(int x) {
        this.chapitre = x;
        if(this.getSystemList().isEmpty()) {
            switch (x) {
                case 1:
                    this.getSystemList().add(new MasarSystem(1, 1000000, 3, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 1, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 1, this));
                    this.getSystemList().add(new MasarSystem(2, 100000, 2, this));

                    this.getSystemList().get(0).setPos(this.getCoordinates()[8][0], this.getCoordinates()[8][1]);
                    this.getSystemList().get(1).setPos(this.getCoordinates()[15][0], this.getCoordinates()[15][1]);
                    this.getSystemList().get(2).setPos(this.getCoordinates()[9][0], this.getCoordinates()[9][1]);
                    this.getSystemList().get(3).setPos(this.getCoordinates()[16][0], this.getCoordinates()[16][1]);

                    break;
                case 2:
                    this.getSystemList().add(new MasarSystem(1, 1000000, 3, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 1, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 1, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 3, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 1, this));
                    this.getSystemList().add(new MasarSystem(0, 0, 1, this));
                    this.getSystemList().add(new MasarSystem(2, 1000000, 3, this));

                    this.getSystemList().get(0).setPos(this.getCoordinates()[1][0], this.getCoordinates()[1][1]);
                    this.getSystemList().get(1).setPos(this.getCoordinates()[2][0], this.getCoordinates()[2][1]);
                    this.getSystemList().get(2).setPos(this.getCoordinates()[7][0], this.getCoordinates()[7][1]);
                    this.getSystemList().get(3).setPos(this.getCoordinates()[8][0], this.getCoordinates()[8][1]);
                    this.getSystemList().get(4).setPos(this.getCoordinates()[9][0], this.getCoordinates()[9][1]);
                    this.getSystemList().get(5).setPos(this.getCoordinates()[15][0], this.getCoordinates()[15][1]);
                    this.getSystemList().get(6).setPos(this.getCoordinates()[16][0], this.getCoordinates()[16][1]);

                    break;
                case 3:



                    break;
                case 4:
                    this.getSystemList().add(new MasarSystem(1,1000000, 3, this));
                    this.getSystemList().get(0).setPos(this.getCoordinates()[0][0], this.getCoordinates()[0][1]);

                    for ( int i = 1 ; i<23 ; i++){
                        this.getSystemList().add(new MasarSystem(0, 0,1, this));
                        this.getSystemList().get(i).setPos(this.getCoordinates()[i][0], this.getCoordinates()[i][1]);

                    }
                    this.getSystemList().add(new MasarSystem(2, 1000000, 3, this));
                    this.getSystemList().get(23).setPos(this.getCoordinates()[23][0], this.getCoordinates()[23][1]);
                    break;
                case 5:
                    this.getSystemList().add(new MasarSystem(1, 400000, 3, this));
                    this.getSystemList().add(new MasarSystem(2, 100000, 1, this));
                    this.getSystemList().add(new MasarSystem(2, 100000, 1, this));
                    this.getSystemList().add(new MasarSystem(2, 100000, 1, this));
                    this.getSystemList().add(new MasarSystem(2, 100000, 2, this));
                    for ( int i=0 ; i<8 ; i++)
                        this.getSystemList().add(new MasarSystem(0,0,1,this));

                    this.getSystemList().get(0).setPos(this.getCoordinates()[15][0], this.getCoordinates()[15][1]);
                    this.getSystemList().get(1).setPos(this.getCoordinates()[1][0], this.getCoordinates()[1][1]);
                    this.getSystemList().get(2).setPos(this.getCoordinates()[5][0], this.getCoordinates()[5][1]);
                    this.getSystemList().get(3).setPos(this.getCoordinates()[18][0], this.getCoordinates()[18][1]);
                    this.getSystemList().get(4).setPos(this.getCoordinates()[23][0], this.getCoordinates()[23][1]);
                    this.getSystemList().get(5).setPos(this.getCoordinates()[2][0], this.getCoordinates()[2][1]);
                    this.getSystemList().get(6).setPos(this.getCoordinates()[8][0], this.getCoordinates()[8][1]);
                    this.getSystemList().get(7).setPos(this.getCoordinates()[4][0], this.getCoordinates()[4][1]);
                    this.getSystemList().get(8).setPos(this.getCoordinates()[9][0], this.getCoordinates()[9][1]);
                    this.getSystemList().get(9).setPos(this.getCoordinates()[14][0], this.getCoordinates()[14][1]);
                    this.getSystemList().get(10).setPos(this.getCoordinates()[16][0], this.getCoordinates()[16][1]);
                    this.getSystemList().get(11).setPos(this.getCoordinates()[19][0], this.getCoordinates()[19][1]);
                    this.getSystemList().get(12).setPos(this.getCoordinates()[22][0], this.getCoordinates()[22][1]);

                    break;
            }
        }
    }

    public void loadImages(){
        this.systemsImages.put("ne_1planet_var1", new MasarSprite("img/System/Neutral/ne_starsys1_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("ne_2planet_var1", new MasarSprite("img/System/Neutral/ne_starsys1_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("ne_3planet_var1", new MasarSprite("img/System/Neutral/ne_starsys1_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("ne_1planet_var2", new MasarSprite("img/System/Neutral/ne_starsys2_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("ne_2planet_var2", new MasarSprite("img/System/Neutral/ne_starsys2_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("ne_3planet_var2", new MasarSprite("img/System/Neutral/ne_starsys2_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("ne_1planet_var3", new MasarSprite("img/System/Neutral/ne_starsys3_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("ne_2planet_var3", new MasarSprite("img/System/Neutral/ne_starsys3_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("ne_3planet_var3", new MasarSprite("img/System/Neutral/ne_starsys3_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("allied_1planet_var1", new MasarSprite("img/System/Allied/allied_starsys1_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("allied_2planet_var1", new MasarSprite("img/System/Allied/allied_starsys1_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("allied_3planet_var1", new MasarSprite("img/System/Allied/allied_starsys1_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("allied_1planet_var2", new MasarSprite("img/System/Allied/allied_starsys2_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("allied_2planet_var2", new MasarSprite("img/System/Allied/allied_starsys2_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("allied_3planet_var2", new MasarSprite("img/System/Allied/allied_starsys2_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("allied_1planet_var3", new MasarSprite("img/System/Allied/allied_starsys3_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("allied_2planet_var3", new MasarSprite("img/System/Allied/allied_starsys3_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("allied_3planet_var3", new MasarSprite("img/System/Allied/allied_starsys3_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("en_1planet_var1", new MasarSprite("img/System/Enemy/en_starsys1_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("en_2planet_var1", new MasarSprite("img/System/Enemy/en_starsys1_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("en_3planet_var1", new MasarSprite("img/System/Enemy/en_starsys1_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("en_1planet_var2", new MasarSprite("img/System/Enemy/en_starsys2_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("en_2planet_var2", new MasarSprite("img/System/Enemy/en_starsys2_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("en_3planet_var2", new MasarSprite("img/System/Enemy/en_starsys2_130_105px_3pl.png", 130,105,5));
        this.systemsImages.put("en_1planet_var3", new MasarSprite("img/System/Enemy/en_starsys3_130_105px_1pl.png", 130,105,5));
        this.systemsImages.put("en_2planet_var3", new MasarSprite("img/System/Enemy/en_starsys3_130_105px_2pl.png", 130,105,5));
        this.systemsImages.put("en_3planet_var3", new MasarSprite("img/System/Enemy/en_starsys3_130_105px_3pl.png", 130,105,5));

        this.systemsImages.put("al_res_map", new MasarSprite("img/UI/res_var1_12_11.png", 12, 11, 1));
        this.systemsImages.put("en_res_map", new MasarSprite("img/UI/res_var2_12_11.png", 12, 11, 1));
        this.buttonsImages.put("Chapters", new MasarSprite("img/Buttons/sheet_b_chapters.png", 312, 104, 2));
        this.buttonsImages.put("Settings", new MasarSprite("img/Buttons/sheet_b_settings.png", 320, 104, 2));
        this.buttonsImages.put("Quit", new MasarSprite("img/Buttons/sheet_b_quit.png", 200, 104, 2));
        this.buttonsImages.put("BackLte", new MasarSprite("img/Buttons/sheet_b_back_lte.png", 46, 26, 2));
        this.buttonsImages.put("Back", new MasarSprite("img/Buttons/sheet_b_back.png", 115, 65, 2));
        this.buttonsImages.put("Chapter1", new MasarSprite("img/Buttons/sheet_b_chapter1.png", 344, 104, 2));
        this.buttonsImages.put("Chapter2", new MasarSprite("img/Buttons/sheet_b_chapter2.png", 344, 104, 2));
        this.buttonsImages.put("Chapter3", new MasarSprite("img/Buttons/sheet_b_chapter3.png", 360, 104, 2));
        this.buttonsImages.put("Chapter4", new MasarSprite("img/Buttons/sheet_b_chapter4.png", 360, 104, 2));
        this.buttonsImages.put("Chapter5", new MasarSprite("img/Buttons/sheet_b_chapter5.png", 344, 104, 2));
        this.buttonsImages.put("Done", new MasarSprite("img/Buttons/sheet_b_done.png", 115, 65, 2));
        this.buttonsImages.put("PlayPause", new MasarSprite("img/Buttons/sheet_b_playpause.png", 54, 26, 2));
        this.buttonsImages.put("Music", new MasarSprite("img/Buttons/sheet_b_music.png", 344, 104, 2));
        this.buttonsImages.put("BackEnd", new MasarSprite("img/Buttons/sheet_b_back.png", 115, 65, 2));

        this.systemsImages.put("en_3planet_var3", new MasarSprite("img/System/Enemy/en_starsys3_130_105px_3pl.png", 130,105,5));


        this.linkImages.put("player_link_sprite", new MasarSprite("img/Link/ray_pl_loop_180px_sheet.png", 170, 11, 19));
        this.linkImages.put("enemy_link_sprite", new MasarSprite("img/Link/ray_en_loop_180px_sheet.png", 170, 11, 19));

        Image i1 = null;
        Image i2 = null;
        Image i3 = null;
        Image i4 = null;
        try {
            i1 = new Image("img/UI/res_allied.png");
            i2 = new Image("img/UI/res_enemy.png");
            i3 = new Image("img/UI/victory.png");
            i4 = new Image("img/UI/defeat.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.imageList.add(i1);
        this.imageList.add(i2);
        this.imageList.add(i3);
        this.imageList.add(i4);
        this.buttonsImages.put("Enemy", new MasarSprite("img/UI/human_enemy.png", 28, 27, 9));
        this.buttonsImages.put("Allied", new MasarSprite("img/UI/human_allied.png", 28, 27, 9));

        this.linkImages.put("conflict_link_sprite", new MasarSprite("img/Link/ray_conflict_180px_sheet.png", 180, 19, 20));


        this.planetImage.put("planet1", new MasarSprite("img/Planets/planet1.png", 26, 26, 25));
        this.planetImage.put("planet2", new MasarSprite("img/Planets/planet2.png", 26, 26, 25));
        this.planetImage.put("planet3", new MasarSprite("img/Planets/planet3.png", 28, 26, 17));

        this.fontMap.put("default", new TrueTypeFont(new Font("Monospaced", Font.PLAIN, 12), false));
        this.fontMap.put("default2", new TrueTypeFont(new Font("Monospaced", Font.PLAIN, 9), false));
        this.fontMap.put("UITopFont", new TrueTypeFont(new Font("Monospaced", java.awt.Font.PLAIN, 25),  false));
        this.fontMap.put("OnMapText", new TrueTypeFont(new Font("Monospaced", Font.BOLD, 12), false));

        this.planetImage.put("InfoWindow", new MasarSprite("img/InfoWindow/window.png", 240, 137, 1));
    }
}