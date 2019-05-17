package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import UI.MenuButton;
import backend.ClickManager;

import java.util.LinkedList;

public class MasarData{
    private LinkedList<MasarSystem> systemList;
    private LinkedList<SystemLink> linkList;
    private LinkedList<MenuButton> buttonList;
    private int DisplayMode;

    private ClickManager clickManager;

    public LinkedList<MasarSystem> getSystemList(){return this.systemList;}
    public LinkedList<SystemLink> getLinkList(){return this.linkList;}
    public LinkedList<MenuButton> getButtonList() {return this.buttonList;}

    public ClickManager getClickManager(){return this.clickManager;}
    public void setClickManager(ClickManager c){this.clickManager = c;}

    public MasarData(){
        this.systemList = new LinkedList<MasarSystem>();
        this.linkList = new LinkedList<SystemLink>();
        this.buttonList = new LinkedList<MenuButton>();
        this.DisplayMode = 0;
    }

    public int getDisplayMode() {
        return this.DisplayMode;
    }

    public void setDisplayMode(int a) {
      this.DisplayMode = a;
    }
}