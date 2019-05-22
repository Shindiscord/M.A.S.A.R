package IA;

import Objects.MasarSystem;
import Objects.SystemLink;
import backend.LinkClickable;
import backend.MasarData;

import java.util.LinkedList;
import java.util.Random;

public class Bot {
    private MasarData gameData;
    private LinkedList<SystemLink> possibleLink;

    public Bot(MasarData gameData) {
        this.gameData = gameData;
        possibleLink = new LinkedList<>();
    }

    public void update(){
        possibleLink.clear();

        for(MasarSystem s: this.gameData.getSystemList()){
            if(s.getClan() == MasarSystem.ENNEMY){
                for(MasarSystem c: this.gameData.getSystemList()){
                    if(c != s){
                        if(s.getDistance(c) < MasarData.DIST_SYST + 5f){
                            SystemLink newLink = new SystemLink(s, c, gameData);
                            if(!linkExist(newLink))
                                possibleLink.add(newLink);
                        }
                    }
                }
            }
        }

        if(possibleLink.size() > 0 && gameData.getResE() > 5) {
            Random rand = new Random();
            SystemLink newLink = possibleLink.get(rand.nextInt(possibleLink.size()));
            this.gameData.getLinkList().add(newLink);
            this.gameData.getCurrentRoom().addClickable(new LinkClickable(newLink, this.gameData));
        }
    }

    private boolean linkExist(SystemLink newLink){
        for(SystemLink link: this.gameData.getLinkList()){
            if(link.equals(newLink)) {
                System.out.println("BOT : ce lien existe déjà");
                return true;
            }
        }
        return false;
    }
}
