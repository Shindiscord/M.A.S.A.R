package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import backend.ClickManager;
import org.newdawn.slick.GameContainer;
import UI.MasarSprite;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MasarData{
    private LinkedList<MasarSystem> systemList;
    private LinkedList<SystemLink> linkList;
    private LinkedList<MasarRoom> roomList;
    private Map<String, MasarSprite> systemsImages;
    private Map<String, MasarSprite> buttonsImages;
    private float Coordinates[][];

    private MasarRoom currentRoom;
    private GameContainer gc;

    private static int NB_SYSTEMS = 30;
    private static float DIST_SYST = 170;
    private static float DIST_JOINING_CONFLICT = 146;

    public LinkedList<MasarSystem> getSystemList(){return this.systemList;}
    public LinkedList<SystemLink> getLinkList(){return this.linkList;}
    public LinkedList<MasarRoom> getRoomList(){return this.roomList;}
    public GameContainer getGameContainer(){return this.gc;}
    public MasarRoom getCurrentRoom(){return this.currentRoom;}
    public void setCurrentRoom(MasarRoom room){this.currentRoom = room;}
    public Map<String, MasarSprite> getSystemsImages() {return this.systemsImages;}
    public Map<String, MasarSprite> getButtonsImages() {return this.buttonsImages;}
    public float[][] getCoordinates(){ return this.Coordinates;}

    public void setRoom(int index){
        if(index < roomList.size() && roomList.get(index) != null){
            this.roomList.get(index).setAsRoom(this);
        }else{
            System.out.println("Error, Room number "+ index+ " Can't be found");
        }
    }

    public void removeLink(SystemLink link){
        linkList.remove(link);
        for(Clickable l:this.currentRoom.getClickManager().getRegisteredClickables()){
            if(l instanceof LinkClickable && ((LinkClickable) l).getAttachedLink() == link)
                this.currentRoom.getClickManager().getRegisteredClickables().remove(l);
        }
    }

    public MasarData(GameContainer gc){
        this.gc = gc;
        this.systemList = new LinkedList<MasarSystem>();
        this.linkList = new LinkedList<SystemLink>();
        this.roomList = new LinkedList<MasarRoom>();
        this.systemsImages = new HashMap<String, MasarSprite>();
        this.buttonsImages = new HashMap<>();
        this.Coordinates = new float[NB_SYSTEMS][2];
        this.currentRoom = null;
    }

    public void loadCoordinates(){
        int itab = 0; //nombres de systemes sur une ligne = 6
        int ytab = 0; //nombres de lignes = 5
        for( int i=0 ; i<NB_SYSTEMS ; i++ ){
            if ( ytab%2 == 0 )
                this.Coordinates[i][0] = 130 + itab*DIST_SYST;
            else
                this.Coordinates[i][0] = 130 + 85+ itab*DIST_SYST;

            this.Coordinates[i][1] = 32 + 68 + ytab*DIST_JOINING_CONFLICT;
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

        this.systemsImages.put("en_3planet_var3", new MasarSprite("res/img/System/Enemy/en_starsys3_130_105px_3pl.png", 130,105,5));
    }

}