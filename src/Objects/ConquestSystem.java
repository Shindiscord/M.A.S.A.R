package Objects;

import backend.MasarData;


class ConquestSystem {

    private int ressourcesNeeded;
    private MasarSystem systemConquered;
    private int alliedProgression;
    private int ennemyProgression;
    private boolean in_conquest;

    private MasarData gameData;

    private MasarSystem getSystemConquered() {
        return systemConquered;
    }

    int getAlliedProgression() {
        return alliedProgression;
    }

    int getEnnemyProgression() {
        return ennemyProgression;
    }

    boolean isIn_conquest() {
        return in_conquest;
    }

    double getPercentAllied(){
        return (double) this.alliedProgression  * 100 / (double)this.ressourcesNeeded;
    }

    double getPercentEnnemy(){
        return (double) this.ennemyProgression  * 100 / (double)this.ressourcesNeeded;
    }


    ConquestSystem(MasarData gd, MasarSystem s){
        this.systemConquered = s;
        this.gameData = gd;
        this.ressourcesNeeded = s.getMaxRPS() * 4;
        this.alliedProgression = 0;
        this.ennemyProgression = 0;
        this.in_conquest = false;
    }

    //avancement du progrès de conquête allié
    void addAlliedProgress(int totalRPM){
        this.in_conquest = true;
        if ( this.ennemyProgression == 0 )
            this.alliedProgression += totalRPM;
        else {
            this.ennemyProgression -= totalRPM;
            if ( this.ennemyProgression < 0 ){
                this.alliedProgression -= this.ennemyProgression;
                this.ennemyProgression = 0;
            }
        }
        //verification de victoire
        if ( this.alliedProgression >= this.ressourcesNeeded ){
            this.in_conquest = false;
            this.systemConquered.changeClan(1);
            this.gameData.removeAllLinksOfDefeated(this.getSystemConquered());
            //tranfert du quart de la population des systèmes victorieux au conqueri
            for ( SystemLink l : this.gameData.getLinkList() ){
                int transferredPopulace;
                if ( l.getSys2() == this.getSystemConquered() ){
                    transferredPopulace = l.getSys1().getPop()/4;
                    l.getSys1().subPopulace(transferredPopulace);
                    l.getSys2().addPopulace(transferredPopulace);
                }
            }
        }
        //double test_percent = (double) this.alliedProgression  * 100 / (double)this.ressourcesNeeded;
        //System.out.println("TotalRPM : " + totalRPM + " this.ressourcesNeeded : " + this.ressourcesNeeded);
        //System.out.println("Test percent ?? : " + test_percent);
        //System.out.println("Progress conquest allied : " + this.alliedProgression);
    }

    //avancement du progrès de conquête ennemi
    void addEnnemyProgress(int totalRPM){
        this.in_conquest = true;
        if ( this.alliedProgression == 0 )
            this.ennemyProgression += totalRPM;
        else {
            this.alliedProgression -= totalRPM;
            if ( this.alliedProgression < 0 ){
                this.ennemyProgression -= this.alliedProgression;
                this.alliedProgression = 0;
            }
        }
        //vérification de victoire
        if ( this.ennemyProgression >= this.ressourcesNeeded ){
            this.in_conquest = false;
            this.systemConquered.changeClan(2);
            this.gameData.removeAllLinksOfDefeated(this.getSystemConquered());
            //tranfert du quart de la population des systèmes victorieux au conqueri
            for ( SystemLink l : this.gameData.getLinkList() ){
                int transferredPopulace;
                if ( l.getSys2() == this.getSystemConquered() ){
                    transferredPopulace = l.getSys1().getPop()/4;
                    l.getSys1().subPopulace(transferredPopulace);
                    l.getSys2().addPopulace(transferredPopulace);
                }
            }
        }
    }
}
