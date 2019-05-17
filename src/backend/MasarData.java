package backend;

import Objects.MasarSystem;
import Objects.SystemLink;
import backend.ClickManager;

import java.util.LinkedList;

public class MasarData{
    private LinkedList<MasarSystem> systemList;
    private LinkedList<SystemLink> linkList;

    private ClickManager clickManager;

    public LinkedList<MasarSystem> getSystemList(){return this.systemList;}
    public LinkedList<SystemLink> getLinkList(){return this.linkList;}

    public ClickManager getClickManager(){return this.clickManager;}
    public void setClickManager(ClickManager c){this.clickManager = c;}

    public MasarData(){
        this.systemList = new LinkedList<MasarSystem>();
        this.linkList = new LinkedList<SystemLink>();
    }
}