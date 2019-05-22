package IA;

import Objects.MasarSystem;
import Objects.SystemLink;
import backend.LinkClickable;
import backend.MasarData;

import java.util.LinkedList;
import java.util.Random;

//Cette classe implémente les fonctions permettant au bot de fonctionner
public class Bot {
    private MasarData gameData;
    private LinkedList<SystemLink> possibleLink;

    public Bot(MasarData gameData) {
        this.gameData = gameData;
        possibleLink = new LinkedList<>();
    }

    //Update le bot et créé un lien si possible
    public void update(){
        possibleLink.clear();

        //Parcours de tous les couples de systèmes Ennemi-Autre et liste ceux où un lien est créable
        //Pour qu'un lien soit créable il faut que le système source ait au minimum 5 de ressources et qu'il ne soit pas à une distance trop importante du système cible
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

        //Si la liste de liens créables n'est pas vite, en choisit un au hasard et le créé
        if(possibleLink.size() > 0 && gameData.getResE() > 5) {
            Random rand = new Random();
            SystemLink newLink = possibleLink.get(rand.nextInt(possibleLink.size()));
            this.gameData.getLinkList().add(newLink);
            this.gameData.getCurrentRoom().addClickable(new LinkClickable(newLink, this.gameData));
        }
    }

    // Vérifie l'existance d'un lien afin de savoir si on l'ajoute ou non dans la liste de liens potentiels à créer dans la fonction précédente
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
