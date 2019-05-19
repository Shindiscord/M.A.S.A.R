package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import backend.ClickManager;
import org.newdawn.slick.GameContainer;

import java.util.LinkedList;

public class MasarData{
    private LinkedList<MasarSystem> systemList;
    private LinkedList<SystemLink> linkList;
    private LinkedList<MasarRoom> roomList;

    private MasarRoom currentRoom;
    private GameContainer gc;

    public LinkedList<MasarSystem> getSystemList(){return this.systemList;}
    public LinkedList<SystemLink> getLinkList(){return this.linkList;}
    public LinkedList<MasarRoom> getRoomList(){return this.roomList;}
    public GameContainer getGameContainer(){return this.gc;}
    public MasarRoom getCurrentRoom(){return this.currentRoom;}
    public void setCurrentRoom(MasarRoom room){this.currentRoom = room;}

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
        this.currentRoom = null;
    }
}