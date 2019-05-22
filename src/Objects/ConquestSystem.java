package Objects;

import backend.MasarData;


public class ConquestSystem {

    private int ressourcesNeeded;
    private MasarSystem systemConquered;
    private int alliedProgression;
    private int ennemyProgression;
    private boolean in_conquest;

    private MasarData gameData;

    public MasarSystem getSystemConquered() {
        return systemConquered;
    }

    public int getAlliedProgression() {
        return alliedProgression;
    }

    public int getEnnemyProgression() {
        return ennemyProgression;
    }

    public boolean isIn_conquest() {
        return in_conquest;
    }

    public double getPercentAllied(){
        return (double) this.alliedProgression  * 100 / (double)this.ressourcesNeeded;
    }

    public double getPercentEnnemy(){
        return (double) this.ennemyProgression  * 100 / (double)this.ressourcesNeeded;
    }


    public ConquestSystem(MasarData gd, MasarSystem s){
        this.systemConquered = s;
        this.gameData = gd;
        this.ressourcesNeeded = s.getMaxRPS() * 4;
        this.alliedProgression = 0;
        this.ennemyProgression = 0;
        this.in_conquest = false;
    }

    public void addAlliedProgress(int totalRPM){
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
        if ( this.alliedProgression >= this.ressourcesNeeded ){
            this.in_conquest = false;
            this.systemConquered.changeClan(1);
            this.gameData.removeAllLinksOfDefeated(this.getSystemConquered());
            for ( SystemLink l : this.gameData.getLinkList() ){
                int transferredPopulace = 0;
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

    public void addEnnemyProgress(int totalRPM){
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

        if ( this.ennemyProgression >= this.ressourcesNeeded ){
            this.in_conquest = false;
            this.systemConquered.changeClan(2);
            this.gameData.removeAllLinksOfDefeated(this.getSystemConquered());
            for ( SystemLink l : this.gameData.getLinkList() ){
                int transferredPopulace = 0;
                if ( l.getSys2() == this.getSystemConquered() ){
                    transferredPopulace = l.getSys1().getPop()/4;
                    l.getSys1().subPopulace(transferredPopulace);
                    l.getSys2().addPopulace(transferredPopulace);
                }
            }
        }
    }
}
